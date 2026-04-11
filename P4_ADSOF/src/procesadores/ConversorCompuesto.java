package procesadores;

public class ConversorCompuesto implements Conversor {
	private Conversor primero;
	private Conversor segundo;

	public ConversorCompuesto(Conversor primero, Conversor segundo) {
		this.primero = primero;
		this.segundo = segundo;
	}

	@Override
	public double convertir(double valor) {
		return segundo.convertir(primero.convertir(valor));
	}
}
