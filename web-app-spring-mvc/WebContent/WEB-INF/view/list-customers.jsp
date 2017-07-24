<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>

<head>
	<title>List Customers</title>
	
	<!-- reference our style sheet -->

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
		
			<!-- Button for add customer -->
			<input type="button" value="Add Customer"
				   onclick="window.location.href='showFormForAdd'; return false"
				   class="add-button"
			/>
			
			<!-- Search box -->
			<form:form action="search" method="POST">
				Search customer: <input type="text" name="theSearch" />
				<input type="submit" value="Search" class="add-button" />
			</form:form>
			
			<!-- Button for filter customers -->
			<input type="button" value="Show age: +18"
				   onclick="window.location.href='filterByAge'; return false"
				   class="add-button"
			/>
		
			<!-- html table with customers -->
			<table>
				<tr>
					<td>First name</td>
					<td>Last name</td>
					<td>Email</td>
					<td>Coupons</td>
					<td>Birth date</td>
					<td>Color</td>
					<td>Action</td>
				</tr>
				
				<!-- loop over and print each customer -->
				<c:forEach var="tempCustomer" items="${customers}">
				
					<!-- Create a link with the customer id for Update action -->
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomer.id}"/>
					</c:url>
					
					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomer.id}"/>
					</c:url>
				
					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<td>${tempCustomer.discountCoupons}</td>
						<td><fmt:formatDate pattern="dd-MM-yyyy" value="${tempCustomer.birthDate}" /></td>
						<td>${theColorOptions.get(tempCustomer.color)}</td>
						
						<!-- Display the link for update -->
						<td>
							<a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}">Delete</a>
						</td>
					</tr>
				</c:forEach>
				
			</table>
				
		</div>
	
	</div>
	

</body>

</html>









