package com.eduardo.springdemo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/customer")
public class CustomerController {

	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// Create one message
		String message = "Hello World from SpringMVC + Maven + Git";
				
		// add the customers to the model
		theModel.addAttribute("message", message);
		
		return "list-customers";
	}
}
