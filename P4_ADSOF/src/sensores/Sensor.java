/**
 * Este paquete contiene las clases necesarias para gestionar sensores
 */
package sensores;

import java.time.*;
import estrategias.*;
import excepciones.*;
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
	private static final long cambioBruscoPorDefecto = 50;
	
	private String id;
	private double offsetCalibracion;
	private double offsetLectura;
	private double ultimaLectura;
	private LocalDateTime tiempoUltimaLectura;
	private LocalDateTime ultimaCalibracion; /*A lo mejor habría que quitarlo y poner duration como estática*/
	private LocalDateTime fechaInstalacion;
	private Estrategia estrategia;
	private Procesador procesador;
	private Duration caducidad;
	private long cambioBrusco;
	
	
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
	public Sensor(String id, double offsetCalibracion, double ultimaLectura, LocalDateTime tiempoUltimaLectura, LocalDateTime ultimaCalibracion, 
					LocalDateTime fechaInstalacion, Procesador procesador, Duration caducidad, long cambioBrusco) {
		
		this(id, offsetCalibracion, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion, new EstrategiaCercana(ultimaLectura, 5),
				procesador, caducidad, cambioBrusco);
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
	public Sensor(String id, double offsetCalibracion, double ultimaLectura, LocalDateTime tiempoUltimaLectura, LocalDateTime ultimaCalibracion, 
				LocalDateTime fechaInstalacion, Estrategia estrategia, Procesador procesador, Duration caducidad, long cambioBrusco) {
		this.id = id;
		this.offsetCalibracion = offsetCalibracion;
		this.ultimaLectura = ultimaLectura;
		this.tiempoUltimaLectura = tiempoUltimaLectura;
		this.ultimaCalibracion = ultimaCalibracion;
		this.fechaInstalacion = fechaInstalacion;
		this.estrategia = estrategia;
		this.procesador = procesador;
		this.caducidad = caducidad;
		this.cambioBrusco = cambioBrusco;
	}

	public Sensor(String id, double offsetCalibracion, double ultimaLectura, LocalDateTime tiempoUltimaLectura, LocalDateTime ultimaCalibracion, 
			LocalDateTime fechaInstalacion, Procesador procesador) {
		this(id, offsetCalibracion, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion, procesador, 
				Sensor.caducidadPorDefecto, Sensor.cambioBruscoPorDefecto);
	}
	
	public Sensor(String id, double offsetCalibracion, double ultimaLectura, LocalDateTime tiempoUltimaLectura, LocalDateTime ultimaCalibracion, 
			LocalDateTime fechaInstalacion, Estrategia estrategia, Procesador procesador) {
		this(id, offsetCalibracion, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion, estrategia, procesador, 
				Sensor.caducidadPorDefecto, Sensor.cambioBruscoPorDefecto);
	}

	/**
	 * @param id
	 * @param offset
	 * @param ultimaLectura
	 * @param tiempoUltimaLectura
	 * @param ultimaCalibracion
	 * @param fechaInstalacion
	 * @param estrategia
	 * @param procesador
	 * @param caducidad
	 */
	public Sensor(String id, double offsetCalibracion, double ultimaLectura, LocalDateTime tiempoUltimaLectura,
			LocalDateTime ultimaCalibracion, LocalDateTime fechaInstalacion, Estrategia estrategia,
			Procesador procesador, Duration caducidad) {
		
		this(id, offsetCalibracion, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion, estrategia, procesador, 
				caducidad, Sensor.cambioBruscoPorDefecto);
	}

	/**
	 * @param id
	 * @param offset
	 * @param ultimaLectura
	 * @param tiempoUltimaLectura
	 * @param ultimaCalibracion
	 * @param fechaInstalacion
	 * @param estrategia
	 * @param procesador
	 * @param cambioBrusco
	 */
	public Sensor(String id, double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura,
			LocalDateTime ultimaCalibracion, LocalDateTime fechaInstalacion, Estrategia estrategia,
			Procesador procesador, long cambioBrusco) {
		
		this(id, offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion, estrategia, procesador, 
				Sensor.caducidadPorDefecto, cambioBrusco);
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
	 * @param caducidad la nueva duración de la caducidad en días
	 */
	public void cambiarCaducidad(int dias) {
		this.caducidad = Duration.ofDays(dias);
	}
	
	public void cambiarCaducidad(LocalDate date) {
		this.caducidad = Duration.between(LocalDate.now(), date);
	}

	/**
	 * @return the caducidadPorDefecto
	 */
	public static Duration getCaducidadPorDefecto() {
		return caducidadPorDefecto;
	}
	
	/**
	 * @return the cambiobruscopordefecto
	 */
	public static long getCambiobruscopordefecto() {
		return cambioBruscoPorDefecto;
	}

	/**
	 * @return the cambioBrusco
	 */
	public long getCambioBrusco() {
		return cambioBrusco;
	}

	/**
	 * @param cambioBrusco the cambioBrusco to set
	 */
	public void setCambioBrusco(long cambioBrusco) {
		this.cambioBrusco = cambioBrusco;
	}

	/**
	 * Obtiene el offset de calibración
	 * @return el offset 
	 */
	public double getOffset() {
		return offsetCalibracion;
	}


	/**
	 * Establece el offset
	 * @param offset el nuevo offset
	 */
	public void setOffset(double offsetCalibracion) {
		this.offsetCalibracion = offsetCalibracion;
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
	public LocalDateTime getFechaInstalacion() {
		return fechaInstalacion;
	}


	/**
	 * Establece la fecha de instalación del sensor
	 * @param fechaInstalacion la fecha de instalación
	 */
	public void setFechaInstalacion(LocalDateTime fechaInstalacion) {
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
	 * @return the offsetLectura
	 */
	public double getOffsetLectura() {
		return offsetLectura;
	}

	/**
	 * @param offsetLectura the offsetLectura to set
	 */
	public void setOffsetLectura(double offsetLectura) {
		this.offsetLectura = offsetLectura;
	}

	public void realizarLectura() {
		double valor = estrategia.generarValor()-offsetCalibracion;
		this.setUltimaLectura(valor);
		this.setTiempoUltimaLectura(LocalDateTime.now());
		this.procesador.procesar(valor);
	}

	/**
	 * Comprobar que un sensor está calibrado
	 * @return true si está calibrado, false si no lo está
	 */
	public boolean calibrado() throws CalibracionCaducada, LecturaFueraRango {
		Duration amount = Duration.between(ultimaCalibracion, LocalDateTime.now());
		if(amount.compareTo(caducidad)>0) 
			throw new CalibracionCaducada(this, LocalDateTime.now());
		else if(this.lecturaEnRango(ultimaLectura) == false)
			throw new LecturaFueraRango(this, LocalDateTime.now(), this.ultimaLectura());
		return true;
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
