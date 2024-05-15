package enset.ma.digitalbankingbackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountHistoryDTO {
    private String accoutnId;
    private double balance;
    private String type;
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private List<OperationDTO> operationDTOS;
}
