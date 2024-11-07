package project.duan.qlybancafe.service.auth;

import java.text.ParseException;

import com.nimbusds.jose.JOSEException;

import project.duan.qlybancafe.dto.request.*;
import project.duan.qlybancafe.dto.response.AuthenticationResponse;
import project.duan.qlybancafe.dto.response.IntrospectResponse;

public interface IAuthenticationService {
    IntrospectResponse introspect(IntrospectRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void logout(LogoutRequest request) throws ParseException, JOSEException;

    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;

    String resetPassword(ResetPasswordRequest request);
}
