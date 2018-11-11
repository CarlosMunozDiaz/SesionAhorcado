package SesionAhorcado;

import java.io.IOException;
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
		
		//Se crea la sesi�n
		HttpSession laSesion= request.getSession(true);
		
		palabra = LogicaAhorcado.generaPalabra(mapaPalabras);
		palabraNormalizada = LogicaAhorcado.palabraNormalizada(palabra);
		
		imagen = 
		
		palabraGuiones = LogicaAhorcado.generarPalabraOculta(palabra, palabraNormalizada, listaLetras);
		
		if(laSesion != null) {
			if (request.getParameter("empezar") != null) {  // se ha recibido el par�metro empezar
				laSesion.invalidate();  // se inactiva la sesi�n
			} else {
				
			}
		}
		
		
		//Se enruta a la vista --> la ruta debe comenzar por /
		String vista = "/sesionahorcado.jsp";  
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
		dispatcher.forward(request,response);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		letra = request.getParameter("caracter");
		letra = letra.toLowerCase();
		
		boolean esCaracter = Character.isLetter(letra.charAt(0));
		
		// se recupera la sesi�n
		HttpSession laSesion= request.getSession(false);  //carga la sessi�n si existe, devuelve null si no
		
		if(laSesion != null) {
			
			if(letra.length() == 1 && esCaracter == true) {
				
				//Aqu� va el grueso --> Todo lo respectivo a la session
				
			
			//Aqu� debemos incluir si la letra ya est� en la lista --> Crear un m�todo
			} else if (letra.length() != 1){
				frase = "No has indicado una �nica letra";
			} else if (esCaracter == false) {
				frase = "El car�cter utilizado no es una letra";
			} else {
				frase = "La letra indicada ya ha sido utilizada de forma previa";
			}
			
			
			//Aqu� introducimos todos los setAttributes a excepci�n de la frase
			
			//Se enruta a la vista --> la ruta debe comenzar por /
			String vista = "/sesionahorcado.jsp";  
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(vista);
			dispatcher.forward(request,response);
		
		
		}
		
		
		
		
		
		
		
		
		
	}

}
