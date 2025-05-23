package ma.ensa.gatway.service;

import lombok.RequiredArgsConstructor;

import ma.ensa.gatway.dto.AuthResponse;
import ma.ensa.gatway.dto.RequestAuth;
import ma.ensa.gatway.entity.User;
import ma.ensa.gatway.jwt.JwtService;
import ma.ensa.gatway.repository.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    public final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthResponse login(RequestAuth request) {





            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            var user=userRepo.findByEmail(request.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException(request.getUsername()));
            var jwtToken=jwtService.generateToken(user);


            return AuthResponse.builder()
                    .token(jwtToken)
                    .build();
    }

    public AuthResponse register(RequestAuth request) {




            var user = User.builder()
                    .email(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .phone(request.getPhone())
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())


                    .build();
            userRepo.save(user);
            var jwtToken=jwtService.generateToken(user);

            return AuthResponse.builder()
                    .token(jwtToken)
                    .build();
    }
}
