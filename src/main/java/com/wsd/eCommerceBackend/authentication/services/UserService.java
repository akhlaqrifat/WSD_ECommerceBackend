package com.wsd.eCommerceBackend.authentication.services;

import com.wsd.eCommerceBackend.authentication.models.dtos.requests.LoginRequest;
import com.wsd.eCommerceBackend.authentication.models.dtos.requests.RegistrationRequest;
import com.wsd.eCommerceBackend.authentication.models.dtos.requests.TokenRefreshRequest;
import com.wsd.eCommerceBackend.authentication.models.dtos.responses.LoginResponse;
import com.wsd.eCommerceBackend.authentication.models.dtos.responses.LogoutResponse;
import com.wsd.eCommerceBackend.authentication.models.dtos.responses.RegistrationResponse;
import com.wsd.eCommerceBackend.authentication.models.dtos.responses.TokenRefreshResponse;
import com.wsd.eCommerceBackend.authentication.models.entities.User;

public interface UserService {

    RegistrationResponse registration(RegistrationRequest registrationRequest);

    LoginResponse loginUser(LoginRequest loginRequest);

    TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefreshRequest);

    LogoutResponse logout();

    User getCurrentUser();

}
