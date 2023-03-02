package com.example.app.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EtudiantRepository extends CrudRepository<Etudiant, Long> {
    List<Etudiant> findByEmail(String email);
}
