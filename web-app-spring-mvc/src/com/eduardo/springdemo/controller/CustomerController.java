package com.eduardo.springdemo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eduardo.springdemo.entity.Customer;
import com.eduardo.springdemo.service.CustomerService;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// Inject the CustomerService
	@Autowired
	private CustomerService customerService;
	
	// Load the property file (colors.properties) into the Map, we will add it to the model
	@Value("#{colorOptions}")
	private Map<String,String> colorOptions;
	
	// add an initBinder to convert trim all input Strings
	// remove leading and trailing whitespace
	// resolve issue for our validation
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
			
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
			
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
		
		// Date
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		dateFormat.setLenient(false);
		
		dataBinder.registerCustomEditor(Date.class, "birthDate",
	            new CustomDateEditor(dateFormat, true));
	}

	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// get customers from the service
		List<Customer> theCustomers = customerService.getCustomers();
				
		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		// Add the color options to the model
		theModel.addAttribute("theColorOptions", colorOptions);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Customer customer = new Customer();
		
		// add one customer to the model for the form
		theModel.addAttribute("customer", customer);
		
		// Add the color options to the model
		theModel.addAttribute("theColorOptions", colorOptions);
		
		return "customer-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
									Model theModel) {
		
		// get the customer using our service
		Customer theCustomer = customerService.getCustomer(theId);
		
		// add the customer to the model to prepopulate the form with that data
		theModel.addAttribute("customer", theCustomer);
		
		// Add the color options to the model
		theModel.addAttribute("theColorOptions", colorOptions);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer theCustomer,
			BindingResult theBindingResult) {
		
		// printing for see the errors and creating custom messages System.out.println("" + theBindingResult);
		
		// if there are errors just return to the form
		if(theBindingResult.hasErrors()) {
			return "customer-form";
		}
		
		// save the customer using our service
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		// delete customer using our service
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
	
	@PostMapping("/search")
	public String searchCustomers(@RequestParam("theSearch") String theSearch,
								  Model theModel) {
		
		// search customers using our service based on the search
		List<Customer> theCustomers = customerService.searchCustomers(theSearch);
		
		// add these customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		// Add the color options to the model
		theModel.addAttribute("theColorOptions", colorOptions);
		
		return "list-customers";
	}
	
	@GetMapping("/filterByAge")
	public String filterByAge(Model theModel) {

		List<Customer> theCustomers = customerService.getCustomersAgeFiltered();

		// add these customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		// Add the color options to the model
		theModel.addAttribute("theColorOptions", colorOptions);
		
		return "list-customers";
	}
	
	@GetMapping("/deleteAll")
	public String deleteAllCustomers() {
		
		// delete customer using our service
		customerService.deleteAllCustomers();
		
		return "redirect:/customer/list";
	}
}
