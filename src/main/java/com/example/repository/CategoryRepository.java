package com.example.repository;

import com.example.entity.Category;
import com.example.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
//    @Query(name = "select * from category ",nativeQuery = true)
 //   Language findByLanguagesidNative(Integer languages_id);
}
