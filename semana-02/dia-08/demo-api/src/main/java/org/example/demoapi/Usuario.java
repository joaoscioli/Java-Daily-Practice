package org.example.demoapi;

import jakarta.persistence.*;

@Entity
public record Usuario(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id,
        @Column(nullable = false) String nome,
        @Column(nullable = false, unique = true) String email
) {}