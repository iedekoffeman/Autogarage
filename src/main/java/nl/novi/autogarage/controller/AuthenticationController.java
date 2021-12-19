package nl.novi.autogarage.controller;

import nl.novi.autogarage.dto.AuthenticationRequestDto;
import nl.novi.autogarage.dto.AuthenticationResponseDto;
import nl.novi.autogarage.service.UserAuthenticateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    UserAuthenticateService userAuthenticateService;

    @Autowired
    public AuthenticationController(UserAuthenticateService userAuthenticateService) {
        this.userAuthenticateService = userAuthenticateService;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequestDto) {

        AuthenticationResponseDto authenticationResponseDto = userAuthenticateService.authenticateUser(authenticationRequestDto);

        return ResponseEntity.ok(authenticationResponseDto);
    }

}
