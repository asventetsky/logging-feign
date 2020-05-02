package org.sventetsky.logging.feign.example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.sventetsky.logging.feign.example.client.thereportoftheweek.model.Report;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TheReportOfTheWeekServiceTest {

    @Autowired
    private TheReportOfTheWeekService service;

    @Test
    void fetchEnergyCrisisReports_shouldReturnCorrectNumberOfCorrespondingReportsInCategory() {
        List<Report> reports = service.fetchEnergyCrisisReports();
        assertEquals(156, reports.size());
    }
}