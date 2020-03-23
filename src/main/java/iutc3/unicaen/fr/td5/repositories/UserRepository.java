package iutc3.unicaen.fr.td5.repositories;

import iutc3.unicaen.fr.td5.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getById(int id);

    List<User> findAll();
}
