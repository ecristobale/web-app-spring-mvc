package com.eduardo.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eduardo.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	// Inject the session factory configured in spring-mvc-servlet.xml
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		
		// get hibernate current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query sorted by last name: call the table with the names of my Customer entity class
		Query<Customer> theQuery =
				currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		// execute the query and get the result list
		List<Customer> customers = theQuery.getResultList();

		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// get hibernate current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save or update the customer into the DB
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		
		// get hibernate current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// retrieve customer from DB using PK
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {

		// get hibernate current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete customer from DB creating a Query and using the PK
		Query theQuery = currentSession.createQuery(
				"delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
	}

}
