package by.vasilevsky.itftask.dao;

import by.vasilevsky.itftask.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidDao extends JpaRepository<Bid, Long> {

    @Override
    Bid save(Bid bid);

    Bid findBidById(Long id);

    void deleteById(Long id);

    List<Bid> findAll();
}
