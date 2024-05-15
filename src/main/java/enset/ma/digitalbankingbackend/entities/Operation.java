package enset.ma.digitalbankingbackend.entities;

import enset.ma.digitalbankingbackend.enums.OpType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Date date;
    private double amount;
    @Enumerated(EnumType.STRING)
    private OpType type;
    @ManyToOne
    private BankAccount bankAccount;
    private String description;
}