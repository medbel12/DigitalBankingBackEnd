package enset.ma.digitalbankingbackend.mappers;

import enset.ma.digitalbankingbackend.dto.CurrentBankAccountDTO;
import enset.ma.digitalbankingbackend.dto.CustomerDTO;
import enset.ma.digitalbankingbackend.dto.OperationDTO;
import enset.ma.digitalbankingbackend.dto.SavingBankAccountDTO;
import enset.ma.digitalbankingbackend.entities.CurrentAccount;
import enset.ma.digitalbankingbackend.entities.Customer;
import enset.ma.digitalbankingbackend.entities.Operation;
import enset.ma.digitalbankingbackend.entities.SavingAccount;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {
    public CustomerDTO fromCustomer(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    public Customer fromCustomer(CustomerDTO customerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);

        return customer;
    }

    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount) {
        SavingBankAccountDTO savingBankAccountDTO = new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount, savingBankAccountDTO);
        savingBankAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));
        savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingBankAccountDTO;
    }
    public SavingAccount fromBankAccount(SavingBankAccountDTO savingBankAccountDTO) {
        SavingAccount account = new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO, account);
        account.setCustomer(fromCustomer(savingBankAccountDTO.getCustomerDTO()));
        return account;

    }

    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount){
        CurrentBankAccountDTO currentBankAccountDTO = new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentAccount, currentBankAccountDTO);
        currentBankAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
        currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());

        return currentBankAccountDTO;

    }
    public CurrentAccount fromBankAccount(CurrentBankAccountDTO currentBankAccountDTO){
        CurrentAccount currentAccount = new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDTO,currentAccount);
        currentAccount.setCustomer(fromCustomer(currentBankAccountDTO.getCustomerDTO()));
        return currentAccount;
    }
    public OperationDTO fromOperation(Operation operation){
        OperationDTO operationDTO = new OperationDTO();
        BeanUtils.copyProperties(operation,operationDTO);

        return operationDTO;
    }
    public Operation fromOperation(OperationDTO operationDTO){
        Operation operation = new Operation();
        BeanUtils.copyProperties(operationDTO,operation);
        return operation;
    }
}