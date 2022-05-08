package com.example.repository;

import com.example.entity.Example;
import com.oracle.xmlns.internal.webservices.jaxws_databinding.JavaParam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Example,Integer> {
    boolean existsByTextAndIdNot(String text, Integer id);
    boolean existsByText(String text);
}
