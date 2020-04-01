package s4.spring.td6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import s4.spring.td6.models.Language;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    Language getById(int id);

    List<Language> findAll();
}
