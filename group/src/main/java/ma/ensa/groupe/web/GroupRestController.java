package ma.ensa.groupe.web;

import lombok.RequiredArgsConstructor;
import ma.ensa.groupe.entity.Group;
import ma.ensa.groupe.service.GroupService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class GroupRestController {

    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        return ResponseEntity.ok(groupService.createGroup(group));
    }

    @GetMapping
    public ResponseEntity<Page<Group>> getAllGroups(Pageable pageable) {
        return ResponseEntity.ok(groupService.getAllGroups(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/remove-receiver/{receiverId}")
    public ResponseEntity<Void> removeReceiver(@PathVariable Long id, @PathVariable Long receiverId) {
        groupService.removeReceiverFromGroup(id, receiverId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Group> renameGroup(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        return ResponseEntity.ok(groupService.renameGroup(id, updates.get("name"), updates.get("description")));
    }

}
