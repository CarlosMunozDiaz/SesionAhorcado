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
import java.net.URLDecoder;


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
	
	boolean encontradaLetra = false;
	
	int encontradaPalabra = 1;
       
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
			put("nombre9","Espa�a");
			put("nombre10","M�laga");
		}};
		
		try {
			
		//Se crea la sesi�n
		HttpSession laSesion= request.getSession(true);
		
		if (request.getParameter("empezar") != null) {  // se ha recibido el par�metro empezar
			laSesion.invalidate();  // se inactiva la sesi�n
			letra = "";
			listaLetras.clear();
			numeroIntentos = 0;
			numeroRestantes = 6;
			encontradaLetra = false;
			encontradaPalabra = 1;
						
		}
		
		palabra = LogicaAhorcado.generaPalabra(mapaPalabras);
		palabraNormalizada = LogicaAhorcado.palabraNormalizada(palabra);
		
		listaLetras = LogicaAhorcado.generarLista(letra, listaLetras);
		
		imagen = LogicaAhorcado.generaImagen(numeroRestantes);
		
		palabraGuiones = LogicaAhorcado.generarPalabraOculta(palabra, palabraNormalizada, listaLetras);
									
			request.setAttribute("letra", letra);
			request.setAttribute("listaLetras", listaLetras);
			request.setAttribute("frase", "");
			request.setAttribute("palabra", palabra);
			request.setAttribute("palabraNormalizada", palabraNormalizada);
			request.setAttribute("palabraGuiones", palabraGuiones);
			request.setAttribute("imagen", imagen);
			request.setAttribute("numeroIntentos", numeroIntentos);
			request.setAttribute("numeroRestantes", numeroRestantes);
			
			request.setAttribute("encontradaPalabra", encontradaPalabra);
			
			//Se enruta a la vista --> la ruta debe comenzar por /
			String vista = "/sesionahorcado.jsp";  
			RequestDispatcher view = request.getRequestDispatcher(vista);
			view.forward(request, response);
		
		//Capturar la excepci�n de forma apropiada (como vimos hace dos semanas)
		} catch (Exception e) {
			//out.println("Se produce una excepci�n <br />");
            //out.println(e.getMessage());
		}
				
		}
			
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getCharacterEncoding() == null) {
		    request.setCharacterEncoding("UTF-8");
		}
		letra = request.getParameter("caracter");
		letra = letra.toLowerCase();
				 
		boolean esCaracter = Character.isLetter(letra.charAt(0));
		
		try {
		
		// se recupera la sesi�n
		HttpSession laSesion= request.getSession(false);  //carga la sessi�n si existe, devuelve null si no
		
		if(laSesion != null) {
			
			encontradaLetra = LogicaAhorcado.letraUtilizada(letra, listaLetras);
			
			/*laSesion.setAttribute("letra", letra);
			laSesion.setAttribute("listaLetras", listaLetras);
			laSesion.setAttribute("frase", frase);
			laSesion.setAttribute("palabra", palabra);
			laSesion.setAttribute("palabraNormalizada", palabraNormalizada);
			laSesion.setAttribute("palabraGuiones", palabraGuiones);
			laSesion.setAttribute("imagen", imagen);
			laSesion.setAttribute("numeroIntentos", numeroIntentos);
			laSesion.setAttribute("numeroRestantes", numeroRestantes);
			laSesion.setAttribute("encontradaPalabra", encontradaPalabra);*/
												
			if (laSesion.getAttribute("letra") != null) {
				letra = (String) laSesion.getAttribute("letra");
			} else if (laSesion.getAttribute("palabra") != null) {
				palabra = (String) laSesion.getAttribute("palabra");
			} else if (laSesion.getAttribute("palabraNormalizada") != null) {
				palabraNormalizada = (String) laSesion.getAttribute("palabraNormalizada");
			} else if (laSesion.getAttribute("listaLetras") != null) {
				listaLetras = (ArrayList<String>) request.getAttribute("listaLetras");
			} else if (laSesion.getAttribute("imagen") != null) {
				imagen = (String) request.getAttribute("imagen");
			} else if (laSesion.getAttribute("palabraGuiones") != null) {
				palabraGuiones = (String[]) request.getAttribute("palabraGuiones");
			} else if (laSesion.getAttribute("numeroIntentos") != null) {
				numeroIntentos = (Integer) request.getAttribute("numeroIntentos");
			} else if (laSesion.getAttribute("numeroRestantes") != null) {
				numeroRestantes = (Integer) request.getAttribute("numeroRestantes");
			} else if (laSesion.getAttribute("frase") != null) {
				frase = (String) request.getAttribute("frase");
			} else if (laSesion.getAttribute("encontradaPalabra") != null) {
				encontradaPalabra = (Integer) request.getAttribute("encontradaPalabra");
			}
			
			if(letra.length() == 1) {				
				
				if (encontradaLetra == true) {
					
					frase = "La letra indicada ya ha sido utilizada de forma previa";
					request.setAttribute("frase", frase);
					request.setAttribute("letra", "-");
					
				} else {
					
					listaLetras = LogicaAhorcado.generarLista(letra, listaLetras);
					
					palabraGuiones = LogicaAhorcado.generarPalabraOculta(palabra, palabraNormalizada, listaLetras);
					
					numeroIntentos++;
					
					numeroRestantes = LogicaAhorcado.generaNumeroErrores(letra, palabraNormalizada, numeroRestantes);
					
					imagen = LogicaAhorcado.generaImagen(numeroRestantes);
										
					encontradaPalabra = LogicaAhorcado.comprobarGuiones(palabraGuiones);
										
					frase = "";
					request.setAttribute("frase", "");
					request.setAttribute("letra", letra);
				}
				
			} else if (letra.length() != 1){
				
				frase = "No has indicado una �nica letra";
				request.setAttribute("frase", frase);
				request.setAttribute("letra", "");
				
			} else if (esCaracter == false) {
				
				frase = "El car�cter utilizado no es una letra";
				request.setAttribute("frase", frase);
				request.setAttribute("letra", "");
				
			} 
			
			//Aqu� introducimos todos los setAttributes a excepci�n de la frase
			request.setAttribute("palabra", palabra);
			request.setAttribute("palabraNormalizada", palabraNormalizada);
			request.setAttribute("listaLetras", listaLetras);
			request.setAttribute("palabraGuiones", palabraGuiones);
			request.setAttribute("imagen", imagen);
			request.setAttribute("numeroIntentos", numeroIntentos);
			request.setAttribute("numeroRestantes", numeroRestantes);
			request.setAttribute("encontradaPalabra", encontradaPalabra);
			
			//Se enruta a la vista --> la ruta debe comenzar por /
			String vista = "/sesionahorcado.jsp";  
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
			dispatcher.forward(request,response);
			
		 
		}
		//Capturar la excepci�n de forma apropiada (como vimos hace dos semanas)
		} catch (Exception e) {
			//out.println("Se produce una excepci�n <br />");
            //out.println(e.getMessage());
		}
		
	}

}
