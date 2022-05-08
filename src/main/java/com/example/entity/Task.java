package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    private String text;

    private String solution;

    private String hint;
    @Column(nullable = false)
    private String method;

    private boolean hasStar;
    @ManyToOne
    private Language languages;

}
