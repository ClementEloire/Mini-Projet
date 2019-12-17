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
        <title>Bon de commande</title>
    </head>
    <body>
        <c:forEach items="${BonCom}" var="u">
            Commande ref : ${u.getCommandeObj().getCommande()}</br>
            Montant total : ${u.getMontantTot()}€</br>
            <table>
                <tr>
                    <td>Nom</td>
                    <td>Quantite</td>
                    <td>Montant</td>
                </tr>
            <c:forEach items="${u.getLigne()}" var="l">
                <tr>
                    <td>${l.getProduit()}</td>
                    <td>${l.getQuantite()}</td>
                    <td>${l.getMontant()}€</td>
                </tr>
            </c:forEach>
            </table><br/>
        </c:forEach>
            
        
        <form action="ServletClient" method="POST">
            <input type="submit" value="Page produit"/>
        </form>
    </body>
</html>
