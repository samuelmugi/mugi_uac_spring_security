package com.mugi.uac.controller;

import com.mugi.uac.entity.dao.JwtAuthenticationResponse;
import com.mugi.uac.entity.dao.LoginRequest;
import com.mugi.uac.entity.dao.RoleName;
import com.mugi.uac.entity.dao.SignUpRequest;
import com.mugi.uac.entity.User;
import com.mugi.uac.repos.RoleRepo;
import com.mugi.uac.repos.UserRepository;
import com.mugi.uac.security.JwtTokenProvider;
import com.mugi.uac.utils.RestResponse;
import com.mugi.uac.utils.RestResponseObject;
import io.swagger.annotations.ApiOperation;
import java.util.Calendar;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Users")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepo roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, null, true));
    }

    @PostMapping("/register")
    public RestResponse registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new RestResponse(new RestResponseObject(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
        }

        // Creating user's account String firstName, String lastName, String email, String password
        User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getEmail(), RoleName.ROLE_USER.toString());

        user.setPassword(passwordEncoder.encode(signUpRequest.getRawPassword()));
        user.setCreateDate(Calendar.getInstance().getTime());

        User result = userRepository.save(user);

        return new RestResponse(new RestResponseObject(true, "User registered successfully"), HttpStatus.OK);
    }

    @PostMapping("/update")
    public RestResponse registerUser(@Valid @RequestBody User updatedUser) {

        // Creating user's account String firstName, String lastName, String email, String password
        Optional<User> foundUser = userRepository.findByUserId(updatedUser.getUserId());
        if (foundUser.isPresent()) {
            User updated = foundUser.get();
            updated.setFirstName(updatedUser.getFirstName());
            updated.setLastName(updatedUser.getLastName());
            updated.setEmail(updatedUser.getEmail());
            updated.setPassword(passwordEncoder.encode(updatedUser.getRawPassword()));
//        user.setCreateDate(Calendar.getInstance().getTime());

            User result = userRepository.save(updated);

            return new RestResponse(new RestResponseObject(true, "User registered successfully"), HttpStatus.OK);
        } else {
            return new RestResponse(new RestResponseObject(false, "User not Found!"), HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping(value = "/listAll", produces = "application/json")
    @ApiOperation(value = "Get a  list of all   Assignment", notes = "The json list of Assignment")
    @Transactional
    public RestResponse listAssignment(Pageable pageable) {
        RestResponseObject resp = new RestResponseObject();
        resp.setMessage("Error listing Assignment  ");
        resp.setRequestStatus(false);
        resp.setPayload(null);

        try {
            Pageable sortedAssignmentId = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("userId").descending());

            resp.setPayload(userRepository.findAll(sortedAssignmentId));
            resp.setRequestStatus(true);
            resp.setMessage("Success");
            return new RestResponse(resp, HttpStatus.OK);
        } catch (Exception er) {
            System.err.println(" ERROR:- " + er.getLocalizedMessage());
            resp.setMessage("Error fetching Assignment  ");
            resp.setRequestStatus(false);
            return new RestResponse(resp, HttpStatus.BAD_REQUEST);
        }
    }

}
