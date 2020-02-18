package s4.spring.td2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import s4.spring.td2.entities.Organization;

import java.util.List;

public interface OrgaRepository extends JpaRepository<Organization, Integer> {
    Organization findById(int id);
    List<Organization> findAll();
    
    @Query("SELECT o FROM Organization o WHERE o.name LIKE %:search% OR o.domain LIKE %:search% OR o.aliases LIKE %:search%")
    List<Organization> findBySearch(String search);
}
