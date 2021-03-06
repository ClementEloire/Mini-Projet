<%-- 
    Document   : produitClient
    Created on : 4 déc. 2019, 14:35:24
    Author     : pedago
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
        <title>Visiteur</title>
    </head>
    <body>
       
        <form action="login" method="POST">
            <input type='submit' name='action' value='logout'>
        </form>
        <form action="ServletClient.java" method="POST">
            <select  name='CategorieClient' onchange='this.form.submit()'>
                <c:forEach var="CategorieClient" items="${CategoriesClient}">
                    <option value='${CategorieClient.getCode()}'
                            selected
                    >${CategorieClient.getLibelle()}</option>
                </c:forEach>
            </select>
        </form>
            <table border=2>
                 <tr> <th>Références</th> <th>Nom</th> <th>Fournisseur</th></tr>
                 <tbody>

                    <c:forEach items="${ProduitsClient}" var="u">
                        <tr>
                            <td>${u.getReference()}</td>
                            <td>${u.getNom()}</td>
                            <td>${u.getFournisseur()}</td>
                            <form action="ServletClient" method="POST">
                                <input type="hidden" name="Panier" value="${sessionScope.Panier}"/>
                                <input type="hidden" name="RefProd" value="${u.getReference()}"/>
                                <td><select name="Quantite">
                                        <%for(int i = 1; i <= 99 ; i++) {
                                            out.println("<option>"+i+"</option>");
                                        }%>
                                    </select></td>
                                    <td><input name="action" type="submit" value="Ajouter au panier"/></td>
                            </form>
                        </tr>
            </c:forEach>
            </table>
        <form action="ServletInfoClient" method="POST">    
            <input type='submit' name="action" value='Info Client'>
        </form>
        
        <form action="ServletBonCommande" method="POST">    
            <input type="hidden" name="CodeClient" value="${sessionScope.Client.code}"/>
            <input type='submit' name="action" value='Bon Commande'>s
        </form>
            
            <form action="ServletPanier" methode="POST">
                <input type="submit" name="action" value="Panier Client"/>
            </form>
        
    </body>
</html>
