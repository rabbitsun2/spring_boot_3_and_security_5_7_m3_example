package com.cakeon.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;


import com.cakeon.springboot.dao.ITransaction3Dao;

@Service
public class LogWriteService implements ILogWriteService {

	@Autowired
	ITransaction3Dao transaction3;
		
	@Autowired
	TransactionTemplate transactionTemplate;
	
	@Override
	public int write(String consumerId, int amount) {

		try
		{
			transaction3.pay(consumerId, amount);
					
			return 1;
			
		}catch(Exception e) {
			return 0;
		}
		
	}

}
