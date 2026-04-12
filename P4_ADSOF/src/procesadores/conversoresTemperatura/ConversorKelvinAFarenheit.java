package procesadores.conversoresTemperatura;

public class ConversorKelvinAFarenheit extends ConversorTemperatura {
	@Override
	public double convertir(double valor) {
		return (valor - 273.15) * 9 / 5 + 32;
	}
	
	@Override
	public String toString() {
		return " con conversor a ºF";
	}
}
