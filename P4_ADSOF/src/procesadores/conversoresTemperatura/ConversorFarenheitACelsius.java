package procesadores.conversoresTemperatura;

/**
 * Esta clase representa un conversor de Farenheit a Celsius
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera.
 * @version 1.0 Nombre del fichero: ConversorFarenheitACelsius.java
 * 
 */
public class ConversorFarenheitACelsius extends ConversorTemperatura {
	
	/**
	 * Crea un nuevo conversor de Farenheit a Celsius
	 */
	public ConversorFarenheitACelsius() {
	}

	@Override
	public double convertir(double valor) {
		return valor * 9.0 / 5.0 + 32;
	}
	
	@Override
	public String toString() {
		return " con conversor a Cº";
	}
}
