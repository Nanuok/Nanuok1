package com.contractfarming.services;

import com.contractfarming.Entity.Contract;
import com.contractfarming.respository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {
    @Autowired
    private ContractRepository contractRepository;

    public Contract saveOne(Contract contract) {
        return contractRepository.save(contract);
    }

    public List<Contract> getListContracts() {
        return contractRepository.findAll();
    }


    public Contract getListByBuyerId(String buyerId) {
        return contractRepository.findByBuyerId(buyerId);
    }

    public List<Contract> getAllListByStatus(String status) {
        return contractRepository.findAllByStatus(status);
    }

    public List<Contract> getAllContractFarmer() {
       return contractRepository.findAll();
    }

   /* public Contract UpdateContractRequest(Contract contract, String orderId) {
        Optional<Contract> byId = contractRepository.findById(orderId);
            Contract contract1 = byId.get();
            contract1.setCity(contract.getCity());
            return contractRepository.save(contract1);
    }*/
    public Contract cancelContractRequest(String orderId) {
        return contractRepository.removeByOrderId(orderId);
    }

//update the request

    public Contract updateEmployeeDetails(Contract contract) {

        Contract listByBuyerId = getListByBuyerId(contract.getBuyerId());

        if (contract.getStatus() != null) {

            listByBuyerId.setStatus(contract.getStatus());

        }
        return listByBuyerId;
    }
}
