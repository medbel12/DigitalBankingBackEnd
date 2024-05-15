package enset.ma.digitalbankingbackend.exeptions;

public class CustomerException extends Exception {
    public CustomerException(String customerNotFound) {
        super(customerNotFound);
    }
}