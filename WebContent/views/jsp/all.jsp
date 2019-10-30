<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sudo</title>
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>
<br>
<sec:authorize access="hasRole('ADMIN')">
  only Admin can see!
</sec:authorize>
<br>
<sec:authorize access="hasRole('USER')">
  only USER can see!
</sec:authorize>
<br>

<sec:authorize access="hasRole('USER', 'ADMIN')">
  User or Admin
</sec:authorize>
<br>
  Everyone can see!
<br>
</body>
</html>