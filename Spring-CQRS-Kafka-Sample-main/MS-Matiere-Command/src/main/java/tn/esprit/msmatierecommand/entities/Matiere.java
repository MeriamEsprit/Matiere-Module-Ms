package tn.esprit.msmatierecommand.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Matiere implements Serializable {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomMatiere;
    private int coef;
    Long moduleId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Matiere(Long id, String nomMatiere, int coef, LocalDateTime createdAt, LocalDateTime updatedAt, Long moduleId) {
        this.id = id;
        this.nomMatiere = nomMatiere;
        this.coef = coef;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.moduleId = moduleId;
    }
}
