package com.cakeon.springboot;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cakeon.springboot.dao.ISimpleBbsDao;
import com.cakeon.springboot.service.ISimpleBbsService;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class MyController {

	//private ISimpleBbsDao dao;
	@Autowired
	private ISimpleBbsService bbs;
	
	@RequestMapping("/")
	public String root() throws Exception{
		return "redirect:list";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String userlistPage(Model model) {
		
		model.addAttribute("list", bbs.list());

		int nResult = bbs.count();
		System.out.println("Count : " + nResult);
		
		return "list";
		
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view(HttpServletRequest req, Model model) {
		
		String sId = req.getParameter("id");
		
		model.addAttribute("dto", bbs.view(sId));
		return "view";
		
	}
	

	@RequestMapping("/writeForm")
	public String writeForm() {
		
		return "writeForm";
		
	}
	
	@RequestMapping("/write")
	public String write(Model model, HttpServletRequest req) {
		
		String sName = req.getParameter("writer");
		String sTitle = req.getParameter("title");
		String sContent = req.getParameter("content");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("item1", sName);
		map.put("item2", sTitle);
		map.put("item3", sContent);
		
		int nResult = bbs.write(map);
		System.out.println("Write : " + nResult);
		
		return "redirect:list";
		
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest req, Model model) {
		
		String sId = req.getParameter("id");
		int nResult = bbs.delete(sId);
		System.out.println("Delete : " + nResult);
		
		return "redirect:list";
		
	}
	
}
