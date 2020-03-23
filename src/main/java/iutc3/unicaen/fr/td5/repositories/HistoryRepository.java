package iutc3.unicaen.fr.td5.repositories;

import iutc3.unicaen.fr.td5.models.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Integer> {
    History findById(int id);

    List<History> findAll();
}
