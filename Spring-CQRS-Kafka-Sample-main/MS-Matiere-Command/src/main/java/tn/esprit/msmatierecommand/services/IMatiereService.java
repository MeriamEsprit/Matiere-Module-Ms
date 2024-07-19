package tn.esprit.msmatierecommand.services;

import tn.esprit.dto.MatiereDto;


import java.util.Map;

public interface IMatiereService {

    MatiereDto add(MatiereDto matiereDto);

    MatiereDto update(long idMatiere, Map<Object,Object> fields);

    boolean delete(long idMatiere);


}
