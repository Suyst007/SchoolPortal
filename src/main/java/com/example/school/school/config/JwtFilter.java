package com.example.school.school.config;

import com.example.school.school.Entity.User;
import com.example.school.school.Repository.UserRepository;
import com.example.school.school.Service.JwtService;
import jakarta.servlet.FilterChain;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {
private JwtService jwtService;
private UserRepository userRepository;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }
@Override
    public void doFilterInternal(HttpServletRequest request
    , HttpServletResponse response,FilterChain filterChain) throws ServletException, IOException {


        String token = request.getHeader("Authorization");
        if(token !=null && token.startsWith("Bearer")){
            String JwtToken = token.substring(7, token.length());
            System.out.println(JwtToken);
            String name = jwtService.verifytoken(JwtToken);
            Optional<User> OpName = userRepository.findByName(name);
            User user=OpName.get();
            UsernamePasswordAuthenticationToken passwordAuthenticationToken=new UsernamePasswordAuthenticationToken(null,null,null);


        }

filterChain.doFilter(request,response);
    }


}
