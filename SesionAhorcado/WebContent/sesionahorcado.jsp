<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
<%
	String letra = (String) request.getAttribute("letra");
	String imagen = (String) request.getAttribute("imagen");
	String palabra = (String) request.getAttribute("palabra");
	String[] palabraGuiones = (String[]) request.getAttribute("palabraGuiones");
	ArrayList<String> listaLetras = (ArrayList<String>) request.getAttribute("listaLetras");
	
	Integer numeroIntentos = (Integer) request.getAttribute("numeroIntentos");
	Integer numeroRestantes = (Integer) request.getAttribute("numeroRestantes");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ahorcado's Game</title>
<link href="Estilos/estilosAhorcado.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1>Juego del Ahorcado</h1>
	<h2>La letra seleccionado es: <%=letra%></h2>
	<img src="<%=imagen%>">
	<h2>La palabra sería: <%=palabra%></h2>
	<h2>La palabra en guiones:
	<%for(int i = 0; i < palabraGuiones.length; i++){ %>
		<%=palabraGuiones[i] %>
	<%} %>
	</h2>
	<form action="SesionAhorcado" method="post">
	Elige letra:
	<input type="text" name="caracter">
	<button type="submit">Enviar letra</button>
	</form>
	<div id="informacion">
		<h3>Última letra recibida: <%=letra%></h3>
		<h3>Letras ya probadas:
		<%for(int i = 0; i < listaLetras.size(); i++){ %>
			<%=listaLetras.get(i) %>
		<%} %>
		</h3>
		<h3>Número de intentos: <%=numeroIntentos%></h3>
		<h3>Número de intentos restantes: <%=numeroRestantes%></h3>
	</div>
	<div id="boton">
		<a href="SesionAhorcado.java?empezar">Volver a jugar</a>
	</div>
</body>
</html>