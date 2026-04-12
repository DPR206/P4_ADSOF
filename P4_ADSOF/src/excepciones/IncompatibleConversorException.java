/**
 * Este paquete contiene las clase necesarias para la gestión de excepciones
 */
package excepciones;

/**
 * Esta clase error por conversor incompatible
 * @author Claudia Saiz Escribano y Duna Puente Romera. 
 * @version 1.0
 * Nombre del fichero: IncompatibleConversorException.java
 * 
 */
public class IncompatibleConversorException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Crea una nueva excepción por conversor incompatible
	 * @param message el mensaje de error
	 */
	public IncompatibleConversorException(String message) {
		super(message);
	}
}
