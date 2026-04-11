package procesadores.conversoresPresion;

public class PaAMbar extends ConversorPresion {
	@Override
	public double convertir(double valor) {
		return valor / 100.0;
	}
}
