package com.cakeon.springboot.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	public Member member1() {
		
		Member member1 = new Member();
		member1.setName("Hongil");
		member1.setNickname("Dosi");
		member1.setPrinter(new PrinterA());
		
		return member1;
		
	}
	
	@Bean(name="hello")
	public Member member2() {
		
		return new Member("Jeonwoochi", "Dosi", new PrinterA());
		
	}
	
	@Bean
	public PrinterA printerA() {
		return new PrinterA();
	}
	
	@Bean
	public PrinterB printerB() {
		return new PrinterB();
	}
	
}
