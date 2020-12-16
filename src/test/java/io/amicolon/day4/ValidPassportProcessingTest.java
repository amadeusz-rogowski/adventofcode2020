package io.amicolon.day4;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static io.amicolon.day4.Validation.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidPassportProcessingTest
{

    private final PassportProcessing passportProcessing = new PassportProcessing();

    @Test
    void day4PartATest() throws IOException, URISyntaxException
    {
        final var fileName = "test.txt";

        long result = passportProcessing.countValidPassports(fileName, BASIC);

        assertEquals(2, result);
    }

    @Test
    void day4PartA() throws IOException, URISyntaxException
    {
        final var fileName = "passports.txt";

        long result = passportProcessing.countValidPassports(fileName, BASIC);

        assertEquals(242, result);
    }

    @Test
    void day4PartBTest() throws IOException, URISyntaxException
    {
        final var fileName = "test2.txt";

        long result = passportProcessing.countValidPassports(fileName, EXTENDED);

        assertEquals(4, result);
    }

    @Test
    void day4PartB() throws IOException, URISyntaxException
    {
        final var fileName = "passports.txt";

        long result = passportProcessing.countValidPassports(fileName, EXTENDED);

        assertEquals(186, result);
    }

}