package tn.esprit.msmatierequery.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.msmatierequery.entities.Matiere;

import java.util.Optional;

public interface MatiereRepository extends MongoRepository<Matiere, Long> {

    Optional<Matiere> findByNomMatiere(String name);
}
