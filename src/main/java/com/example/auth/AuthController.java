package com.example.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {

    @Autowired
    JWTGen jwtGen;

    @GetMapping("/test1")
    public String test1(){
        return jwtGen.jwtGenerate();
    }

    @GetMapping("/test2")
    public String test2(HttpServletRequest request){

        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            jwtGen.validateJwtToken( headerAuth.substring(7));
        }

        return "hello im X years old";
    }
}
