package com.patrimoine.rattrapage.unit;

import com.patrimoine.rattrapage.controller.PatrimoineController;
import com.patrimoine.rattrapage.controller.exeption.NotFoundException;
import com.patrimoine.rattrapage.dao.Patrimoine;
import com.patrimoine.rattrapage.service.PatrimoineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatrimoineControllerTest {

    @Mock
    private PatrimoineService patrimoineService;

    @InjectMocks
    private PatrimoineController patrimoineController;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void shouldReturn404ErrorWhenRequestedPatrimoineIdDoesNotExist() throws IOException {
        String nonExistingPatrimoineId = "non-existing-id";
        when(patrimoineService.getPatrimoine(anyString())).thenReturn(null);

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> patrimoineController.findPatrimoineById(nonExistingPatrimoineId));

        assertEquals("Patrimoine with id " + nonExistingPatrimoineId + " not found", exception.getMessage());
    }

    @Test
    public void shouldReturnCorrectPatrimoineDataWhenIdProvidedInLowerCase() throws IOException {
        // Given
        String id = "lowercase-id";
        Patrimoine expectedPatrimoine = new Patrimoine(); // Initialize with expected data
        when(patrimoineService.getPatrimoine(id)).thenReturn(expectedPatrimoine);

        // When
        Patrimoine actualPatrimoine = patrimoineController.findPatrimoineById(id);

        // Then
        assertThat(actualPatrimoine).isEqualTo(expectedPatrimoine);
    }
}
