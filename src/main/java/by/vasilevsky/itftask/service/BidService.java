package by.vasilevsky.itftask.service;

import by.vasilevsky.itftask.entity.Bid;
import by.vasilevsky.itftask.exception.BidServiceException;

import java.util.List;

public interface BidService {

    List<Bid> findAll();

    void updateBidStatus(Bid bid) throws BidServiceException;
}
