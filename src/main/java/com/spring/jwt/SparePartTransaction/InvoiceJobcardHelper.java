package com.spring.jwt.SparePartTransaction;

import com.spring.jwt.VehicleReg.VehicleServiceUsed.VehicleServicesUsed;
import com.spring.jwt.VehicleReg.VehicleServiceUsed.VehicleServicesUsedRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceJobcardHelper {

    private final SparePartTransactionRepository transactionRepository;
    private final VehicleServicesUsedRepository vehicleServicesUsedRepository;

    public InvoiceJobcardHelper(SparePartTransactionRepository transactionRepository,
                                VehicleServicesUsedRepository vehicleServicesUsedRepository) {
        this.transactionRepository = transactionRepository;
        this.vehicleServicesUsedRepository = vehicleServicesUsedRepository;
    }

    public InvoiceJobcardNumbers getOrGenerateNumbers(Integer vehicleId) {
        List<SparePartTransaction> partTransactions =
                transactionRepository.findByVehicleRegIdAndInvoiceNumberIsNotNull(vehicleId);
        if (!partTransactions.isEmpty()) {
            SparePartTransaction txn = partTransactions.get(0);
            return new InvoiceJobcardNumbers(txn.getInvoiceNumber(), txn.getJobCardNumber());
        }

        List<VehicleServicesUsed> serviceRecords =
                vehicleServicesUsedRepository.findByVehicleIdAndInvoiceNumberIsNotNull(vehicleId);
        if (!serviceRecords.isEmpty()) {
            VehicleServicesUsed record = serviceRecords.get(0);
            return new InvoiceJobcardNumbers(record.getInvoiceNumber(), record.getJobCardNumber());
        }
        Integer maxInvoice = transactionRepository.findMaxInvoiceNumber();
        Integer maxJobCard = transactionRepository.findMaxJobCardNumber();
        int newInvoice = (maxInvoice == null || maxInvoice < 1000) ? 1000 : maxInvoice + 1;
        int newJobCard = (maxJobCard == null || maxJobCard < 250) ? 250 : maxJobCard + 1;
        return new InvoiceJobcardNumbers(newInvoice, newJobCard);
    }

    public void saveNumbersIfNotExists(Integer vehicleId, int invoiceNumber, int jobCardNumber) {
        List<VehicleServicesUsed> serviceRecords =
                vehicleServicesUsedRepository.findByVehicleIdAndInvoiceNumberIsNotNull(vehicleId);
        if (serviceRecords.isEmpty()) {
            VehicleServicesUsed newRecord = new VehicleServicesUsed();
            newRecord.setVehicleId(vehicleId);
            newRecord.setInvoiceNumber(invoiceNumber);
            newRecord.setJobCardNumber(jobCardNumber);
            vehicleServicesUsedRepository.save(newRecord);
        }
    }
}
