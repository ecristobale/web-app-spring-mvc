package com.eduardo.springdemo.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

	@Override
	public List<Customer> searchCustomers(String theSearch) {
		
		// get hibernate current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Customer> theQuery = null;
		
		// retrieve customers that matches the search if theSearch isn't null
		if(theSearch != null && theSearch.trim().length() > 0) {
			// search is not case sensitive, that is why using lower in query
			theQuery = currentSession.createQuery(
					"from Customer where lower(firstName) like :theName or " +
					"lower(lastName) like :theName order by lastName", Customer.class);
			theQuery.setParameter("theName", "%" + theSearch.toLowerCase() + "%");
		} else {
			theQuery = currentSession.createQuery(
					"from Customer order by lastName", Customer.class);
		}
		
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}

	@Override
	public List<Customer> getCustomersAgeFiltered() {

		// get hibernate current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -18); // to get 18 before year we add -18
		Date oldDate = cal.getTime();
		
		// create query to get dates less than the oldDate (18 years before now) sorted by lastName
		Query<Customer> theQuery = currentSession.createQuery("from Customer where birthDate <= :oldDate "
				+ "order by lastName", Customer.class);
		theQuery.setParameter("oldDate", oldDate);
		
		// execute the query and get the result list
		List<Customer> customers = theQuery.getResultList();

		return customers;
	}

	@Override
	public void deleteAllCustomers() {
		
		// get hibernate current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete all customers in the DB
		currentSession.createQuery("delete from Customer").executeUpdate();
	}

}
