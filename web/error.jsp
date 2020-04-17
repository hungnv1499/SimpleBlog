<%-- 
    Document   : error
    Created on : Jan 14, 2020, 12:56:16 AM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p> <font color="red"> ${requestScope.ERROR}</font></p>
            <c:url var="homeLink" value="index.jsp">

        </c:url>
        <a href="${homeLink}">Go home</a>
    </body>
</html>
