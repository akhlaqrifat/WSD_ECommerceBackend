package com.wsd.eCommerceBackend.authentication.services.impl;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.wsd.eCommerceBackend.authentication.models.dtos.requests.LoginRequest;
import com.wsd.eCommerceBackend.authentication.models.dtos.requests.RegistrationRequest;
import com.wsd.eCommerceBackend.authentication.models.dtos.requests.TokenRefreshRequest;
import com.wsd.eCommerceBackend.authentication.models.dtos.responses.LoginResponse;
import com.wsd.eCommerceBackend.authentication.models.dtos.responses.LogoutResponse;
import com.wsd.eCommerceBackend.authentication.models.dtos.responses.RegistrationResponse;
import com.wsd.eCommerceBackend.authentication.models.dtos.responses.TokenRefreshResponse;
import com.wsd.eCommerceBackend.authentication.models.entities.Role;
import com.wsd.eCommerceBackend.authentication.models.entities.User;
import com.wsd.eCommerceBackend.authentication.repositories.RoleRepository;
import com.wsd.eCommerceBackend.authentication.repositories.UserRepository;
import com.wsd.eCommerceBackend.authentication.security.CurrentUser;
import com.wsd.eCommerceBackend.authentication.security.JWTService;
import com.wsd.eCommerceBackend.authentication.services.UserService;
import com.wsd.eCommerceBackend.exceptions.CustomException;
import com.wsd.eCommerceBackend.exceptions.DatabaseException;
import com.wsd.eCommerceBackend.utils.CommonHelper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @Override
    @Transactional
    public RegistrationResponse registration(RegistrationRequest registrationRequest) {
        if (!isAlphabeticString(registrationRequest.getFullName())) {
            throw new CustomException("Only alphabets and space is allowed for full name!");
        }

        if (!isValidPassword(registrationRequest.getPassword())) {
            throw new CustomException("Password is should be of length at least 8 and contain at least one letter, one digit and one special character!");
        }

        if (!validatePhoneNumber(CommonHelper.formatMSISDN(registrationRequest.getPhone()))) {
            throw new CustomException("Error: Phone is not valid!");
        }

        String username = makeUserName(registrationRequest.getFullName(), registrationRequest.getPhone());

        if (!isValidEmail(registrationRequest.getEmail())) {
            throw new CustomException("Email id is not valid!");
        }


        if (userRepository.existsByPhone(registrationRequest.getPhone())) {
            throw new CustomException("Error: Phone is already taken!");
        }

        // Create new user's account
        User user = new User();
        user.setUserName(username);
        user.setEmail(registrationRequest.getEmail());
        user.setPhone(CommonHelper.formatMSISDN(registrationRequest.getPhone()));
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        String strRoles = registrationRequest.getRole();
        Role userRole;
        if ("admin".equals(strRoles)) {
            userRole = roleRepository.findByNameIgnoreCase("ROLE_ADMIN").orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        } else if ("super_admin".equals(strRoles)) {
            userRole = roleRepository.findByNameIgnoreCase("ROLE_SUPER_ADMIN").orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        } else {
            userRole = roleRepository.findByNameIgnoreCase("ROLE_USER").orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        }

        user.setRole(userRole);
        user.setFullName(registrationRequest.getFullName());

        //create User
        try {
            user = userRepository.save(user);
        } catch (DatabaseException databaseException) {
            throw new CustomException("Couldn't save new User!");
        }

        return new RegistrationResponse(user.getId(), user.getUsername(), user.getEmail(), user.getFullName(), registrationRequest.getPhone());

    }

    @Override
    public LogoutResponse logout() {
        UserDetails userDetails = CurrentUser.get();
        try {
            Long userId = userRepository.findByUserName(userDetails.getUsername()).map(User::getId).orElseThrow(() -> new RuntimeException("Error: user is not found."));
            jwtService.deleteRefreshToken(userId);
            return new LogoutResponse("Successfully logged out!");
        } catch (DatabaseException de) {
            throw new DatabaseException(de);
        }
    }

    @Override
    public User getCurrentUser() {
        return  CurrentUser.getUser();
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {

        if (isValidEmail(loginRequest.getPhoneOrEmail()) || validatePhoneNumber(loginRequest.getPhoneOrEmail())){

            Optional<User> userOptional = userRepository.findByPhoneOrEmail(CommonHelper.formatMSISDN(loginRequest.getPhoneOrEmail()));
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                try {
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), loginRequest.getPassword()));
                } catch (Exception e) {
                    throw new CustomException("Credentials not valid!");
                }
                String accessToken = jwtService.generateToken(user);
                String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
                jwtService.saveRefreshToken(user.getId(), refreshToken);
                return new LoginResponse(accessToken, refreshToken, user.getId(), user.getUsername(), user.getEmail(), user.getRole().getName(), user.getFullName(), false);
            } else {
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setNewUser(true);
                return loginResponse;
            }
        }else {
            throw new CustomException("Invalid phone or email!");
        }

    }

    @Override
    public TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefreshRequest) {
        try {
            if (jwtService.validateRefreshToken(tokenRefreshRequest.getUserId(), tokenRefreshRequest.getRefreshToken())
                    ) {

               Optional<User> optional = userRepository.findById(tokenRefreshRequest.getUserId());
               if (optional.isPresent()) {
                   String accessToken = jwtService.generateToken(optional.get());
                   return new TokenRefreshResponse(accessToken);
               }else{
                   throw new CustomException("User not found with this id!");
               }
            } else {
                return new TokenRefreshResponse(null);
            }
        } catch (DatabaseException de) {
            throw new DatabaseException(de);
        }
    }

    public boolean isAlphabeticString(String str) {
        return str.matches("[a-zA-Z ]+");
    }

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$#!%*?&])[A-Za-z\\d@$#!%*?&]{8,}$");

    private boolean isValidPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    public boolean validatePhoneNumber(String phone) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

        try {
            Phonenumber.PhoneNumber canadianNumber = phoneNumberUtil.parse(phone, "BD");
            if (phoneNumberUtil.isValidNumber(canadianNumber)) {
                System.out.println("Valid Bangladeshi phone number");
                return true;
            } else {
                System.out.println("Invalid Bangladeshi phone number");
                return false;
            }
        } catch (NumberParseException e) {
            System.err.println("NumberParseException: " + e);
            return false;
        }
    }

    public boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        if (validator.isValid(email)) {
            if (userRepository.existsByEmail(email)) {
                throw new CustomException("Error: Email is already taken!");
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private String makeUserName(String fullName, String phone) {
        //create a unique username by removing space from fullName and adding last 4 digits of phone
        String convertedFullName = fullName.replace(" ", "_");
        String lastFourDigits = phone.substring(phone.length() - 4);
        String username = convertedFullName + "_" + lastFourDigits + "1";
        int i = 0;
        while (userRepository.existsByUserName(username)) {
            username = username.substring(0, username.length() - 1) + (i + 1);
            i++;
        }

        return username;
    }

}
