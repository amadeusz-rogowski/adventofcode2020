package io.amicolon.day4;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.Arrays.asList;

public class PassportProcessing
{
    public long countValidPassports(String entriesFile, Validation validation) throws IOException, URISyntaxException
    {
        final Path path = Paths.get(getClass().getResource(entriesFile).toURI());

        final String map = Files.lines(path)
                .map(this::mapToNewLineIfEmpty)
                .reduce("", String::concat);

        List<String> passports = asList(map.split("\n"));

        return passports.stream()
                .filter(passport -> PassportValidator.validateLine(passport, validation))
                .count();
    }

    private String mapToNewLineIfEmpty(String line)
    {
        return line.equals("") ? "\n" : line + " ";
    }
}
