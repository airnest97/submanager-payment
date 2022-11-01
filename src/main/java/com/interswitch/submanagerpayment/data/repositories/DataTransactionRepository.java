package com.interswitch.submanagerpayment.data.repositories;

import com.interswitch.submanagerpayment.data.models.DataTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataTransactionRepository extends JpaRepository<DataTransaction, Long> {
}
