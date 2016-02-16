<%--
  Created by IntelliJ IDEA.
  User: v
  Date: 31/01/16
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
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
<h1>Catalog</h1>
<%--<c:out value="${commodity.name}" /> <c:out value="${commodity.cost}" /> <br/>
<c:out value="${commodity.manufacturer}" />
<c:out value="${commodity.characteristics}" /><br/>
<c:out value="${commodity.description}" /><br/>--%>
<h1>Goods</h1>
<%-- <ul class="goodsList">
       <c:forEach items="${goodsList}" var="commodity" >
            <li id="commodity_<c:out value="commodity.id"/>">
                <div class="name"><c:out value="${commodity.name}" /></div>
                <span class="manufacturer"><c:out value="${commodity.manufacturer}" /></span>
                <div>
                    <span class="characteristics"><c:out value="${commodity.characteristics}" /></span>
                    <span class="description"><c:out value="${commodity.description}" /></span>
                </div>
         /li>
        </c:forEach>
    </ul>
    <c:if test="${fn:length(goodsList) gt 20}">
        <hr />
        <s:url value="/goods?count=${nextCount}" var="more_url" />
        <a href="${more_url}">Show more</a>
    </c:if>--%>
<c:if test="${not empty lists}">
    <ul>
        <c:forEach var="listValue" items="${lists}">
            <li>${listValue}</li>
        </c:forEach>
    </ul>
</c:if>
</body>
</html>
