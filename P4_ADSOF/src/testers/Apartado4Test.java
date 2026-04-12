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
 * @version 1.0 
 * Nombre del fichero: Apartado4Test.java
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
		
		//Realizamos una primera lectura
		est.realizarLecturasPeriodicas(100, 5);
		try {
		    Thread.sleep(2000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		//Forzamos lectura fuera de rango
		est.calibrarSensor(est.obtenerSensorId("TEMP_0001"), 1000);
		
		//Realizamos una segunda lectura
		est.realizarLecturasPeriodicas(100, 5);
		try {
		    Thread.sleep(2000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		//Forzamos que un sensor tenga la calibracion caducada
		est.obtenerSensorId("PRES_0000").cambiarCaducidad(0);;
		
		est.realizarLecturasPeriodicas(100, 5);
		try {
		    Thread.sleep(2000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		//Forzamos un cambio brusco de valores
		//est.obtenerSensorId("HUM_0001").setCambioBrusco(5);
		//est.calibrarSensor(est.obtenerSensorId("HUM_0001"), 30);
		
		//est.realizarLecturas();
		
		System.out.println(est);

	}

}
