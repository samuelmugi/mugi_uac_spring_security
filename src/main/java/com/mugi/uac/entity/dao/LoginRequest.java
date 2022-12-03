package com.mugi.uac.entity.dao;

import javax.validation.constraints.NotBlank;

import com.mugi.uac.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author @SaQlever
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor

public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
    private boolean success;
    private String message;
    private boolean procurement;
    User user;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
