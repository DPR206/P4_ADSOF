/**
 * Este paquete contiene las clase necesarias para la gestión de excepciones
 */
package excepciones;

import sensores.*;

import java.time.*;

/**
 * Esta clase error por un sensor sin calibrar
 * @author Claudia Saiz Escribano y Duna Puente Romera. 
 * @version 1.0
 * Nombre del fichero: SensorSinCalibrar.java
 * 
 */
public class SensorSinCalibrar extends Exception{
	
	private static final long serialVersionUID = 1L;
	/**El sensor que ha generado el error*/
	private Sensor sensor;
	/**El momento en el que se ha producido el error*/
	private LocalDateTime instanteError;

	/**
	 * Crea una nueva excepción por un sensor sin calibrar
	 * @param sensor el sensor que ha generado el error
	 * @param instanteError el momento en el que se ha producido el error
	 */
	public SensorSinCalibrar(Sensor sensor, LocalDateTime instanteError) {
		this.sensor = sensor;
		this.instanteError = instanteError;
	}
	
	/**
	 * Obtiene el sensor que ha generado el error
	 * @return el sensor
	 */
	public Sensor getSensor() {
		return sensor;
	}

	/**
	 * Obtiene el momento en el que se ha producido el error
	 * @return el instante de error
	 */
	public LocalDateTime getInstanteError() {
		return instanteError;
	}

	@Override
	/**
	 * Muestra el mensaje de error de la excepción
	 * @return String con la información
	 */
	public String toString() {
		return "[" + instanteError + "]";
	}
}
