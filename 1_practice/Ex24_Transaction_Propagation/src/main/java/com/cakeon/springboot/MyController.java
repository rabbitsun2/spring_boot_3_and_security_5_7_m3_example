package com.cakeon.springboot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cakeon.springboot.service.IBuyAndLogService;
import com.cakeon.springboot.service.IBuyTicketService;


@Controller
public class MyController {

//	@Autowired
//	IBuyTicketService buyTicket;
	@Autowired
	IBuyAndLogService buyTicketLog;
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception{
		return "Transaction Propagation (4)";
	}
	
	@RequestMapping("/buy_ticket")
	public String buy_ticket() {
		return "buy_ticket";
	}
	
	@RequestMapping("/buy_ticket_card")
	public String buy_ticket_card(@RequestParam("consumerId") String consumerId, 
													@RequestParam("amount") String amount, 
													@RequestParam("error") String error, 
													Model model) {
		
//		int nResult = buyTicket.buy(consumerId, Integer.valueOf(amount), error);
		int nResult = buyTicketLog.buy(consumerId, Integer.valueOf(amount), error);
		
		
		model.addAttribute("consumerId", consumerId);
		model.addAttribute("amount", amount);
		
		if (nResult == 1) {
			return "buy_ticket_end";
		}else {
			return "buy_ticket_error";
		}
		
	}
	
}