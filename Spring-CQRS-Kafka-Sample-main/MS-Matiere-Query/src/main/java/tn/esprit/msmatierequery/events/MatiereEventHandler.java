package tn.esprit.msmatierequery.events;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.dto.MatiereDto;
import tn.esprit.msmatierequery.services.IMatiereService;

@Service
@RequiredArgsConstructor
public class MatiereEventHandler {
    private final IMatiereService matiereService;

    public void handleMatiereCreatedEvent(MatiereDto matiereDto) {
        matiereService.add(MatiereDto.mapToMatiere(matiereDto));
    }

    public void handleMatiereUpdatedEvent(MatiereDto matiereDto) {
        matiereService.update(MatiereDto.mapToMatiere(matiereDto));
    }

    public void handleMatiereDeletedEvent(long id) {
        matiereService.delete(id);
    }
}
