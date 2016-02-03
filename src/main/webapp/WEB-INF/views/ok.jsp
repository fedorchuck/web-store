<%--
  Created by IntelliJ IDEA.
  User: v
  Date: 03/02/16
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>web store</title>
  <link rel="stylesheet"
        type="text/css"
        href="<c:url value="/resources/css/style.css" />" >
  <link rel="stylesheet"
        type="text/css"
        href="<c:url value="/resources/css/bootstrap.css" />">
</head>
<body>
<div class="col-md-12 text-center">
  <h1>OK</h1>
  <form action="${pageContext.request.contextPath}/ok" method="post">
    <input type="submit" name="ok" value="ok" />
  </form>
</div>
</body>
</html>
