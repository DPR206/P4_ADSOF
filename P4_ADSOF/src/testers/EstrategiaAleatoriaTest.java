package testers;

import estacion_meteorologica.*;
import sensores.TipoSensor;

public class EstrategiaAleatoriaTest {

	public static void main(String[] args) {
		EstacionMeteorologica e = new EstacionMeteorologica("Madrid centro", new UbicacionGeografica(23, 54), 20, 20);
		e.addSensor(TipoSensor.TEMPERATURA, 20);
	}

}
