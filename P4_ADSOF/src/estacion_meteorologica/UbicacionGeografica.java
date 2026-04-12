/**
 * Este paquete contiene las clases necesarias para gestionar una estación meteorológica
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
	
	private double latitud;
	private double longitud;

	/**
	 * Crea una nueva ubicación geográfica
	 * 
	 * @param longitud, longitud de la ubicación
	 * @param latitud, latitud de la ubicación
	 */
	public UbicacionGeografica(double latitud, double longitud) {
		this.longitud = longitud;
		this.latitud = latitud;
	}

	/**
	 * Obtiene la longitud en grados decimales
	 * @return la longitud
	 */
	public double getLongitud() {
		return longitud;
	}

	/**
	 * Establece la longitud en grados decimales
	 * @param longitud the longitud to set
	 */
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	/**
	 * Obtiene la latitud en grados decimales
	 * @return la latitud
	 */
	public double getLatitud() {
		return latitud;
	}

	/**
	 * Establece la latitud en grados decimales
	 * @param latitud the latitud to set
	 */
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	@Override
	public String toString() {
		return latitud + ", " + longitud;
	}
	
}
