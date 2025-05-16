package com.daw.cardmarket.controller;

import com.daw.cardmarket.model.Login;
import com.daw.cardmarket.model.Token;
import com.daw.cardmarket.service.ActorService;
import com.daw.cardmarket.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class ActorController {

    @Autowired
    private ActorService actorService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody Login login) {
        String username = login.getUsername();
        String password = login.getPassword();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Token token = jwtUtils.generateToken(authentication);

        return ResponseEntity.ok(token);
    }

    @GetMapping("/actor/{username}")
    public ResponseEntity<?> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(actorService.findByUsername(username));
    }

}
