package by.vasilevsky.itftask.service.impl;

import by.vasilevsky.itftask.dao.BidDao;
import by.vasilevsky.itftask.entity.Bid;
import by.vasilevsky.itftask.entity.BidStatus;
import by.vasilevsky.itftask.service.ReportingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReportingServiceImplTest {
    private static final Bid BID_1 = new Bid(null, null, null, BidStatus.QUEUE);
    private static final Bid BID_2 = new Bid(null, null, null, BidStatus.QUEUE);
    private static final Bid BID_3 = new Bid(null, null, null, BidStatus.QUEUE);
    private static final Bid BID_4 = new Bid(null, null, null, BidStatus.REJECTED);
    private static final Bid BID_5 = new Bid(null, null, null, BidStatus.REJECTED);
    private static final Bid BID_6 = new Bid(null, null, null, BidStatus.PROCESSED);

    private static final int EXPECTED_PROCESSED = 1;
    private static final int EXPECTED_REJECTED = 2;
    private static final int EXPECTED_QUEUE = 3;

    @Mock
    private BidDao bidDao;

    private ReportingService reportingService;


    @Before
    public void setUp() throws Exception {
        when(bidDao.findAll()).thenReturn(Arrays.asList(BID_1,BID_2,BID_3,BID_4,BID_5,BID_6));

        reportingService = new ReportingServiceImpl(bidDao);
    }

    @Test
    public void bidsCountStatistic() throws Exception {
        Map<String, Integer> report = reportingService.bidsCountStatistic();

        assertTrue(EXPECTED_PROCESSED == report.get(ReportingServiceImpl.PROCESSED_FIELD));
        assertTrue(EXPECTED_REJECTED == report.get(ReportingServiceImpl.REJECTED_FIELD));
        assertTrue(EXPECTED_QUEUE == report.get(ReportingServiceImpl.QUEUE_FIELD));
    }
}