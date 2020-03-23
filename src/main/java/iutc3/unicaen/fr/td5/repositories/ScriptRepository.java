package iutc3.unicaen.fr.td5.repositories;

import iutc3.unicaen.fr.td5.models.Script;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScriptRepository extends JpaRepository<Script, Integer> {
    Script findById(int id);

    List<Script> findAll();
}
