package io.amicolon.day1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;

import static java.util.stream.Collectors.toCollection;

public class FileReader
{
    public Set<Integer> retrieveNumbersFromFile(String entriesFile) throws IOException, URISyntaxException
    {
        final Path path = Paths.get(getClass().getResource(entriesFile).toURI());

        return Files.lines(path)
                .map(Integer::parseInt)
                .collect(toCollection(TreeSet::new));
    }
}
