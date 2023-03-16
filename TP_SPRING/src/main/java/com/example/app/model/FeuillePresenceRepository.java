package com.example.app.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeuillePresenceRepository extends CrudRepository<FeuillePresence, Long> {
    List<FeuillePresence> findByAnneeAndMois(int annee, int mois);

    List<FeuillePresence> findByAnneeAndMoisAndEtudiant(int annee, int mois, Etudiant owner);

    FeuillePresence findById(long id);

    List<FeuillePresence> findByEtudiant(Etudiant etu);
}
