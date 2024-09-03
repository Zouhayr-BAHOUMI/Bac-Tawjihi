package tawjih.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tawjih.auth.AuthenticationRequest;
import tawjih.auth.AuthenticationResponse;
import tawjih.auth.RegisterRequest;
import tawjih.enums.Role;
import tawjih.service.implimentation.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register-etudiant")
    public ResponseEntity<AuthenticationResponse> register
            (@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request, Role.ETUDIANT));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login
            (@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }
}
