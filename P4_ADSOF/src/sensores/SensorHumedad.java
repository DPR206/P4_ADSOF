/**
 * Este paquete contiene las clases necesarias para gestionar sensores
 */
package sensores;

import java.time.LocalDateTime;
import java.time.LocalTime;

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
	 * @param id, ID del sensor
	 * @param offset, offset de calibración
	 * @param ultimaLectura, valor de la última lectura
	 * @param tiempoUltimaLectura, fecha y hora de la última lectura
	 * @param ultimaCalibracion
	 * @throws IncompatibleConversorException 
	 */
	public SensorHumedad(String id, double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura, LocalDateTime ultimaCalibracion, LocalTime fechaInstalacion, Procesador procesador) throws IncompatibleConversorException {
		super(id, offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion, procesador);
		if(!(procesador.getConversor() instanceof ConversorIdentidad)) throw new IncompatibleConversorException("Este sensor debe tener un convesor identidad");
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
	public SensorHumedad(double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura, LocalDateTime ultimaCalibracion, LocalTime fechaInstalacion, Procesador procesador) throws IncompatibleConversorException {
		super(idType+String.format("%04d", ids), offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion, procesador);
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
	
	
	
}
