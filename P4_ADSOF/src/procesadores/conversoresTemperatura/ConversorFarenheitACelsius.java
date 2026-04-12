package procesadores.conversoresTemperatura;

public class ConversorFarenheitACelsius extends ConversorTemperatura {
	@Override
	public double convertir(double valor) {
		return valor * 9.0 / 5.0 + 32;
	}
	
	@Override
	public String toString() {
		return " con conversor a Cº";
	}
}
