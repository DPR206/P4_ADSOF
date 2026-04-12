package testers;

import estacion_meteorologica.*;
import excepciones.IncompatibleConversorException;
import procesadores.conversoresPresion.*;
import procesadores.conversoresTemperatura.*;
import procesadores.*;
import sensores.TipoSensor;

/**
 * Esta clase representa un test de procesadores
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera.
 * @version 1.0 Nombre del fichero: Apartado3Test.java
 * 
 */
class Apartado3Test {

	public static void main(String[] args) {
		EstacionMeteorologica est = new EstacionMeteorologica("Madrid Centro", new UbicacionGeografica(32, 54));
		
		// Añadir un sensor con un  conversor no válido lanza una excepción
		est.addSensor(TipoSensor.TEMPERATURA, 0, new Procesador(new hPaAPa()));
		est.addSensor(TipoSensor.PRESION, 0, new Procesador(new ConversorCelsiusAKelvin()));
		est.addSensor(TipoSensor.HUMEDAD, 0, new Procesador(new ConversorCelsiusAKelvin()));
		try {
			est.addSensor(TipoSensor.TEMPERATURA, 0, new Procesador(new ConversorCompuesto(new ConversorCelsiusAKelvin(), new ConversorFarenheitACelsius())));
		} catch (IncompatibleConversorException e) {
			System.out.println("Warning: " + e);
		}
		try {
			est.addSensor(TipoSensor.TEMPERATURA, 0, new Procesador(new ConversorCompuesto(new ConversorCelsiusAKelvin(), new hPaAPa())));
		} catch (IncompatibleConversorException e) {
			System.out.println("Warning: " + e);
		}
		
		// Añadir un sensor sin especificar el conversor asigna un ConversorIdentidad
		est.addSensor(TipoSensor.TEMPERATURA, 0);
		System.out.println(est.obtenerSensorId("TEMP_0000").getProcesador().getConversor());
		
		// Añadimos 3 sensores con diferentes conversores
		est.addSensor(TipoSensor.TEMPERATURA, 0, new Procesador(new ConversorCelsiusAKelvin()));
		est.addSensor(TipoSensor.PRESION, 0, new Procesador(new hPaAPa()));
		est.addSensor(TipoSensor.HUMEDAD, 0, new Procesador(new ConversorIdentidad()));
		try {
			est.addSensor(TipoSensor.TEMPERATURA, 0, new Procesador(new ConversorCompuesto(new ConversorCelsiusAKelvin(), new ConversorKelvinAFarenheit())));
		} catch (IncompatibleConversorException e) {
			System.out.println("Warning: " + e);
		}
		
		// Configuramos 5 lecturas en cada uno e imprimimos la información de la estación
		est.realizarLecturasPeriodicas(100, 5);
		try {
		    Thread.sleep(2000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		System.out.println(est);
		
	}
	
}
