package by.vasilevsky.itftask.service.impl;

import by.vasilevsky.itftask.dao.BidDao;
import by.vasilevsky.itftask.entity.Bid;
import by.vasilevsky.itftask.entity.BidStatus;
import by.vasilevsky.itftask.exception.BidServiceException;
import by.vasilevsky.itftask.service.BidService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BidServiceImplTest {
    private static final Long GIVEN_ID_PROCESSED = 46L;
    private static final Long GIVEN_ID_REJECTED = 93L;
    private static final Long GIVEN_ID_QUEUE = 99L;
    private static final Long GIVEN_ID_NOT_EXIST = 77L;

    private static Bid storedBidQueue = new Bid(
            "request",
            new BigDecimal(111),
            LocalDate.of(2018, 5, 5),
            BidStatus.QUEUE
    );
    private static Bid storedBidRejected = new Bid(
            "request",
            new BigDecimal(111),
            LocalDate.of(2018, 5, 5),
            BidStatus.REJECTED
    );

    private static Bid storedBidProcessed = new Bid(
            "request",
            new BigDecimal(111),
            LocalDate.of(2018, 5, 5),
            BidStatus.REJECTED
    );


    @Mock
    private BidDao bidDao;

    private BidService bidService;

    @Before
    public void setUp() throws Exception {
        storedBidProcessed.setId(GIVEN_ID_PROCESSED);
        storedBidRejected.setId(GIVEN_ID_REJECTED);
        storedBidProcessed.setId(GIVEN_ID_PROCESSED);

        when(bidDao.findBidById(GIVEN_ID_PROCESSED)).thenReturn(storedBidProcessed);
        when(bidDao.findBidById(GIVEN_ID_QUEUE)).thenReturn(storedBidQueue);
        when(bidDao.findBidById(GIVEN_ID_REJECTED)).thenReturn(storedBidRejected);

        bidService = new BidServiceImpl(bidDao);
    }

    @Test(expected = BidServiceException.class)
    public void disallowedUpdateProcessedToQueue() throws Exception {
        Bid givenBid = new Bid(
                null,
                null,
                null,
                BidStatus.QUEUE
        );
        givenBid.setId(GIVEN_ID_PROCESSED);

        bidService.updateBidStatus(givenBid);
    }

    @Test(expected = BidServiceException.class)
    public void disallowedUpdateRejectedToQueue() throws Exception {
        Bid givenBid = new Bid(
                null,
                null,
                null,
                BidStatus.QUEUE
        );
        givenBid.setId(GIVEN_ID_REJECTED);

        bidService.updateBidStatus(givenBid);
    }

    @Test(expected = BidServiceException.class)
    public void disallowedUpdateRejectedToProcessed() throws Exception {
        Bid givenBid = new Bid(
                null,
                null,
                null,
                BidStatus.PROCESSED
        );
        givenBid.setId(GIVEN_ID_REJECTED);

        bidService.updateBidStatus(givenBid);
    }

    @Test(expected = BidServiceException.class)
    public void disallowedUpdateProcessedToRejected() throws Exception {
        Bid givenBid = new Bid(
                null,
                null,
                null,
                BidStatus.REJECTED
        );
        givenBid.setId(GIVEN_ID_PROCESSED);

        bidService.updateBidStatus(givenBid);
    }

    @Test(expected = BidServiceException.class)
    public void disallowedUpdateNotExistingBid() throws Exception {
        Bid givenBid = new Bid(
                null,
                null,
                null,
                BidStatus.REJECTED
        );
        givenBid.setId(GIVEN_ID_NOT_EXIST);

        bidService.updateBidStatus(givenBid);
    }

    @Test(expected = BidServiceException.class)
    public void disallowedUpdateNullableBidStatus() throws Exception {
        Bid givenBid = new Bid(
                null,
                null,
                null,
                null
        );
        givenBid.setId(GIVEN_ID_REJECTED);

        bidService.updateBidStatus(givenBid);
    }

    @Test
    public void allowedUpdateQueueToProcessed() throws Exception {
        Bid givenBid = new Bid(
                null,
                null,
                null,
                BidStatus.PROCESSED
        );
        givenBid.setId(GIVEN_ID_QUEUE);

        ArgumentCaptor<Bid> argument = ArgumentCaptor.forClass(Bid.class);

        bidService.updateBidStatus(givenBid);

        verify(bidDao).save(argument.capture());

        assertEquals(BidStatus.PROCESSED, argument.getValue().getBidStatus());
    }
}