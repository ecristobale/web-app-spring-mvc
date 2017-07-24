package com.eduardo.springdemo.service;

import java.util.List;

import com.eduardo.springdemo.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCustomers(String theSearch);

	public List<Customer> getCustomersAgeFiltered();

	public void deleteAllCustomers();
}
