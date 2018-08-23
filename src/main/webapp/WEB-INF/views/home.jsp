<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
	<input type="text" value="${registration}"/>
	<hr/>
	<h4>Q<c:out value="${questionno}"></c:out>)
	<c:out value="${selectoption.question.text}"></c:out> </h4>
	<form method="post" action="/save">
		<input type="hidden" value="${selectoption.question.cano}" name="sAns" />
		<c:forEach var="ans" items="${answer}" >
			<c:if test="${ans.qno == selectoption.question.qno}">
				<input type="radio" name="selectOption" value="${ans.ano}" /> <c:out value="${ans.text}"></c:out> <br/> 
			</c:if>
		</c:forEach>
		<a href="/forQue?questionno=${selectoption.question.qno}">Later</a>
		<input type="submit" value="Submit" />
	</form>
</body>
</html>