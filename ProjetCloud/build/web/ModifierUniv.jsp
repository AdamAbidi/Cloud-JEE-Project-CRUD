<%-- 
    Document   : ModifierJSP
    Created on : 24 déc. 2019, 00:45:46
    Author     : User
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifier universitaires</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body  style="background-image: url('sunset.jpg');background-repeat: no-repeat;background-size: 100% 100%;background-attachment: fixed;">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/Acceuil.jsp">Acceuil</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="AjouterUniv.jsp">Ajouter </a>
                </li>
                <li class="nav-item active">
                    <div class="nav-link">Modifier</div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="SuppUniv.jsp">Supprimer</a>
                </li>
                <li class="nav-item" style="padding-left: 1100px;">
                    <a class="nav-link"  href="About.jsp">About</a>
                </li>
            </ul> 
        </div>
    </nav> 
    <br><br><br><br>
    <h3 style="padding-left: 400px;"><font color="white">Filtres :</font></h3>
    <div align="center">
        
        <form action="${pageContext.request.contextPath}/UnivServlet" method="POST" >
           
        <div class="input-group" style="width:50%" align="center">
            <select class="custom-select" id="inputGroupSelect01" name="tri">
                <option value=""selected>Trier par :</option>
                
                <option value="absenteisme">Absenteisme</option>
            </select>
            <input type="text" class="form-control" placeholder="Nom" aria-label="Nom" aria-describedby="button-addon2" name="nom">
            <input type="text" class="form-control" placeholder="Prenom" aria-label="Prenom" aria-describedby="button-addon2" name="prenom">
            <input type="text" class="form-control" placeholder="Classe" aria-label="Classe" aria-describedby="button-addon2" name="classe">
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="submit" id="button-addon2" name="ModifFormUniv">Chercher</button>
            </div>
        </div>
        </form>
    </div>
    <br><br><br><br>
    <h3 style="padding-left: 400px;"><font color="white">Resultat de la recherche :</font></h3>
    <table class="table table-striped table-dark" style="width:50%" align="center">
        <thead>
            <tr>
                <th scope="col">Nom</th>
                <th scope="col">Prenom</th>
                <th scope="col">Classe</th>
                <th scope="col">Grade</th>
                <th scope="col">Absenteisme</th>
                <th scope="col">Modifier</th>
            </tr>
        </thead>
        <tbody>
            <%@ page import="java.util.*" %>
            <% List<String> list = (List<String>)session.getAttribute("UnivSearch"); %>
            <c:forEach var="i" begin="1" end="${fn:length(UnivSearch)}" step="6" >
            <tr>
            <th scope="row">${UnivSearch[i]}</th>
            <td>${UnivSearch[i+1]}</td>
            
            <td>${UnivSearch[i+2]}</td>
           
            <td>${UnivSearch[i+3]}</td>
            <td>${UnivSearch[i+4]} %</td>
            <td><a href="ModifierFormUniv.jsp?id=${UnivSearch[i-1]}&nom=${UnivSearch[i]}&prenom=${UnivSearch[i+1]}&classe=${UnivSearch[i+2]}&grade=${UnivSearch[i+3]}&abs=${UnivSearch[i+4]}">Modifier</a></td>
            </tr>
            </c:forEach>
           
        </tbody>
    </table>
             <style>
.alert {
  padding: 20px;
  background-color: #f44336;
  color: white;
  opacity: 1;
  transition: opacity 0.6s;
  margin-bottom: 15px;
  margin-left: 80px;
  margin-right: 80px;
}

.alert.success {background-color: #4CAF50;}
.alert.info {background-color: #2196F3;}
.alert.warning {background-color: #ff9800;}

.closebtn {
  margin-left: 15px;
  color: white;
  font-weight: bold;
  float: right;
  font-size: 22px;
  line-height: 20px;
  cursor: pointer;
  transition: 0.3s;
}

.closebtn:hover {
  color: black;
}
    </style>
${ModifUniv} 


    <script>
var close = document.getElementsByClassName("closebtn");
var i;

for (i = 0; i < close.length; i++) {
  close[i].onclick = function(){
    var div = this.parentElement;
    div.style.opacity = "0";
    setTimeout(function(){ div.style.display = "none"; }, 600);
  }
}
    </script>


    </body>
</html>
