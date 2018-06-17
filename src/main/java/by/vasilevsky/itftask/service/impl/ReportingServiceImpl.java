package by.vasilevsky.itftask.service.impl;

import by.vasilevsky.itftask.dao.BidDao;
import by.vasilevsky.itftask.entity.Bid;
import by.vasilevsky.itftask.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportingServiceImpl implements ReportingService{
    static final String REJECTED_FIELD = "rejected";
    static final String PROCESSED_FIELD = "processed";
    static final String QUEUE_FIELD = "queue";

    @Autowired
    public ReportingServiceImpl(BidDao bidDao) {
        this.bidDao = bidDao;
    }

    private BidDao bidDao;

    @Override
    public Map<String, Integer> bidsCountStatistic() {
        List<Bid> bids = bidDao.findAll();
        int queueCounter = 0;
        int rejectedCounter = 0;
        int processedCounter = 0;

        for (Bid item : bids) {
            switch (item.getBidStatus()) {
                case PROCESSED: {
                    processedCounter++;
                    break;
                }case REJECTED: {
                    rejectedCounter++;
                    break;
                }case QUEUE: {
                    queueCounter++;
                    break;
                }
            }
        }

        Map<String, Integer> statistic = new HashMap<>();
        statistic.put(REJECTED_FIELD, rejectedCounter);
        statistic.put(PROCESSED_FIELD, processedCounter);
        statistic.put(QUEUE_FIELD, queueCounter);

        return statistic;
    }
}
