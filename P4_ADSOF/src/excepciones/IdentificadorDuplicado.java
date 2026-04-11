/**
 * 
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
	private Sensor sensorNuevo, sensorExistente;

	/**
	 * @param sensorNuevo
	 * @param sensorExistente
	 */
	public IdentificadorDuplicado(Sensor sensorNuevo, Sensor sensorExistente) {
		this.sensorNuevo = sensorNuevo;
		this.sensorExistente = sensorExistente;
	}

	@Override
	public String toString() {
		return "No se puede añadir: " + sensorNuevo + ". Ya hay un sensor preexistente con ese identificador" + sensorExistente;
	}
	

}
