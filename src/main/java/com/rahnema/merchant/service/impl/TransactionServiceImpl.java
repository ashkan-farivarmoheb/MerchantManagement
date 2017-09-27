package com.rahnema.merchant.service.impl;

import org.springframework.stereotype.Service;

import com.rahnema.merchant.domain.Transaction;
import com.rahnema.merchant.repository.TransactionRepository;
import com.rahnema.merchant.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{

	private final TransactionRepository transactionRepository;
	
	public TransactionServiceImpl(TransactionRepository transactionRepository) {
		super();
		this.transactionRepository = transactionRepository;
	}

	@Override
	public Transaction save(Transaction transaction) {
		transaction = transactionRepository.save(transaction);
		return transaction;
	}

}
