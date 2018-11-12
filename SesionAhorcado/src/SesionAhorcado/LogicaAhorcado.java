package SesionAhorcado;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.TreeMap;

public class LogicaAhorcado {
	
	public static String generaPalabra(TreeMap<String,String> mapaPalabras) {
		
		String palabra = "";
		
		int numeroPalabra = (int) (Math.random()*mapaPalabras.size()+1);
		
		palabra = mapaPalabras.get("nombre"+numeroPalabra);
		
		return palabra;
		
	}
	
	public static String palabraNormalizada (String palabra) {
		
		String palabraNormalizada = palabra.toLowerCase();
		
		palabraNormalizada = palabraNormalizada.replace('ñ', '\001');
		palabraNormalizada = Normalizer.normalize(palabraNormalizada, Normalizer.Form.NFD);
		palabraNormalizada = palabraNormalizada.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		palabraNormalizada = palabraNormalizada.replace('\001', 'ñ');
	 		
		return palabraNormalizada;
		
	}
	
	public static ArrayList<String> generarLista(String letra, int numeroIntentos){
		
		ArrayList<String> listaLetras = new ArrayList<String>();
		
		//Aquí no hacemos chequeo: ya se realiza en el doPost del Servlet
		listaLetras.add(letra);
		numeroIntentos++;
	
		return listaLetras;
		
	}
	
	public static String[] generarPalabraOculta(String palabra, String palabraNormalizada, ArrayList<String> listaLetras) {
		
		String[] palabraGuiones = new String[palabraNormalizada.length()];
		
		for(int i = 0; i < palabraNormalizada.length(); i++) {
			
			palabraGuiones[i] = palabraNormalizada.substring(i, i+1);			
			
			for(int j = 0; j < listaLetras.size(); j++) {
				
				if(listaLetras.get(j).equals(palabraGuiones[i])) {
					
					palabraGuiones[i] = palabra.substring(i) + " ";				
					
				} else {
					
					palabraGuiones[i] = "_ ";
					
				}
				
			}
			
		}
		
		return palabraGuiones;
		
	}
	
	public static int generaNumeroErrores(String letra, String palabraNormalizada, int numeroErrores) {
		
		boolean estaLetra = false;
		
		for (int i = 0; i < palabraNormalizada.length(); i++) {
			
			if(letra.equals(palabraNormalizada.substring(i))) {
				estaLetra = true;
				break;
			}
					
		}
		
		if(estaLetra == false) {
			numeroErrores++;
		}		
		
		return numeroErrores;
		
	}
		
	public static String generaImagen(int numeroRestantes) {
		
		String imagen = "";
		
		if(numeroRestantes == 6) {
			imagen = "imagenes_ahorcado\\/imagen_1.png";
		} else if(numeroRestantes == 5) {
			imagen = "imagenes_ahorcado\\/imagen_2.png";
		} else if(numeroRestantes == 4) {
			imagen = "imagenes_ahorcado\\/imagen_3.png";
		} else if(numeroRestantes == 3) {
			imagen = "imagenes_ahorcado\\/imagen_4.png";
		} else if(numeroRestantes == 2) {
			imagen = "imagenes_ahorcado\\/imagen_5.png";
		} else if(numeroRestantes == 1) {
			imagen = "imagenes_ahorcado\\/imagen_6.png";
		} else if(numeroRestantes == 0) {
			imagen = "imagenes_ahorcado\\/imagen_7.png";
		}		
		
		return imagen;
		
	}
	
	//Incluir si la letra ya ha sido utilizada
	
	//Incluir si se ha ganado el juego

}
