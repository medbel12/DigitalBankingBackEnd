package enset.ma.digitalbankingbackend;

import enset.ma.digitalbankingbackend.dto.BankAccountDTO;
import enset.ma.digitalbankingbackend.dto.CurrentBankAccountDTO;
import enset.ma.digitalbankingbackend.dto.CustomerDTO;
import enset.ma.digitalbankingbackend.dto.SavingBankAccountDTO;
import enset.ma.digitalbankingbackend.entities.*;
import enset.ma.digitalbankingbackend.enums.AccountStatus;
import enset.ma.digitalbankingbackend.enums.OpType;
import enset.ma.digitalbankingbackend.exeptions.CustomerException;
import enset.ma.digitalbankingbackend.repositories.BankAccountRepository;
import enset.ma.digitalbankingbackend.repositories.CustomerRepository;
import enset.ma.digitalbankingbackend.repositories.OperationRepository;
import enset.ma.digitalbankingbackend.service.BankAccountService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
        return args -> {
            Stream.of("mohamed","benelarbi ","karim").forEach(name->{
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setName(name);
                customerDTO.setEmail(name+"@gmail.com");
                bankAccountService.saveCustomer(customerDTO);
            });

            bankAccountService.listCostumer().forEach(cust ->{
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*9000,9000,cust.getId());
                    bankAccountService.saveSavingBankAccount(Math.random()*4000,5.5,cust.getId());

                } catch (CustomerException e) {
                    e.printStackTrace();
                }
            });
            List<BankAccountDTO> bankAccountList = bankAccountService.bankAccountList();
            for(BankAccountDTO bankAccount : bankAccountList){
                for (int i = 0; i <10 ; i++) {
                    String accountId;
                    if( bankAccount instanceof SavingBankAccountDTO){
                        accountId = ((SavingBankAccountDTO) bankAccount).getId();
                    }else {
                        accountId = ((CurrentBankAccountDTO) bankAccount).getId();
                    }
                    bankAccountService.credit(accountId,1000+Math.random()*23000,"Credit");
                    bankAccountService.debit(accountId,100+Math.random()*2300,"Debit");
                }

            }
        };
    }

    //@Bean

    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            OperationRepository operationRepository){
        return  args -> {

            Stream.of("mohamed","benealrbi","karim").forEach(name->{
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                customerRepository.save(customer);

            });
            customerRepository.findAll().forEach(cust ->{
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*90000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(cust);
                currentAccount.setOverDraft(5000);
                currentAccount.setCurrency("24000");
                bankAccountRepository.save(currentAccount);


                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());

                savingAccount.setBalance(Math.random()*90000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(cust);
                savingAccount.setInterestRate(950);
                savingAccount.setCurrency("24000");
                bankAccountRepository.save(savingAccount);

            });
            bankAccountRepository.findAll().forEach(acc ->{
                for (int i = 0; i < 10; i++) {
                    Operation operation = new Operation();
                    operation.setAmount(5000);
                    operation.setType(Math.random()>0.5? OpType.DEBIT:OpType.CREDIT);
                    operation.setDate(new Date());
                    operation.setBankAccount(acc);
                    operationRepository.save(operation);
                }
            });

        };
    }


}
