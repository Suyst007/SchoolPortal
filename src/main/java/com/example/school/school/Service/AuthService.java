package com.example.school.school.Service;

import com.auth0.jwt.JWT;
import com.example.school.school.Entity.User;
import com.example.school.school.Payload.LoginDto;
import com.example.school.school.Payload.RegistrationDto;
import com.example.school.school.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private JwtService jwtService;

    public AuthService(UserRepository userRepository, ModelMapper modelMapper, JwtService jwtService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }


    public ResponseEntity<?> createStudent(RegistrationDto registrationDto) {
        Optional<User> OpName = userRepository.findByName(registrationDto.getName());
        if (OpName.isPresent()) {
            return new ResponseEntity<>("Name already exists in database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> OpEmail = userRepository.findByEmail(registrationDto.getEmail());
        if (OpEmail.isPresent()) {
            return new ResponseEntity<>("Email already present in database", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> OpMobile = userRepository.findByMobile(registrationDto.getMobile());
        if (OpMobile.isPresent()) {
            return new ResponseEntity<>("Mobile already present in the databse", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        User user = convertToEntity(registrationDto);

        String hashpw = BCrypt.hashpw(registrationDto.getPassword(), BCrypt.gensalt(6));
        user.setPassword(hashpw);
        userRepository.save(user);


        return new ResponseEntity<>("User succesfully created", HttpStatus.OK);

    }

    public RegistrationDto convertToDto(User user) {
        RegistrationDto map = modelMapper.map(user, RegistrationDto.class);
        return map;


    }

    public User convertToEntity(RegistrationDto registrationDto) {
        User map = modelMapper.map(registrationDto, User.class);
        return map;
    }


    public String authentication(LoginDto loginDto){
        Optional<User> OpName = userRepository.findByName(loginDto.getName());
        if (OpName.isPresent()) {
            User user = OpName.get();
            boolean checkpw = BCrypt.checkpw(loginDto.getPassword(), user.getPassword());
            if(checkpw){
              return jwtService.genrateToken(loginDto.getName());


            }

        }
        return null;
    }











public void gitMessage(){
    System.out.print("hi");
}

}


















