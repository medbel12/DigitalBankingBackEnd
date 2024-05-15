package enset.ma.digitalbankingbackend.dto;


import enset.ma.digitalbankingbackend.enums.AccountStatus;
import lombok.Data;

import java.util.Date;


@Data


public  class SavingBankAccountDTO extends  BankAccountDTO{
    private String id;
    private Date createdAt;
    private double balance;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double interestRate;
}
