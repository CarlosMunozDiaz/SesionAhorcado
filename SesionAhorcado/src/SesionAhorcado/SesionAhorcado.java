package SesionAhorcado;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class SesionAhorcado
 */
@WebServlet("/SesionAhorcado")
public class SesionAhorcado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String letra = "";
	String palabra = "";
	String palabraNormalizada = "";
	
	ArrayList<String> listaLetras = new ArrayList<String>();
	
	String[] palabraGuiones;
	
	String imagen = "";
	
	String frase = "";
	
	int numeroIntentos = 0;
	int numeroRestantes = 6;
       
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TreeMap<String,String> mapaPalabras = new TreeMap<String,String>(){{
			put("nombre1","Barcelona");
			put("nombre2","Madrid");
			put("nombre3","Valencia");
			put("nombre4","Sevilla");
			put("nombre5","Getafe");
			put("nombre6","Valladolid");
			put("nombre7","Granada");
			put("nombre8","Suances");
		}};
		
		//Se crea la sesión
		HttpSession laSesion= request.getSession(true);
		
		palabra = LogicaAhorcado.generaPalabra(mapaPalabras);
		palabraNormalizada = LogicaAhorcado.palabraNormalizada(palabra);
		listaLetras = LogicaAhorcado.generarLista(letra, numeroIntentos);
		
		//numeroRestantes = LogicaAhorcado.generaNumeroErrores(letra, palabraNormalizada, numeroRestantes);
		
		imagen = LogicaAhorcado.generaImagen(numeroRestantes);
		
		palabraGuiones = LogicaAhorcado.generarPalabraOculta(palabra, palabraNormalizada, listaLetras);
		
		
			
			
			if (request.getParameter("empezar") != null) {  // se ha recibido el parámetro empezar
				laSesion.invalidate();  // se inactiva la sesión
			} else {
				
				if (laSesion.getAttribute("letra") != null) {
					letra = (String) laSesion.getAttribute("letra");
				} else if (laSesion.getAttribute("palabra") != null) {
					palabra = (String) laSesion.getAttribute("palabra");
				} else if (laSesion.getAttribute("palabraNormalizada") != null) {
					palabraNormalizada = (String) laSesion.getAttribute("palabraNormalizada");
				} else if (laSesion.getAttribute("palabraGuiones") != null) {
					palabraGuiones = (String[]) request.getAttribute("palabraGuiones");
				} else if (laSesion.getAttribute("imagen") != null) {
					imagen = (String) request.getAttribute("imagen");
				} else if (laSesion.getAttribute("listaLetras") != null) {
					listaLetras = (ArrayList<String>) request.getAttribute("listaLetras");
				} else if (laSesion.getAttribute("numeroIntentos") != null) {
					numeroIntentos = (Integer) request.getAttribute("numeroIntentos");
				} else if (laSesion.getAttribute("numeroRestantes") != null) {
					numeroRestantes = (Integer) request.getAttribute("numeroRestantes");
				} else if (laSesion.getAttribute("frase") != null) {
					frase = (String) request.getAttribute("frase");
				}
							
			}
			
			request.setAttribute("letra", letra);
			request.setAttribute("listaLetras", listaLetras);
			request.setAttribute("frase", "");
			request.setAttribute("palabra", palabra);
			request.setAttribute("palabraNormalizada", palabraNormalizada);
			request.setAttribute("palabraGuiones", palabraGuiones);
			request.setAttribute("imagen", imagen);
			request.setAttribute("numeroIntentos", numeroIntentos);
			request.setAttribute("numeroRestantes", numeroRestantes);
			
			//Se enruta a la vista --> la ruta debe comenzar por /
			String vista = "/sesionahorcado.jsp";  
			RequestDispatcher view = request.getRequestDispatcher(vista);
			view.forward(request, response);
				
		}
			

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		letra = request.getParameter("caracter");
		letra = letra.toLowerCase();
		
		boolean esCaracter = Character.isLetter(letra.charAt(0));
		
		// se recupera la sesión
		HttpSession laSesion= request.getSession(false);  //carga la sessión si existe, devuelve null si no
		
		if(laSesion != null) {
			
			laSesion.setAttribute("letra", letra);
			laSesion.setAttribute("listaLetras", listaLetras);
			laSesion.setAttribute("frase", frase);
			laSesion.setAttribute("palabra", palabra);
			laSesion.setAttribute("palabraNormalizada", palabraNormalizada);
			laSesion.setAttribute("palabraGuiones", palabraGuiones);
			laSesion.setAttribute("imagen", imagen);
			laSesion.setAttribute("numeroIntentos", numeroIntentos);
			laSesion.setAttribute("numeroRestantes", numeroRestantes);
			
			if(letra.length() == 1 && esCaracter == true) {
				
				listaLetras = LogicaAhorcado.generarLista(letra, numeroIntentos);
				
				numeroRestantes = LogicaAhorcado.generaNumeroErrores(letra, palabraNormalizada, numeroRestantes);
				
				imagen = LogicaAhorcado.generaImagen(numeroRestantes);
				
				palabraGuiones = LogicaAhorcado.generarPalabraOculta(palabra, palabraNormalizada, listaLetras);
				
				if (laSesion.getAttribute("letra") != null) {
					letra = (String) laSesion.getAttribute("letra");
				} else if (laSesion.getAttribute("palabra") != null) {
					palabra = (String) laSesion.getAttribute("palabra");
				} else if (laSesion.getAttribute("palabraNormalizada") != null) {
					palabraNormalizada = (String) laSesion.getAttribute("palabraNormalizada");
				} else if (laSesion.getAttribute("palabraGuiones") != null) {
					palabraGuiones = (String[]) request.getAttribute("palabraGuiones");
				} else if (laSesion.getAttribute("imagen") != null) {
					imagen = (String) request.getAttribute("imagen");
				} else if (laSesion.getAttribute("listaLetras") != null) {
					listaLetras = (ArrayList<String>) request.getAttribute("listaLetras");
				} else if (laSesion.getAttribute("numeroIntentos") != null) {
					numeroIntentos = (Integer) request.getAttribute("numeroIntentos");
				} else if (laSesion.getAttribute("numeroRestantes") != null) {
					numeroRestantes = (Integer) request.getAttribute("numeroRestantes");
				} else if (laSesion.getAttribute("frase") != null) {
					frase = (String) request.getAttribute("frase");
				}			
				
				frase = "";
				request.setAttribute("frase", "");
				request.setAttribute("letra", letra);
					
			//Aquí debemos incluir si la letra ya está en la lista --> Crear un método
			} else if (letra.length() != 1){
				frase = "No has indicado una única letra";
				request.setAttribute("frase", frase);
				request.setAttribute("letra", "");
				
			} else if (esCaracter == false) {
				frase = "El carácter utilizado no es una letra";
				request.setAttribute("frase", frase);
				request.setAttribute("letra", "");
			} else {
				frase = "La letra indicada ya ha sido utilizada de forma previa";
				request.setAttribute("frase", frase);
				request.setAttribute("letra", "");
			}
			
			
			//Aquí introducimos todos los setAttributes a excepción de la frase
			request.setAttribute("palabra", palabra);
			request.setAttribute("palabraNormalizada", palabraNormalizada);
			request.setAttribute("listaLetras", listaLetras);
			request.setAttribute("palabraGuiones", palabraGuiones);
			request.setAttribute("imagen", imagen);
			request.setAttribute("numeroIntentos", numeroIntentos);
			request.setAttribute("numeroRestantes", numeroRestantes);
			
			//Se enruta a la vista --> la ruta debe comenzar por /
			String vista = "/sesionahorcado.jsp";  
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
			dispatcher.forward(request,response);
		
		
		}
		
	}

}
