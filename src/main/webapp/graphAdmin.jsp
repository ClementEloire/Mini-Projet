<%-- 
    Document   : graphAdmin
    Created on : 10 d�c. 2019, 14:26:05
    Author     : Cl�ment Eloire
--%>
<html>
    <head>
        <title>Admin</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- On charge la biblioth�que JQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<!-- On charge le moteur de template mustache https://mustache.github.io/ -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/mustache.js/0.8.1/mustache.min.js"></script>
        <!-- On charge l'API Google -->
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
                    google.load("visualization", "1", {packages: ["corechart"]});
                    $(document).ready(// Ex�cut� � la fin du chargement de la page
                    function () {
                        // On remplit le <select> avec les �tats existants
                        fillCDateDSelector();
                        document.querySelector("select").addEventListener("change", function ()
                        {
                            fillCDateFSelector();
                            google.setOnLoadCallback(doAjaxClient);
                            google.setOnLoadCallback(doAjaxPays);
                            google.setOnLoadCallback(doAjaxCat);
                        });
                    });
			
                    function fillCDateDSelector() {
                        // On fait un appel AJAX pour chercher les �tats existants
                        $.ajax({
                            url: "ListDateServlet",
                            dataType: "json",
                            error: showError,
                            success: // La fonction qui traite les r�sultats
                            function(result) {
                                // Le code source du template est dans la page
                                var template = $('#dateDTemplate').html();
                                // On combine le template avec le r�sultat de la requ�te
                                var processedTemplate = Mustache.to_html(template, result);
                                // On affiche la liste des options dans le select
                                $('#dateD').html(processedTemplate);
                                // On initialise l'affichage des clients
                                fillCDateFSelector();
                            }
                        });
                    }
                                                                 
                    function fillCDateFSelector() {
                        var selectedDate = document.getElementById("dateD").value;
                        // On fait un appel AJAX pour chercher les �tats existants
                        $.ajax({
                            url: "ListDateSupeServelet",
                            dataType: "json",
                            data: { "dateD" : selectedDate },
                            error: showError,
                            success: // La fonction qui traite les r�sultats
                            function(result) {
                                // Le code source du template est dans la page
                                var template = $('#dateFTemplate').html();
                                // On combine le template avec le r�sultat de la requ�te
                                var processedTemplate = Mustache.to_html(template, { dateD: selectedDate, records: result.records });
                                // On affiche la liste des options dans le select
                                $('#dateF').html(processedTemplate);
                                doAjaxClient();
                                doAjaxCat();
                                doAjaxPays();
                            }
                        });
                    }
                                                                 
                        
                    // Afficher les ventes par client
                    function doAjaxClient() {
                        var selectedDateF = document.getElementById("dateF").value;
                        var selectedDateD = document.getElementById("dateD").value;
                        $.ajax({
                            url: "VenteParClientServlet?dadeD='"+selectedDateD+"'&dateF='"+selectedDateF+"'",
                            dataType: "json",
                            success: // La fonction qui traite les r�sultats
                            function (result) {
                                var chartData = [];
                                // On met le descriptif des donn�es
                                chartData.push(["Client", "Ventes"]);
                                for(var client in result.records) {
                                    chartData.push([client, result.records[client]]);
                                }
                                // On dessine le graphique
                                drawChartClient(chartData);
                            },
                            error: showError
                        });
                    }
                                                                    
                    function drawChartClient(dataArray) {
                        var data = google.visualization.arrayToDataTable(dataArray);
                        var options = {
                            title: 'Ventes par client',
                            is3D: false
                        };
                        var chart = new google.visualization.PieChart(document.getElementById('piechartClient'));
                        chart.draw(data, options);
                    }
                                                                    
                    function doAjaxPays() {
                        var selectedDateF = document.getElementById("dateF").value;
                        var selectedDateD = document.getElementById("dateD").value;
                        $.ajax({
                            url: "VenteParPaysServelet?dadeD='"+selectedDateD+"'&dateF='"+selectedDateF+"'",
                            dataType: "json",
                            success: // La fonction qui traite les r�sultats
                            function (result) {
                                var chartData = [];
                                // On met le descriptif des donn�es
                                chartData.push(["Pays", "Ventes"]);
                                for(var pays in result.records) {
                                    chartData.push([pays, result.records[pays]]);
                                }
                                // On dessine le graphique
                                drawChartPays(chartData);
                            },
                            error: showError
                        });
                    }
                    
                    function drawChartPays(dataArray) {
                        var data = google.visualization.arrayToDataTable(dataArray);
                        var options = {
                            title: 'Ventes par pays',
                            is3D: false
                        };
                        var chart = new google.visualization.PieChart(document.getElementById('piechartPays'));
                        chart.draw(data, options);
                    }
                                                                    
                    function doAjaxCat() {
                        var selectedDateF = document.getElementById("dateF").value;
                        var selectedDateD = document.getElementById("dateD").value;
                        $.ajax({
                            url: "VenteParCatServlet?dadeD='"+selectedDateD+"'&dateF='"+selectedDateF+"'",
                            dataType: "json",
                            success: // La fonction qui traite les r�sultats
                            function (result) {
                                var chartData = [];
                                // On met le descriptif des donn�es
                                chartData.push(["Client", "Ventes"]);
                                for(var client in result.records) {
                                    chartData.push([client, result.records[client]]);
                                }
                                // On dessine le graphique
                                drawChartCat(chartData);
                            },
                            error: showError
                        });
                    }
                                                                    
                    function drawChartCat(dataArray) {
                        var data = google.visualization.arrayToDataTable(dataArray);
                        var options = {
                            title: 'Ventes par client',
                            is3D: false
                        };
                        var chart = new google.visualization.PieChart(document.getElementById('piechartCat'));
                        chart.draw(data, options);
                    }
		
			
                    // Fonction qui traite les erreurs de la requ�te
                    function showError(xhr, status, message) {
                        alert("Erreur: " + status + " : " + message);
                    }
        </script>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <div>
            <br>
            <h1> Affichage des produit disponible : </h1>
            <h2>Veuillez selectionner par categorie.</h2>
        </div>
        
   
            <div id="erreur"></div>
            <form>
                <label for="dateD">Choix de la date de d�but : </label>
                <select id="dateD"></select>
            </form>
            <br>
            <form>
                <label for="dateF">Choix de la date de fin : </label>
                <select id="dateF"></select>
            </form>

            <!-- Le template qui sert � formatter la liste des clients r�sultats -->
            <script id="dateDTemplate" type="text/template">
            {{! Pour chaque �tat dans le tableau}}
            {{#records}}
                {{! Une option dans le select }}
                {{! le point repr�sente la valeur courante du tableau }}
                <OPTION VALUE="{{.}}">{{.}}</OPTION>
            {{/records}}
        </script>
        <script id="dateFTemplate" type="text/template">
            {{! Pour chaque �tat dans le tableau}}
            {{#records}}
                {{! Une option dans le select }}
                {{! le point repr�sente la valeur courante du tableau }}
                <OPTION VALUE="{{.}}">{{.}}</OPTION>
            {{/records}}
        </script>
         <div id="piechartClient" style="width: 900px; height: 500px;"></div>
         </br>
         <div id="piechartPays" style="width: 900px; height: 500px;"></div>
         </br>
         <div id="piechartCat" style="width: 900px; height: 500px;"></div>
        
         
    <a href="logout">Deconnection</a>
    <a  href="/Mini-Projet//ServletModifAdmin">Produit</a>
    </body>
</html>