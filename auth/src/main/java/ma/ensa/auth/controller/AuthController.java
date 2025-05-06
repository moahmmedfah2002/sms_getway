package ma.ensa.auth.controller;

import lombok.RequiredArgsConstructor;
import ma.ensa.auth.dto.AuthResponse;
import ma.ensa.auth.dto.RequestAuth;
import ma.ensa.auth.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/")
public class AuthController {


        private final AuthenticationService service;

        @GetMapping("login")
        public ResponseEntity<AuthResponse> login(@RequestBody RequestAuth request){
            return  ResponseEntity.ok(service.login(request));

        }
        @GetMapping("logup")
        public ResponseEntity<AuthResponse>  logup(@RequestBody  RequestAuth request){

            return  ResponseEntity.ok(service.register(request));
        }
}
