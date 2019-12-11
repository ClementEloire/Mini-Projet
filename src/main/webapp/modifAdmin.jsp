<%-- 
    Document   : modifAdmin
    Created on : 10 déc. 2019, 14:27:19
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
                 <tr> 
                    <th>Références</th> 
                    <th>Nom</th> 
                    <th>Fournisseur</th> 
                    <th>Quantité/unité</th> 
                    <th>Prix/unité</th> 
                    <th>Unités en Stock</th>
                    <th>Unités Commandées</th>
                    <th>Niveau de réaprovisionnement</th>
                    <th>Indisponible</th>
                    <th>Supprimer</th>
                    <th>Modifier</th>
                 </tr>
                 <tbody>

                    <c:forEach items="${Produits}" var="u">
                        <tr>
                    <form method='GET'>
                            <td><input type="text" name="idProduit" value=${u.getReference()} /></td>
                            <td>${u.getNom()}</td>
                            <td>${u.getFournisseur()}</td>
                            <td>${u.getQuantiteParUnite()}</td>
                            <td>${u.getprixUnitaire()}</td>
                            <td>${u.getUniteEnStock()}</td>
                            <td>${u.getUniteCommande()}</td>
                            <td>${u.getNiveauDeReaprovi()}</td>
                            <td>${u.getIndisponible()}</td>
                            <td><input type="submit" name="action" value="X" ></td>
                            <td><input type="submit" name="action" value="Modifier"/></td>
                    </form>
                        </tr>
                    </c:forEach>
    </table> 
    <input type="submit" name="action" value="Ajouter"/>
    </body>
</html>
