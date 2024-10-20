package com.Hms.controller;

import com.Hms.entity.UserApp;
import com.Hms.payload.LoginDto;
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

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(
            @RequestBody UserAppDto userAppDto
    ){
        return userAppService.createUser(userAppDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginDto loginDto
    ){
        String token = userAppService.verifyLogin(loginDto);
        if(token!=null){
            return new ResponseEntity<>(token,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Invalid Username/Password",HttpStatus.FORBIDDEN);
        }
    }
}


