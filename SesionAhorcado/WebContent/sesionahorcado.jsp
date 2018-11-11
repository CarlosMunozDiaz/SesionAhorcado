<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String letra = (String) request.getAttribute("letra");
	String imagen = (String) request.getAttribute("imagen");
	String palabra = (String) request.getAttribute("palabra");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ahorcado's Game</title>
</head>
<body>
	<h1>Juego del Ahorcado</h1>
	<h2>La letra seleccionado es: <%=letra%></h2>
	<img src="<%=imagen%>">
	<h2>La palabra sería: <%=palabra%></h2>
	<h2>La palabra en guiones: Aquí recorreríamos el array</h2>
	<form action="SesionAhorcado" method="post">
	Elige letra:
	<input type="text" name="caracter">
	<button type="submit">Enviar letra</button>
	</form>
	<div>
	
	</div>
	<div>
		<a href="SesionAhorcado.java?empezar">Volver a jugar</a>
	</div>

</body>
</html>