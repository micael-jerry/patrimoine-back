package com.patrimoine.rattrapage.unit;

import com.patrimoine.rattrapage.dao.Patrimoine;
import com.patrimoine.rattrapage.service.PatrimoineService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AllArgsConstructor
@NoArgsConstructor
public class PatrimoineServiceTest {

    @Test
    public void shouldReturnCorrectPatrimoineWhenIdMatchesExistingPatrimoine() throws IOException {
        // Given
        PatrimoineService patrimoineService = new PatrimoineService();
        Patrimoine existingPatrimoine = new Patrimoine("1", "John Doe", LocalDateTime.now());
        List<Patrimoine> existingPatrimoines = List.of(existingPatrimoine);
        Mockito.when(patrimoineService.loadPatrimoines()).thenReturn(existingPatrimoines);

        // When
        Patrimoine result = patrimoineService.getPatrimoine("1");

        // Then
        Assertions.assertEquals(existingPatrimoine, result);
    }
}
