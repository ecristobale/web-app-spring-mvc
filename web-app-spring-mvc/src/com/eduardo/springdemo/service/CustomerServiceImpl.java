package com.eduardo.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduardo.springdemo.dao.CustomerDAO;
import com.eduardo.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	// CustomerDAO injected
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}
}