package by.vasilevsky.itftask.exception;

public class BidServiceException extends Exception{
    public BidServiceException() {
    }

    public BidServiceException(String message) {
        super(message);
    }

    public BidServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BidServiceException(Throwable cause) {
        super(cause);
    }

    public BidServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
