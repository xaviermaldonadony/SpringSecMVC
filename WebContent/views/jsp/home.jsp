<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="_menu.jsp"></jsp:include>

welcome${user.name}
<br>
${user.username}
<br>
  Tis is an authenticated home page
</body>
</html>