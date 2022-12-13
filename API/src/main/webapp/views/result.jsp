<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
	String joke = (String) request.getAttribute("joke");
	out.print("<h1>" + joke + "</h1>");
	%>

<a href="ApiController">Get me another joke</a>

</body>
</html>