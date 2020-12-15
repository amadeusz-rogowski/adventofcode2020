package io.amicolon.day1;

import io.amicolon.day1.container.NumberContainer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;

public class ReportRepair
{
    private final FileReader fileReader = new FileReader();

    public int generateReport(String entriesFile, Part part) throws IOException, URISyntaxException
    {
        final Set<Integer> numbers = fileReader.retrieveNumbersFromFile(entriesFile);

        NumberContainer numberContainer = part.getRetrieveNumberContainerStrategy().apply(numbers);

        return numberContainer.product();
    }
}
