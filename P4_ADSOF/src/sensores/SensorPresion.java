/**
 * Este paquete contiene las clases necesarias para gestionar sensores
 */
package sensores;

import estrategias.Estrategia;
import estrategias.EstrategiaAleatoria;
import excepciones.IncompatibleConversorException;
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
	private static String unidad = "hPa";
	private static final Estrategia estrategiaPorDefecto = new EstrategiaAleatoria(cotaInferior, cotaSuperior, 0);
	
	/**
	 * Crea un nuevo sensor de presión
	 * @param offset offset de calibración
	 * @param procesador procesador de datos
	 * @throws IncompatibleConversorException error por conversor incompatible
	 */
	public SensorPresion(double offset, Procesador procesador) throws IncompatibleConversorException {
		super(idType+String.format("%04d", ids), offset, estrategiaPorDefecto, procesador);
		if(!(procesador.getConversor() instanceof ConversorPresion) && !(procesador.getConversor() instanceof ConversorIdentidad)) throw new IncompatibleConversorException("Este sensor debe tener un conversor de presión");
		ids++;
	}
	
	/**
	 * Crea un nuevo sensor de presión
	 * @param offset offset de calibración
	 * @param estrategia estrategia de toma de valores
	 * @param procesador procesador de datos
	 * @throws IncompatibleConversorException error por conversor incompatible
	 */
	public SensorPresion(double offset, Estrategia estrategia, Procesador procesador) throws IncompatibleConversorException {
		super(idType+String.format("%04d", ids), offset, estrategia, procesador);
		if(!(procesador.getConversor() instanceof ConversorPresion) && !(procesador.getConversor() instanceof ConversorIdentidad)) throw new IncompatibleConversorException("Este sensor debe tener un conversor de presión");
		ids++;
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
