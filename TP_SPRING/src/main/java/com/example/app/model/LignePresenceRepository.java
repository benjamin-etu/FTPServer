package com.example.app.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LignePresenceRepository extends CrudRepository<LignePresence, Long> {

}
