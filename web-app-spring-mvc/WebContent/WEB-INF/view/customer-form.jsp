<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<title>Save Customer</title>
	
	<link type="text/css" 
		   rel="stylesheet"
		   href="${pageContext.request.contextPath}/resources/css/style.css" >
		   
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Management</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Save Customer</h3>
		
		<form:form action="saveCustomer" modelAttribute="customer" method="POST">
		
			<!-- For update is needed to show the customer id -->
			<form:hidden path="id"/>
			
			<table>
				<tbody>
					<tr>
						<td><label>First name (*):</label></td>
						<td><form:input path="firstName"/></td>
						<td><form:errors path="firstName" cssClass="error"/></td>
					</tr>
					<tr>
						<td style="width: 120px;"><label>Last name (*):</label></td>
						<td><form:input path="lastName"/></td>
						<td><form:errors path="lastName" cssClass="error"/></td>
						
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><form:input path="email"/></td>
					</tr>
					<tr>
						<td><label>Coupons (*):</label></td>
						<td><form:input path="discountCoupons"/></td>
						<td><form:errors path="discountCoupons" cssClass="error"/></td>
					</tr>
					<tr>
						<td><label>Birth i.e. (31-12-1998):</label></td>
						<td><form:input path="birthDate"/></td>
						<td><form:errors path="birthDate" cssClass="error"/></td>
					</tr>
					<tr>
						<td><label>Color:</label></td>
						<td><form:select path="color">
								<form:options items="${theColorOptions}" />
							</form:select></td>
						
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save"/></td>
					</tr>
				</tbody>
			</table>
		</form:form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/customer/list">Back to list</a>
		</p>
	</div>

</body>
</html>