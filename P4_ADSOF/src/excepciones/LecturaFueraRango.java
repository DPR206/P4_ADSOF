/**
 * 
 */
package excepciones;

import java.time.LocalDateTime;

import sensores.Sensor;

/**
 * 
 */
public class LecturaFueraRango extends SensorSinCalibrar{

	private static final long serialVersionUID = 1L;
	private String lecturaError;

	/**
	 * @param sensor
	 * @param instanteError
	 * @param lecturaError
	 */
	public LecturaFueraRango(Sensor sensor, LocalDateTime instanteError, String lecturaError) {
		super(sensor, instanteError);
		this.lecturaError = lecturaError;
	}

	/**
	 * @return the lecturaError
	 */
	public String getLecturaError() {
		return lecturaError;
	}



	@Override
	public String toString() {
		return super.toString() + " Lectura fuera de rango en " + this.getSensor().getId() + ":" + this.getSensor().ultimaLectura();
	}

	
	
}
