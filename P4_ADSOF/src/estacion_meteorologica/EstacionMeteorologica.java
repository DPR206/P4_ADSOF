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
	private ArrayList<Sensor> sensores;
	
	/**
	 * Crea una nueva estación meteorológica
	 * 
	 * @param nombre, nombre de la estación
	 * @param ubicacion, ubicación de la estación
	 * @param sensores, sensores de la estación
	 */
	public EstacionMeteorologica(String nombre, UbicacionGeografica ubicacion, ArrayList<Sensor> sensores) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.sensores = sensores;
	}
	
	

}
