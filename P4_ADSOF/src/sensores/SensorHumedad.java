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
	 * Crea un nuevo sensor de temperatura
	 * 
	 * @param offset, offset de calibración
	 * @param ultimaLectura, valor de la última lectura
	 * @param tiempoUltimaLectura, fecha y hora de la última lectura
	 * @param ultimaCalibracion
	 * @param fechaInstalacion, fecha de instalación del sensor
	 * @throws IncompatibleConversorException 
	 */
	public SensorHumedad(double offset, double ultimaLectura, Procesador procesador) throws IncompatibleConversorException {
		super(idType+String.format("%04d", ids), offset, ultimaLectura, procesador);
		if(!(procesador.getConversor() instanceof ConversorIdentidad)) throw new IncompatibleConversorException("Este sensor debe tener un convesor identidad");
		ids++;
	}
	
	/**
	 * Crea un nuevo sensor de temperatura
	 * 
	 * @param offset, offset de calibración
	 * @param ultimaLectura, valor de la última lectura
	 * @param tiempoUltimaLectura, fecha y hora de la última lectura
	 * @param ultimaCalibracion
	 * @param fechaInstalacion, fecha de instalación del sensor
	 * @throws IncompatibleConversorException 
	 */
	public SensorHumedad(double offset, double ultimaLectura, Estrategia estrategia, Procesador procesador) throws IncompatibleConversorException {
		super(idType+String.format("%04d", ids), offset, ultimaLectura, estrategia, procesador);
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
