<%-- 
    Document   : ModifForm
    Created on : 27 déc. 2019, 23:54:18
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mise a jour</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    </head>
    <body style="background-color:#28695d">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/Acceuil.jsp">Acceuil</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="AjoutEtudJSP.jsp">Ajouter </a>
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
        <%   session.setAttribute("userId", Math.round(Float.parseFloat(request.getParameter("id")))); %>
        <%   session.setAttribute("Fname",request.getParameter("nom")); %>
        <%   session.setAttribute("Lname",request.getParameter("prenom")); %>
        <%   session.setAttribute("Classes",request.getParameter("classe")); %>
        <%   session.setAttribute("moy",request.getParameter("moy")); %>
        <%   session.setAttribute("abs",request.getParameter("abs")); %>

<br> <br> <br>
        <div style="padding-left:450px;">
            <form action="${pageContext.request.contextPath}/EtudServlet" method="POST" >
                <div  style="width: 600px; text-align:center;" ><h6><font color="white">Nom :</font> </h6>
                    <div style="padding:10px;" class="input-group mb-3">
                       <input name="nom" class="form-control" value="${Fname} " placeholder="Nom"/>
                    </div>
                    <div style="padding:10px;"><h6><font color="white">Prenom :</font> </h6>
                        <input name="prenom" class="form-control" value="${Lname}" placeholder="Prenom"/>
                    </div>
                
                    <div style="padding:10px;"><h6><font color="white">Classe :</font> </h6>
                        <input name="classe" class="form-control" value="${Classes}" placeholder="Classe"/>
                    </div>
                    <div style="padding:10px;"><h6><font color="white">Moyenne annuelle :</font> </h6>
                        <input name="moy" class="form-control" value="${moy}" placeholder="Moyenne annuelle"/>
                    </div>
                    <div style="padding:10px;"><h6><font color="white">Absenteisme :</font> </h6>
                        <input name="abs" class="form-control" value="${abs}" placeholder="Absenteisme"/>
                    </div>
                
                    <div style="padding:20px;text-align:center">
                        <input type="submit" value="submit" class="btn btn-primary"  name="UpdateForm" />
                    </div>
                </div>
            </form>
        </div>
        
    </body>
</html>
