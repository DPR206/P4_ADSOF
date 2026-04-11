/**
 * 
 */
package sensores;

/**
 * 
 */
public enum TipoSensor {

	TEMPERATURA("TEMP"),
	HUMEDAD("HUM"),
	PRESION("PRES");
	
	private String tipo;

	/**
	 * @param tipo
	 */
	private TipoSensor(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	
	
}
