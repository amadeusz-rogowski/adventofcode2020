package io.amicolon.day2;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordPhilosophyTest
{
    private final PasswordPhilosophy passwordPhilosophy = new PasswordPhilosophy();

    @Test
    void shouldCountValidPasswordsForProvidedData() throws IOException, URISyntaxException
    {
        String fileName = "password_policies.txt";

        long result = passwordPhilosophy.countValidPasswords(fileName);

        assertEquals(410, result);
    }

    @Test
    void shouldCountValidPasswordsForTestData() throws IOException, URISyntaxException
    {
        String fileName = "test.txt";

        long result = passwordPhilosophy.countValidPasswords(fileName);

        assertEquals(2, result);
    }
}