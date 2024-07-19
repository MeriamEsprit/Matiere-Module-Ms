package tn.esprit.dto;

import java.time.LocalDateTime;

public record Event(EventType type, MatiereDto matiereDto, LocalDateTime eventCreatedAt){}
