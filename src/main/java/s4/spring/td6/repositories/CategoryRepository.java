package s4.spring.td6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import s4.spring.td6.models.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category getById(int id);

    List<Category> findAll();
}
