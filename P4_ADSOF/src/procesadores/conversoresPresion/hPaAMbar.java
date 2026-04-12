package procesadores.conversoresPresion;

/**
 * Esta clase representa un conversor de hectopascales a milibares
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera.
 * @version 1.0 Nombre del fichero: hPaAMbar.java
 * 
 */
public class hPaAMbar extends ConversorPresion {
	
	/**
	 * Crea un nuevo conversor de hPa a mBar
	 */
	public hPaAMbar() {
	}

	@Override
	public double convertir(double valor) {
		return valor;
	}
	
	@Override
	public String toString() {
		return " con conversor a mBar";
	}
}
