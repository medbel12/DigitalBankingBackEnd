package enset.ma.digitalbankingbackend.repositories;




import enset.ma.digitalbankingbackend.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation,Long > {
    public List<Operation> findByBankAccountId(String accountId);
    Page<Operation> findByBankAccountId(String accountId, Pageable pageable);

}