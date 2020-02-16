<%-- 
    Document   : About
    Created on : 23 déc. 2019, 23:28:25
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
body {
  font-family: Arial, Helvetica, sans-serif;
  overflow: hidden;

}

.flip-card {
  background-color: transparent;
  width: 300px;
  height: 300px;
  perspective: 1000px;
}

.flip-card-inner {
  position: relative;
  width: 100%;
  height: 100%;
  text-align: center;
  transition: transform 0.6s;
  transform-style: preserve-3d;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
}

.flip-card:hover .flip-card-inner {
  transform: rotateY(180deg);
}

.flip-card-front, .flip-card-back {
  position: absolute;
  width: 100%;
  height: 100%;
  backface-visibility: hidden;
}

.flip-card-front {
  background-color: #bbb;
  color: black;
}

.flip-card-back {
  background-color: #2980b9;
  color: white;
  transform: rotateY(180deg);
}


</style>
</head>
<body style="background-image: url('success.jpg');background-repeat: no-repeat;background-size: 100% 100%;background-attachment: fixed;">
     
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/Acceuil.jsp">Acceuil</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav mr-auto">
                
                <li class="nav-item active" style="padding-left: 1300px;">
                    <div class="nav-link" >About</div>
                </li>
            </ul> 
        </div>
    </nav> 
            <br> <br> <br>
    <div class="flip-card" style="transform: translate(100px, 0px);">
        <div class="flip-card-inner">
            <div class="flip-card-front" align="left">
                <img src="adam.jpg" alt="Avatar" style="width:300px;height:300px;">
            </div> 
            <div class="flip-card-back" style="background-color: #2f5244;">
                <h1>Adam Abidi</h1> 
                <p>IF5</p> 
                <p>ISEM</p>
            </div>
        </div>
    </div>
    <div class="flip-card" style="transform: translate(600px, -300px);">
        <div class="flip-card-inner">
            <div class="flip-card-front" align="left">
                <img src="chaieb.jpg" alt="Avatar" style="width:300px;height:300px;">
            </div>
            <div class="flip-card-back">
                <h1>Souha Chaieb</h1> 
                <p>IF5</p> 
                <p>ISEM</p>
            </div>
        </div>
    </div>
    <div class="flip-card" style="transform: translate(1100px, -600px);">
        <div class="flip-card-inner">
            <div class="flip-card-front" align="left">
                <img src="mokni.jpg" alt="Avatar" style="width:300px;height:300px;">
            </div>
            <div class="flip-card-back">
                <h1>Souha Mokni</h1> 
                    <p>IF5</p> 
                    <p>IRSA</p>
            </div>
        </div>
    </div>

</body>