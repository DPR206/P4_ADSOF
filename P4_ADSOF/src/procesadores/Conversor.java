package procesadores;

/**
 * Esta interfaz representa un conversor de unidades
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera.
 * @version 1.0 Nombre del fichero: Conversor.java
 * 
 */
public interface Conversor {
	
	/**
	 * Convierte un valor de una unidad de medida a otra
	 * @param valor Valor en la unidad inicial
	 * @return Valor en la unidad convertida
	 */
	double convertir(double valor);
}
