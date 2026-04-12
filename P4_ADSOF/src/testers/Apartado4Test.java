/**
 * 
 */
package testers;

import estacion_meteorologica.*;
import estrategias.*;
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
class Apartado4Test {

	public static void main(String[] args) {
		EstacionMeteorologica est = new EstacionMeteorologica("Madrid Centro", new UbicacionGeografica(32, 54));
		
		//Añadimos sensores de estrategia aleatoria a la estación
		//Añadimos un sensor de estrategia cercana sobre el que luego forzar un cambio brusco
		est.addSensor(TipoSensor.TEMPERATURA, 0, new EstrategiaCercana(0, 50) ,new Procesador(new ConversorCelsiusAKelvin()));
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
		
		//Forzamos un cambio brusco de valores en un sensor de estrategia cercana
		est.obtenerSensorId("TEMP_0000").setCambioBrusco(5);
		est.calibrarSensor(est.obtenerSensorId("TEMP_0000"), 50);
		
		est.realizarLecturas();
		
		System.out.println(est);

	}

}
