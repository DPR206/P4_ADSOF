/**
 * Este paquete contiene las clase necesarias para la gestión de excepciones
 */
package excepciones;

import java.time.LocalDateTime;

import sensores.Sensor;

/**
 * Esta clase error por lectura fuera de rango
 * @author Claudia Saiz Escribano y Duna Puente Romera. 
 * @version 1.0
 * Nombre del fichero: LecturaFueraRango.java
 * 
 */
public class LecturaFueraRango extends SensorSinCalibrar{

	private static final long serialVersionUID = 1L;
	/**La lectura fuera de rango*/
	private String lecturaError;

	/**
	 * Crea una nueva excepción por lectura fuera de rango
	 * @param sensor sensor que ha generado el error
	 * @param instanteError el momento en el que se ha producido el error
	 * @param lecturaError la lectura fuera de rango
	 */
	public LecturaFueraRango(Sensor sensor, LocalDateTime instanteError, String lecturaError) {
		super(sensor, instanteError);
		this.lecturaError = lecturaError;
	}

	/**
	 * Obtiene la lectura que ha generado el error
	 * @return la lectura fuera de rango
	 */
	public String getLecturaError() {
		return lecturaError;
	}

	@Override
	/**
	 * Muestra el mensaje de error de la excepción
	 * @return String con la información
	 */
	public String toString() {
		return super.toString() + " Lectura fuera de rango en " + this.getSensor().getId() + ":" + this.getSensor().ultimaLectura();
	}
	
}
