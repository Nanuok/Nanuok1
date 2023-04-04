package com.contractfarming.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "contract")

public class Contract {

    private String OrderId;
    private String qualityGuidelines;
    private String quotationLink;
    @Id
    private String buyerId;
    private String consumerAdminId;
    private String optional;
    private String productName;
    @Lob
    private String productDescription;
    private String category;
    private String assetType;
    private String modelId;
    private String city;
    private String status;
    @Column(name = "price", nullable=false, length = 45)
    private long price;
    @Column(name = "quantity", nullable=false, length = 45)
    private int quantity;
    private String servicePackages;

    private Date timestamp;


}
