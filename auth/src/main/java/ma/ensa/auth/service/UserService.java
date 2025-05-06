package ma.ensa.auth.service;

import lombok.RequiredArgsConstructor;
import ma.ensa.auth.entity.User;
import ma.ensa.auth.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepository;
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
