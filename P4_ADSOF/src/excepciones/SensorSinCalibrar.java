/**
 * 
 */
package excepciones;

import sensores.*;

import java.time.*;

/**
 * 
 */
public class SensorSinCalibrar extends Exception{
	
	private static final long serialVersionUID = 1L;
	private Sensor sensor;
	private LocalDateTime instanteError;

	/**
	 * @param sensor
	 * @param instanteError
	 */
	public SensorSinCalibrar(Sensor sensor, LocalDateTime instanteError) {
		this.sensor = sensor;
		this.instanteError = instanteError;
	}
	
	/**
	 * @return the sensor
	 */
	public Sensor getSensor() {
		return sensor;
	}



	/**
	 * @return the instanteError
	 */
	public LocalDateTime getInstanteError() {
		return instanteError;
	}



	@Override
	public String toString() {
		return "[" + instanteError + "]";
	}
}
