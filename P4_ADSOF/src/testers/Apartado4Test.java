/**
 * 
 */
package testers;

import estacion_meteorologica.*;
import procesadores.*;
import procesadores.conversoresPresion.*;
import procesadores.conversoresTemperatura.*;
import sensores.*;

/**
 * Esta clase representa un test de las alertas (apartado 4)
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera.
 * @version 1.0 Nombre del fichero: Apartado4.java
 * 
 */
public class Apartado4Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EstacionMeteorologica est = new EstacionMeteorologica("Madrid Centro", new UbicacionGeografica(32, 54));
		
		//Añadimos sensores a la estación
		est.addSensor(TipoSensor.TEMPERATURA, 0, new Procesador(new ConversorCelsiusAKelvin()));
		est.addSensor(TipoSensor.PRESION, 0, new Procesador(new hPaAPa()));
		est.addSensor(TipoSensor.HUMEDAD, 0, new Procesador(new ConversorIdentidad()));
		est.addSensor(TipoSensor.TEMPERATURA, 0);
		est.addSensor(TipoSensor.PRESION, 0);
		est.addSensor(TipoSensor.HUMEDAD, 0);
		est.addSensor(TipoSensor.PRESION, 0);
		
		est.realizarLecturasPeriodicas(100, 5);
		try {
		    Thread.sleep(2000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		est.calibrarSensor(est.obtenerSensorId("TEMP_0001"), 1000);
		
		est.realizarLecturasPeriodicas(100, 5);
		try {
		    Thread.sleep(2000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		System.out.println(est);

	}

}
