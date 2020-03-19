<%--
    Document   : analyse
    Created on : Feb 14, 2020, 10:40:00 AM
    Author     : manohisoa
--%>

<%@page import="model.Patient"%>
<%@page import="model.ComposantTaux"%>
<%@page import="model.Analyse"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>chart</title>
    </head>
    <style>
        #myChartdiv{
            width:500px;
            height: 500px;
            background-color: white;
            display: inline-block;
        }
        #myChartdiv2{
            width:500px;
            height: 500px;
            background-color: white;
            display: inline-block;
            border-left: solid 1px grey;
        }
        #conteneur{

        }
    </style>
    <body>
        <script src="rata/chart.js"></script>
        <script src="rata/chartjs-plugin-draggable.min.js"></script>
        <div id="conteneur">
          <% Patient[] p = (Patient[]) request.getAttribute("patients"); %>
            <select name="patient" id="patient">
                <%for (int i = 0; i < p.length; i++) {%>
                    <option value="<%=p[i].get_id()%>"><%=p[i].getNom()%> <%=p[i].getPrenom()%></option>
                <%}%>
            </select>
            <button onclick="getAnalyseByPatient()">get Analyse</button>
           <label for="autreAnalyse"> analyses</label>
           <select name="autreAnalyse" id="analysePatient" >

           </select>
           <button onclick="voirAnalyse()">voir</button>
           <button onclick="diagnostic()">diagnostic</button>

        </div>
        <div id="pourcentage">
          <table border="1">
            <tr><th>Maladie</th><th>Pourcentage</th></tr>
          </table>
        </div>
           <div id = "myChartdiv">
               <canvas id = "myChart" >

                </canvas>
           </div>
               <div id="conteneur">
            <select name="patient" id="patient2">
                <%for (int i = 0; i < p.length; i++) {%>
                    <option value="<%=p[i].get_id()%>"><%=p[i].getNom()%> <%=p[i].getPrenom()%></option>
                <%}%>
            </select>
            <button onclick="getAnalyseByPatient2()">get Analyse</button>
           <label for="autreAnalyse"> analyses</label>
           <select name="autreAnalyse" id="analysePatient2" >

           </select>
           <button onclick="voirAnalyse2()">voir</button>

        </div>
            <div id = "myChartdiv2">
               <canvas id = "myChart2" >

                </canvas>
           </div>

            <script src="controleur.js">

            </script>
    </body>
</html>
