package ma.ensa.groupe.service.impl;

import lombok.RequiredArgsConstructor;
import ma.ensa.groupe.entity.Group;
import ma.ensa.groupe.openFeaign.ReceiverRestClient;
import ma.ensa.groupe.repository.GroupRepository;
import ma.ensa.groupe.service.GroupService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final ReceiverRestClient receiverClient;

    @Override
    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Page<Group> getAllGroups(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    @Override
    public Optional<Group> getGroupById(Long id) {
        return groupRepository.findById(id);
    }

    @Override
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    @Override
    public void removeReceiverFromGroup(Long groupId, Long receiverId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        group.getReceiverIds().remove(receiverId);
        groupRepository.save(group);
    }

    @Override
    public Group renameGroup(Long groupId, String name, String description) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        group.setName(name);
        group.setDescription(description);
        return groupRepository.save(group);
    }

    @Override
    public List<Group> addRandomGroups(int count) {
        List<Group> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Group group = Group.builder()
                    .name("Group-" + UUID.randomUUID().toString().substring(0, 8))
                    .description("Description-" + UUID.randomUUID().toString().substring(0, 8))
                    .userId(UUID.randomUUID().toString().substring(0, 8))
                    .receiverIds(new ArrayList<>())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            groups.add(groupRepository.save(group));
        }
        return groups;
    }
}