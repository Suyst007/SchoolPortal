package com.example.school.school.Controller;

import com.example.school.school.Entity.User;
import com.example.school.school.Payload.LoginDto;
import com.example.school.school.Payload.RegistrationDto;
import com.example.school.school.Service.AuthService;
import com.example.school.school.Service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/school")
public class Controller {

private JwtService jwtService;

    private AuthService authService;

    public Controller(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;

        this.authService = authService;
    }


    @PostMapping
    public ResponseEntity<?> createSchool(@RequestBody RegistrationDto registrationDto) {
        authService.createStudent(registrationDto);
 return  new ResponseEntity<>("Student database created", HttpStatus.ACCEPTED);


    }
    @GetMapping
    public String message(){
        return "School Application";
    }


    @PostMapping("/login")
    public ResponseEntity<String>  login(@RequestBody LoginDto loginDto){
        authService.authentication(loginDto);
        return new ResponseEntity<>(jwtService.genrateToken(loginDto.getName()),HttpStatus.OK);
    }



}
