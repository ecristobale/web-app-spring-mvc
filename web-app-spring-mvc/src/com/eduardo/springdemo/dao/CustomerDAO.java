package com.eduardo.springdemo.dao;

import java.util.List;

import com.eduardo.springdemo.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();
}