package com.hospitalmanagement.Controller;

import com.hospitalmanagement.Entity.Roles;
import com.hospitalmanagement.Payload.JWTAuthResponse;
import com.hospitalmanagement.Payload.LoginDTO;
import com.hospitalmanagement.Entity.Staff;
import com.hospitalmanagement.Payload.SignUpDTO;
import com.hospitalmanagement.Repository.RoleRepository;
import com.hospitalmanagement.Repository.StaffRepository;
import com.hospitalmanagement.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.Role;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDTO loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(authentication);
        return  ResponseEntity.ok(new JWTAuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDTO signUpDto){

        // add check for username exists in a DB
        if(staffRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(staffRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        Staff staff = new Staff();
        staff.setName(signUpDto.getName());
        staff.setUsername(signUpDto.getUsername());
        staff.setEmail(signUpDto.getEmail());
        staff.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Roles roles = roleRepository.findByName("ROLE_STAFF").get();
        staff.setRoles(Collections.singleton(roles));


        staffRepository.save(staff);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
}


