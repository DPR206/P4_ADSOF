package estrategias;

import java.util.List;

/**
 * Esta clase representa un tipo de estrategia de lectura que genera un valor cercano a la media de valores anteriores
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera
 * @version 1.0
 * Nombre del fichero: EstrategiaAleatoria.java
 */
public class EstrategiaMedia implements Estrategia {

	private List<Double> historial;
    private double porcentaje;

    /**
     * Constructor de la estrategia media
     * @param historial Lista de valores generados anteriormente
     * @param porcentaje Desviación máxima del nuevo valor respecto del anterior
     */
    public EstrategiaMedia(List<Double> historial, double porcentaje) {
        this.historial = historial;
        this.porcentaje = porcentaje;
    }

    @Override
    public double generarValor() {
        double media = historial.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        double variacion = media * porcentaje / 100;
        double nuevo = media + (Math.random() * 2 - 1) * variacion;
        historial.add(nuevo);
        
        return nuevo;
    }
}
