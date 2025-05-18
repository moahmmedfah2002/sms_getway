package ma.ensa.groupe.openFeaign;


import ma.ensa.groupe.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(value = "user", url = "gateway:7001/auth/")
public interface UserFeign {

    @GetMapping("user")
    Optional<User> getUser(@RequestParam  String username);
}
