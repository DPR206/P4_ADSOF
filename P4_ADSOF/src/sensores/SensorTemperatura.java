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
	 * @param offset, offset de calibración
	 * @param ultimaLectura, valor de la última lectura
	 * @param tiempoUltimaLectura, fecha y hora de la última lectura
	 * @param ultimaCalibracion, 
	 * @param fechaInstalacion, fecha de instalación del sensor
	 * @param tipo, tipo de unidad en la que se mide
	 */
	public SensorTemperatura(String id, double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura,
			LocalTime ultimaCalibracion, LocalTime fechaInstalacion,TipoTemp tipo) {
		super(id, offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion);
		this.tipo = tipo;
	}
	
	/**
	 * Crea un nuevo sensor de temperatura por defecto
	 * 
	 * @param offset, offset de calibración
	 * @param ultimaLectura, valor de la última lectura
	 * @param tiempoUltimaLectura, fecha y hora de la última lectura
	 * @param fechaInstalacion, fecha de instalación del sensor
	 * @param ultimaCalibracion
	 */
	public SensorTemperatura(double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura, LocalTime fechaInstalacion,
			LocalTime ultimaCalibracion) {
		this(idType+ids, offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion,TipoTemp.CELSIUS);
		ids++;
	}
}
