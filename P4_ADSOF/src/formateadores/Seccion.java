package formateadores;

import java.util.List;

/**
 * Esta clase representa una seccion de un IDocumento
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera.
 * @version 1.0 Nombre del fichero: Seccion.java
 * 
 */
public class Seccion {

	private String titulo;
	private List<String> elementos;

	/**
	 * Crea una seccion
	 * @param titulo Titulo de la seccion
	 * @param elementos Lista de elementos que forman la seccion
	 */
	public Seccion(String titulo, List<String> elementos) {
		this.titulo = titulo;
		this.elementos = elementos;
	}

	/**
	 * Devuelve el titulo de la seccion
	 * @return Titulo de la seccion
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Devuelve los elementos de la seccion
	 * @return Lista con los elementos de la seccion
	 */
	public List<String> getElementos() {
		return elementos;
	}
}
