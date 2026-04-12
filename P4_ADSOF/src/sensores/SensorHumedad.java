/**
 * Este paquete contiene las clases necesarias para gestionar sensores
 */
package sensores;

import estrategias.Estrategia;
import excepciones.IncompatibleConversorException;
import procesadores.*;

/**
 * Esta clase representa un sensor de humedad
 * @author Claudia Saiz Escribano y Duna Puente Romera. 
 * @version 1.0
 * Nombre del fichero: SensorHumedad.java
 * 
 */
public class SensorHumedad extends Sensor{
	
	private static int ids = 0;
	private static String idType = "HUM_";
	private static double cotaInferior = 0;
	private static double cotaSuperior = 100;
	private static String unidad = "%";
	
	/**
	 * Crea un nuevo sensor de humedad
	 * 
	 * @param offset, offset de calibración
	 * @param procesador, el procesador del sensor
	 * @throws IncompatibleConversorException error por conversor incompatible
	 */
	public SensorHumedad(double offset, Procesador procesador) throws IncompatibleConversorException {
		super(idType+String.format("%04d", ids), offset, procesador);
		if(!(procesador.getConversor() instanceof ConversorIdentidad)) throw new IncompatibleConversorException("Este sensor debe tener un convesor identidad");
		ids++;
	}
	
	/**
	 * Crea un nuevo sensor de humedad
	 * 
	 * @param offset, offset de calibración
	 * @param estrategia estrtegia de lectura
	 * @param procesador, el procesador del sensor
	 * @throws IncompatibleConversorException error por conversor incompatible
	 */
	public SensorHumedad(double offset, Estrategia estrategia, Procesador procesador) throws IncompatibleConversorException {
		super(idType+String.format("%04d", ids), offset, estrategia, procesador);
		if(!(procesador.getConversor() instanceof ConversorIdentidad)) throw new IncompatibleConversorException("Este sensor debe tener un convesor identidad");
		ids++;
	}

	@Override
	public String ultimaLectura() {
		return this.getUltimaLectura()+SensorHumedad.unidad;
	}

	@Override
	public boolean lecturaEnRango(double valor) {
		if(valor < SensorHumedad.cotaInferior || valor > SensorHumedad.cotaSuperior)
			return false;
		return true;
	}

	@Override
	public String detallesHijo() {
		return " Sensor Humedad (" + this.ultimaLectura();
	}
	
	
}
