/**
 * Este paquete contiene las clases necesarias para gestionar sensores
 */
package sensores;

/**
 * Esta clase representa la enumeración de los tipos de sensores
 * @author Claudia Saiz Escribano y Duna Puente Romera. 
 * @version 1.0
 * Nombre del fichero: TipoSensor.java
 * 
 */
public enum TipoSensor {

	/**Sensor de temperatura*/
	TEMPERATURA("TEMP"),
	/**Sensor de humedad*/
	HUMEDAD("HUM"),
	/**Sensor de presión*/
	PRESION("PRES"); 
	
	private String tipo;

	/**
	 * Crea un nuevo tipo de sensor
	 * @param tipo el tipo en String (para el id)
	 */
	private TipoSensor(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Obtiene el tipo de sensor
	 * @return el tipo en String (para el id)
	 */
	public String getTipo() {
		return tipo;
	}
	
	
}
