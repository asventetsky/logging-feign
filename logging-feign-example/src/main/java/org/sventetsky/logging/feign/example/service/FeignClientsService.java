package org.sventetsky.logging.feign.example.service;

import org.springframework.stereotype.Service;
import org.sventetsky.logging.feign.example.client.jsonplaceholder.JsonPlaceHolderClient;
import org.sventetsky.logging.feign.example.client.jsonplaceholder.model.Post;
import org.sventetsky.logging.feign.example.client.thereportoftheweek.TheReportOfTheWeekClient;

import java.util.List;

@Service
public class FeignClientsService {

    private JsonPlaceHolderService jsonPlaceHolderService;
    private TheReportOfTheWeekService theReportOfTheWeekService;

    public FeignClientsService(JsonPlaceHolderService jsonPlaceHolderService, TheReportOfTheWeekService theReportOfTheWeekService) {
        this.jsonPlaceHolderService = jsonPlaceHolderService;
        this.theReportOfTheWeekService = theReportOfTheWeekService;
    }

    public void invokeFeignClients() {
        jsonPlaceHolderService.fetchPosts();
        jsonPlaceHolderService.createPost();
        theReportOfTheWeekService.fetchEnergyCrisisReports();
    }
}
