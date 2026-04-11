/**
 * Este paquete contiene las clases necesarias para gestionar sensores
 */
package sensores;

import java.time.*;
import estrategias.*;
/**
 * Esta clase representa un sensor
 * @author Claudia Saiz Escribano y Duna Puente Romera. 
 * @version 1.0
 * Nombre del fichero: Sensor.java
 * 
 */
public abstract class Sensor {
	private static Duration caducidad;
	//duration
	
	private String id;
	private double offset;
	private double ultimaLectura;
	private LocalDateTime tiempoUltimaLectura;
	private LocalDateTime ultimaCalibracion; /*A lo mejor habría que quitarlo y poner duration como estática*/
	private LocalTime fechaInstalacion;
	private Estrategia estrategia;
	
	
	/**
	 * Crea un nuevo sensor
	 * 
	 * @param id el identificador del sensor
	 * @param offset offset de calibración
	 * @param ultimaLectura valor de la última lectura
	 * @param tiempoUltimaLectura fecha y hora de la última lectura
	 * @param ultimaCalibracion fecha y hora de la última calibración
	 * @param fechaInstalacion fecha de instalación del sensor
	 */
	public Sensor(String id, double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura, LocalDateTime ultimaCalibracion,
			LocalTime fechaInstalacion) {
		this.id = id;
		this.offset = offset;
		this.ultimaLectura = ultimaLectura;
		this.tiempoUltimaLectura = tiempoUltimaLectura;
		this.ultimaCalibracion = ultimaCalibracion;
		this.fechaInstalacion = fechaInstalacion;
		this.estrategia = new EstrategiaCercana(ultimaLectura, 5);
	}
	
	/**
	 * Crea un nuevo sensor
	 * 
	 * @param id el identificador del sensor
	 * @param offset offset de calibración
	 * @param ultimaLectura valor de la última lectura
	 * @param tiempoUltimaLectura fecha y hora de la última lectura
	 * @param ultimaCalibracion fecha y hora de la última calibración
	 * @param fechaInstalacion fecha de instalación del sensor
	 */
	public Sensor(String id, double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura, LocalDateTime ultimaCalibracion,
			LocalTime fechaInstalacion, Estrategia estrategia) {
		this.id = id;
		this.offset = offset;
		this.ultimaLectura = ultimaLectura;
		this.tiempoUltimaLectura = tiempoUltimaLectura;
		this.ultimaCalibracion = ultimaCalibracion;
		this.fechaInstalacion = fechaInstalacion;
		this.estrategia = estrategia;
	}


	/**
	 * Obtiene el tiempo de caducidad de las calibraciones
	 * @return el tiempo de caducidad de la calibracion
	 */
	public static Duration getCaducidad() {
		return caducidad;
	}


	/**
	 * Establece el tiempo de lecturas realizadas en rango
	 * @param caducidad la nueva duración de la caducidad
	 */
	public static void setCaducidad(Duration caducidad) {
		Sensor.caducidad = caducidad;
	}


	/**
	 * Obtiene el offset de calibración
	 * @return el offset 
	 */
	public double getOffset() {
		return offset;
	}


	/**
	 * Establece el offset
	 * @param offset el nuevo offset
	 */
	public void setOffset(double offset) {
		this.offset = offset;
	}


	/**
	 * Obtiene el valor de la última lectura
	 * @return la última Lectura
	 */
	public double getUltimaLectura() {
		return ultimaLectura;
	}


	/**
	 * Establece el valor de la última lectura
	 * @param ultimaLectura el valor de la última lectura
	 */
	public void setUltimaLectura(double ultimaLectura) {
		if(this.valorValido(ultimaLectura))
			this.ultimaLectura = ultimaLectura;
	}


	/**
	 * Obtiene la fecha y hora de la última lectura
	 * @return el tiempo de la última lectura
	 */
	public LocalDateTime getTiempoUltimaLectura() {
		return tiempoUltimaLectura;
	}


	/**
	 * Establece la fecha y hora de la última lectura
	 * @param tiempoUltimaLectura la fecha y hora de la última lectura
	 */
	public void setTiempoUltimaLectura(LocalDateTime tiempoUltimaLectura) {
		this.tiempoUltimaLectura = tiempoUltimaLectura;
	}


	/**
	 * Obtiene la fecha y hora de la última calibración
	 * @return el tiempo de la última calibración
	 */
	public LocalDateTime getUltimaCalibracion() {
		return ultimaCalibracion;
	}


	/**
	 * Establece la fecha y hora de la última calibración
	 * @param ultimaCalibracion el tiempo de la última calibración
	 */
	public void setUltimaCalibracion(LocalDateTime ultimaCalibracion) {
		this.ultimaCalibracion = ultimaCalibracion;
	}
	
	
	/**
	 * Obtiene la fecha de instalación del sensor
	 * @return la fecha de instalación
	 */
	public LocalTime getFechaInstalacion() {
		return fechaInstalacion;
	}


	/**
	 * Establece la fecha de instalación del sensor
	 * @param fechaInstalacion la fecha de instalación
	 */
	public void setFechaInstalacion(LocalTime fechaInstalacion) {
		this.fechaInstalacion = fechaInstalacion;
	}


	/**
	 * Obtiene el id del sensor
	 * @return el identificador
	 */
	public String getId() {
		return id;
	}


	/**
	 * Comprobar que una lectura se ha realizado en rango
	 * @return true si se ha hecho en rango, false si se hace fuera de rango
	 */
	public boolean lecturaEnRango() {
		Duration amount = Duration.between(ultimaCalibracion, LocalDateTime.now());
		if(amount.compareTo(caducidad)>0)
			return false;
		return true;
	}
	
	/**
	 * Devuelve un valor simulado conforme a la estrategia del sensor
	 * @return Valor simulado generado
	 */
	public double leerValorSimulado() {
	        return estrategia.generarValor();
	    }

	/**
	 * Obtiene la última lectura con unidades
	 * @return String con el valor de la última lectura y las unidades
	 */
	public abstract String ultimaLectura();
	
	/**
	 * Determina si el valor es valido de acuerdo al tipo de sensor
	 * @param valor el valor a comprobar
	 * @return true si el valor es válido, false en caso contrario
	 */
	public abstract boolean valorValido(double valor);
	
	/**
	 * Determina si el valor de temperatura es válido
	 * @param valor el valor a comprobar
	 * @param tipo el tipo de temperatura
	 * @return true si el valor es válido, false en caso contrario
	 */
	//public abstract boolean valorValido(double valor, TipoTemp tipo);
}
