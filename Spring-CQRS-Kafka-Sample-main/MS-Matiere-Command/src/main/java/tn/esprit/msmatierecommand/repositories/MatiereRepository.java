package tn.esprit.msmatierecommand.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.msmatierecommand.entities.Matiere;

public interface MatiereRepository extends JpaRepository<Matiere, Long> {
}
