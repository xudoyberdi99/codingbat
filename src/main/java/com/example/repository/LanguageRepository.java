package com.example.repository;

import com.example.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language,Integer> {
        boolean existsByName(String name);
        boolean existsByNameAndIdNot(String name, Integer id);

}
