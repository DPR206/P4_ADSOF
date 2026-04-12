package procesadores.conversoresPresion;

/**
 * Esta clase representa un conversor hectopascales a pascales
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera.
 * @version 1.0 Nombre del fichero: hPaAPa.java
 * 
 */
public class hPaAPa extends ConversorPresion {
	
	/**
	 * Crea un nuevo constructor de hPa a Pa
	 */
	public hPaAPa() {
	}

	@Override
	public double convertir(double valor) {
		return valor * 100.0;
	}
	
	@Override
	public String toString() {
		return " con conversor a Pa";
	}
}
