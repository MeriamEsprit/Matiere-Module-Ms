package tn.esprit.msmatierequery.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Document
@Data
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
//@FieldDefaults(level = AccessLevel.NONE)
public class Matiere implements Serializable {

    @Id
    @Setter(AccessLevel.NONE)
     Long id;
     String nomMatiere;
     int coef;
    Long moduleId;
     LocalDateTime createdAt;
     LocalDateTime updatedAt;

    public Matiere(Long id, String nomMatiere, int coef, LocalDateTime createdAt, LocalDateTime updatedAt, Long moduleId) {
        this.id = id;
        this.nomMatiere = nomMatiere;
        this.coef = coef;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.moduleId = moduleId;
    }
}
