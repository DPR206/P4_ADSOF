package procesadores;

/**
 * Esta clase representa un conversor identidad
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera.
 * @version 1.0 Nombre del fichero: ConversorIdentidad.java
 * 
 */
public class ConversorIdentidad implements Conversor {

	/**
	 * Crea un nuevo conversor identidad
	 */
	public ConversorIdentidad() {
	}

	@Override
	public double convertir(double valor) {
		return valor;
	}
	
	@Override
	public String toString() {
		return "";
	}

}
