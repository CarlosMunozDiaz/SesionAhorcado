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
	
	String frase = (String) request.getAttribute("frase");
	
	Integer encontradaPalabra = (Integer) request.getAttribute("encontradaPalabra");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ahorcado's Game</title>
<link href="Estilos/estilosAhorcado.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%if (numeroRestantes == 0){ %>
		<img src="<%=imagen%>">
		<h3 class="rojoTitulo">¡Has perdido! Vuelve a intentarlo</h3>
	<%} else if (encontradaPalabra == 0) {%>
		<img src="<%=imagen%>">
		<h3>¡Has ganado! Vuelve a intentarlo</h3>
	<%} else {%>
	<h1>Juego del Ahorcado</h1>
	<h2>La letra seleccionada es: <%=letra%></h2>
	<h3 class="rojoTitulo"><%=frase%></h3>
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
	<%} %>
	<div id="boton">
		<a href="SesionAhorcado?empezar">Volver a jugar</a>
	</div>
</body>
</html>