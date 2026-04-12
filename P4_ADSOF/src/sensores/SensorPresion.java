/**
 * Este paquete contiene las clases necesarias para gestionar sensores
 */
package sensores;

import estrategias.Estrategia;
import excepciones.IncompatibleConversorException;
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
	private static String unidad = "hPa";
	
	/**
	 * Crea un nuevo sensor de presión
	 * @param offset
	 * @param procesador
	 * @throws IncompatibleConversorException 
	 */
	public SensorPresion(double offset, Procesador procesador) throws IncompatibleConversorException {
		super(idType+String.format("%04d", ids), offset, procesador);
		if(!(procesador.getConversor() instanceof ConversorPresion)) throw new IncompatibleConversorException("Este sensor debe tener un convesor de presión");
		ids++;
	}
	
	/**
	 * Crea un nuevo sensor de presión
	 * @param offset
	 * @param estrategia
	 * @param procesador
	 * @throws IncompatibleConversorException 
	 */
	public SensorPresion(double offset, Estrategia estrategia, Procesador procesador) throws IncompatibleConversorException {
		super(idType+String.format("%04d", ids), offset, estrategia, procesador);
		if(!(procesador.getConversor() instanceof ConversorPresion)) throw new IncompatibleConversorException("Este sensor debe tener un convesor de presión");
		ids++;
	}
	
	@Override
	public String ultimaLectura() {
		return this.getUltimaLectura()+SensorPresion.unidad;
	}

	@Override
	public boolean lecturaEnRango(double valor) {
		if(valor < SensorPresion.cotaInferior || valor > SensorPresion.cotaSuperior)
			return false;
		return true;
	}

	@Override
	public String detallesHijo() {
		return " Sensor Presion (" + this.ultimaLectura();
	}
	
	
}
