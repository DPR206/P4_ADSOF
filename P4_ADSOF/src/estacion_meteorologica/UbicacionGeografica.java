/**
 * Este paquete contiene las clases necesarias para gestionar una ubicación geográfica
 */
package estacion_meteorologica;

/**
 * Esta clase representa una ubicacion geográfica
 * @author Claudia Saiz Escribano y Duna Puente Romera. 
 * @version 1.0
 * Nombre del fichero: UbicacionGeografica.java
 * 
 */
public class UbicacionGeografica {
	
	private double longitud;
	private double latitud;

	/**
	 * Crea una nueva ubicación geográfica
	 * 
	 * @param longitud, longitud de la ubicación
	 * @param latitud, latitud de la ubicación
	 */
	public UbicacionGeografica(double longitud, double latitud) {
		this.longitud = longitud;
		this.latitud = latitud;
	}

}
