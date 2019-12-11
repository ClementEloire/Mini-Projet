<%-- 
    Document   : infoClient
    Created on : 4 déc. 2019, 14:18:57
    Author     : Alexis Jalabert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Informations Personnelles</title>
    </head>
    <body>
        <form method="POST" action="ServletInfoClient">
            Nom : <input name="Contact" value="${sessionScope.Client.contact}"/></br>
            Societe : <input name="Societe" value="${sessionScope.Client.societe}"/></br>
            Fonction : <input name="Fonction" value="${sessionScope.Client.fonction}"/></br>
            Adresse : <input name="Adresse" value="${sessionScope.Client.adresse}"/></br>
            Ville : <input name="Ville" value="${sessionScope.Client.ville}"/></br>
            Region : <input name="Region" value="${sessionScope.Client.region}"/></br>
            Code Postal : <input name="CodePostal" value="${sessionScope.Client.getCodePostal()}"/></br>
            Pays : <input name="Pays" value="${sessionScope.Client.pays}"/></br>
            Telephone : <input name="Tel" value="${sessionScope.Client.tel}"/></br>
            Fax : <input name="Fax" value="${sessionScope.Client.fax}"/></br>
            <input name="action" value="update" type="submit"/>
        </form>
    </body>
</html>
