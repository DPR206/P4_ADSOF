/**
 * Este paquete contiene las clases necesarias para gestionar sensores
 */
package sensores;

/**
 * Esta clase representa los tipos de sensores de temperatura
 * @author Claudia Saiz Escribano y Duna Puente Romera. 
 * @version 1.0
 * Nombre del fichero: TipoTemp.java
 * 
 */
public enum TipoTemp {

	/**Temperatura en celsius*/
	CELSIUS("ºC"),
	/**Temperatura en fahrenheit*/
	FAHRENHEIT("ºF"), 
	/**Temperatura en kelvin*/
	KELVIN("K");
	
	private String unidad;

	/**
	 * Crea una nueva unidad de temperatura
	 * 
	 * @param unidad, la unidad de medida
	 */
	private TipoTemp(String unidad) {
		this.unidad = unidad;
	}

	/**
	 * Obtiene la unidad de medición
	 * 
	 * @return the unidad, la unidad de medida
	 */
	public String getUnidad() {
		return unidad;
	}
	
	/**
	 * Devuelve la información del formato de temperatura
	 * 
	 * @return String con información de la unidad de medición
	 */
	public String toString() {
		return this.unidad;
	}
}
