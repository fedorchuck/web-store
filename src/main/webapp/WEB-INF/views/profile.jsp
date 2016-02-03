<%--
  Created by IntelliJ IDEA.
  User: v
  Date: 28/01/16
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
  <title>web store</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />" >
</head>
<body>
<h1>Your Profile</h1>
<c:out value="${user.username}" /><br/>
<c:out value="${user.firstName}" /> <c:out value="${user.lastName}" /><br/>
<c:out value="${user.email}" />
</body>
</html>

