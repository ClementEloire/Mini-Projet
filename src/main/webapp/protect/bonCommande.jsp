<%-- 
    Document   : bonCommande
    Created on : 13 déc. 2019, 21:50:59
    Author     : Alexis Jalabert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bon de commande</title>
    </head>
    <body>
        <c:forEach items="${sessionScope.Commande}" var="u">
            Référence commande : ${u}<br/>
            <c:forEach items="${sessionScope.Ligne.get()}" var="v">
                
            <c:forEach>
        </c:forEach>
    </body>
</html>
