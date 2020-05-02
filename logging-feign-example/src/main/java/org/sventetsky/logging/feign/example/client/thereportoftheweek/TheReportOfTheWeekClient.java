package org.sventetsky.logging.feign.example.client.thereportoftheweek;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.sventetsky.logging.feign.example.client.thereportoftheweek.model.Report;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(
        name = "thereportoftheweek",
        url = "https://thereportoftheweek-api.herokuapp.com/reports"
)
public interface TheReportOfTheWeekClient {

    @RequestMapping(method = GET)
    List<Report> fetchReportsByCategory(@RequestParam String category);
}
