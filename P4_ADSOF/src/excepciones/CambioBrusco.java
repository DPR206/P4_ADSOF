/**
 * Este paquete contiene las clase necesarias para la gestión de excepciones
 */
package excepciones;

import sensores.*;

import java.time.*;

/**
 * Esta clase representa una excepción por calibración caducada
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera.
 * @version 1.0 
 * Nombre del fichero: CambioBrusco.java
 * 
 */
public class CambioBrusco extends Exception{

	private static final long serialVersionUID = 1L;
	 /**el sensor que ha generado el error*/
	private Sensor sensor;
	/**un string de la lectura sospechosa*/
	private String lecturaSospechosa; 
	/**el momento en el que se ha generado el error*/
	private LocalDateTime instanteError; 

	/**
	 * Crea una nueva excepción por cambio brusco de lecturas
	 * @param sensor el sensor que ha generado el error
	 * @param lecturaSospechosa un string de la lectura sospechosa
	 * @param instanteError el momento en el que se ha generado el error
	 */
	public CambioBrusco(Sensor sensor, String lecturaSospechosa, LocalDateTime instanteError) {
		this.sensor = sensor;
		this.lecturaSospechosa = lecturaSospechosa;
		this.instanteError = instanteError;
	}

	@Override
	/**
	 * Muestra el mensaje de error de la excepción
	 * @return String con la información
	 */
	public String toString() {
		return "[" + instanteError + "]" + " CambioBrusco en sensor " + sensor.getId() + ": " + lecturaSospechosa 
				+ " (anterior: " +  sensor.ultimaLectura() + ")";
	}
}
