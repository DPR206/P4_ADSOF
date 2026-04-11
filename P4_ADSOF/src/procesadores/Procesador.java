package procesadores;

import java.util.*;

public class Procesador {
	private List<Double> historial = new ArrayList<>();
	private Conversor conversor;

	public Procesador(Conversor conversor) {
		this.conversor = conversor;
	}

	public void procesar(double valor) {
		double convertido = conversor.convertir(valor);
		historial.add(convertido);
	}

	public double getMin() {
		return historial.stream().mapToDouble(Double::doubleValue).min().orElse(0);
	}

	public double getMax() {
		return historial.stream().mapToDouble(Double::doubleValue).max().orElse(0);
	}

	public double getMedia() {
		return historial.stream().mapToDouble(Double::doubleValue).average().orElse(0);
	}
}
