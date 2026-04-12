/**
 * Este paquete contiene las clases necesarias para gestionar sensores
 */
package sensores;

import java.time.Duration;

import estrategias.Estrategia;
import estrategias.EstrategiaAleatoria;
import excepciones.IncompatibleConversorException;
import procesadores.Conversor;
import procesadores.ConversorCompuesto;
import procesadores.ConversorIdentidad;
import procesadores.Procesador;
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
	private static final double valorInicial = (cotaInferior+cotaSuperior)/2;
	private static String unidad = "hPa";
	private static final Estrategia estrategiaPorDefecto = new EstrategiaAleatoria(cotaInferior, cotaSuperior, 0);
	
	/**
	 * Crea un nuevo sensor de presión
	 * @param offset offset de calibración
	 * @param procesador procesador de datos
	 * @throws IncompatibleConversorException error por conversor incompatible
	 */
	public SensorPresion(double offset, Procesador procesador) throws IncompatibleConversorException {
		this(offset, estrategiaPorDefecto, procesador, getCaducidadPorDefecto(), getCambiobruscopordefecto());
	}
	
	/**
	 * Crea un nuevo sensor de presión
	 * @param offset offset de calibración
	 * @param estrategia estrategia de toma de valores
	 * @param procesador procesador de datos
	 * @throws IncompatibleConversorException error por conversor incompatible
	 */
	public SensorPresion(double offset, Estrategia estrategia, Procesador procesador) throws IncompatibleConversorException {
		this(offset, estrategia, procesador, getCaducidadPorDefecto(), getCambiobruscopordefecto());
	}
	
	/**
	 * Crea un nuevo sensor de presión
	 * @param offset offset de calibración
	 * @param procesador procesador de datos
	 * @param caducidad el tiempo hasta caducar el sensor
	 * @throws IncompatibleConversorException error por conversor incompatible
	 */
	public SensorPresion(double offset, Procesador procesador, Duration caducidad) throws IncompatibleConversorException {
		this(offset, estrategiaPorDefecto, procesador, caducidad, getCambiobruscopordefecto());
	}
	
	/**
	 * Crea un nuevo sensor de presión
	 * @param offset offset de calibración
	 * @param procesador procesador de datos
	 * @param cambioBrusco el porcentaje máximo de cambio que se permite
	 * @throws IncompatibleConversorException error por conversor incompatible
	 */
	public SensorPresion(double offset, Procesador procesador, double cambioBrusco) throws IncompatibleConversorException {
		this(offset, estrategiaPorDefecto, procesador, getCaducidadPorDefecto(), cambioBrusco);
	}
	
	/**
	 * Crea un nuevo sensor de presión
	 * @param offset offset de calibración
	 * @param estrategia estrategia de toma de valores
	 * @param procesador procesador de datos
	 * @param caducidad el tiempo hasta caducar el sensor
	 * @param cambioBrusco el porcentaje máximo de cambio que se permite
	 * @throws IncompatibleConversorException error por conversor incompatible
	 */
	public SensorPresion(double offset, Estrategia estrategia, Procesador procesador, Duration caducidad, double cambioBrusco) throws IncompatibleConversorException {
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
		if (!(conversor instanceof ConversorPresion) && !(conversor instanceof ConversorIdentidad) && !(conversor instanceof ConversorCompuesto))
			throw new IncompatibleConversorException("Este sensor debe tener un conversor de presión");
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
		return this.getUltimaLectura()+SensorPresion.unidad;
	}

	@Override
	public boolean lecturaEnRango(double valor) {
		if(valor < SensorPresion.cotaInferior || valor > SensorPresion.cotaSuperior) {
			
			System.out.println(valor + "esta saltando esto\n");
			
			return false;
		}
		return true;
	}

	@Override
	public String detallesHijo() {
		return " Sensor Presion (" + this.ultimaLectura();
	}
	
	@Override
	public String infoProcesador() {
		StringBuilder sb = new StringBuilder();

		sb.append(super.getId() + " (" + unidad + ")");
		sb.append(super.getProcesador());
		return sb.toString();
	}
	
	
}
