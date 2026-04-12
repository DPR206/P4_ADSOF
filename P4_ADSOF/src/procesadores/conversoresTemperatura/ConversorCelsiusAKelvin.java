package procesadores.conversoresTemperatura;

import sensores.TipoTemp;

/**
 * Esta clase representa un conversor de Celsius a Kelvin
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera.
 * @version 1.0 Nombre del fichero: ConversorCelsiusAKelvin.java
 * 
 */
public class ConversorCelsiusAKelvin extends ConversorTemperatura {

	/**
	 * Crea un conversor de celsius a kelvin
	 */
	public ConversorCelsiusAKelvin() {
		super(TipoTemp.CELSIUS, TipoTemp.KELVIN);
	}

	@Override
	public double convertir(double valor) {
		return valor + 273.15;
	}
	
	@Override
	public String toString() {
		return " con conversor a ºK";
	}
}
