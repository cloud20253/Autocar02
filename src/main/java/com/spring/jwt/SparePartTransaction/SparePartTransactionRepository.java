package com.spring.jwt.SparePartTransaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SparePartTransactionRepository extends JpaRepository<SparePartTransaction, Integer> {

    List<SparePartTransaction> findByVehicleRegId(Integer vehicleRegID);
    List<SparePartTransaction> findByUserId(Integer userId);

    List<SparePartTransaction> findByTransactionDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<SparePartTransaction> findByBillNo(String billNo);

    List<SparePartTransaction> findBySparePartIdAndTransactionDateBetween(Integer sparePartId, LocalDateTime startDate, LocalDateTime endDate);

    List<SparePartTransaction> findByTransactionTypeAndNameAndTransactionDateBetween(TransactionType transactionType, String name, LocalDateTime startDate, LocalDateTime endDate);

    List<SparePartTransaction> findByNameOrPartNumber(String name, String partNumber);

    List<SparePartTransaction> findByName(String name);

    List<SparePartTransaction> findByPartNumber(String partNumber);

    @Query("SELECT MAX(s.invoiceNumber) FROM SparePartTransaction s")
    Integer findMaxInvoiceNumber();

    @Query("SELECT MAX(s.jobCardNumber) FROM SparePartTransaction s")
    Integer findMaxJobCardNumber();

    List<SparePartTransaction> findByVehicleRegIdAndInvoiceNumberIsNotNull(Integer vehicleId);
}
