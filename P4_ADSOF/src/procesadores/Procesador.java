package procesadores;

import java.time.LocalDateTime;
import java.util.*;

public class Procesador {
	private List<Valor> historial = new ArrayList<>();
	private Conversor conversor;

	public Procesador(Conversor conversor) {
		this.conversor = conversor;
	}

	public Conversor getConversor() {
		return conversor;
	}
	
	public void procesar(double valor) {
		double convertido = conversor.convertir(valor);
		historial.add(new Valor(convertido, LocalDateTime.now()));
	}

	public double getMin() {
		double min = historial.get(0).getValor();
		for(Valor v : historial) {
			if(v.getValor() < min) min = v.getValor();
		}
		return min;
	}

	public double getMax() {
		double max = historial.get(0).getValor();
		for(Valor v : historial) {
			if(v.getValor() > max) max = v.getValor();
		}
		return max;
	}

	public double getMedia() {
		double media = 0;
		for(Valor v : historial) {
			media += v.getValor();
		}
		return media/historial.size();
	}
	
	@Override
	public String toString() {
		String valores = "[";
		for(Valor v : historial) {
			valores += v.getValor();
			valores += ", ";
		}
		if (!historial.isEmpty()) {
		    valores = valores.substring(0, valores.length() - 2);
		}
		valores += "]";
		
		return valores + "-- MIN: " + this.getMin() + "-- MAX: " + this.getMax() + "-- MEDIA: " + this.getMedia();
	}
}
