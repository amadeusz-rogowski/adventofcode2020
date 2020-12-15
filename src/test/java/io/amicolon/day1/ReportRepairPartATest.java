package io.amicolon.day1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportRepairPartATest {

    private final ReportRepair reportRepair = new ReportRepair();

    @Test
    @DisplayName("Should find numbers which sum to 2020 and then calculate their product")
    void shouldGenerateReportForProvidedData() throws IOException, URISyntaxException {

        final String fileName = "entries.txt";

        var result = reportRepair.generateReport(fileName);

        assertEquals(1_003_971, result);
    }

    @Test
    void shouldGenerateReportForTDDData() throws IOException, URISyntaxException {

        final String fileName = "testA.txt";

        var result = reportRepair.generateReport(fileName);

        assertEquals(780_000, result);
    }
}