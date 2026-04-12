package procesadores;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Esta clase representa un procesador de datos de un sensor
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera.
 * @version 1.0 Nombre del fichero: Procesador.java
 * 
 */
public class Procesador {
	private List<Valor> historial = new ArrayList<>();
	private Conversor conversor;

	/**
	 * Crea un nuevo procesador
	 * @param conversor Conversor asociado al procesador
	 */
	public Procesador(Conversor conversor) {
		this.conversor = conversor;
	}

	/**
	 * Devuelve el conversor del procesador
	 * @return El conversor
	 */
	public Conversor getConversor() {
		return conversor;
	}
	
	/**
	 * Procesa un nuevo dato
	 * @param valor Nuevo dato generado
	 */
	public void procesar(double valor) {
		double convertido = conversor.convertir(valor);
		historial.add(new Valor(convertido, LocalDateTime.now()));
	}

	/**
	 * Devuelve el mínimo valor procesado
	 * @return Mínimo valor procesado
	 */
	public double getMin() {
		double min = historial.get(0).getValor();
		for(Valor v : historial) {
			if(v.getValor() < min) min = v.getValor();
		}
		return min;
	}

	/**
	 * Devuelve el máximo valor procesado
	 * @return Máximo valor procesado
	 */
	public double getMax() {
		double max = historial.get(0).getValor();
		for(Valor v : historial) {
			if(v.getValor() > max) max = v.getValor();
		}
		return max;
	}

	/**
	 * Devuelve la media de valores procesados
	 * @return Media de valores procesados
	 */
	public double getMedia() {
		double media = 0;
		for(Valor v : historial) {
			media += v.getValor();
		}
		return media/historial.size();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(conversor + ": ");
		sb.append(historial);
		sb.append(" -- MIN: " + this.getMin() + " MAX: " + this.getMax() + " MEDIA: " + this.getMedia());
		return sb.toString();
	}
}
