package project.duan.qlybancafe.controller;

import java.text.ParseException;

import org.apache.kafka.shaded.com.google.protobuf.Api;
import org.springframework.web.bind.annotation.*;

import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import project.duan.qlybancafe.dto.ApiResponse;
import project.duan.qlybancafe.dto.request.*;
import project.duan.qlybancafe.dto.response.AuthenticationResponse;
import project.duan.qlybancafe.dto.response.IntrospectResponse;
import project.duan.qlybancafe.service.auth.IAuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationController {

    IAuthenticationService iAuthenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(iAuthenticationService.authenticate(request))
                .message("Authentication Successful")
                .build();
    }

    @PostMapping("/reset-password")
    ApiResponse<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        iAuthenticationService.resetPassword(request);
        return ApiResponse.<String>builder()
                .message("Reset Password Successful")
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) {
        return ApiResponse.<IntrospectResponse>builder()
                .result(iAuthenticationService.introspect(request))
                .message("Introspection Successful")
                .build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> refreshToken(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(iAuthenticationService.refreshToken(request))
                .message("Refresh Successful")
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        iAuthenticationService.logout(request);
        return ApiResponse.<Void>builder().message("Logout successful").build();
    }
}
