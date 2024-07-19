package tn.esprit.msmatierecommand.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;
import tn.esprit.dto.Event;
import tn.esprit.dto.EventType;
import tn.esprit.dto.MatiereDto;
import tn.esprit.dto.ModuleDTO;
import tn.esprit.msmatierecommand.entities.Matiere;
import tn.esprit.msmatierecommand.events.KafkaProducer;
import tn.esprit.msmatierecommand.repositories.MatiereRepository;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IMatiereServiceImp implements IMatiereService {
    private final MatiereRepository matiereRepository;
    private final KafkaProducer kafkaProducer;
    private final RestTemplate restTemplate;

    private final String MODULE_MS_URL = "http://localhost:9090/module/get"; // Change to the actual module-ms URL

    public ModuleDTO getModuleById(Long moduleId) {
        return restTemplate.getForObject(MODULE_MS_URL + "/" + moduleId, ModuleDTO.class);
    }

    @Override
    public MatiereDto add(MatiereDto matiereDto) {
        Matiere matiere = MatiereDto.mapToMatiere(matiereDto);
        matiere.setCreatedAt(LocalDateTime.now());
        matiereDto = MatiereDto.mapToMatiereDto(matiereRepository.save(matiere));

        // Fetch the ModuleDTO using RestTemplate if moduleId is not null
        if (matiereDto.moduleId() != null) {
            ModuleDTO moduleDTO = getModuleById(matiereDto.moduleId());
            matiereDto = new MatiereDto(matiereDto.id(), matiereDto.nomMatiere(), matiereDto.coef(), matiereDto.createdAt(), matiereDto.updatedAt(), moduleDTO, matiereDto.moduleId());
        }

        kafkaProducer.produceEvent(new Event(EventType.CREATED_MATIERE_EVENT, matiereDto, LocalDateTime.now()));
        return matiereDto;
    }

    @Override
    public MatiereDto update(long id, Map<Object, Object> fields) {
        Matiere matiere = matiereRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matiere not found: " + id));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Matiere.class, (String) key);
            field.setAccessible(true);

            if (field.getType().equals(LocalDate.class)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
                LocalDate localDate = LocalDate.parse((String) value, formatter);
                ReflectionUtils.setField(field, matiere, localDate);
            } else {
                ReflectionUtils.setField(field, matiere, value);
            }
        });

        matiere.setUpdatedAt(LocalDateTime.now());
        MatiereDto matiereDto = MatiereDto.mapToMatiereDto(matiereRepository.save(matiere));

        // Fetch the ModuleDTO using RestTemplate if moduleId is not null
        if (matiereDto.moduleId() != null) {
            ModuleDTO moduleDTO = getModuleById(matiereDto.moduleId());
            matiereDto = new MatiereDto(matiereDto.id(), matiereDto.nomMatiere(), matiereDto.coef(), matiereDto.createdAt(), matiereDto.updatedAt(), moduleDTO, matiereDto.moduleId());
        }

        kafkaProducer.produceEvent(new Event(EventType.UPDATED_MATIERE_EVENT, matiereDto, LocalDateTime.now()));
        return matiereDto;
    }

    @Override
    public boolean delete(long id) {
        MatiereDto matiereDto = MatiereDto.mapToMatiereDto(matiereRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matiere not found: " + id)));

        matiereRepository.deleteById(id);
        kafkaProducer.produceEvent(new Event(EventType.DELETED_MATIERE_EVENT, matiereDto, LocalDateTime.now()));

        return !matiereRepository.existsById(id);
    }

}
