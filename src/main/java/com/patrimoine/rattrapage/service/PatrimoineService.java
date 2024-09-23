package com.patrimoine.rattrapage.service;

import com.patrimoine.rattrapage.dao.Patrimoine;
import org.springframework.stereotype.Service;

import javax.json.*;
import javax.json.stream.JsonParsingException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatrimoineService {
    private final String filePath = "patrimoines.json";

    public Patrimoine getPatrimoine(String id) throws IOException {
        List<Patrimoine> patrimoines = this.loadPatrimoines();

        for (Patrimoine p : patrimoines) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public Patrimoine saveOrUpdatePatrimoine(Patrimoine patrimoine) throws IOException {
        List<Patrimoine> patrimoines = this.loadPatrimoines();

        for (int i = 0; i < patrimoines.size(); i++) {
            if (patrimoines.get(i).getId().equals(patrimoine.getId())) {
                patrimoines.set(i, patrimoine);
                this.savePatrimoines(patrimoines);
                return patrimoine;
            }
        }
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

        try (InputStream is = Files.newInputStream(Paths.get(filePath));
             JsonReader reader = Json.createReader(is)) {

            JsonArray jsonArray = reader.readArray();
            for (JsonValue value : jsonArray) {
                JsonObject obj = value.asJsonObject();
                Patrimoine patrimoine = new Patrimoine();
                patrimoine.setPossesseur(obj.getString("possesseur"));
                patrimoine.setDerniereModification(LocalDateTime.parse(obj.getString("derniereModification")));
                patrimoines.add(patrimoine);
            }
        } catch (JsonParsingException e) {
            throw new IOException("Erreur lors de la lecture du fichier JSON.", e);
        }
        return patrimoines;
    }

    private void savePatrimoines(List<Patrimoine> patrimoines) throws IOException {
        try (OutputStream os = Files.newOutputStream(Paths.get(filePath));
             JsonWriter writer = Json.createWriter(os)) {

            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (Patrimoine patrimoine : patrimoines) {
                JsonObjectBuilder objBuilder = Json.createObjectBuilder()
                        .add("possesseur", patrimoine.getPossesseur())
                        .add("derniereModification", patrimoine.getDerniereModification().toString());
                arrayBuilder.add(objBuilder);
            }

            writer.writeArray(arrayBuilder.build());
        }
    }
}
