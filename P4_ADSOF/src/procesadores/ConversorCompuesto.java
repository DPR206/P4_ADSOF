package procesadores;

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
	 * @param primero Primer conversor
	 * @param segundo Segundo conversor
	 */
	public ConversorCompuesto(Conversor primero, Conversor segundo) {
		this.primero = primero;
		this.segundo = segundo;
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
