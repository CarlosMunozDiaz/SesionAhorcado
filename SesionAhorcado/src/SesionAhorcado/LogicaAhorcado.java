package SesionAhorcado;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.TreeMap;

public class LogicaAhorcado {
	
	public static String generaPalabra(TreeMap<String,String> mapaPalabras) {
		
		String palabra = "";
		
		int numeroPalabra = (int) (Math.random()*mapaPalabras.size());
		
		palabra = mapaPalabras.get("nombre"+numeroPalabra);
		
		return palabra;
		
	}
	
	public static String palabraNormalizada (String palabra) {
		
		String palabraNormalizada = palabra.toLowerCase();
		
		palabraNormalizada = palabraNormalizada.replace('�', '\001');
		palabraNormalizada = Normalizer.normalize(palabraNormalizada, Normalizer.Form.NFD);
		palabraNormalizada = palabraNormalizada.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		palabraNormalizada = palabraNormalizada.replace('\001', '�');
	 		
		return palabraNormalizada;
		
	}
	
	public static ArrayList<String> generarLista(String letra){
		
		ArrayList<String> listaLetras = new ArrayList<String>();
		
		//Aqu� no hacemos chequeo: ya se realiza en el doPost del Servlet
		listaLetras.add(letra);
	
		return listaLetras;
		
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

}
