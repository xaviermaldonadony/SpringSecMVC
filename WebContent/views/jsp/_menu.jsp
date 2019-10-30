<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

  <div>
    <a href="${pageContext.request.contextPath}/home">Home</a>
    <a href="${pageContext.request.contextPath}/admin">Admin</a>
    <a href="${pageContext.request.contextPath}/subscriber">Subscriber</a>
    <a href="${pageContext.request.contextPath}/all">Audit</a>
    <a href="${pageContext.request.contextPath}/contactus">Contact Us</a>
    
    <c:if test="${pageContext.request.userPrincipal.name != null}">
      <a href="${pageContext.request.contextPath}/logout"> Contact</a>   
    </c:if>
   
  </div>

</body>
</html>