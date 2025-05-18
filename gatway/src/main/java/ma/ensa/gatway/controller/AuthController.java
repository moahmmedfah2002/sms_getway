package ma.ensa.gatway.controller;

import lombok.RequiredArgsConstructor;

import ma.ensa.gatway.dto.AuthResponse;
import ma.ensa.gatway.dto.RequestAuth;
import ma.ensa.gatway.entity.User;
import ma.ensa.gatway.jwt.JwtService;
import ma.ensa.gatway.repository.UserRepo;
import ma.ensa.gatway.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/")
@CrossOrigin(origins = "*")
public class AuthController {
    private final UserRepo userRepository;
    private  final JwtService jwtService;
    

    private final AuthenticationService service;

        @PostMapping("login")
        public ResponseEntity<AuthResponse> login(@RequestBody RequestAuth request){
            System.out.println("true");
            return  ResponseEntity.ok(service.login(request));

        }
            @PostMapping("logup")
        public ResponseEntity<AuthResponse>  logup(@RequestBody  RequestAuth request){

            return  ResponseEntity.ok(service.register(request));
        }

    @GetMapping("user")
    public Optional<User> getUser(@RequestParam String username){
        return userRepository.findByEmail(username);

    }
    @PostMapping("isAuth")
    public ResponseEntity<AuthResponse> getUserByToken(@RequestBody AuthResponse response){
            String username=jwtService.extractUsername(response.getToken());
            AuthResponse authResponse=new AuthResponse();
            User user =this.getUser(username).get();
            AuthResponse.builder()
                    .valid(jwtService.validateToken(response.getToken(),user))
                    .user(user)
                    .token(response.getToken())
                    .build()
            ;



            return ResponseEntity.ok(authResponse);



    }
}
