package com.cakeon.springboot.service;

public interface IBuyTicketService {
	public int buy(String consumerId, int amount, String error);
}
