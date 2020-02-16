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
    <body style="background-image: url('flowers.jpg');background-repeat: no-repeat;background-size: 100% 100%;background-attachment: fixed;">
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
        <%   session.setAttribute("userId", Math.round(Float.parseFloat(request.getParameter("id")))); %>
        <%   session.setAttribute("Fname",request.getParameter("nom")); %>
        <%   session.setAttribute("Lname",request.getParameter("prenom")); %>
        <%   session.setAttribute("fonction",request.getParameter("fonction")); %>

<br> <br> <br>
        <div style="padding-left:450px;">
            <form action="${pageContext.request.contextPath}/FoncServlet" method="POST" >
                <div  style="width: 600px; text-align:center;" ><h6><font color="white">Nom :</font> </h6>
                    <div style="padding:10px;" class="input-group mb-3">
                       <input name="nom" class="form-control" value="${Fname} " placeholder="Nom"/>
                    </div>
                    <div style="padding:10px;"><h6><font color="white">Prenom :</font> </h6>
                        <input name="prenom" class="form-control" value="${Lname}" placeholder="Prenom"/>
                    </div>
                    <div style="padding:10px;"><h6><font color="white">Fonction :</font> </h6>
                        <input name="fonction" class="form-control" value="${fonction}" placeholder="Fonction"/>
                    </div>
                    
                
                    <div style="padding:20px;text-align:center">
                        <input type="submit" value="submit" class="btn btn-primary"  name="UpdateFormFonc" />
                    </div>
                </div>
            </form>
        </div>
        
    </body>
</html>
