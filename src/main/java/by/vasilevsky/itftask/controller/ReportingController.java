package by.vasilevsky.itftask.controller;

import by.vasilevsky.itftask.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportingController {

    private ReportingService reportingService;

    @Autowired
    public ReportingController(ReportingService reportingService) {
        this.reportingService = reportingService;
    }

    @GetMapping("/bids/counter")
    public Map<String, Integer> getBidsStatistic() {

        return reportingService.bidsCountStatistic();
    }
}
