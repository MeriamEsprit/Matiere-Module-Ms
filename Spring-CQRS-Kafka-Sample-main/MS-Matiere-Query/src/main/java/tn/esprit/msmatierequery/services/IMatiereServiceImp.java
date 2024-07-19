package tn.esprit.msmatierequery.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.dto.MatiereDto;
import tn.esprit.dto.ModuleDTO;
import tn.esprit.msmatierequery.entities.Matiere;
import tn.esprit.msmatierequery.repositories.MatiereRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IMatiereServiceImp implements IMatiereService {

    private final MatiereRepository matiereRepository;
    private final RestTemplate restTemplate;

    private final String MODULE_MS_URL = "http://localhost:9090/module/get"; // Change to the actual module-ms URL

    private ModuleDTO getModuleById(Long moduleId) {
        if (moduleId == null) {
            return null; // Or handle appropriately
        }
        return restTemplate.getForObject(MODULE_MS_URL + "/" + moduleId, ModuleDTO.class);
    }

    @Override
    public Matiere add(Matiere matiere) {
        matiere.setCreatedAt(LocalDateTime.now());
        return matiereRepository.save(matiere);
    }

    @Override
    public Matiere update(Matiere matiere) {
        return matiereRepository.save(matiere);
    }

    @Override
    public boolean delete(long id) {
        matiereRepository.deleteById(id);
        return !matiereRepository.existsById(id);
    }

    @Override
    public List<MatiereDto> getMatieres() {
        return matiereRepository.findAll().stream()
                .map(matiere -> {
                    MatiereDto matiereDto = MatiereDto.mapToMatiereDto(matiere);
                    ModuleDTO moduleDTO = getModuleById(matiereDto.moduleId());
                    return new MatiereDto(matiereDto.id(), matiereDto.nomMatiere(), matiereDto.coef(), matiereDto.createdAt(), matiereDto.updatedAt(), moduleDTO, matiereDto.moduleId());
                })
                .collect(Collectors.toList());
    }

    @Override
    public MatiereDto getMatiere(long id) {
        Matiere matiere = matiereRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matiere not found: " + id));
        MatiereDto matiereDto = MatiereDto.mapToMatiereDto(matiere);
        ModuleDTO moduleDTO = getModuleById(matiereDto.moduleId());
        return new MatiereDto(matiereDto.id(), matiereDto.nomMatiere(), matiereDto.coef(), matiereDto.createdAt(), matiereDto.updatedAt(), moduleDTO, matiereDto.moduleId());
    }

    @Override
    public MatiereDto getMatiereByName(String name) {
        Matiere matiere = matiereRepository.findByNomMatiere(name)
                .orElseThrow(() -> new IllegalArgumentException("Matiere not found with name: " + name));
        MatiereDto matiereDto = MatiereDto.mapToMatiereDto(matiere);
        ModuleDTO moduleDTO = getModuleById(matiereDto.moduleId());
        return new MatiereDto(matiereDto.id(), matiereDto.nomMatiere(), matiereDto.coef(), matiereDto.createdAt(), matiereDto.updatedAt(), moduleDTO, matiereDto.moduleId());
    }
}
