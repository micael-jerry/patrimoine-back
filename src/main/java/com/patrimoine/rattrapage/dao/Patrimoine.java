package com.patrimoine.rattrapage.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patrimoine {
    private String id;
    private String possesseur;
    private LocalDateTime derniereModification;
}
