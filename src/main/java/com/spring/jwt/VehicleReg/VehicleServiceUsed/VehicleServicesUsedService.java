package com.spring.jwt.VehicleReg.VehicleServiceUsed;

import com.spring.jwt.SparePartTransaction.InvoiceJobcardHelper;
import com.spring.jwt.SparePartTransaction.InvoiceJobcardNumbers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServicesUsedService {

    private final VehicleServicesUsedRepository repository;

    private final InvoiceJobcardHelper invoiceJobcardHelper;

    public VehicleServicesUsedService(VehicleServicesUsedRepository repository, InvoiceJobcardHelper invoiceJobcardHelper) {
        this.repository = repository;
        this.invoiceJobcardHelper = invoiceJobcardHelper;
    }

    public VehicleServicesUsed createService(VehicleServicesUsed serviceUsed) {
        if (serviceUsed.getVehicleId() != null) {
            InvoiceJobcardNumbers numbers = invoiceJobcardHelper.getOrGenerateNumbers(serviceUsed.getVehicleId());
            serviceUsed.setInvoiceNumber(numbers.getInvoiceNumber());
            serviceUsed.setJobCardNumber(numbers.getJobCardNumber());

            invoiceJobcardHelper.saveNumbersIfNotExists(serviceUsed.getVehicleId(), numbers.getInvoiceNumber(), numbers.getJobCardNumber());
        }
        return repository.save(serviceUsed);
    }

    public VehicleServicesUsed getServiceById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found with id " + id));
    }

    public List<VehicleServicesUsed> getAllServices() {
        return repository.findAll();
    }

    public List<VehicleServicesUsed> getServicesByVehicleId(Integer vehicleId) {
        List<VehicleServicesUsed> list = repository.findByVehicleId(vehicleId);
        if(list.isEmpty()){
            throw new RuntimeException("No services found for vehicle id " + vehicleId);
        }
        return list;
    }

    public boolean deleteServiceById(Integer id) {
        Optional<VehicleServicesUsed> serviceOptional = repository.findById(id);
        if (serviceOptional.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

}
