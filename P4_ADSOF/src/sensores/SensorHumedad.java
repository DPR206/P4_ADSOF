/**
 * Este paquete contiene las clases necesarias para gestionar sensores
 */
package sensores;

import java.time.Duration;

import estrategias.*;
import excepciones.IncompatibleConversorException;
import procesadores.*;

/**
 * Esta clase representa un sensor de humedad
 * @author Claudia Saiz Escribano y Duna Puente Romera. 
 * @version 1.0
 * Nombre del fichero: SensorHumedad.java
 * 
 */
public class SensorHumedad extends Sensor{
	
	private static int ids = 0;
	private static String idType = "HUM_";
	private static double cotaInferior = 0;
	private static double cotaSuperior = 100;
	private static final double valorInicial = (cotaInferior+cotaSuperior)/2;
	private static String unidad = "%";
	private static final Estrategia estrategiaPorDefecto = new EstrategiaAleatoria(cotaInferior, cotaSuperior, 0);
	
	/**
	 * Crea un nuevo sensor de humedad
	 * 
	 * @param offset, offset de calibración
	 * @param procesador, el procesador del sensor
	 * @throws IncompatibleConversorException error por conversor incompatible
	 */
	public SensorHumedad(double offset, Procesador procesador) throws IncompatibleConversorException {
		this(offset, estrategiaPorDefecto, procesador, getCaducidadPorDefecto(), getCambiobruscopordefecto());
	}
	
	/**
	 * Crea un nuevo sensor de humedad
	 * 
	 * @param offset, offset de calibración
	 * @param estrategia la estrategia para realizar lecturas
	 * @param procesador, el procesador del sensor
	 * @throws IncompatibleConversorException error por conversor incompatible
	 */
	public SensorHumedad(double offset, Estrategia estrategia, Procesador procesador) throws IncompatibleConversorException {
		this(offset, estrategia, procesador, getCaducidadPorDefecto(), getCambiobruscopordefecto());
	}
	
	/**
	 * Crea un nuevo sensor de humedad
	 * 
	 * @param offset, offset de calibración
	 * @param procesador, el procesador del sensor
	 * @param caducidad el tiempo hasta caducar el sensor
	 * @throws IncompatibleConversorException error por conversor incompatible
	 */
	public SensorHumedad(double offset, Procesador procesador, Duration caducidad) throws IncompatibleConversorException {
		this(offset, estrategiaPorDefecto, procesador, caducidad, getCambiobruscopordefecto());
	}
	
	/**
	 * Crea un nuevo sensor de humedad
	 * 
	 * @param offset, offset de calibración
	 * @param procesador, el procesador del sensor
	 * @param cambioBrusco el porcentaje máximo de cambio que se permite
	 * @throws IncompatibleConversorException error por conversor incompatible
	 */
	public SensorHumedad(double offset, Procesador procesador, double cambioBrusco) throws IncompatibleConversorException {
		this(offset, estrategiaPorDefecto, procesador, getCaducidadPorDefecto(), cambioBrusco);
	}
	
	/**
	 * Crea un nuevo sensor de humedad
	 * 
	 * @param offset, offset de calibración
	 * @param estrategia estrategia de lectura
	 * @param procesador, el procesador del sensor
	 * @param caducidad el tiempo hasta caducar el sensor
	 * @param cambioBrusco el porcentaje máximo de cambio que se permite
	 * @throws IncompatibleConversorException error por conversor incompatible
	 */
	public SensorHumedad(double offset, Estrategia estrategia, Procesador procesador, Duration caducidad, double cambioBrusco) throws IncompatibleConversorException {
		super(idType+String.format("%04d", ids), offset, valorInicial, estrategia, procesador, caducidad, cambioBrusco);
		conversorCorrecto(procesador.getConversor());
		ids++;
	}
	
	/**
	 * Comprueba que el tipo del conversor es acorde con el tipo de sensor
	 * @param conversor Conversor que se quiere asociar
	 * @throws IncompatibleConversorException Error con el tipo de conversor
	 */
	private void tipoConversorCorrecto(Conversor conversor) throws IncompatibleConversorException {
		if (!(conversor instanceof ConversorIdentidad) && !(conversor instanceof ConversorCompuesto))
			throw new IncompatibleConversorException("Este sensor debe tener un conversor identidad");
	}

	/**
	 * Comprueba en general que el conversor es acorde con el sensor al que se quiere asociar
	 * @param conversor Conversor que se quiere asociar
	 * @throws IncompatibleConversorException Error con el conversor
	 */
	private void conversorCorrecto(Conversor conversor) throws IncompatibleConversorException {
		tipoConversorCorrecto(conversor);
		
		if (conversor instanceof ConversorCompuesto) {
			ConversorCompuesto compuesto = (ConversorCompuesto) conversor;
			conversorCorrecto(compuesto.getPrimero());
			tipoConversorCorrecto(compuesto.getSegundo());
			
		}
	}

	@Override
	public String ultimaLectura() {
		return this.getUltimaLectura()+SensorHumedad.unidad;
	}

	@Override
	public boolean lecturaEnRango(double valor) {
		if(valor < SensorHumedad.cotaInferior || valor > SensorHumedad.cotaSuperior)
			return false;
		return true;
	}

	@Override
	public String detallesHijo() {
		return " Sensor Humedad (" + this.ultimaLectura();
	}
	
	@Override
	public String infoProcesador() {
		StringBuilder sb = new StringBuilder();

		sb.append(super.getId() + " (" + unidad + ")");
		sb.append(super.getProcesador());
		return sb.toString();
	}
	
	
}
