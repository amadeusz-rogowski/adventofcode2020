package io.amicolon.day2;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static io.amicolon.day2.Part.PART_A;
import static io.amicolon.day2.Part.PART_B;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PasswordPhilosophyTest
{
    private final PasswordPhilosophy passwordPhilosophy = new PasswordPhilosophy();

    @Test
    void shouldCountValidPasswordsPartAForProvidedData() throws IOException, URISyntaxException
    {
        String fileName = "password_policies.txt";

        long result = passwordPhilosophy.countValidPasswords(fileName, PART_A);

        assertEquals(410, result);
    }

    @Test
    void shouldCountValidPasswordsPartAForTestData() throws IOException, URISyntaxException
    {
        String fileName = "test.txt";

        long result = passwordPhilosophy.countValidPasswords(fileName, PART_A);

        assertEquals(2, result);
    }

    @Test
    void shouldCountValidPasswordsPartBForProvidedData() throws IOException, URISyntaxException
    {
        String fileName = "password_policies.txt";

        long result = passwordPhilosophy.countValidPasswords(fileName, PART_B);

        assertEquals(694, result);
    }

    @Test
    void shouldCountValidPasswordsPartBForTestData() throws IOException, URISyntaxException
    {
        String fileName = "test.txt";

        long result = passwordPhilosophy.countValidPasswords(fileName, PART_B);

        assertEquals(1, result);
    }
}