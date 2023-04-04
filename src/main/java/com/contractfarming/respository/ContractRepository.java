package com.contractfarming.respository;

import com.contractfarming.Entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface ContractRepository extends JpaRepository<Contract,String> {
    Contract findByBuyerId(String buyerId);

    List<Contract> findAllByStatus(String status);


    @Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM Contract WHERE orderId= :orderId")
    @Transactional                                                                // it will delete all the record with specific name
    Contract removeByOrderId(String orderId);

    //Contract findByOrderId(String orderId);


}
