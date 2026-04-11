/**
 * 
 */
package excepciones;

import java.time.LocalDateTime;

import sensores.Sensor;

/**
 * 
 */
public class CalibracionCaducada extends SensorSinCalibrar{
	
	private static final long serialVersionUID = 1L;

	/**
	 * @param sensor
	 * @param instanteError
	 */
	public CalibracionCaducada(Sensor sensor, LocalDateTime instanteError) {
		super(sensor, instanteError);
	}

	@Override
	public String toString() {
		return super.toString() + " Sensor " + this.getSensor().getId() + " sin calibrar (calibración caducada desde " 
				+ this.getSensor().getUltimaCalibracion() + ")";
	}

	
}
