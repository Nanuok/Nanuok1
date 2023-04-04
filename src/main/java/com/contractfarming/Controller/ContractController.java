package com.contractfarming.Controller;

import com.contractfarming.Entity.Contract;
import com.contractfarming.respository.ContractRepository;
import com.contractfarming.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/contractFarmingGw")
public class ContractController {
@Autowired
private ContractService contractService;


// Saving the Data
    @PostMapping("/buyContractRequest")
    public ResponseEntity<Contract> saveOneContract(@RequestBody Contract contract) {
        try {
            contract.setOrderId(UUID.randomUUID().toString());
            Date date = new Date();
            contract.setTimestamp(date);
            Contract save = contractService.saveOne(contract);
            return ResponseEntity.of(Optional.of(save));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get the Contract Farming based on the BuyerId.
    @GetMapping("/getByBuyerId/{buyerId}")
    public ResponseEntity<Contract> findContractByBuyerId(@PathVariable String buyerId){
        Contract listByBuyerId = contractService.getListByBuyerId(buyerId);
        if (listByBuyerId == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(listByBuyerId));
    }

    // Get All the Contracts based on status
    @GetMapping("/getAllByStatus/{status}")
        public ResponseEntity<List<Contract>> findAllByStatus(@PathVariable String status){
        List<Contract> allListByStatus = contractService.getAllListByStatus(status);
        if (allListByStatus.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(allListByStatus));
    }
    @GetMapping("/getAllContractFarming")
    public ResponseEntity<List<Contract>> finAllTheContracts(){
        List<Contract> allContractFarmer = contractService.getAllContractFarmer();
        if (allContractFarmer.size()<=0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(allContractFarmer));
    }
    @DeleteMapping("/deleteContractRequest/{orderId}")
    public ResponseEntity<Contract> cancelRequest(@PathVariable String orderId){
        try {
            Contract contract = contractService.cancelContractRequest(orderId);
            return ResponseEntity.ok(contract);  // return 200, with json body
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //return 404, with null body
        }
    }

    /*@PutMapping("/updateContractRequest{orderId}")
    public ResponseEntity<Contract> UpdateContract(@RequestBody Contract contract, @PathVariable("orderId") String orderId){
       try {
           Contract updateData = contractService.UpdateContractRequest(contract, orderId);
           return ResponseEntity.ok().body(updateData);
       }catch (Exception e){
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }

    }*/

    @PutMapping("updateContractRequest/{buyerId}")
    public ResponseEntity<Contract> updateRequest(@RequestBody Contract contract){
        Contract contract1 = contractService.updateEmployeeDetails(contract);
        return ResponseEntity.ok().body(contract1);
    }
    
    }
