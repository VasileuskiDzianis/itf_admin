package by.vasilevsky.itftask.controller;

import by.vasilevsky.itftask.entity.Bid;
import by.vasilevsky.itftask.exception.BidServiceException;
import by.vasilevsky.itftask.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bids")
public class BidController {

    private static final String FIELD_NAME = "error";
    private BidService bidService;

    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @GetMapping
    public List<Bid> getAllBids() {

        return bidService.findAll();
    }

    @PutMapping("{bidId}/status")
    public ResponseEntity updateBid(@PathVariable(name = "bidId") Long bidId,
                                    @RequestBody Bid bid) {
        if (bidId != bid.getId()) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        try {
            bidService.updateBidStatus(bid);
        } catch (BidServiceException e) {
            Map<String, String> errorCode = new HashMap<>();
            errorCode.put(FIELD_NAME, e.getMessage());

            return new ResponseEntity<>(errorCode, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
