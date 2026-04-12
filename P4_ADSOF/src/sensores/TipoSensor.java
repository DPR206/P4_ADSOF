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

	TEMPERATURA("TEMP"), /**Sensor de temperatura*/
	HUMEDAD("HUM"), /**Sensor de humedad*/
	PRESION("PRES"); /**Sensor de presión*/
	
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
