/**
 * Este paquete contiene las clases necesarias para gestionar sensores
 */
package sensores;

import java.time.*;
import estrategias.*;
import procesadores.Procesador;
/**
 * Esta clase representa un sensor
 * @author Claudia Saiz Escribano y Duna Puente Romera. 
 * @version 1.0
 * Nombre del fichero: Sensor.java
 * 
 */
public abstract class Sensor {
	private static final Duration caducidadPorDefecto = Duration.ofDays(365);
	
	private String id;
	private double offset;
	private double ultimaLectura;
	private LocalDateTime tiempoUltimaLectura;
	private LocalDateTime ultimaCalibracion; /*A lo mejor habría que quitarlo y poner duration como estática*/
	private LocalTime fechaInstalacion;
	private Estrategia estrategia;
	private Procesador procesador;
	private Duration caducidad;
	
	
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
	public Sensor(String id, double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura, LocalDateTime ultimaCalibracion, LocalTime fechaInstalacion, Procesador procesador) {
		this.id = id;
		this.offset = offset;
		this.ultimaLectura = ultimaLectura;
		this.tiempoUltimaLectura = tiempoUltimaLectura;
		this.ultimaCalibracion = ultimaCalibracion;
		this.fechaInstalacion = fechaInstalacion;
		this.estrategia = new EstrategiaCercana(ultimaLectura, 5);
		this.procesador = procesador;
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
	public Sensor(String id, double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura, LocalDateTime ultimaCalibracion, LocalTime fechaInstalacion, Estrategia estrategia, Procesador procesador) {
		this.id = id;
		this.offset = offset;
		this.ultimaLectura = ultimaLectura;
		this.tiempoUltimaLectura = tiempoUltimaLectura;
		this.ultimaCalibracion = ultimaCalibracion;
		this.fechaInstalacion = fechaInstalacion;
		this.estrategia = estrategia;
		this.procesador = procesador;
	}


	/**
	 * Obtiene el tiempo de caducidad de las calibraciones
	 * @return el tiempo de caducidad de la calibracion
	 */
	public Duration getCaducidad() {
		return caducidad;
	}


	/**
	 * Establece el tiempo de lecturas realizadas en rango
	 * @param caducidad la nueva duración de la caducidad
	 */
	public void setCaducidad(Duration caducidad) {
		this.caducidad = caducidad;
	}

	/**
	 * @return the caducidadPorDefecto
	 */
	public static Duration getCaducidadPorDefecto() {
		return caducidadPorDefecto;
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
		if(this.lecturaEnRango(ultimaLectura))
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

	public void realizarLectura() {
		this.setUltimaLectura(this.leerValorSimulado()-offset);
		this.setTiempoUltimaLectura(LocalDateTime.now());
	}

	/**
	 * Comprobar que un sensor está calibrado
	 * @return true si está calibrado, false si no lo está
	 */
	public boolean calibrado() {
		Duration amount = Duration.between(ultimaCalibracion, LocalDateTime.now());
		if(amount.compareTo(caducidad)>0)
			return false;
		else if(this.lecturaEnRango(ultimaLectura) == false)
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
	 * Determina si el valor de una lectura está dentro de rango
	 * @param valor el valor a comprobar
	 * @return true si el valor es válido, false en caso contrario
	 */
	public abstract boolean lecturaEnRango(double valor);

	@Override
	public String toString() {
		return id + " (desde: " + this.fechaInstalacion + "): "+ detallesHijo() + ") última lectura: " + this.ultimaLectura;
	}
	
	public abstract String detallesHijo();
}
