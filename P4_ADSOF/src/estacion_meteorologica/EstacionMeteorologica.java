/**
 * Este paquete contiene las clases necesarias para gestionar una estacion meteorologica
 */
package estacion_meteorologica;

import java.util.*;

import sensores.*;
/**
 * Esta clase representa la estacion meteorologica
 * @author Claudia Saiz Escribano y Duna Puente Romera. 
 * @version 1.0
 * Nombre del fichero: EstacionMeteorologica.java
 * 
 */
public class EstacionMeteorologica {
	
	private String nombre;
	private UbicacionGeografica ubicacion;
	private HashMap<String, Sensor> sensores;
	
	/**
	 * Crea una nueva estación meteorológica
	 * 
	 * @param nombre, nombre de la estación
	 * @param ubicacion, ubicación de la estación
	 * @param sensores, sensores de la estación
	 */
	public EstacionMeteorologica(String nombre, UbicacionGeografica ubicacion, HashMap<String, Sensor> sensores) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.sensores = sensores;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the ubicacion
	 */
	public UbicacionGeografica getUbicacion() {
		return ubicacion;
	}

	/**
	 * @param ubicacion the ubicacion to set
	 */
	public void setUbicacion(UbicacionGeografica ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * @return the sensores
	 */
	public HashMap<String, Sensor> getSensores() {
		return sensores;
	}

	/**
	 * @param sensores the sensores to set
	 */
	public void setSensores(HashMap<String, Sensor> sensores) {
		this.sensores = sensores;
	}
	
	public List<Sensor> sensoresResgitrados(){
		return new ArrayList<>(this.sensores.values());
	}
	
	public Sensor obtenerSensorId(String id) {
		return this.sensores.get(id);
	}

}
