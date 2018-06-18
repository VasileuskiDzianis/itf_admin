package by.vasilevsky.itftask.service.impl;

import by.vasilevsky.itftask.dao.BidDao;
import by.vasilevsky.itftask.entity.Bid;
import by.vasilevsky.itftask.entity.BidStatus;
import by.vasilevsky.itftask.exception.BidServiceException;
import by.vasilevsky.itftask.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BidServiceImpl implements BidService {

    private static final int LEAST_ID = 0;
    private BidDao bidDao;

    @Autowired
    public BidServiceImpl(BidDao bidDao) {
        this.bidDao = bidDao;
    }

    @Override
    public List<Bid> findAll() {

        return bidDao.findAll();
    }

    @Override
    @Transactional
    public void updateBidStatus(Bid bid) throws BidServiceException {
        validateBid(bid);

        Bid storedBid = bidDao.findBidById(bid.getId());

        if (storedBid == null) {
            throw new BidServiceException("No such bid with id=" + bid.getId());
        }

        if (isTransferAllowed(storedBid.getBidStatus(), bid.getBidStatus())) {
            storedBid.setBidStatus(bid.getBidStatus());
            bidDao.save(storedBid);
        } else {
            throw new BidServiceException("Illegal Bid's status transfer: "
                    + storedBid.getBidStatus() + " -> " + bid.getBidStatus());
        }
    }

    private void validateBid(Bid bid) {
        if (bid == null) {
            throw new IllegalArgumentException("Bid cannot be null");
        }

        if (bid.getId() <= LEAST_ID) {
            throw new IllegalArgumentException("Bid's id must be greater than 0");
        }
    }

    private boolean isTransferAllowed(BidStatus currentBid, BidStatus targetBid) {

        return targetBid != null && currentBid == BidStatus.QUEUE;
    }
}
