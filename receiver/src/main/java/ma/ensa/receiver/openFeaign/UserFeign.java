package ma.ensa.receiver.openFeaign;


import ma.ensa.receiver.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(value = "user", url = "gateway:7001/auth/")
@Service
public interface UserFeign {

    @GetMapping("user")
    Optional<User> getUser(@RequestParam  String username);
}
