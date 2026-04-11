/**
 * Este paquete contiene las clases necesarias para gestionar una estacion meteorologica
 */
package estacion_meteorologica;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import estrategias.Estrategia;
import sensores.*;
import excepciones.*;
import procesadores.ConversorIdentidad;
import procesadores.Procesador;

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
	Timer timer = new Timer();
	private long periodoLectura;
	private int maximoLecturas;
	private AtomicInteger contadorLecturas = new AtomicInteger(0);

	/**
	 * Crea una nueva estación meteorológica
	 * 
	 * @param nombre, nombre de la estación
	 * @param ubicacion, ubicación de la estación
	 * @param sensores, sensores de la estación
	 * @param timer
	 * @param periodoLectura
	 * @param maximoLecturas
	 */
	public EstacionMeteorologica(String nombre, UbicacionGeografica ubicacion, HashMap<String, Sensor> sensores,
			Timer timer, long periodoLectura, int maximoLecturas) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.sensores = sensores;
		this.timer = timer;
		this.periodoLectura = periodoLectura;
		this.maximoLecturas = maximoLecturas;
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
	 * @return the periodoLectura
	 */
	public long getPeriodoLectura() {
		return periodoLectura;
	}

	/**
	 * @param periodoLectura the periodoLectura to set
	 */
	public void setPeriodoLectura(long periodoLectura) {
		this.periodoLectura = periodoLectura;
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
	
	public void addSensor(TipoSensor tipo, double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura, LocalDateTime ultimaCalibracion, LocalDateTime fechaInstalacion) {
		Sensor s = null;
		Procesador procesador = new Procesador(new ConversorIdentidad());
		try {
			switch(tipo) {
			case TipoSensor.TEMPERATURA: 
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion, procesador);
				break;
			case TipoSensor.PRESION:
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion, procesador);
				break;
			case TipoSensor.HUMEDAD:
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion, procesador);
				break;
			}
		
			if(this.sensores.containsValue(s))
				throw new IdentificadorDuplicado(s, this.sensores.get(s.getId()));
			else
				this.sensores.put(s.getId(), s);
		} catch (IncompatibleConversorException | IdentificadorDuplicado e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addSensor(TipoSensor tipo, double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura, LocalDateTime ultimaCalibracion, LocalDateTime fechaInstalacion, Estrategia estrategia) {
		Sensor s = null;
		Procesador procesador = new Procesador(new ConversorIdentidad());
		try {
			switch(tipo) {
			case TipoSensor.TEMPERATURA: 
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion, estrategia, procesador);
				break;
			case TipoSensor.PRESION:
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion, estrategia, procesador);
				break;
			case TipoSensor.HUMEDAD:
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion, estrategia, procesador);
				break;
			}
		
			if(this.sensores.containsValue(s))
				throw new IdentificadorDuplicado(s, this.sensores.get(s.getId()));
			else
				this.sensores.put(s.getId(), s);
		} catch (IncompatibleConversorException | IdentificadorDuplicado e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addSensor(TipoSensor tipo, double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura, LocalDateTime ultimaCalibracion, LocalDateTime fechaInstalacion, Procesador procesador) {
		Sensor s = null;
		try {
			switch(tipo) {
			case TipoSensor.TEMPERATURA: 
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion, procesador);
				break;
			case TipoSensor.PRESION:
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion, procesador);
				break;
			case TipoSensor.HUMEDAD:
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion, procesador);
				break;
			}
		
			if(this.sensores.containsValue(s))
				throw new IdentificadorDuplicado(s, this.sensores.get(s.getId()));
			else
				this.sensores.put(s.getId(), s);
		} catch (IncompatibleConversorException | IdentificadorDuplicado e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addSensor(TipoSensor tipo, double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura, LocalDateTime ultimaCalibracion, LocalDateTime fechaInstalacion, Estrategia estrategia, Procesador procesador) {
		Sensor s = null;
		try {
			switch(tipo) {
			case TipoSensor.TEMPERATURA: 
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion, estrategia, procesador);
				break;
			case TipoSensor.PRESION:
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion, estrategia, procesador);
				break;
			case TipoSensor.HUMEDAD:
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion, fechaInstalacion, estrategia, procesador);
				break;
			}
		
			if(this.sensores.containsValue(s))
				throw new IdentificadorDuplicado(s, this.sensores.get(s.getId()));
			else
				this.sensores.put(s.getId(), s);
		} catch (IncompatibleConversorException | IdentificadorDuplicado e) {
			System.out.println(e.getMessage());
		}
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
	
	/**
	 * Realizar una lectura simultánea de todos los sensores
	 */
	public void realizarLecturas() {
		this.sensores.values().parallelStream().forEach(Sensor::realizarLectura);
	}
	
	/**
	 * Realizar lecturas periódicas de los sensores
	 */
	public void realizarLecturasPeriodicas() {
		TimerTask tarea = new TimerTask() {
			@Override
	        public void run() {
				int lecturasActual = contadorLecturas.incrementAndGet();
				if(lecturasActual <= maximoLecturas)
					EstacionMeteorologica.this.realizarLecturas();
				else
					this.cancel();
					timer.purge();
			}
		};
		this.timer.scheduleAtFixedRate(tarea, 0, periodoLectura);
	}

}
