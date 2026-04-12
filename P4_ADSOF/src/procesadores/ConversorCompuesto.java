package procesadores;

import excepciones.IncompatibleConversorException;
import procesadores.conversoresPresion.ConversorPresion;
import procesadores.conversoresTemperatura.ConversorTemperatura;

/**
 * Esta clase representa un conversor compuestode dos conversores
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera.
 * @version 1.0 Nombre del fichero: ConversorCompuesto.java
 * 
 */
public class ConversorCompuesto implements Conversor {
	private Conversor primero;
	private Conversor segundo;

	/**
	 * Crea un nuevo conversor compuesto
	 * 
	 * @param primero Primer conversor
	 * @param segundo Segundo conversor
	 * @throws IncompatibleConversorException Error cuando las unidades de los conversores no cuadran
	 */
	public ConversorCompuesto(Conversor primero, Conversor segundo) throws IncompatibleConversorException {
		if (primero instanceof ConversorTemperatura ct1 && segundo instanceof ConversorTemperatura ct2) {
			if (!ct1.getSalida().equals(ct2.getEntrada()))
				throw new IncompatibleConversorException("La salida del primero (" + ct1.getSalida()
						+ ") no es compatible con la entrada del segundo (" + ct2.getEntrada() + ")");
		}
		if (primero instanceof ConversorPresion && segundo instanceof ConversorPresion) {
			throw new IncompatibleConversorException("La salida del primero no es compatible con la entrada del segundo");
		}
		this.primero = primero;
		this.segundo = segundo;
	}

	/**
	 * Devuelve el primer conversor que lo forma
	 * 
	 * @return EL primer conversor
	 */
	public Conversor getPrimero() {
		return primero;
	}

	/**
	 * Devuelve el segundo conversor que lo forma
	 * 
	 * @return EL segundo conversor
	 */
	public Conversor getSegundo() {
		return segundo;
	}

	@Override
	public double convertir(double valor) {
		return segundo.convertir(primero.convertir(valor));
	}

	@Override
	public String toString() {
		return segundo.toString();
	}
}
