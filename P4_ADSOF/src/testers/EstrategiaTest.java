package testers;

import estrategias.*;
import excepciones.CambioBrusco;
import excepciones.IncompatibleConversorException;
import procesadores.*;
import sensores.*;

public class EstrategiaTest {

	public static void main(String[] args) throws IncompatibleConversorException, CambioBrusco {
		
		// Por defecto sin especificar estrategia se pone la cercana con valor inicial 0 y desviación 10%
		// El resultado deberá estar entre -0.1 y 0.1 (si el valor base es 0 se cambia por un valor mínimo para ver una variación)
		Sensor s = new SensorTemperatura(0, new Procesador(new ConversorIdentidad()));
		s.realizarLectura();
		System.out.println("Caso 1: " + s.getUltimaLectura());
		
		// El resultado deberá estar entre 0 y 10 (0% de salir fuera del rango)
		EstrategiaAleatoria e1 = new EstrategiaAleatoria(0, 10, 0);
		System.out.println("Caso 2: " + e1.generarValor());
		
		// El resultado NO deberá estar entre 0 y 10 (100% de salir fuera del rango)
		EstrategiaAleatoria e2 = new EstrategiaAleatoria(0, 10, 100);
		System.out.println("Caso 3: " + e2.generarValor());
		
		// El resultado deberá estar entre 90 y 110
		EstrategiaCercana e3 = new EstrategiaCercana(100, 10);
		System.out.println("Caso 4: " + e3.generarValor());
		
		// El resultado deberá estar entre 90 y 110
		EstrategiaMedia e4 = new EstrategiaMedia(100, 10);
		System.out.println("Caso 5: " + e4.generarValor());
		
		//Con el nuevo resultado la media cambia así el valor podría salir de 90 y 110
		System.out.println("Caso 6: " + e4.generarValor());
	}

}
