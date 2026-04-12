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
	private static final Estrategia estrategiaPorDefecto = new EstrategiaCercana(0, 10);
	private static final double primeraLectura = 0;
	
	private String id;
	private double offsetCalibracion;
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
	 * @param offsetCalibracion offset de calibración
	 * @param procesador procesador de datos
	 * @param caducidad duración de la caducidad de la calibración
	 * @param cambioBrusco porcentaje de cambio brusco entre lecturas
	 */
	public Sensor(String id, double offsetCalibracion, Procesador procesador, Duration caducidad, long cambioBrusco) {
		
		this(id, offsetCalibracion, estrategiaPorDefecto, procesador, caducidad, cambioBrusco);
	}
	
	/**
	 * Crea un nuevo sensor
	 * 
	 * @param id el identificador del sensor
	 * @param offsetCalibracion offset de calibración
	 * @param estrategia estrategia de toma de valores
	 * @param procesador procesador de datos
	 * @param caducidad duración de la caducidad de la calibración
	 * @param cambioBrusco porcentaje de cambio brusco entre lecturas
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

	/**
	 * Crea un nuevo sensor
	 * 
	 * @param id el identificador del sensor
	 * @param offsetCalibracion offset de calibración
	 * @param procesador procesador de datos
	 */
	public Sensor(String id, double offsetCalibracion, Procesador procesador) {
		this(id, offsetCalibracion, procesador, Sensor.caducidadPorDefecto, Sensor.cambioBruscoPorDefecto);
	}
	
	/**
	 * Crea un nuevo sensor
	 * 
	 * @param id el identificador del sensor
	 * @param offsetCalibracion offset de calibración
	 * @param estrategia estrategia de toma de valores
	 * @param procesador procesador de datos
	 */
	public Sensor(String id, double offsetCalibracion, Estrategia estrategia, Procesador procesador) {
		this(id, offsetCalibracion, estrategia, procesador, Sensor.caducidadPorDefecto, Sensor.cambioBruscoPorDefecto);
	}

	/**
	 * Crea un nuevo sensor
	 * 
	 * @param id el identificador del sensor
	 * @param offsetCalibracion offset de calibración
	 * @param estrategia estrategia de toma de valores
	 * @param procesador procesador de datos
	 * @param caducidad duración de la caducidad de la calibración
	 */
	public Sensor(String id, double offsetCalibracion, Estrategia estrategia, Procesador procesador, Duration caducidad) {
		
		this(id, offsetCalibracion, estrategia, procesador, caducidad, Sensor.cambioBruscoPorDefecto);
	}

	/**
	 * Crea un nuevo sensor
	 * 
	 * @param id el identificador del sensor
	 * @param offset offset de calibración
	 * @param estrategia estrategia de toma de valores
	 * @param procesador procesador de datos
	 * @param cambioBrusco porcentaje de cambio brusco entre lecturas
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
	 * @param dias la nueva duración de la caducidad en días
	 */
	public void cambiarCaducidad(int dias) {
		this.caducidad = Duration.ofDays(dias);
	}
	
	/**
	 * Cambia la caduciada de la calibración
	 * @param date fecha de fin de la calibración
	 */
	public void cambiarCaducidad(LocalDate date) {
		this.caducidad = Duration.between(LocalDate.now(), date);
	}

	/**
	 * Obtiene la caducidad por defecto de un sensor
	 * @return la caducidad por defecto
	 */
	public static Duration getCaducidadPorDefecto() {
		return caducidadPorDefecto;
	}
	
	/**
	 * Obtiene la medida de cambio brusco por defecto
	 * @return el cambio brusco por defecto (en porcentaje)
	 */
	public static long getCambiobruscopordefecto() {
		return cambioBruscoPorDefecto;
	}

	/**
	 * Obtiene la medidad de cambio brusco (en porcentaje)
	 * @return el cambio brusco
	 */
	public long getCambioBrusco() {
		return cambioBrusco;
	}

	/**
	 * Establce una nueva medidad de cambio brusco (en porcentaje)
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
	 * @param offsetCalibracion el nuevo offset
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
	 * Determina si ha habido un cambio brusco entre las lecturas
	 * @param valor el valor de la lectura más reciente
	 * @return true si ha habido un cambio brusco, false if else
	 */
	private boolean cambioBrusco(double valor) {
		double diferencia = ((valor - ultimaLectura)/ultimaLectura)*100;
		if(diferencia > cambioBrusco)
			return true;
		return false;
	}

	/**
	 * El sensor realiza una nueva lectura
	 * @throws CambioBrusco error por cambio brusco entre la lectura anterior y esta
	 */
	public void realizarLectura() throws CambioBrusco{
		double valor = estrategia.generarValor()-offsetCalibracion;
		String lecturaAnterior = this.ultimaLectura();
		this.setUltimaLectura(valor);
		this.setTiempoUltimaLectura(LocalDateTime.now());
		this.procesador.procesar(valor);
		if(this.cambioBrusco(valor))
			throw new CambioBrusco(this, lecturaAnterior, LocalDateTime.now());
	}

	/**
	 * Comprobar que un sensor está calibrado
	 * @return true si está calibrado, false si no lo está
	 * @throws CalibracionCaducada error porque el sensor no está calibrado
	 * @throws LecturaFueraRango error por una lectura fuera de rango
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
	
	/**
	 * Obtiene el string con la información específica de los sensores
	 * @return string con la información de las subclases hijas
	 */
	public abstract String detallesHijo();
}
