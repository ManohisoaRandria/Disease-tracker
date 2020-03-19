<%-- 
    Document   : patients
    Created on : Feb 14, 2020, 10:45:36 AM
    Author     : manohisoa
--%>

<%@page import="model.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Patient[] p = (Patient[]) request.getAttribute("patients"); %>
        <form method="get" action="test">
            <select name="patient" >
                <%for (int i = 0; i < p.length; i++) {%>
                <option value="<%=p[i].get_id()%>"><%=p[i].getNom()%> <%=p[i].getPrenom()%></option>
             
                <%}%>
            </select>

            <input name="getAnalyse" type="submit" value="voir">
        </form>

    </body>
</html>
