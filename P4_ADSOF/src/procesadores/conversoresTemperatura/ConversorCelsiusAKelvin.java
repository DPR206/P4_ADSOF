package procesadores.conversoresTemperatura;

public class ConversorCelsiusAKelvin extends ConversorTemperatura {

	@Override
	public double convertir(double valor) {
		return valor + 273.15;
	}
	
	@Override
	public String toString() {
		return " con conversor a ºK";
	}
}
