package br.com.financialtransaction.service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.financialtransaction.models.Saldos;

public class ReaderJson extends AbstracrReader {

    /**
     * Read a list of Saldos objects from a JSON file.
     * 
     * @param path The path to the JSON file.
     * @return A list of Saldos objects.
     * @throws IOException If there is an error reading the file.
     */
    public static List<Saldos> readListFrom(String path) throws IOException {
        // Read the JSON text from the file
        String jsonText = readJson(path);

        // Define the type of the collection to deserialize into
        Type collectionType = new TypeToken<List<Saldos>>() {
        }.getType();

        // Deserialize the JSON text into a list of Saldos objects
        return new Gson().fromJson(jsonText, collectionType);
    }
}
