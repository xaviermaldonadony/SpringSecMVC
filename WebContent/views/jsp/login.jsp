<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="_menu.jsp"></jsp:include>
<img alt="logo" src ="${pageContext.request.contextPath}/images/logo.png">

  <form action='<spring:url value="/loginAction"/>'method="post" >
  
  <table>
  <tr>
	  <td> Username</td>
	  <td> <input type="text" name ="username"></td>
  
  </tr>
  
    <tr>
    <td> Password</td>
    <td> <input type="text" name ="password"></td>
  
  </tr>
  <tr>
    <td><input type="submit" value="submit" name="submit"></td>
  </tr>
  </table>
  </form>
  
</body>
</html>