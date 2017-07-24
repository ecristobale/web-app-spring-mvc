<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
	<title>404 not found</title>
	
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
			<h2>CRM - 404 not found</h2>
		</div>
	</div>
	
	<div id="container">
	
		<div id="content">
			
			<h3>The page requested is not found</h3> <br>
			<a href="${pageContext.request.contextPath}/customer/list">Go back</a>
			
		</div>
	</div>

</body>

</html>