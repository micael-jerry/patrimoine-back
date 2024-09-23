package com.patrimoine.rattrapage.controller.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatrimoineDto {
    private String possesseur;
    private LocalDateTime derniereModification;
}
