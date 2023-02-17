package com.cakeon.springboot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cakeon.springboot.dao.ISimpleBbsDao;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class MyController {

	@Autowired
	private ISimpleBbsDao dao;
	
	@RequestMapping("/")
	public String root() throws Exception{
		return "redirect:list";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String userlistPage(Model model) {
		
		model.addAttribute("list", dao.listDao());
		return "list";
		
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view(HttpServletRequest req, Model model) {
		
		String sId = req.getParameter("id");
		
		model.addAttribute("dto", dao.viewDao(sId));
		return "view";
		
	}
	

	@RequestMapping("/writeForm")
	public String writeForm() {
		
		return "writeForm";
		
	}
	
	@RequestMapping("/write")
	public String write(Model model, HttpServletRequest req) {
		
		dao.writeDao(req.getParameter("writer"), 
								req.getParameter("title"), 
								req.getParameter("content"));
		
		return "redirect:list";
		
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest req, Model model) {
		
		dao.deleteDao(req.getParameter("id"));
		return "redirect:list";
		
	}
	
}
