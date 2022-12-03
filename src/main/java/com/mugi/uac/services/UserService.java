/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mugi.uac.services;

import com.mugi.uac.entity.dao.LoginRequest;
import com.mugi.uac.repos.UserRepository;
import com.mugi.uac.utils.RestResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author @SaQlever
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public LoginRequest authenticateUser(String username, String password, String ipAddress) {

        RestResponseObject resp = new RestResponseObject();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);

        try {
            loginRequest.setSuccess(true);
            loginRequest.setMessage("Logged in successfully");
//            if (!auth.trim().isEmpty()) {
//                loginRequest.setSuccess(true);
//                loginRequest.setMessage("Logged in successfully");
//
//                System.out.println(ldapUser.toString());
//                User userLog = new User();
//                userLog.setAccountName(ldapUser.getAccountname());
//                userLog.setFirstName(ldapUser.getFirstname());
//                userLog.setLastName(ldapUser.getLastname());
//                userLog.setEmail(ldapUser.getEmail());
//                userLog.setLoggedDate(Calendar.getInstance().getTime());
//                userLog.setDeviceIp(ipAddress);
//
////                TODO ADD DEPARTMENT RETRIEVED FROM LDAP
//                userLog.setDepartment("to retrieve from ldap");
//                User ul = userRepository.save(userLog);
//                loginRequest.setUser(ul);
//            } else {
//                loginRequest.setSuccess(false);
//                loginRequest.setMessage("Login Unsuccessful");
//            }
            log.info("User " + loginRequest.getUsername() + " tried loggin from ipAddress=" + ipAddress + "");
            return loginRequest;
        } catch (Exception ex) {
            resp.setMessage("Error assigning user role");
            resp.setRequestStatus(false);
            log.error("fetchLdapDetailsError \n." + ex);
            loginRequest.setSuccess(false);
            loginRequest.setMessage("Login Unsuccessful");
            return loginRequest;
        }
    }

}
