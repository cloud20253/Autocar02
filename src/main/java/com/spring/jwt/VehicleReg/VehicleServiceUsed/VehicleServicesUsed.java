package com.spring.jwt.VehicleReg.VehicleServiceUsed;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class VehicleServicesUsed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vehicleServicesUsedId;

    private String serviceName;

    private Integer quantity;

    private Integer Rate;

    private Integer cGST;

    private Integer sGST;

    private Integer vehicleId;

    @Column(name = "InvoiceNumber")
    private Integer invoiceNumber;

    @Column(name = "JobCardNumber")
    private Integer jobCardNumber;
}
