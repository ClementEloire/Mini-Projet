<%-- 
    Document   : panierClient
    Created on : 4 déc. 2019, 14:34:08
    Author     : Alexis Jalabert
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <style>
				.table
				{
					display:table;
					border-collapse:separate;
					border-spacing:2px;
				}
				.thead
				{
					display:table-header-group;
					color:white;
					font-weight:bold;
					background-color:grey;
				}
				.tbody
				{
					display:table-row-group;
				}
				.tr
				{
					display:table-row;
				}
				.td
				{
					display:table-cell;
					border:1px solid black;
					padding:1px;
				}			
		</style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panier</title>
    </head>
    <body>
        <table border=2>
            <tr> <th>Références</th> <th>Nom</th> <th>Quantite</th> <th>Prix</th> <th></th></tr>
            <c:forEach items="${Produit}" var="u">
                <tr>
                    <td>${u.getRef()}</td>
                    <td>${u.getNom()}</td>
                    <td>${u.getQuantite()}</td>
                    <td>${u.getPrix()}</td>
                    <form action="ServletPanier" method="POST">
                        <input type="hidden" name="Qte" value="${u.getQuantite()}"/>
                        <input type="hidden" name="Ref" value="${u.getRef()}"/>
                        <td><input type="submit" name="action" value="Supprimer"/></td>
                    </form>
                </tr>
            </c:forEach>
        </table>
        <form action="ServletPanier" method="POST">
            <input type="submit" name="action" value="Valider"/>
        </form>
        <form action="ServletClient" method="POST">
            <input type="submit" value="Page produit"/>
        </form>
    </body>
</html>
