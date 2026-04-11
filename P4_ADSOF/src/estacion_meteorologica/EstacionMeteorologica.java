/**
 * Este paquete contiene las clases necesarias para gestionar una estacion meteorologica
 */
package estacion_meteorologica;

import java.util.*;

import sensores.*;
import excepciones.*;

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
	
	/**
	 * Obtener una lista de los sensores registrados
	 * @return
	 */
	public List<Sensor> sensoresResgitrados(){
		return new ArrayList<>(this.sensores.values());
	}
	
	/**
	 * Obtener un sensor en base a su id
	 * @param id identificador del sensor
	 * @return el sensor asociado
	 */
	public Sensor obtenerSensorId(String id) {
		return this.sensores.get(id);
	}
	
	/**
	 * Añadir sensores a la estación impidiendo duplicados
	 * @param sensores sensores a añadir
	 * @throws IdentificadorDuplicado error por sensor preexistente
	 */
	public void addSensor(Sensor...sensores) throws IdentificadorDuplicado {
		for(Sensor s : sensores)
			if(this.sensores.containsValue(s))
				throw new IdentificadorDuplicado(s, this.sensores.get(s.getId()));
			else
				this.sensores.put(s.getId(), s);
	}
	
	/**
	 * Obtener una lista de sensores de un tipo concreto
	 * @param tipo el tipo de sensor
	 * @return lista con los sensores del tipo especificado
	 */
	public List<Sensor> buscarTipoSensores(TipoSensor tipo){
		List<Sensor> sensores = new ArrayList<>();
		
		for(String id : this.sensores.keySet())
			if(id.startsWith(tipo.getTipo()))
				sensores.add(this.obtenerSensorId(id));
		
		return sensores;
	}
	
	public void realizarLecturas() {
		
	}

}
