package day1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Set;

import static java.lang.Integer.sum;
import static java.util.stream.Collectors.toSet;

public class ReportRepair {

    public int generateReport(String entriesFile) throws IOException, URISyntaxException {

        final Set<Integer> numbers = retrieveNumbersFromFile(entriesFile);

        NumberPair numberPair = getNumberPairWhichSumTo2020(numbers);

        return numberPair.product();
    }

    private Set<Integer> retrieveNumbersFromFile(String entriesFile) throws IOException, URISyntaxException {

        final Path path = Paths.get(getClass().getResource(entriesFile).toURI());

        return Files.lines(path)
                .map(Integer::parseInt)
                .collect(toSet());
    }

    private NumberPair getNumberPairWhichSumTo2020(Set<? extends Integer> numbers) {

        return numbers.stream()
                .map(number -> mapToNumberPairWhichSumTo2020OrNull(number, numbers))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private NumberPair mapToNumberPairWhichSumTo2020OrNull(Integer number, Set<? extends Integer> numbers) {

        return numbers.stream()
                .filter(potentialAddend -> !potentialAddend.equals(number))
                .filter(potentialAddend -> sum(potentialAddend, number) == 2020)
                .map(addend -> new NumberPair(number, addend))
                .findAny()
                .orElse(null);
    }
}
