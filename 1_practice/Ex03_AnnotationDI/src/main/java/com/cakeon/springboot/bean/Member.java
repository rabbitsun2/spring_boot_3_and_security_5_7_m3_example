package com.cakeon.springboot.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Member {

	@Value("Hong gildong")
	private String name;
	@Value("Dosi")
	private String nickname;
	@Autowired
	@Qualifier("printerA")
	private Printer printer;
	
	public Member() { }
	
	public Member(String name, String nickname, Printer printer) {
		this.name = name;
		this.nickname = nickname;
		this.printer = printer;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public void setPrinter(Printer printer) {
		this.printer = printer;
	}
	
	public void print() {
		printer.print("Hello " + name + " : " + nickname);
	}
	
	
}
