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
public class BuyAndLogService implements IBuyAndLogService {

	@Autowired
	BuyTicketService buyTicket;
	@Autowired
	ILogWriteService logWrite;
	
	@Autowired
	TransactionTemplate transactionTemplate;
	
	@Override
	public int buy(String consumerId, int amount, String error) {

		try
		{
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {

				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {

					buyTicket.buy(consumerId, amount, error);
					
					// 의도적 에러 발생
					if (error.equals("2")) { int n = 10 / 0; }
					
					logWrite.write(consumerId, amount);
					
				}
				
			});
					
			return 1;
			
		}catch(Exception e) {
			System.out.println("[Transaction Propagation #1] Rollback");
			return 0;
		}
		
	}

}
