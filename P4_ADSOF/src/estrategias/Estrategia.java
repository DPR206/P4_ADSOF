package estrategias;

/**
 * Esta interfaz representa una estrategia de generación de valores simulados
 */
public interface Estrategia {
	
	/**
	 * Genera un valor simulado según la estrategia especificada
	 * @return Valor generado
	 */
	double generarValor();
}
