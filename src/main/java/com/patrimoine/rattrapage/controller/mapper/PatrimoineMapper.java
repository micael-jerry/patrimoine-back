package com.patrimoine.rattrapage.controller.mapper;

import com.patrimoine.rattrapage.controller.dto.PatrimoineDto;
import com.patrimoine.rattrapage.dao.Patrimoine;
import org.springframework.stereotype.Component;

@Component
public class PatrimoineMapper {
    public Patrimoine toEntity(String id,PatrimoineDto patrimoineDto) {
        Patrimoine patrimoine = new Patrimoine();
        patrimoine.setId(id);
        patrimoine.setPossesseur(patrimoineDto.getPossesseur());
        patrimoine.setDerniereModification(patrimoineDto.getDerniereModification());
        return patrimoine;
    }
}
