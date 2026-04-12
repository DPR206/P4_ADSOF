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
	private static final double primeraLectura = 0;
	
	private String id;
	private double offsetCalibracion;
	private double offsetLectura;
	private double ultimaLectura;
	private LocalDateTime tiempoUltimaLectura;
	private LocalDateTime ultimaCalibracion;
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
	public Sensor(String id, double offsetCalibracion, Estrategia estrategia, Procesador procesador, Duration caducidad, long cambioBrusco) {
		this.id = id;
		this.offsetCalibracion = offsetCalibracion;
		this.ultimaLectura = primeraLectura;
		this.tiempoUltimaLectura = LocalDateTime.now();
		this.ultimaCalibracion = LocalDateTime.now();
		this.fechaInstalacion = LocalDateTime.now();
		this.estrategia = estrategia;
		this.procesador = procesador;
		this.caducidad = caducidad;
		this.cambioBrusco = cambioBrusco;
	}
	
	public Sensor(String id, double offsetCalibracion, Estrategia estrategia, Procesador procesador) {
		this(id, offsetCalibracion, estrategia, procesador, Sensor.caducidadPorDefecto, Sensor.cambioBruscoPorDefecto);
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
	public Sensor(String id, double offsetCalibracion, Estrategia estrategia, Procesador procesador, Duration caducidad) {
		
		this(id, offsetCalibracion, estrategia, procesador, caducidad, Sensor.cambioBruscoPorDefecto);
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
	public Sensor(String id, double offset, Estrategia estrategia, Procesador procesador, long cambioBrusco) {
		
		this(id, offset, estrategia, procesador, Sensor.caducidadPorDefecto, cambioBrusco);
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
	
	private boolean cambioBrusco(double valor) {
		if (Math.abs(ultimaLectura) < 1e-9)
	        return false;
		
		double diferencia = ((valor - ultimaLectura)/ultimaLectura)*100;
		if(diferencia > cambioBrusco)
			return true;
		return false;
	}

	public void realizarLectura() throws CambioBrusco, LecturaFueraRango{
		double valor = estrategia.generarValor()-offsetCalibracion;
		
		if(this.lecturaEnRango(valor) == false)
			throw new LecturaFueraRango(this, LocalDateTime.now(), valor + "");
		
		String lecturaAnterior = this.ultimaLectura();
		this.setUltimaLectura(valor);
		this.setTiempoUltimaLectura(LocalDateTime.now());
		this.procesador.procesar(valor);
		if(this.cambioBrusco(valor)) throw new CambioBrusco(this, lecturaAnterior, LocalDateTime.now());
	}

	/**
	 * Comprobar que un sensor está calibrado
	 * @return true si está calibrado, false si no lo está
	 */
	public boolean calibrado() throws CalibracionCaducada {
		Duration amount = Duration.between(ultimaCalibracion, LocalDateTime.now());
		if(amount.compareTo(caducidad)>0) 
			throw new CalibracionCaducada(this, LocalDateTime.now());
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
		return id + " (desde: " + this.fechaInstalacion + "): "+ detallesHijo() + ") última lectura: " + this.tiempoUltimaLectura;
	}
	
	public abstract String detallesHijo();
}
