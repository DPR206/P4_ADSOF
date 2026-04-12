package estrategias;

import java.util.*;

/**
 * Esta clase representa un tipo de estrategia de lectura que genera un valor cercano a la media de valores anteriores
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera
 * @version 1.0
 * Nombre del fichero: EstrategiaMEdia.java
 */
public class EstrategiaMedia implements Estrategia {

	private static final double valorMinimo = 1.0;
	private List<Double> historial = new ArrayList<>();
    private double porcentaje;

    /**
     * Constructor de la estrategia media
     * @param valorInicial Primer valor que se añade en el historial
     * @param porcentaje Desviación máxima del nuevo valor respecto del anterior
     */
    public EstrategiaMedia(double valorInicial, double porcentaje) {
        this.historial.add(valorInicial);
        this.porcentaje = porcentaje;
    }

    @Override
    public double generarValor() {
        double media = 0;
		for(Double d : historial) {
			media += d;
		}
		media/=historial.size();
		
		double base = (Math.abs(media) < 1e-9) ? valorMinimo : Math.abs(media);
        double variacion = base * porcentaje / 100.0;
        double nuevo = media + (Math.random() * 2 - 1) * variacion;
        historial.add(nuevo);
        return nuevo;
    }
}
