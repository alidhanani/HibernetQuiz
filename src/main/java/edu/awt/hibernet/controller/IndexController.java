package edu.awt.hibernet.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import edu.awt.hibernet.model.Option;
import edu.awt.hibernet.model.Question;
import edu.awt.hibernet.model.Selectoption;
import edu.awt.hibernet.service.DataSheetService;


@Controller
public class IndexController {
	
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSheetService service;
	
	static int currentQue = 0;
	static int num = 1;
	static int count = 1;
	static Queue<Integer> qLater = new LinkedList<Integer>();
	static int numShow = 1;
	static int regno = 0;
	static int questionNo = 1;
	static int questionNoLater = 1;
	Random rand = new Random();
	ArrayList<Integer> present = new ArrayList<>();
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String Index(ModelMap map) {
		present.clear();
		return "start";
	}
	
	@RequestMapping(value="/remaining", method=RequestMethod.GET)
	public String remain(ModelMap map) {
		if(qLater.isEmpty()) {
			return "end";
		}
		int getQuestionNo = qLater.remove();
		map.addAttribute("registration", regno);
		map.addAttribute("selectoption", service.getSelectOption(getQuestionNo));
		map.addAttribute("answer", service.getOptionByQNo());
		map.addAttribute("questionno", questionNoLater++);
		map.addAttribute("forLater", true);
		//map.addAttribute("value", numShow++);
		return "home";
	}
	
	@RequestMapping(value="/enter", method=RequestMethod.GET)
	public String forward(ModelMap map) {
	    num = rand.nextInt((10 - 1) + 1) + 1;
		while(present.contains(num)) {
			num = rand.nextInt((10 - 1) + 1) + 1;
		}
		present.add(num);
		count++;
		currentQue = num;
		map.addAttribute("registration", regno);
		map.addAttribute("question", service.getQuestionByNo(num));
		map.addAttribute("answer", service.getOptionByQNo());
		map.addAttribute("questionno", questionNo++);
		map.addAttribute("value", numShow++);
		if(count > 10) {
			return "redirect:/remaining";
		}
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		
		Question question = new Question();
		Option option = new Option();	
		Selectoption selectoption = new Selectoption();
		selectoption.setQuestion(question);
						
		
		session.getTransaction().commit();
		session.close();
		
		return "home";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String sendData(@RequestParam(required=false) Integer selectOption, @RequestParam(required=false) Integer sano) {
		int result = 0;
		if(selectOption == sano) 
			result = 1;
		else
			result = 0;
		
		service.addUserOption(regno, (num-1), selectOption, result);
		return "redirect:/enter";
	}
	
	@RequestMapping(value="/go", method=RequestMethod.POST)
	public String enter(@RequestParam(required=false) Integer regno) {
		if(service.searchRegno(regno) == null) {
			this.regno = regno;
			return "redirect:/enter";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/forQue", method=RequestMethod.GET)
	public String later(@RequestParam(required=false) Integer questionno) {
		if(questionno != null)
			qLater.add(questionno);
			//System.out.println(qLater.size());
		return "redirect:/enter";
	}
	
}
