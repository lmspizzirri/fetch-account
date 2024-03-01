package org.triunfo.fetchaccount.controller;

// ... outros imports (omitidos) ...

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.triunfo.fetchaccount.DTO.AuthDto;
import org.triunfo.fetchaccount.DTO.TokenDto;
import org.triunfo.fetchaccount.service.TokenService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody AuthDto authDto) {
        System.out.println(authDto);
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(authDto.username(), authDto.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken(auth.getName());
        return new TokenDto(token);
    }
}