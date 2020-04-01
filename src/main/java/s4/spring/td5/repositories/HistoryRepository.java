package s4.spring.td5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import s4.spring.td5.models.History;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Integer> {
    History findById(int id);

    List<History> findAll();
}
