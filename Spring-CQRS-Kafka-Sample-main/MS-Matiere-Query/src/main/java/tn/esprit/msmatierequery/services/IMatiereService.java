package tn.esprit.msmatierequery.services;

import org.springframework.data.domain.Page;
import tn.esprit.dto.MatiereDto;
import tn.esprit.msmatierequery.entities.Matiere;

import java.util.List;

public interface IMatiereService {

    Matiere add(Matiere matiere);
    Matiere update(Matiere matiere);
    boolean delete(long id);


    List<MatiereDto> getMatieres();

    MatiereDto getMatiere(long id);

    MatiereDto getMatiereByName(String name);
}
