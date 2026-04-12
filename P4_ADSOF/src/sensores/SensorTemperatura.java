/**
 * Este paquete contiene las clases necesarias para gestionar sensores
 */
package sensores;

import estrategias.Estrategia;
import estrategias.EstrategiaAleatoria;
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
	private static final double valorInicial = (cotaInferior+cotaSuperior)/2;
	private static final double KelvinCelsius = -273.15;
	private static final Estrategia estrategiaPorDefecto = new EstrategiaAleatoria(cotaInferior, cotaSuperior, 0);
	
	private TipoTemp tipo;
	
	/**
	 * Crea un nuevo sensor de temperatura por defecto
	 * 
	 * @param offset, offset de calibración
	 * @param procesador, el procesador del sensor
	 * @throws IncompatibleConversorException error por conversor incompatible
	 */
	public SensorTemperatura(double offset, Procesador procesador) throws IncompatibleConversorException {
		super(idType+String.format("%04d", ids), offset, valorInicial, estrategiaPorDefecto, procesador);
		if(!(procesador.getConversor() instanceof ConversorTemperatura) && !(procesador.getConversor() instanceof ConversorIdentidad)) throw new IncompatibleConversorException("Este sensor debe tener un conversor de temperatura");
		ids++;
		this.tipo = TipoTemp.CELSIUS;
	}
	
	/**
	 * Crea un nuevo sensor de temperatura por defecto
	 * 
	 * @param offset, offset de calibración
	 * @param estrategia estrtegia de lectura
	 * @param procesador, el procesador del sensor
	 * @throws IncompatibleConversorException error por conversor incompatible
	 */
	public SensorTemperatura(double offset, Estrategia estrategia, Procesador procesador) throws IncompatibleConversorException {
		super(idType+String.format("%04d", ids), offset, valorInicial, estrategia, procesador);
		if(!(procesador.getConversor() instanceof ConversorTemperatura) && !(procesador.getConversor() instanceof ConversorIdentidad)) throw new IncompatibleConversorException("Este sensor debe tener un conversor de temperatura");
		ids++;
		this.tipo = TipoTemp.CELSIUS;
	}

	/**
	 * Obtiene el "tipo" de temperatura que se está midiendo
	 * @return el tipo de temperatura
	 */
	public TipoTemp getTipo() {
		return tipo;
	}

	/**
	 * Establece el "tipo" de temperatura que se está midiendo
	 * @param tipo the tipo to set
	 */
	public void setTipo(TipoTemp tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Covnierte el valor a unidades Celsius
	 * @param valor el valor a convertir
	 * @return el valor en Celsius
	 */
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
	 * Convierte valores de Kelvin a Celsius
	 * @param valor el valor en Kelvin
	 * @return el valor en Celsius
	 */
	private double kelvinCelsius(double valor) {
		double valorCelsius;
		valorCelsius = valor - SensorTemperatura.KelvinCelsius;
		return valorCelsius;
	}

	/**
	 * Convierte el valor de Fahrenheit a Celius
	 * @param valor el valor en Fahrenheit
	 * @return el valor en Celsius
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
	public String detallesHijo() {
		return " Sensor Temperatura (" + this.ultimaLectura();
	}
	
	@Override
	public String infoProcesador() {
		StringBuilder sb = new StringBuilder();

		sb.append(super.getId() + " (" + tipo.getUnidad() + ")");
		sb.append(super.getProcesador());
		return sb.toString();
	}
	
	
}
