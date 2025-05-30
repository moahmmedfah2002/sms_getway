package ma.ensa.gatway.service;

import lombok.RequiredArgsConstructor;

import ma.ensa.gatway.entity.User;
import ma.ensa.gatway.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepository;
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
