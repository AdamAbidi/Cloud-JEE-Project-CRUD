<%-- 
    Document   : AjoutEtudJSP
    Created on : 21 déc. 2019, 12:45:09
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter universitaire</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body style="background-image: url('newyork.PNG');background-repeat: no-repeat;background-size: 100% 100%;background-attachment: fixed;">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/Acceuil.jsp">Acceuil</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <div class="nav-link">Ajouter</div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ModifierUniv.jsp">Modifier </a>
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
            <br><br><br><br><br>
        <div style="padding-left:450px;">
        <form action="${pageContext.request.contextPath}/UnivServlet" method="POST" >
            <div  style="width: 600px; text-align:center;" >
                <div style="padding:10px;" class="input-group mb-3">
                    <input name="nom" class="form-control" placeholder="Nom"/>
                </div>
                <div style="padding:10px;">
                    <input name="prenom" class="form-control" placeholder="Prenom"/>
                </div>
                <div style="padding:10px;">
                   <input name="age" class="form-control" placeholder="Age"/>
                </div>
                <div style="padding:10px;">
                   <input name="grade" class="form-control" placeholder="grade"/>
                </div>
                <div style="padding:10px;">
                    <input name="classe" class="form-control" placeholder="Classe"/>
                </div>
                <div style="padding:10px;">
                    <input name="abs" class="form-control" placeholder="pourcentage Absenteimse"/>
                </div>
                
                <div style="padding:20px;text-align:center">
                    <input type="submit" value="Submit" class="btn btn-primary"  name="AjoutFormUniv" />
                </div>
            </div>
        </form>
    </div>
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
${AjoutProf} 

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
