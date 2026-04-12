package estrategias;

import java.util.Random;

/**
 * Esta clase representa un tipo de estrategia de lectura que genera un valor
 * aleatorio en un rango
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera
 * @version 1.0 Nombre del fichero: EstrategiaAleatoria.java
 */
public class EstrategiaAleatoria implements Estrategia {

	private double min;
	private double max;
	private double probFueraRango;
	private Random rand = new Random();

	/**
	 * Constructor de la estrategia aleatoria
	 * 
	 * @param min            Valor mínimo del rango
	 * @param max            Valor máximo del rango
	 * @param probFueraRango Probabilidad de que el valor se encuentre fuera del
	 *                       rango
	 */
	public EstrategiaAleatoria(double min, double max, double probFueraRango) {
		this.min = min;
		this.max = max;
		this.probFueraRango = probFueraRango;
	}

	@Override
	public double generarValor() {
		if (rand.nextDouble() * 100 < probFueraRango) {
			// Fuera de rango
			if (rand.nextBoolean()) {
				return min - rand.nextDouble() * (max - min);
			} else {
				return max + rand.nextDouble() * (max - min);
			}
		} else {
			// Dentro del rango normal
			return min + (max - min) * rand.nextDouble();
		}
	}

}
