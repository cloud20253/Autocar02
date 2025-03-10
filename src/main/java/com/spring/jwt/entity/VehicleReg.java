package com.spring.jwt.entity;

import com.spring.jwt.VehicleReg.VehicleRegDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class VehicleReg {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer vehicleRegId;

    @Column
    private Integer appointmentId;

    // Vehicle details
    @Column(length = 45)
    private String vehicleNumber;

    @Column(length = 45)
    private String vehicleBrand;

    @Column(length = 45)
    private String vehicleModelName;

    @Column(length = 45)
    private String vehicleVariant;

    @Column(length = 45)
    private String engineNumber;

    @Column(length = 45)
    private String chasisNumber;

    @Column(length = 45)
    private String numberPlateColour;

    // Customer details
    @Column
    private Integer customerId;

    @Column(length = 45)
    private String customerName;

    @Column(length = 45)
    private String customerAddress;

    @Column(length = 45)
    private String customerMobileNumber;

    @Column(length = 45)
    private String customerAadharNo;

    @Column(length = 45)
    private String customerGstin;

    // Other existing fields
    @Column(length = 45)
    private String superwiser;

    @Column(length = 45)
    private String technician;

    @Column(length = 45)
    private String worker;

    @Column
    private String status;

    @Column
    private Integer userId;

    @Column
    private String date;

    public VehicleReg() {
    }

    public VehicleReg(VehicleRegDto vehicleRegDto) {
        this.vehicleRegId = vehicleRegDto.getVehicleRegId();
        this.appointmentId = vehicleRegDto.getAppointmentId();
        this.vehicleNumber = vehicleRegDto.getVehicleNumber();
        this.vehicleBrand = vehicleRegDto.getVehicleBrand();
        this.vehicleModelName = vehicleRegDto.getVehicleModelName();
        this.vehicleVariant = vehicleRegDto.getVehicleVariant();
        this.engineNumber = vehicleRegDto.getEngineNumber();
        this.chasisNumber = vehicleRegDto.getChasisNumber();
        this.numberPlateColour = vehicleRegDto.getNumberPlateColour();
        this.customerId = vehicleRegDto.getCustomerId();
        this.customerName = vehicleRegDto.getCustomerName();
        this.customerAddress = vehicleRegDto.getCustomerAddress();
        this.customerMobileNumber = vehicleRegDto.getCustomerMobileNumber();
        this.customerAadharNo = vehicleRegDto.getCustomerAadharNo();
        this.customerGstin = vehicleRegDto.getCustomerGstin();
        this.superwiser = vehicleRegDto.getSuperwiser();
        this.technician = vehicleRegDto.getTechnician();
        this.worker = vehicleRegDto.getWorker();
        this.status = vehicleRegDto.getStatus();
        this.userId = vehicleRegDto.getUserId();
        this.date = vehicleRegDto.getDate();
    }
}
