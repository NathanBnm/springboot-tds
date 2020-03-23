package iutc3.unicaen.fr.td5.repositories;

import iutc3.unicaen.fr.td5.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category getById(int id);

    List<Category> findAll();
}
