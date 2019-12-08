<%-- 
    Document   : Visiteur
    Created on : 2 déc. 2019, 18:14:54
    Author     : Clément Eloire
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
        
        <form>
            <select name='Categorie' onchange='this.form.submit()'>
                <c:forEach var="Categorie" items="${Categories}">
                    <option value='${Categorie.getCode()}'
                            selected
                    >${Categorie.getLibelle()}</option>
                </c:forEach>
            </select>
        <input type='submit'>
        </form>
            <table border=2>
                 <tr> <th>Références</th> <th>Nom</th> <th>Fournisseur</th> </tr>
                 <tbody>

                    <c:forEach items="${Produits}" var="u">
                        <tr>
                            <td>${u.getReference()}</td>
                            <td>${u.getNom()}</td>
                            <td>${u.getFournisseur()}</td>
                        </tr>
            </c:forEach>
    </table>   
    <a href="/Mini-Projet/login">Log In</a>
    </body>
</html>
