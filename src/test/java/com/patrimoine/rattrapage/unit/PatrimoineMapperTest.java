package com.patrimoine.rattrapage.unit;

import com.patrimoine.rattrapage.controller.dto.PatrimoineDto;
import com.patrimoine.rattrapage.controller.mapper.PatrimoineMapper;
import com.patrimoine.rattrapage.dao.Patrimoine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PatrimoineMapperTest {

    private PatrimoineMapper patrimoineMapper;

    @BeforeEach
    public void setUp() {
        patrimoineMapper = new PatrimoineMapper();
    }

    @Test
    public void shouldHandleEmptyPossesseurAndDerniereModificationFields() {
        // Given
        String id = "123";
        PatrimoineDto patrimoineDto = new PatrimoineDto();
        patrimoineDto.setPossesseur(null);
        patrimoineDto.setDerniereModification(null);

        // When
        Patrimoine patrimoine = patrimoineMapper.toEntity(id, patrimoineDto);

        // Then
        assertEquals(id, patrimoine.getId());
        assertNull(patrimoine.getPossesseur());
        assertNull(patrimoine.getDerniereModification());
    }
}