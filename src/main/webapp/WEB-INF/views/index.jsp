<%--
  Created by IntelliJ IDEA.
  User: v
  Date: 28/01/16
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
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
    <div class="container">
        <div class="row">
            <div class="col-md-2 text-right text-uppercase">
                <div class="col-lg-6">
                    <a href="<c:url value="/" />"><h4>web store</h4></a>
                </div>
                <div class="col-lg-6">
                    <a href="<c:url value="/catalog" />"><h4>catalog</h4></a>
                </div>
            </div>
            <div class="col-md-5 text-center">
                search bar
            </div>
            <div class="col-md-4">
                <div class="col-md-4 text-center">
                search button
                </div>
                <div class="col-md-8 text-lowercase text-center">
                    <div class="col-md-6">
                        <a href="<c:url value="/user/authorize" />">authorize</a>
                    </div>
                    <div class="col-md-6">
                        <a href="<c:url value="/user/register" />">register</a>
                    </div>
                </div>
            </div>
        </div>
        <a href="<c:url value="/goods/add" />">add goods</a> <br/>
        <a href="<c:url value="/goods/all" />">all goods</a>
    </div>
</body>
</html>
