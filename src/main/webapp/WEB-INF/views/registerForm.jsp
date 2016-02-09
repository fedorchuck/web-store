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

  <sf:form method="POST" commandName="user">
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
      <input type="submit" value="Register" />
  </sf:form>

</div>
</body>
</html>
