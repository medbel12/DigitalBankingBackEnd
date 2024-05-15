package enset.ma.digitalbankingbackend.exeptions;

public class BankAccountNotFoundException extends Exception {
    public BankAccountNotFoundException(String s) {
        super(s);
    }
}
