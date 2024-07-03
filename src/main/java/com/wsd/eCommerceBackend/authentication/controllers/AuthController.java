package com.wsd.eCommerceBackend.authentication.controllers;

import com.wsd.eCommerceBackend.authentication.models.dtos.requests.LoginRequest;
import com.wsd.eCommerceBackend.authentication.models.dtos.requests.RegistrationRequest;
import com.wsd.eCommerceBackend.authentication.models.dtos.requests.TokenRefreshRequest;
import com.wsd.eCommerceBackend.authentication.models.dtos.responses.LoginResponse;
import com.wsd.eCommerceBackend.authentication.models.dtos.responses.LogoutResponse;
import com.wsd.eCommerceBackend.authentication.models.dtos.responses.RegistrationResponse;
import com.wsd.eCommerceBackend.authentication.models.dtos.responses.TokenRefreshResponse;
import com.wsd.eCommerceBackend.authentication.models.entities.User;
import com.wsd.eCommerceBackend.authentication.services.UserService;
import com.wsd.eCommerceBackend.constants.AppRoutes;
import com.wsd.eCommerceBackend.constants.IConstants;
import com.wsd.eCommerceBackend.models.commons.ResponseModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(AppRoutes.AuthController.ROOT_URL)
@RequiredArgsConstructor
public class AuthController implements IConstants {

    private final UserService userService;

    @PostMapping(AppRoutes.AuthController.SIGN_IN)
    public ResponseEntity<ResponseModel<LoginResponse>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest)  {
        LoginResponse loginResponse = userService.loginUser(loginRequest);
        String message = "Login successful!";
        if (loginResponse.isNewUser())
            message = "New user please register!";
        ResponseEntity<ResponseModel<LoginResponse>> response = ResponseEntity.ok(convertToJSON(loginResponse));
        Objects.requireNonNull(response.getBody()).setMessage(message);
        return response;
    }

    @PostMapping( value = AppRoutes.AuthController.SIGN_UP, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseModel<RegistrationResponse>> registerUser(@ModelAttribute RegistrationRequest signUpRequest) {
        log.info("Registering user!");
        ResponseEntity<ResponseModel<RegistrationResponse>> response = ResponseEntity.ok(convertToJSON(userService.registration(signUpRequest)));
        Objects.requireNonNull(response.getBody()).setMessage("Registration successful, Please login now!");
        return response;
    }

    @PostMapping( AppRoutes.AuthController.REFRESH_TOKEN)
    public ResponseEntity<ResponseModel<TokenRefreshResponse>> refreshToken(@RequestBody TokenRefreshRequest tokenRefreshRequest) {
        log.info("Refreshing token user!");
        ResponseEntity<ResponseModel<TokenRefreshResponse>> response = ResponseEntity.ok(convertToJSON(userService.refreshToken(tokenRefreshRequest)));
        Objects.requireNonNull(response.getBody()).setMessage("Refreshing token successful!");
        return response;
    }

    @DeleteMapping( AppRoutes.AuthController.LOGOUT)
    public ResponseEntity<ResponseModel<LogoutResponse>> logout() {
        log.info("Logging out user!");
        ResponseEntity<ResponseModel<LogoutResponse>> response = ResponseEntity.ok(convertToJSON(userService.logout()));
        Objects.requireNonNull(response.getBody()).setMessage("Logout successful!");
        return response;
    }

    @GetMapping(AppRoutes.AuthController.GET_CURRENT_USER)
    public ResponseEntity<ResponseModel<User>> registerUser() {
        log.info("Getting user!");
        ResponseEntity<ResponseModel<User>> response = ResponseEntity.ok(convertToJSON(userService.getCurrentUser()));
        Objects.requireNonNull(response.getBody()).setMessage("Ok now!");
        return response;
    }
}
