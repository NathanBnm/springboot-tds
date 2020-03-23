package iutc3.unicaen.fr.td5.repositories;

import iutc3.unicaen.fr.td5.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    Language getById(int id);

    List<Language> findAll();
}
