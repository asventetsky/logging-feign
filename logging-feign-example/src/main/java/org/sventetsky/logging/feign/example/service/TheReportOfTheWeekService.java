package org.sventetsky.logging.feign.example.service;

import org.springframework.stereotype.Service;
import org.sventetsky.logging.feign.example.client.thereportoftheweek.TheReportOfTheWeekClient;
import org.sventetsky.logging.feign.example.client.thereportoftheweek.model.Report;

import java.util.List;

@Service
public class TheReportOfTheWeekService {

    private TheReportOfTheWeekClient client;

    public TheReportOfTheWeekService(TheReportOfTheWeekClient client) {
        this.client = client;
    }

    public List<Report> fetchEnergyCrisisReports() {
        return client.fetchReportsByCategory("Energy Crisis");
    }
}
