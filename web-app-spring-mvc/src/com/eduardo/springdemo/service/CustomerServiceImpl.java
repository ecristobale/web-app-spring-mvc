package com.eduardo.springdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.eduardo.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Override
	public List<Customer> getCustomers() {

		List<Customer> customers = new ArrayList<Customer>(){{
			add(getFakeCustomer(1));
			add(getFakeCustomer(2));
			add(getFakeCustomer(3));
			add(getFakeCustomer(4));
		}};
		return customers;
	}

	protected Customer getFakeCustomer(int number) {
		Customer fakeCustomer = new Customer();
		fakeCustomer.setFirstName("John" + number);
		fakeCustomer.setLastName("Doe" + number);
		fakeCustomer.setEmail("johndoe@johndoe.com");
		return fakeCustomer;
	}

}
