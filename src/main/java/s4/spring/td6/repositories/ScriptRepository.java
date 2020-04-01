package s4.spring.td6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import s4.spring.td6.models.Script;
import s4.spring.td6.models.User;

import java.util.List;

public interface ScriptRepository extends JpaRepository<Script, Integer> {
    Script findById(int id);

    List<Script> findByUser(User user);

    List<Script> findAll();
}
