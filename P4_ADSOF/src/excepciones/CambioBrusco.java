/**
 * 
 */
package excepciones;

import sensores.*;

import java.time.*;

/**
 * 
 */
public class CambioBrusco extends Exception{

	private static final long serialVersionUID = 1L;
	private Sensor sensor;
	private String lecturaSospechosa;
	private LocalDateTime instanteError;

	/**
	 * @param sensor
	 * @param lecturaSospechosa
	 * @param instanteError
	 */
	public CambioBrusco(Sensor sensor, String lecturaSospechosa, LocalDateTime instanteError) {
		this.sensor = sensor;
		this.lecturaSospechosa = lecturaSospechosa;
		this.instanteError = instanteError;
	}



	@Override
	public String toString() {
		return "[" + instanteError + "]" + " CambioBrusco en sensor " + sensor.getId() + ": " + lecturaSospechosa 
				+ " (anterior: " +  sensor.ultimaLectura() + ")";
	}
}
