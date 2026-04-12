package procesadores.conversoresPresion;

public class hPaAMbar extends ConversorPresion {
	@Override
	public double convertir(double valor) {
		return valor;
	}
	
	@Override
	public String toString() {
		return " con conversor a mBar";
	}
}
