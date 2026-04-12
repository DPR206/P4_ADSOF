package procesadores.conversoresTemperatura;

import sensores.TipoTemp;

/**
 * Esta clase representa un conversor de Kelvin a Farenheit
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera.
 * @version 1.0 Nombre del fichero: ConversorKelvinAFarenheit.java
 * 
 */
public class ConversorKelvinAFarenheit extends ConversorTemperatura {
	
	/**
	 * Crea un nuevo conversor de Kelvin a Farenheit
	 */
	public ConversorKelvinAFarenheit() {
		super(TipoTemp.KELVIN, TipoTemp.FAHRENHEIT);
	}

	@Override
	public double convertir(double valor) {
		return (valor - 273.15) * 9 / 5 + 32;
	}
	
	@Override
	public String toString() {
		return " con conversor a ºF";
	}
}
