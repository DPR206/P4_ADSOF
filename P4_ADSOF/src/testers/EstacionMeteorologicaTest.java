package testers;

import estacion_meteorologica.*;
import estrategias.*;
import procesadores.*;
import procesadores.conversoresPresion.hPaAPa;
import sensores.TipoSensor;

public class EstacionMeteorologicaTest {
	
	public static void main(String[] args) {
		EstacionMeteorologica est = new EstacionMeteorologica("Madrid Centro", new UbicacionGeografica(32, 54));
		
		// Añadimos 4 sensores, utilizando diferentes constructores
		est.addSensor(TipoSensor.TEMPERATURA, 10);
		est.addSensor(TipoSensor.TEMPERATURA, 20, new EstrategiaAleatoria(10, 50, 20));
		est.addSensor(TipoSensor.HUMEDAD, 30, new Procesador(new ConversorIdentidad()));
		est.addSensor(TipoSensor.PRESION, 40, new EstrategiaAleatoria(10, 50, 20), new Procesador(new hPaAPa()));
		
		// Coger los todos los sensores por tipo
		System.out.println("Caso 1: ");
		System.out.println(est.buscarTipoSensores(TipoSensor.TEMPERATURA));
		System.out.println(est.buscarTipoSensores(TipoSensor.HUMEDAD));
		System.out.println(est.buscarTipoSensores(TipoSensor.PRESION));
		
		// Cogemos un sensor por id
		System.out.println("\nCaso 2 : " + est.obtenerSensorId("TEMP_0000"));
		
		// Realizamos lectura de los sensores
		est.realizarLecturas();
	}
	
}
