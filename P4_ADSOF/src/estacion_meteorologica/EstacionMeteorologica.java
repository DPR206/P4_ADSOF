/**
 * Este paquete contiene las clases necesarias para gestionar una estación meteorológica
 */
package estacion_meteorologica;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import estrategias.Estrategia;
import sensores.*;
import excepciones.*;
import formateadores.IDocumento;
import formateadores.Seccion;
import procesadores.ConversorIdentidad;
import procesadores.Procesador;

/**
 * Esta clase representa la estacion meteorologica
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera.
 * @version 1.0 Nombre del fichero: EstacionMeteorologica.java
 * 
 */
public class EstacionMeteorologica implements IDocumento {

	private String nombre;
	private UbicacionGeografica ubicacion;
	private HashMap<String, Sensor> sensores;
	private HashMap<Sensor, List<Exception>> alertas;
	private Timer timer = new Timer();

	/**
	 * Crea una nueva estación meteorológica
	 * 
	 * @param nombre,        nombre de la estación
	 * @param ubicacion,     ubicación de la estación
	 */
	public EstacionMeteorologica(String nombre, UbicacionGeografica ubicacion) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.sensores = new HashMap<>();
		this.alertas = new HashMap<>();
	}

	/**
	 * Obtiene el nombre de la estación
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Obtiene la ubicación de la estación
	 * @return la ubicacion
	 */
	public UbicacionGeografica getUbicacion() {
		return ubicacion;
	}

	/**
	 * Obtiene los sensores y su id
	 * @return un hash map de los sensores y sus ids
	 */
	public HashMap<String, Sensor> getSensores() {
		return sensores;
	}

	/**
	 * Obtiene un las alertas asociadas a cada sensor
	 * @return un hash map de las alertas
	 */
	public HashMap<Sensor, List<Exception>> getAlertas() {
		return alertas;
	}

	/**
	 * Obtener una lista de los sensores registrados
	 * 
	 * @return una lista de los sensores
	 */
	public List<Sensor> sensoresRegistrados() {
		return new ArrayList<>(this.sensores.values());
	}

	/**
	 * Obtiene una lista de todas las alertas 
	 * @return una lista de las alertas
	 */
	public List<Exception> alertas() {
		return this.alertas.values().stream().flatMap(List::stream).collect(Collectors.toList());
	}

	/**
	 * Obtener un sensor en base a su id
	 * 
	 * @param id identificador del sensor
	 * @return el sensor asociado
	 */
	public Sensor obtenerSensorId(String id) {
		return this.sensores.get(id);
	}

	/**
	 * Añadir sensores a la estación impidiendo duplicados
	 * 
	 * @param tipo el tipo de sensor
	 * @param offset el offset de calibración
	*/
	public void addSensor(TipoSensor tipo, double offset) {
	Sensor s = null;
		Procesador procesador = new Procesador(new ConversorIdentidad());
		try {
			switch (tipo) {
			case TipoSensor.TEMPERATURA:
				s = new SensorTemperatura(offset, procesador);
				break;
			case TipoSensor.PRESION:
				s = new SensorPresion(offset, procesador);
				break;
			case TipoSensor.HUMEDAD:
				s = new SensorHumedad(offset, procesador);
				break;
			}

			if (this.sensores.containsValue(s))
				throw new IdentificadorDuplicado(s, this.sensores.get(s.getId()));
			else
				this.sensores.put(s.getId(), s);
		} catch (IncompatibleConversorException | IdentificadorDuplicado e) {
			System.out.println("Warning: " + e);
		}
	}

	/**
	 * Añadir sensores a la estación impidiendo duplicados
	 * 
	 * @param tipo el tipo de sensor
	 * @param offset el offset de calibración
	 * @param estrategia la estrategia para realizar lecturas
	 */	
	public void addSensor(TipoSensor tipo, double offset, Estrategia estrategia) {

		Sensor s = null;
		Procesador procesador = new Procesador(new ConversorIdentidad());
		try {
			switch (tipo) {
			case TipoSensor.TEMPERATURA:
				s = new SensorTemperatura(offset, estrategia, procesador);
				break;
			case TipoSensor.PRESION:
				s = new SensorPresion(offset, estrategia, procesador);
				break;
			case TipoSensor.HUMEDAD:
				s = new SensorHumedad(offset, estrategia, procesador);
				break;
			}

			if (this.sensores.containsValue(s))
				throw new IdentificadorDuplicado(s, this.sensores.get(s.getId()));
			else
				this.sensores.put(s.getId(), s);
		} catch (IncompatibleConversorException | IdentificadorDuplicado e) {
			System.out.println("Warning: " + e);
		}
	}

	/**
	 * Añadir sensores a la estación impidiendo duplicados
	 * 
	 * @param tipo el tipo de sensor
	 * @param offset el offset de calibración
	 * @param procesador el procesador del sensor
	 */
	public void addSensor(TipoSensor tipo, double offset, Procesador procesador) {

		Sensor s = null;
		try {
			switch (tipo) {
			case TipoSensor.TEMPERATURA:
				s = new SensorTemperatura(offset, procesador);
				break;
			case TipoSensor.PRESION:
				s = new SensorPresion(offset, procesador);
				break;
			case TipoSensor.HUMEDAD:
				s = new SensorHumedad(offset, procesador);
				break;
			}

			if (this.sensores.containsValue(s))
				throw new IdentificadorDuplicado(s, this.sensores.get(s.getId()));
			else
				this.sensores.put(s.getId(), s);
		} catch (IncompatibleConversorException | IdentificadorDuplicado e) {
			System.out.println("Warning: " + e);
		}
	}

	/**
	 * Añadir sensores a la estación impidiendo duplicados
	 * 
	 * @param tipo el tipo de sensor
	 * @param offset el offset de calibración
	 * @param estrategia la estrategia para realizar lecturas
	 * @param procesador el procesador del sensor
	 */
	public void addSensor(TipoSensor tipo, double offset, Estrategia estrategia, Procesador procesador) {
		Sensor s = null;
		try {
			switch (tipo) {
			case TipoSensor.TEMPERATURA:
				s = new SensorTemperatura(offset, estrategia, procesador);
				break;
			case TipoSensor.PRESION:
				s = new SensorPresion(offset, estrategia, procesador);
				break;
			case TipoSensor.HUMEDAD:
				s = new SensorHumedad(offset, estrategia, procesador);
				break;
			}

			if (this.sensores.containsValue(s))
				throw new IdentificadorDuplicado(s, this.sensores.get(s.getId()));
			else
				this.sensores.put(s.getId(), s);
		} catch (IncompatibleConversorException | IdentificadorDuplicado e) {
			System.out.println("Warning: " + e);
		}
	}

	/**
	 * Obtener una lista de sensores de un tipo concreto
	 * 
	 * @param tipo el tipo de sensor
	 * @return lista con los sensores del tipo especificado
	 */
	public List<Sensor> buscarTipoSensores(TipoSensor tipo) {
		List<Sensor> sensores = new ArrayList<>();

		for (String id : this.sensores.keySet())
			if (id.startsWith(tipo.getTipo()))
				sensores.add(this.obtenerSensorId(id));

		return sensores;
	}

	/**
	 * Calibra un sensor estableciendo un offset de lectura
	 * @param sensor el senor a calibrar
	 * @param offsetLectura el nuevo offset de lectura
	 */
	public void calibrarSensor(Sensor sensor, double offsetLectura) {
		sensor.setOffset(offsetLectura);
		this.alertas.get(sensor).clear();
	}
	
	/**
	 * Establece una lista de senosores calibrados
	 * @return una lista de sensores válidos para realizar lecturas
	 */
	private List<Sensor> sensoresValidos(){
		List<Sensor> validos = new ArrayList<>();
		
		for(Sensor s : this.sensoresRegistrados())
			try {
				s.calibrado();
				validos.add(s);
			} catch (CalibracionCaducada e) {
				System.out.println("Warning: " + e);
				this.alertas.computeIfAbsent(s, k -> new ArrayList<>()).add(e);
			}
		return validos;
	}

	/**
	 * Realizar una lectura simultánea de todos los sensores
	 */
	public void realizarLecturas() {
		this.sensoresValidos().parallelStream().forEach(t -> {
			try {
				t.realizarLectura();
			} catch (CambioBrusco | LecturaFueraRango e) {
				System.out.println("Warning: " + e);
				this.alertas.computeIfAbsent(t, k -> new ArrayList<>()).add(e);
			}
		});
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("\nEstación Meteorológica: " + this.nombre + "\n");
		sb.append("Ubicación: " + this.getUbicacion() + "\n");
		sb.append("---------------------------------------\n");
		sb.append("Sensores instalados: " + sensores.size() + "\n");
		LocalDateTime ultimaLectura = null;
		for (Sensor s : sensores.values()) {
			if (ultimaLectura == null || s.getTiempoUltimaLectura().isBefore(ultimaLectura)) {
				ultimaLectura = s.getTiempoUltimaLectura();
			}
		}
		sb.append("Última lectura: " + ultimaLectura + "\n");
		for(Sensor s : sensores.values()) {
			sb.append(s.infoProcesador() + "\n");
		}

		return sb.toString();
	}

	/**
	 * Realizar lecturas periódicas de los sensores
	 * 
	 * @param periodoLectura periodicidad de las lecturas
	 * @param maximoLecturas número máximo de lecturas
	 */
	public void realizarLecturasPeriodicas(long periodoLectura, int maximoLecturas) {
		AtomicInteger contadorLecturas = new AtomicInteger(0);
		TimerTask tarea = new TimerTask() {
			@Override
			public void run() {
				int lecturasActual = contadorLecturas.incrementAndGet();
				if (lecturasActual <= maximoLecturas)
					EstacionMeteorologica.this.realizarLecturas();
				else {
					this.cancel();
					timer.purge();
				}
			}
		};
		this.timer.scheduleAtFixedRate(tarea, 0, periodoLectura);
	}

	@Override
	public String getTituloDocumento() {
		return "Estación meteorológica: " + nombre;
	}

	@Override
	public String getTituloSeccion() {
		return nombre;
	}

	@Override
	public List<String> getParrafos() {
		List<String> parrafos = new ArrayList<>();
		parrafos.add("Ubicacion: " + ubicacion.toString());
		parrafos.add("Sensores instalados: " + sensores.size());
		LocalDateTime ultimaLectura = null;
		for (Sensor s : sensores.values()) {
			if (ultimaLectura == null || s.getTiempoUltimaLectura().isBefore(ultimaLectura)) {
				ultimaLectura = s.getTiempoUltimaLectura();
			}
		}
		parrafos.add("Última lectura: " + ultimaLectura);
		return parrafos;
	}

	@Override
	public List<Seccion> getListas() {
		List<Seccion> secciones = new ArrayList<>();

		List<String> listaSensores = new ArrayList<>();
		for (Sensor s : sensores.values())
			listaSensores.add(s.infoProcesador());
		Seccion sensores = new Seccion("Sensores activos: ", listaSensores);
		secciones.add(sensores);

		List<String> listaAlertas = new ArrayList<>();
		for (Exception e : this.alertas())
			listaAlertas.add(e.toString());
		Seccion alertas = new Seccion("Alertas activas: " + this.alertas.size(), listaAlertas);
		secciones.add(alertas);

		return secciones;
	}

}
