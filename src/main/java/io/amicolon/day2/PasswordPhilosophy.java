package io.amicolon.day2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public class PasswordPhilosophy
{
    public long countValidPasswords(String fileName) throws IOException, URISyntaxException
    {
        final List<String> passwordPolicies = retrievePasswordPolicies(fileName);

        final Pattern pattern = Pattern.compile("^(?<minOccurences>\\d+)-(?<maxOccurences>\\d+) (?<symbol>[a-z]): (?<password>[a-z]+)$");

        return passwordPolicies.stream()
                .filter(policy -> pattern.matcher(policy).matches())
                .map(policy -> new PasswordWithPasswordPolicy(pattern.matcher(policy)))
                .filter(PasswordWithPasswordPolicy::isPasswordValid)
                .count();
    }

    public List<String> retrievePasswordPolicies(String entriesFile) throws IOException, URISyntaxException
    {
        final Path path = Paths.get(getClass().getResource(entriesFile).toURI());

        return Files.lines(path).collect(toList());
    }
}
