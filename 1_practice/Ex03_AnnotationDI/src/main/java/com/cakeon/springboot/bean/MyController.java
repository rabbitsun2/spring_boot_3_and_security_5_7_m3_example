package com.cakeon.springboot.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

	@Autowired
	Member member1;
	@Autowired
	@Qualifier("printerB")
	Printer printer;
	@Autowired
	Member member2;
	
	@RequestMapping("/")
	public @ResponseBody String root() {
		
		// 1. Member Bean 가져오기
		member1.print();
		
		// 2. PrinterB Bean 가져오기
		member1.setPrinter(printer);
		member1.print();
		
		// 3. Singleton 여부 확인
		if ( member1 == member2 ) {
			System.out.println("Same");
		}else {
			System.out.println("Different");
		}

		return "Annotation 사용하기";
		
	}
		
	
}
