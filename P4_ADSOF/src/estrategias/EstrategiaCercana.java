package estrategias;

/**
 * Esta clase representa un tipo de estrategia de lectura que genera un valor cercano al último
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera
 * @version 1.0
 * Nombre del fichero: EstrategiaAleatoria.java
 */
public class EstrategiaCercana implements Estrategia {

	private double valorAnterior;
    private double porcentaje;

    /**
     * Constructor de la estrategia cercana
     * @param valorInicial Valor inicial que consideraremos para la primera lectura
     * @param porcentaje Desviación máxima del nuevo valor respecto del anterior
     */
    public EstrategiaCercana(double valorInicial, double porcentaje) {
        this.valorAnterior = valorInicial;
        this.porcentaje = porcentaje;
    }

    @Override
    public double generarValor() {
        double variacion = valorAnterior * porcentaje / 100;
        double nuevo = valorAnterior + (Math.random() * 2 - 1) * variacion;
        valorAnterior = nuevo;
        return nuevo;
    }

}
