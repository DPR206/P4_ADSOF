package testers;

import estacion_meteorologica.*;
import formateadores.*;
import procesadores.*;
import procesadores.conversoresPresion.hPaAPa;
import procesadores.conversoresTemperatura.ConversorCelsiusAKelvin;
import sensores.TipoSensor;

/**
 * Esta clase representa un test de los formateadores
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera.
 * @version 1.0 Nombre del fichero: Apartado5Test.java
 * 
 */
class Apartado5Test {

	public static void main(String[] args) {
		EstacionMeteorologica est = new EstacionMeteorologica("Madrid Centro", new UbicacionGeografica(32, 54));

		// Añadimos 4 sensores con diferentes conversores
		est.addSensor(TipoSensor.TEMPERATURA, 0);
		est.addSensor(TipoSensor.TEMPERATURA, 0, new Procesador(new ConversorCelsiusAKelvin()));
		est.addSensor(TipoSensor.PRESION, 0, new Procesador(new hPaAPa()));
		est.addSensor(TipoSensor.HUMEDAD, 0, new Procesador(new ConversorIdentidad()));
		
		// Añadirmos alertas
		
		// Configuramos 5 lecturas en cada uno e imprimimos la información de la estación
		est.realizarLecturasPeriodicas(100, 5);
		try {
		    Thread.sleep(2000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		//Formatear a HTML
		System.out.println("Formato HTML: \n\n");
		FormateadorHTML f1 = new FormateadorHTML();
		System.out.println(f1.formatear(est) + "\n\n");
		
		//Formatear a Markdown
		System.out.println("Formato Markdown: \n\n");
		FormateadorMarkdown f2 = new FormateadorMarkdown();
		System.out.println(f2.formatear(est));

	}

}
