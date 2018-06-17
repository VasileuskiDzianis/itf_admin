package by.vasilevsky.itftask.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "bids")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "request")
    private String request;

    @Column(name = "bid")
    private BigDecimal bid;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "bid_status")
    @Enumerated(EnumType.STRING)
    private BidStatus bidStatus;

    public Bid() {
    }

    public Bid(
            String request,
            BigDecimal bid,
            LocalDate dueDate,
            BidStatus bidSatus) {
        this.request = request;
        this.bid = bid;
        this.dueDate = dueDate;
        this.bidStatus = bidSatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BidStatus getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(BidStatus bidStatus) {
        this.bidStatus = bidStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bid)) return false;

        Bid bid1 = (Bid) o;

        if (id != bid1.id) return false;
        if (request != null ? !request.equals(bid1.request) : bid1.request != null) return false;
        if (bid != null ? !bid.equals(bid1.bid) : bid1.bid != null) return false;
        if (dueDate != null ? !dueDate.equals(bid1.dueDate) : bid1.dueDate != null) return false;
        return bidStatus == bid1.bidStatus;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (request != null ? request.hashCode() : 0);
        result = 31 * result + (bid != null ? bid.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (bidStatus != null ? bidStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id=" + id +
                ", request='" + request + '\'' +
                ", bid=" + bid +
                ", dueDate=" + dueDate +
                ", bidStatus=" + bidStatus +
                '}';
    }
}
