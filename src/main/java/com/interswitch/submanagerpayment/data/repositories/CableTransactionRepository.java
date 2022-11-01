package com.interswitch.submanagerpayment.data.repositories;

import com.interswitch.submanagerpayment.data.models.CableTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CableTransactionRepository extends JpaRepository<CableTransaction, Long> {
}
