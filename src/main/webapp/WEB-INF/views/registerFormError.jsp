<%--
  Created by IntelliJ IDEA.
  User: v
  Date: 03/02/16
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Register</title>
  <link rel="stylesheet" type="text/css"
        href="<c:url value="/resources/css/style.css" />" >
  <link rel="stylesheet"
        type="text/css"
        href="<c:url value="/resources/css/bootstrap.css" />">
</head>
<body>
  <div class="col-md-12 text-center">
    <h1>Register</h1>
      <label class="text-center has-error">You made mistake with completing form. </label> <br/>
      <label class="text-center text-left"> Rules: </label><br/>
      <label class="text-center text-left"> firstName have to between 2 and 30 characters and not missed/empty.</label><br/>
      <label class="text-center text-left"> lastName have to between 2 and 30 characters and not missed/empty.</label><br/>
      <label class="text-center text-left"> email not missed/empty.</label><br/>
      <label class="text-center text-left"> username have to between 5 and 16 characters and not missed/empty.</label><br/>
      <label class="text-center text-left"> password have to between 5 and 25 characters and not missed/empty.</label><br/>
  <form action="${pageContext.request.contextPath}/registration" method="POST">
      <label path="firstName" cssErrorClass="has-error">First Name:</label><input type="text" name="firstName" /><br/>
      <label path="lastName" cssErrorClass="has-error">Last Name:</label><input type="text" name="lastName" /><br/>
      <label path="email" cssErrorClass="has-error">Email: </label><input type="email" name="email" /><br/>
      <label path="username" cssErrorClass="has-error">Username: </label><input type="text" name="username" /><br/>
      <label path="password" cssErrorClass="has-error">Password: </label><input type="password" name="password" /><br/>
      <input type="submit" name="register" value="register" />
      <input type="submit" name="cancel" value="cancel" />
  </form>
  </div>
</body>
</html>
