package com.mugi.uac.entity.dao;

import com.mugi.uac.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class JwtAuthenticationResponse {

    private String accessToken;
    private String tokenType = "Bearer";
    User userLog;
    boolean isAuthenticated = false;

    public JwtAuthenticationResponse(String accessToken, User userLog, boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
        this.accessToken = accessToken;
        this.userLog = userLog;

    }

}
