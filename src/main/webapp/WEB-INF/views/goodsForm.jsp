<%--
  Created by IntelliJ IDEA.
  User: v
  Date: 12/02/16
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
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
    <h1>add commodity</h1>
    <sf:form method="POST" commandName="commodity">
        <sf:errors path="*" element="div" cssClass="has-error" />

        <sf:label path="name" cssErrorClass="has-error">name:</sf:label>
        <sf:input path="name" cssErrorClass="has-error" /> <br/>
        <sf:label path="manufacturer" cssErrorClass="has-error">manufacturer:</sf:label>
        <sf:input path="manufacturer" cssErrorClass="has-error" /> <br/>
        <sf:label path="cost" cssErrorClass="has-error">cost:</sf:label>
        <sf:input path="cost" cssErrorClass="has-error" /> <br/>
        <sf:label path="characteristics" cssErrorClass="has-error">characteristics:</sf:label>
        <sf:textarea path="characteristics" cssErrorClass="has-error" /> <br/>
        <sf:label path="description" cssErrorClass="has-error">description:</sf:label>
        <sf:textarea path="description" cssErrorClass="has-error" /> <br/>

      <input type="submit" value="add" />
    </sf:form>
  </div>
</body>
</html>
