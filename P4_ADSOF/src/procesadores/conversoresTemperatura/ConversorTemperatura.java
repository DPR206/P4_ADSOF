package procesadores.conversoresTemperatura;

import procesadores.Conversor;
import sensores.TipoTemp;

/**
 * Esta clase representa un conversor de unidades de temperatura
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera.
 * @version 1.0 Nombre del fichero: ConversorTemperatura.java
 * 
 */
public abstract class ConversorTemperatura implements Conversor {
	
	private TipoTemp entrada;
    private TipoTemp salida;
    
    /**
     * Devuelve la unidad de entrada
     * @return Unidad de entrada
     */
    public TipoTemp getEntrada() {
    	return entrada;
    }
    
    /**
     * Devuelve la unidad de salida
     * @return Unidad de salida
     */
    public TipoTemp getSalida() {
    	return salida;
    }
	
	/**
	 * Crea un nuevo conversor de unidades de temperatura
	 * @param entrada Unidad de entrada
	 * @param salida Unidad de salida
	 */
	public ConversorTemperatura(TipoTemp entrada, TipoTemp salida) {
		this.entrada = entrada;
		this.salida = salida;
	}

}
