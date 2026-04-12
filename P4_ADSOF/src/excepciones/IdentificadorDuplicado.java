/**
 * Este paquete contiene las clase necesarias para la gestión de excepciones
 */
package excepciones;

import sensores.Sensor;

/**
 * Esta clase error por identificador duplicado
 * @author Claudia Saiz Escribano y Duna Puente Romera. 
 * @version 1.0
 * Nombre del fichero: IdentificadorDuplicado.java
 * 
 */
public class IdentificadorDuplicado extends Exception{
	
	private static final long serialVersionUID = 1L;
	/**el sensor nuevo y el sensor  preexistente con el mismo id*/
	private Sensor sensorNuevo, sensorExistente;

	/**
	 * Crea una nueva excepción por identificador duplicado
	 * @param sensorNuevo el sensor nuevo
	 * @param sensorExistente el sensor preexistente con el mismo id
	 */
	public IdentificadorDuplicado(Sensor sensorNuevo, Sensor sensorExistente) {
		this.sensorNuevo = sensorNuevo;
		this.sensorExistente = sensorExistente;
	}

	@Override
	/**
	 * Muestra el mensaje de error de la excepción
	 * @return String con la información
	 */
	public String toString() {
		return "No se puede añadir: " + sensorNuevo + ". Ya hay un sensor preexistente con ese identificador" + sensorExistente;
	}
	

}
