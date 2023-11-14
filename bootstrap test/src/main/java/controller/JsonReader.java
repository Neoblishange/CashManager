package controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

public class JsonReader {
    private String filePath;
    private String name;
    private String cash;


    public JsonReader(String filePath) throws IOException {
        this.filePath = filePath;


        try {
            // Créer un objet ObjectMapper de Jackson
            ObjectMapper objectMapper = new ObjectMapper();

            // Lire le fichier JSON et le convertir en un objet JsonNode
            JsonNode jsonNode = objectMapper.readTree(new File(filePath));

            // Accéder aux données dans le fichier JSON
            String name = jsonNode.get("name").asText();
            String cash = jsonNode.get("cash").asText();

            // Afficher les valeurs
            setName(name);
            setCash(cash);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

}
