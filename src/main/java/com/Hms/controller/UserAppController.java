package com.Hms.controller;

import com.Hms.entity.UserApp;
import com.Hms.payload.LoginDto;
import com.Hms.payload.TokenDto;
import com.Hms.payload.UserAppDto;
import com.Hms.service.UserAppService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserAppController {
    private UserAppService userAppService;

    @PostMapping("/usersignup")
    public ResponseEntity<?> createUser(
            @RequestBody UserAppDto userAppDto
    ){
        return userAppService.createUser(userAppDto);
    }

    @PostMapping("/userlogin")
    public ResponseEntity<?> login(
            @RequestBody LoginDto loginDto
    ){
        String token = userAppService.verifyLogin(loginDto);
        if(token!=null){
            TokenDto tokenDto = new TokenDto();
            tokenDto.setToken(token);
            tokenDto.setType("JWT");
            return new ResponseEntity<>(tokenDto,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Invalid Username/Password",HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/ownersignup")
    public ResponseEntity<?> ownerSignup(
            @RequestBody UserAppDto userAppDto
    ){
        return userAppService.ownerSignup(userAppDto);
    }

    @PostMapping("/ownerlogin")
    public ResponseEntity<?> ownerLogin(
            @RequestBody LoginDto loginDto
    ){
        String token = userAppService.ownerLogin(loginDto);
        if(token!=null){
            TokenDto tokenDto = new TokenDto();
            tokenDto.setToken(token);
            tokenDto.setType("JWT");
            return new ResponseEntity<>(tokenDto,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Invalid Username/Password",HttpStatus.FORBIDDEN);
        }
    }
}


