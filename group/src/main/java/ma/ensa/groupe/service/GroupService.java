package ma.ensa.groupe.service;

import ma.ensa.groupe.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    Group createGroup(Group group);
    Page<Group> getAllGroups(Pageable pageable);
    Optional<Group> getGroupById(Long id);
    void deleteGroup(Long id);
    void removeReceiverFromGroup(Long groupId, Long receiverId);
    Group renameGroup(Long groupId, String name, String description);


    List<Group> addRandomGroups(int count);
}
