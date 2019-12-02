<%-- 
    Document   : Visiteur
    Created on : 2 déc. 2019, 18:14:54
    Author     : Clément Eloire
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visiteur</title>
    </head>
    <body>
        <form action=\"ServletVisiteur\" id=\"listcategorie\" multiple="multiple">
            <select name=\"categorie\" form=\"categorie\">
                <option value="Categori">${categorie}</option>
            </select>
            <input type=\"submit\">
        </form>
    </body>
</html>
