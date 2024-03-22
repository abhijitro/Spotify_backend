package com.cts.spotify.auth.controller;


import com.cts.spotify.auth.dto.JwtToken;
import com.cts.spotify.auth.exception.CustomExceptionHandler;
import com.cts.spotify.auth.exception.CustomUnAuthorizedException;
import com.cts.spotify.auth.filter.JwtUtil;
import com.cts.spotify.auth.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/v1.0/auth")
@RestController
@CrossOrigin(origins = "*")
@Slf4j
public class AuthenticationController
{

    @Value("${secret.key}")
    String secret;


    @Autowired
    private  UserService userService;

    @Autowired
    private  JwtUtil jwtUtil;

    //@Autowired
    //public AuthenticationController(UserService userService, jwtutil jwtutil) {
     //   this.userService = userService;
     //   this.jwtutil = jwtutil;
   // }

    @Operation(summary = "Generate Token")
    public String generateToken(String username, String password) throws ServletException
    {
        String jwtToken;

        if(username==null || password == null)
        {
            throw new ServletException("Please enter valid username and password");
        }

        boolean flag= userService.loginUser(username, password);
        String role=userService.getRoleByUserAndPass(username,password);
        log.info(role+"--from dbb-- inside token"+username);

        if(!flag)
        {
            throw new ServletException("Invalid credentials");

        }
        else
        {
            log.info(role+"--last---");

            jwtToken=Jwts.builder().setSubject(username).claim("role",role)
                    .setIssuedAt(new Date(System.currentTimeMillis())).
                    setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                    .signWith(SignatureAlgorithm.HS256,secret).compact();

        }

        log.info(role+"--last---");
        log.info(jwtToken+"------token");
        return jwtToken;
    }

    @Operation(summary = "Generate Token")
    @PostMapping("/login")
    public ResponseEntity<JwtToken> performLogin(@RequestParam String username, @RequestParam String password)
    {
        JwtToken authAccessToken = new JwtToken();
        log.info(username+"----");
        boolean check=userService.loginUser(username,password);
        log.info(check+"");
        if(check){
            String role=userService.getRoleByUserAndPass(username, password);
            log.info(role);
            try
            {
                log.info(generateToken(username, password));
                String jwtToken = generateToken(username, password);
                log.info(password);
                log.info(jwtToken+" inside login");
                if(role.equalsIgnoreCase("admin"))
                {

                    authAccessToken.setMessage("Admin successfully logged in");
                    log.info(jwtToken);
                    authAccessToken.setJwtToken(jwtToken);
                    authAccessToken.setRole(role);
                    authAccessToken.setUsername(username);
                    return new ResponseEntity<>(authAccessToken, HttpStatus.CREATED);

                }
                else if(role.equalsIgnoreCase("User"))
                {
                    authAccessToken.setMessage("User successfully logged in");
                    authAccessToken.setJwtToken(jwtToken);
                    authAccessToken.setRole(role);
                    authAccessToken.setUsername(username);
                    return new ResponseEntity<>(authAccessToken, HttpStatus.CREATED);
                }

            }

            catch( ServletException e)
            {
                log.info(e+"exception");
                authAccessToken.setMessage("Failed to logged in");
                authAccessToken.setJwtToken(null);
                authAccessToken.setRole(null);
            }

        }
        throw new CustomUnAuthorizedException("UnAuthorized User");

    }

    @Operation(summary = "Validate User")
    @PostMapping("/validate")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Object> validateToken(@RequestHeader("Authorization") String token) {
        log.info(token+"---------");

        if (jwtUtil.validateJwtToken(token)) {
            log.info("-------validate-------");
            Map<String, String> userInfo = new HashMap<>();
            String authToken = token.substring(7);
            String username = jwtUtil.getUserNameFromJwtToken(authToken);
            String role = jwtUtil.getRoleFromToken(authToken);
            userInfo.put(username, role);
            log.info(userInfo.toString());
            return ResponseEntity.status(HttpStatus.OK).body(userInfo);
        } else {
            log.info("-------***********-------");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
}















