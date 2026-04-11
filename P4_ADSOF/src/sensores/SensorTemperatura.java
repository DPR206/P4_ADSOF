/**
 * Este paquete contiene las clases necesarias para gestionar sensores
 */
package sensores;

import java.time.*;

import excepciones.IncompatibleConversorException;
import procesadores.*;
import procesadores.conversoresTemperatura.ConversorTemperatura;

/**
 * Esta clase representa un sensor de temperatura
 * @author Claudia Saiz Escribano y Duna Puente Romera. 
 * @version 1.0
 * Nombre del fichero: SensorTemperatura.java
 * 
 */
public class SensorTemperatura extends Sensor {

	private static int ids = 0;
	private static String idType = "TEMP_";
	private static double cotaInferior = -273.15;
	private static double cotaSuperior = 1000;
	private static final double KelvinCelsius = -273.15;
	
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
	 * @throws IncompatibleConversorException 
	 */
	public SensorTemperatura(String id, double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura, LocalDateTime ultimaCalibracion, LocalTime fechaInstalacion,TipoTemp tipo, Procesador procesador) throws IncompatibleConversorException {
		super(id, offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion, procesador);
		if(!(procesador.getConversor() instanceof ConversorTemperatura)) throw new IncompatibleConversorException("Este sensor debe tener un convesor de temperatura");
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
	 * @throws IncompatibleConversorException 
	 */
	public SensorTemperatura(double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura, LocalTime fechaInstalacion, LocalDateTime ultimaCalibracion, Procesador procesador) throws IncompatibleConversorException {
		this(idType+String.format("%04d", ids), offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion,TipoTemp.CELSIUS, procesador);
		if(!(procesador.getConversor() instanceof ConversorTemperatura)) throw new IncompatibleConversorException("Este sensor debe tener un convesor de temperatura");
		ids++;
	}

	/**
	 * @return the tipo
	 */
	public TipoTemp getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TipoTemp tipo) {
		this.tipo = tipo;
	}
	
	private double convertCelsius(double valor) {
		double valorCelsius;
		switch(this.tipo) {
			case TipoTemp.KELVIN: valorCelsius = kelvinCelsius(valor); break;
			case TipoTemp.FAHRENHEIT: valorCelsius = fahrenheitCelsius(valor); break;
			default: valorCelsius = valor;
		}
		return valorCelsius;
	}

	/**
	 * @param valor
	 * @return
	 */
	private double kelvinCelsius(double valor) {
		double valorCelsius;
		valorCelsius = valor - SensorTemperatura.KelvinCelsius;
		return valorCelsius;
	}

	/**
	 * @param valor
	 * @return
	 */
	private double fahrenheitCelsius(double valor) {
		double valorCelsius;
		valorCelsius = (valor - 32)*5/9;
		return valorCelsius;
	}

	@Override
	public String ultimaLectura() {
		return this.getUltimaLectura()+this.tipo.getUnidad();
	}

	@Override
	public boolean lecturaEnRango(double valor) {
		double lectura = this.convertCelsius(valor);
		if(lectura < SensorTemperatura.cotaInferior || lectura > SensorTemperatura.cotaSuperior)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + " Sensor Temperatura (" + this.ultimaLectura() + ") última lectura: " + this.ultimaLectura();
	}
	
	
}
