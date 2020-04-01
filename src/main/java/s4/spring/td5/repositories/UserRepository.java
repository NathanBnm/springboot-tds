package s4.spring.td5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import s4.spring.td5.models.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getById(int id);

    List<User> findAll();

    User findByLogin(String login);
}
