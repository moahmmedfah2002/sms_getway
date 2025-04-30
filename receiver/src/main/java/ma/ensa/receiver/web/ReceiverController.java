package ma.ensa.receiver.web;

import lombok.AllArgsConstructor;
import ma.ensa.receiver.dto.PageResponseDto;
import ma.ensa.receiver.dto.ReceiverDto;
import ma.ensa.receiver.entities.Receiver;
import ma.ensa.receiver.mappers.ReceiverDtoMapper;
import ma.ensa.receiver.services.ReceiverService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/receivers")
public class ReceiverController {

    private final ReceiverService receiverService;
    private final ReceiverDtoMapper receiverDtoMapper;

    /**
     * Get a paginated list of receivers with optional filtering
     */
    @GetMapping
    public PageResponseDto<ReceiverDto> getReceivers(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        System.out.println("Query: " + query + ", UserId: " + userId + ", Page: " + page + ", Size: " + size);

        // If trying to access other's data, restrict to own data
        if ((userId == null || !userId.equals(getCurrentUserId()))) {
            System.out.println("UserId is null or does not match current user, setting to current user id");
            userId = getCurrentUserId();
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Receiver> receiverPage = receiverService.getReceivers(query, userId, pageable);

        List<ReceiverDto> receiverDtos = receiverPage.getContent().stream()
                .map(receiverDtoMapper::toDto)
                .collect(Collectors.toList());

        PageResponseDto<ReceiverDto> response = new PageResponseDto<>();
        response.setContent(receiverDtos);
        response.setTotalElements(receiverPage.getTotalElements());
        response.setTotalPages(receiverPage.getTotalPages());
        response.setSize(receiverPage.getSize());
        response.setNumber(receiverPage.getNumber());

        return ResponseEntity.ok(response).getBody();
    }

    private String getCurrentUserId() {
        // get user id from jwt token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal().toString());
        if (authentication.getPrincipal() instanceof Jwt jwt) {
            System.out.println("JWT: " + jwt.getClaims().toString());
            System.out.println("JWT id: " + jwt.getSubject());
            return jwt.getSubject();
        }
        return null;
//        return "ff5aef91-c268-43cb-bf3b-e4ff6c2f1539";
    }
}
