<%--
  Created by IntelliJ IDEA.
  User: v
  Date: 01/02/16
  Time: 22:45
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
    <h1>Authorize</h1>
    <form action="${pageContext.request.contextPath}/authorize" method="post">
      Email: <input type="email" name="email" /><br/>
      Password: <input type="password" name="password" /><br/>
      <input type="submit" name="authorize" value="authorize" />
      <input type="submit" name="cancel" value="cancel" />
    </form>
  </div>
</body>
</html>
