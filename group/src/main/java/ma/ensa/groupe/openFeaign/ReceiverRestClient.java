package ma.ensa.groupe.openFeaign;

import ma.ensa.groupe.dto.ReceiverDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "receiver", url="receiver:6003/api/receivers")
public interface ReceiverRestClient {
    @GetMapping("/{id}")
    ReceiverDto getReceiverById(@PathVariable Long id);
}
