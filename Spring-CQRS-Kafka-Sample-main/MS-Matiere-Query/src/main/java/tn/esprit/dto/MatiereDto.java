package tn.esprit.dto;

import tn.esprit.msmatierequery.entities.Matiere;

import java.time.LocalDateTime;

public record MatiereDto(Long id, String nomMatiere, int coef, LocalDateTime createdAt, LocalDateTime updatedAt, ModuleDTO module, Long moduleId) {
    public static Matiere mapToMatiere(MatiereDto matiereDto) {
        return new Matiere(matiereDto.id(), matiereDto.nomMatiere(), matiereDto.coef(), matiereDto.createdAt(), matiereDto.updatedAt(), matiereDto.moduleId());
    }

    public static MatiereDto mapToMatiereDto(Matiere matiere) {
        ModuleDTO moduleDTO = null;
        // Here you should fetch the moduleDTO based on matiere.getModuleId() if needed
        // For example: moduleDTO = moduleService.findModuleById(matiere.getModuleId());

        return new MatiereDto(matiere.getId(), matiere.getNomMatiere(), matiere.getCoef(), matiere.getCreatedAt(), matiere.getUpdatedAt(), moduleDTO, matiere.getModuleId());
    }
}
