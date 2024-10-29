package com.Hms.service;

import com.Hms.entity.UserApp;
import com.Hms.payload.LoginDto;
import com.Hms.payload.UserAppDto;
import com.Hms.repository.UserAppRepository;
import com.Hms.utils.JwtService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserAppService {
    private UserAppRepository userAppRepository;
    private ModelMapper modelMapper;
    private JwtService jwtService;

    public ResponseEntity<?> createUser(UserAppDto userAppDto) {
        Optional<UserApp> username = userAppRepository.findByUsername(userAppDto.getUsername());

        if (username.isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<UserApp> email = userAppRepository.findByEmail(userAppDto.getEmail());

        if (email.isPresent()) {
            return new ResponseEntity<>("Email already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        UserApp user = mapToEntity(userAppDto);

        user.setPassword(BCrypt.hashpw(userAppDto.getPassword(), BCrypt.gensalt(4)));
        user.setRole("ROLE_USER");
        UserApp save = userAppRepository.save(user);

        UserAppDto dto = mapToDto(save);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    UserApp mapToEntity(UserAppDto userAppDto) {
        UserApp user = modelMapper.map(userAppDto, UserApp.class);
        return user;
    }

    UserAppDto mapToDto(UserApp userApp) {
        UserAppDto dto = modelMapper.map(userApp, UserAppDto.class);
        return dto;
    }

    public String verifyLogin(LoginDto loginDto) {
        Optional<UserApp> username = userAppRepository.findByUsername(loginDto.getUsername());
        if (username.isPresent()) {
            UserApp user = username.get();
            if (BCrypt.checkpw(loginDto.getPassword(), user.getPassword())){
                //generate token
                return jwtService.generateToken(user.getUsername());
            }else {
                return null;
            }
        } else {
            return null;
        }

    }

    public ResponseEntity<?> ownerSignup(UserAppDto userAppDto) {
        Optional<UserApp> username = userAppRepository.findByUsername(userAppDto.getUsername());

        if (username.isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Optional<UserApp> email = userAppRepository.findByEmail(userAppDto.getEmail());

        if (email.isPresent()) {
            return new ResponseEntity<>("Email already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        UserApp user = mapToEntity(userAppDto);

        user.setPassword(BCrypt.hashpw(userAppDto.getPassword(), BCrypt.gensalt(4)));
        user.setRole("ROLE_OWNER");
        UserApp save = userAppRepository.save(user);

        UserAppDto dto = mapToDto(save);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    public String ownerLogin(LoginDto loginDto) {
        Optional<UserApp> username = userAppRepository.findByUsername(loginDto.getUsername());
        if (username.isPresent()) {
            UserApp user = username.get();
            if (BCrypt.checkpw(loginDto.getPassword(), user.getPassword())){
                //generate token
                return jwtService.generateToken(user.getUsername());
            }else {
                return null;
            }
        } else {
            return null;
        }

    }

}
