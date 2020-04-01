package s4.spring.td5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import s4.spring.td5.models.Script;
import s4.spring.td5.models.User;

import java.util.List;

public interface ScriptRepository extends JpaRepository<Script, Integer> {
    Script findById(int id);

    List<Script> findByUser(User user);

    List<Script> findAll();
}
