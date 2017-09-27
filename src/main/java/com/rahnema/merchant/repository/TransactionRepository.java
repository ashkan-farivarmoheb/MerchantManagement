package com.rahnema.merchant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rahnema.merchant.domain.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
