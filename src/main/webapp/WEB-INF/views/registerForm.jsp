<%--
  Created by IntelliJ IDEA.
  User: v
  Date: 28/01/16
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<html>
<head>
  <title>web store</title>
  <link rel="stylesheet" type="text/css"
        href="<c:url value="/resources/css/style.css" />" >
  <link rel="stylesheet"
        type="text/css"
        href="<c:url value="/resources/css/bootstrap.css" />">
</head>
<body>
<div class="col-md-12 text-center">
  <h1>Register</h1>
    <label class="text-center">Rules: </label>
    <label class="text-center text-left"> Rules: </label><br/>
    <label class="text-center text-left"> firstName have to between 2 and 30 characters and not missed/empty.</label><br/>
    <label class="text-center text-left"> lastName have to between 2 and 30 characters and not missed/empty.</label><br/>
    <label class="text-center text-left"> email not missed/empty.</label><br/>
    <label class="text-center text-left"> username have to between 5 and 16 characters and not missed/empty.</label><br/>
    <label class="text-center text-left"> password have to between 5 and 25 characters and not missed/empty.</label><br/>
  <%--<sf:form method="POST" commandName="/user"> &lt;%&ndash;action="/web-store/user/register/" commandName="user"&ndash;%&gt;
    <sf:errors path="*" element="div" cssClass="has-error" />
    <sf:label path="firstName" cssErrorClass="has-error">First Name:</sf:label>
                  <sf:input path="firstName" cssErrorClass="has-error" /> <br/>
    <sf:label path="lastName" cssErrorClass="has-error">Last Name:</sf:label>
                  <sf:input path="lastName" cssErrorClass="has-error" /> <br/>
    <sf:label path="email" cssErrorClass="has-error">Email:</sf:label>
                  <sf:input path="email" cssErrorClass="has-error" /> <br/>
    <sf:label path="username" cssErrorClass="has-error">Username:</sf:label>
                  <sf:input path="username" cssErrorClass="has-error" /> <br/>
    <sf:label path="password" cssErrorClass="has-error">Password:</sf:label>
                  <sf:input path="password" cssErrorClass="has-error" /> <br/>
  </sf:form>--%>
   <%-- <sf:form action="/web-store/user/register" method="POST">
        First Name: <input type="text" name="firstName" /><br/>
        Last Name: <input type="text" name="lastName" /><br/>
        Email: <input type="email" name="email" /><br/>
        Username: <input type="text" name="username" /><br/>
        Password: <input type="password" name="password" /><br/>
        <input type="submit" value="register" />
        <input type="submit" value="cancel" />
    </sf:form>--%>
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