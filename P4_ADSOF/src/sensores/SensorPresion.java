/**
 * Este paquete contiene las clases necesarias para gestionar sensores
 */
package sensores;

import java.time.LocalDateTime;
import java.time.LocalTime;

import excepciones.IncompatibleConversorException;
import procesadores.*;
import procesadores.conversoresPresion.ConversorPresion;

/**
 * Esta clase representa un sensor de presión
 * @author Claudia Saiz Escribano y Duna Puente Romera. 
 * @version 1.0
 * Nombre del fichero: SensorPresion.java
 * 
 */
public class SensorPresion extends Sensor{

	private static int ids = 0;
	private static String idType = "PRES_";
	private static double cotaInferior = 300;
	private static double cotaSuperior = 1100;
	private static String unidad = "hPa";
	
	private Procesador procesador;
	
	/**
	 * @param id
	 * @param offset
	 * @param ultimaLectura
	 * @param tiempoUltimaLectura
	 * @param ultimaCalibracion
	 * @param fechaInstalacion
	 */
	public SensorPresion(String id, double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura,
			LocalDateTime ultimaCalibracion, LocalTime fechaInstalacion) {
		super(id, offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion);
		this.procesador = new Procesador(new ConversorIdentidad());
	}
	
	/**
	 * 
	 * @param offset
	 * @param ultimaLectura
	 * @param tiempoUltimaLectura
	 * @param ultimaCalibracion
	 * @param fechaInstalacion
	 */
	public SensorPresion(double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura,
			LocalDateTime ultimaCalibracion, LocalTime fechaInstalacion) {
		super(idType+String.format("%04d", ids), offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion);
		ids++;
		this.procesador = new Procesador(new ConversorIdentidad());
	}
	
	/**
	 * @param id
	 * @param offset
	 * @param ultimaLectura
	 * @param tiempoUltimaLectura
	 * @param ultimaCalibracion
	 * @param fechaInstalacion
	 */
	public SensorPresion(String id, double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura,
			LocalDateTime ultimaCalibracion, LocalTime fechaInstalacion, Conversor conversor) throws IncompatibleConversorException {
		super(id, offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion);
		if(!(conversor instanceof ConversorPresion)) throw new IncompatibleConversorException("Debe asociar un conversor de presión a este sensor");
		this.procesador = new Procesador(conversor);
	}
	
	/**
	 * 
	 * @param offset
	 * @param ultimaLectura
	 * @param tiempoUltimaLectura
	 * @param ultimaCalibracion
	 * @param fechaInstalacion
	 */
	public SensorPresion(double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura,
			LocalDateTime ultimaCalibracion, LocalTime fechaInstalacion, Conversor conversor) throws IncompatibleConversorException {
		super(idType+String.format("%04d", ids), offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion);
		ids++;
		if(!(conversor instanceof ConversorPresion)) throw new IncompatibleConversorException("Debe asociar un conversor de presión a este sensor");
		this.procesador = new Procesador(conversor);
	}
	
	@Override
	public String ultimaLectura() {
		return this.getUltimaLectura()+SensorPresion.unidad;
	}

	@Override
	public boolean valorValido(double valor) {
		if(valor < SensorPresion.cotaInferior || valor > SensorPresion.cotaSuperior)
			return false;
		return true;
	}

	
}
