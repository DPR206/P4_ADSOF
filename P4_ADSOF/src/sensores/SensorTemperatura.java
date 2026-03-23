/**
 * Este paquete contiene las clases necesarias para gestionar sensores
 */
package sensores;

import java.time.*;

/**
 * Esta clase representa un sensor de temperatura
 * @author Claudia Saiz Escribano y Duna Puente Romera. 
 * @version 1.0
 * Nombre del fichero: SensorTemperatura.java
 * 
 */
public class SensorTemperatura extends Sensor {

	private static int ids = 0000;
	private static String idType = "TEMP_";
	private static double cotaInferior = -273.15;
	private static double cotaSuperior = 1000;
	
	private TipoTemp tipo;

	/**
	 * Crea un nuevo sensor de temperatura
	 * 
	 * @param id, ID del sensor
	 * @param offset, 
	 * @param ultimaLectura, valor de la última lectura
	 * @param tiempoUltimaLectura,  
	 * @param ultimaCalibracion, f
	 * @param tipo
	 */
	public SensorTemperatura(String id, double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura,
			LocalTime ultimaCalibracion, TipoTemp tipo) {
		super(id, offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion);
		this.tipo = tipo;
	}
	
	/**
	 * Crea un nuevo sensor de temperatura por defecto
	 * 
	 * @param offset
	 * @param ultimaLectura
	 * @param tiempoUltimaLectura
	 * @param ultimaCalibracion
	 */
	public SensorTemperatura(double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura,
			LocalTime ultimaCalibracion) {
		this(idType+ids, offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, TipoTemp.CELSIUS);
		ids++;
	}
}
