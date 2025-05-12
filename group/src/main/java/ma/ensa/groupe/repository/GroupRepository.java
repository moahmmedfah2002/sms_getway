package ma.ensa.groupe.repository;

import ma.ensa.groupe.model.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    
    Page<Group> findByUserId(String userId, Pageable pageable);
    
    @Query("SELECT g FROM Group g WHERE g.userId = :userId AND " +
           "(LOWER(g.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(g.description) LIKE LOWER(CONCAT('%', :query, '%')))")
    Page<Group> findByUserIdAndNameOrDescriptionContaining(
            @Param("userId") String userId,
            @Param("query") String query, 
            Pageable pageable);
            
    Optional<Group> findByIdAndUserId(Long id, String userId);
    
    List<Group> findByReceiverIdsContaining(Long receiverId);
}
