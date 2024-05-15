package enset.ma.digitalbankingbackend.exeptions;

public class BalanceNotSufficientException extends Exception {
    public BalanceNotSufficientException(String balanceNotSufficien) {
        super(balanceNotSufficien);
    }
}

