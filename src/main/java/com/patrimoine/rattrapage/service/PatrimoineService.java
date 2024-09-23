package com.patrimoine.rattrapage.service;

import com.patrimoine.rattrapage.dao.Patrimoine;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatrimoineService {

    private final String filePath = "patrimoines.data";

    public Patrimoine getPatrimoine(String id) throws IOException {
        List<Patrimoine> patrimoines = this.loadPatrimoines();

        return patrimoines.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Patrimoine saveOrUpdatePatrimoine(Patrimoine patrimoine) throws IOException {
        List<Patrimoine> patrimoines = this.loadPatrimoines();

        patrimoines = patrimoines.stream()
                .filter(p -> !p.getId().equals(patrimoine.getId()))
                .collect(Collectors.toList());

        patrimoines.add(patrimoine);
        this.savePatrimoines(patrimoines);

        return patrimoine;
    }

    private List<Patrimoine> loadPatrimoines() throws IOException {
        List<Patrimoine> patrimoines = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return patrimoines;
        }

        try (InputStream is = Files.newInputStream(Paths.get(filePath))) {
            String jsonContent = new String(is.readAllBytes());
            if (jsonContent.isBlank()) {
                return patrimoines;
            }

            String[] records = jsonContent.split("\\r?\\n"); // Each patrimoine is stored in a new line
            for (String record : records) {
                try {
                    String[] parts = record.split(";");
                    Patrimoine patrimoine = new Patrimoine();
                    patrimoine.setId(parts[0]);
                    patrimoine.setPossesseur(parts[1]);
                    patrimoine.setDerniereModification(LocalDateTime.parse(parts[2]));
                    patrimoines.add(patrimoine);
                } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                    throw new IOException("Erreur lors de la lecture du fichier JSON.", e);
                }
            }
        }

        return patrimoines;
    }

    private void savePatrimoines(List<Patrimoine> patrimoines) throws IOException {
        try (OutputStream os = Files.newOutputStream(Paths.get(filePath))) {
            StringBuilder sb = new StringBuilder();
            for (Patrimoine patrimoine : patrimoines) {
                sb.append(patrimoine.getId()).append(";")
                        .append(patrimoine.getPossesseur()).append(";")
                        .append(patrimoine.getDerniereModification().toString())
                        .append(System.lineSeparator());
            }
            os.write(sb.toString().getBytes());
        }
    }
}
