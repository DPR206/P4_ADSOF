package testers;

import estacion_meteorologica.*;
import estrategias.*;
import procesadores.*;
import sensores.TipoSensor;

/**
 * Esta clase representa un test de la estación meteorológica
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera.
 * @version 1.0 Nombre del fichero: Apartado1Test.java
 * 
 */
class Apartado1Test {
	
	public static void main(String[] args) {
		EstacionMeteorologica est = new EstacionMeteorologica("Madrid Centro", new UbicacionGeografica(32, 54));
		
		// Añadimos 4 sensores, utilizando diferentes constructores
		est.addSensor(TipoSensor.TEMPERATURA, 0);
		est.addSensor(TipoSensor.TEMPERATURA, 0, new EstrategiaAleatoria(10, 50, 20));
		est.addSensor(TipoSensor.HUMEDAD, 0, new Procesador(new ConversorIdentidad()));
		est.addSensor(TipoSensor.PRESION, 0, new EstrategiaAleatoria(600, 800, 0));
		
		// Coger los todos los sensores por tipo
		System.out.println("Caso 1: ");
		System.out.println(est.buscarTipoSensores(TipoSensor.TEMPERATURA));
		System.out.println(est.buscarTipoSensores(TipoSensor.HUMEDAD));
		System.out.println(est.buscarTipoSensores(TipoSensor.PRESION));
		
		// Cogemos un sensor por id
		System.out.println("\nCaso 2: " + est.obtenerSensorId("TEMP_0000"));
		
		// Realizamos lectura de los sensores
		est.realizarLecturas();
		System.out.println("\nCaso 3: " + est.getSensores().values());
		
		// Activamos las lecturas periodicas cada 0,1 segundo con máximo de 5 lecturas
		est.realizarLecturasPeriodicas(100, 5);
		try {
		    Thread.sleep(2000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		System.out.println("\nCaso 4: " + est.getSensores().values());
		
		//
	}
	
}
