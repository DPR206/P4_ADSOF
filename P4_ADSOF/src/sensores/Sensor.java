/**
 * Este paquete contiene las clases necesarias para gestionar sensores
 */
package sensores;

import java.time.*;
import java.time.temporal.*;
/**
 * Esta clase representa un sensor
 * @author Claudia Saiz Escribano y Duna Puente Romera. 
 * @version 1.0
 * Nombre del fichero: Sensor.java
 * 
 */
public abstract class Sensor {
	private static TemporalAmount caducidad;
	//duration
	
	private String id;
	private double offset;
	private double ultimaLectura;
	private LocalTime tiempoUltimaLectura;
	private LocalTime ultimaCalibracion;
	
	
	/**
	 * Crea un nuevo sensor
	 * 
	 * @param offset, 
	 * @param ultimaLectura
	 * @param tiempoUltimaLectura
	 * @param ultimaCalibracion
	 */
	public Sensor(String id, double offset, double ultimaLectura, LocalTime tiempoUltimaLectura, LocalTime ultimaCalibracion) {
		this.id = id;
		this.offset = offset;
		this.ultimaLectura = ultimaLectura;
		this.tiempoUltimaLectura = tiempoUltimaLectura;
		this.ultimaCalibracion = ultimaCalibracion;
	}


	/**
	 * @return el tiempo de caducidad de la calibracion
	 */
	public static TemporalAmount getCaducidad() {
		return caducidad;
	}


	/**
	 * @param caducidad the caducidad to set
	 */
	public static void setCaducidad(TemporalAmount caducidad) {
		Sensor.caducidad = caducidad;
	}


	/**
	 * @return the offset
	 */
	public double getOffset() {
		return offset;
	}


	/**
	 * @param offset the offset to set
	 */
	public void setOffset(double offset) {
		this.offset = offset;
	}


	/**
	 * @return the ultimaLectura
	 */
	public double getUltimaLectura() {
		return ultimaLectura;
	}


	/**
	 * @param ultimaLectura the ultimaLectura to set
	 */
	public void setUltimaLectura(double ultimaLectura) {
		this.ultimaLectura = ultimaLectura;
	}


	/**
	 * @return the tiempoUltimaLectura
	 */
	public LocalTime getTiempoUltimaLectura() {
		return tiempoUltimaLectura;
	}


	/**
	 * @param tiempoUltimaLectura the tiempoUltimaLectura to set
	 */
	public void setTiempoUltimaLectura(LocalTime tiempoUltimaLectura) {
		this.tiempoUltimaLectura = tiempoUltimaLectura;
	}


	/**
	 * @return the ultimaCalibracion
	 */
	public LocalTime getUltimaCalibracion() {
		return ultimaCalibracion;
	}


	/**
	 * @param ultimaCalibracion the ultimaCalibracion to set
	 */
	public void setUltimaCalibracion(LocalTime ultimaCalibracion) {
		this.ultimaCalibracion = ultimaCalibracion;
	}
	
	

}
