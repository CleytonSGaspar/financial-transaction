package br.com.financialtransaction.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class AbstracrReader {
    /**
     * Reads the contents of a JSON file and returns it as a string.
     *
     * @param path The path to the JSON file.
     * @return The contents of the JSON file as a string.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static String readJson(String path) throws IOException {
        // Read all lines from the file
        List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);

        // Join the lines using a space as separator
        String json = String.join(" ", lines);

        // Return the JSON string
        return json;
    }
}