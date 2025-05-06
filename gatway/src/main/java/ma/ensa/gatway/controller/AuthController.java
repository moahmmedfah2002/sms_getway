package ma.ensa.gatway.controller;

import lombok.RequiredArgsConstructor;

import ma.ensa.gatway.dto.AuthResponse;
import ma.ensa.gatway.dto.RequestAuth;
import ma.ensa.gatway.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/")
public class AuthController {


        private final AuthenticationService service;

        @PostMapping("login")
        public ResponseEntity<AuthResponse> login(@RequestBody RequestAuth request){
            return  ResponseEntity.ok(service.login(request));

        }
        @PostMapping("logup")
        public ResponseEntity<AuthResponse>  logup(@RequestBody  RequestAuth request){

            return  ResponseEntity.ok(service.register(request));
        }
}
