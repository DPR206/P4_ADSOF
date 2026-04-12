/**
 * Este paquete contiene las clase necesarias para la gestión de excepciones
 */
package excepciones;

import java.time.LocalDateTime;

import sensores.Sensor;

/**
 * Esta clase representa una excepción por calibración caducada
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera.
 * @version 1.0 
 * Nombre del fichero: CalibracionCaducada.java
 * 
 */
public class CalibracionCaducada extends SensorSinCalibrar{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Crea una nueva excepción por calibración caducada
	 * @param sensor el sensor sin calibrar
	 * @param instanteError el momento en el que se genera el error
	 */
	public CalibracionCaducada(Sensor sensor, LocalDateTime instanteError) {
		super(sensor, instanteError);
	}

	@Override
	/**
	 * Muestra el mensaje de error de la excepción
	 * @return String con la información
	 */
	public String toString() {
		return super.toString() + " Sensor " + this.getSensor().getId() + " sin calibrar (calibración caducada desde " 
				+ this.getSensor().getUltimaCalibracion() + ")";
	}

	
}
