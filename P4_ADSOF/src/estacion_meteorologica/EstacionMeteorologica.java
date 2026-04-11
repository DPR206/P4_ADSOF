/**
 * Este paquete contiene las clases necesarias para gestionar una estacion meteorologica
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
	Timer timer = new Timer();
	private long periodoLectura;
	private int maximoLecturas;
	private AtomicInteger contadorLecturas = new AtomicInteger(0);

	/**
	 * Crea una nueva estación meteorológica
	 * 
	 * @param nombre,        nombre de la estación
	 * @param ubicacion,     ubicación de la estación
	 * @param sensores,      sensores de la estación
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
	 * @return the alertas
	 */
	public HashMap<Sensor, List<Exception>> getAlertas() {
		return alertas;
	}

	/**
	 * @param alertas the alertas to set
	 */
	public void setAlertas(HashMap<Sensor, List<Exception>> alertas) {
		this.alertas = alertas;
	}

	/**
	 * Obtener una lista de los sensores registrados
	 * 
	 * @return
	 */
	public List<Sensor> sensoresRegistrados() {
		return new ArrayList<>(this.sensores.values());
	}

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
	 * @param sensores sensores a añadir
	 * @throws IdentificadorDuplicado error por sensor preexistente
	 */
	public void addSensor(Sensor... sensores) throws IdentificadorDuplicado {
		for (Sensor s : sensores)
			if (this.sensores.containsValue(s))
				throw new IdentificadorDuplicado(s, this.sensores.get(s.getId()));
			else
				this.sensores.put(s.getId(), s);
	}

	public void addSensor(TipoSensor tipo, double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura,
			LocalDateTime ultimaCalibracion, LocalDateTime fechaInstalacion) {
		Sensor s = null;
		Procesador procesador = new Procesador(new ConversorIdentidad());
		try {
			switch (tipo) {
			case TipoSensor.TEMPERATURA:
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion,
						fechaInstalacion, procesador);
				break;
			case TipoSensor.PRESION:
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion,
						fechaInstalacion, procesador);
				break;
			case TipoSensor.HUMEDAD:
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion,
						fechaInstalacion, procesador);
				break;
			}

			if (this.sensores.containsValue(s))
				throw new IdentificadorDuplicado(s, this.sensores.get(s.getId()));
			else
				this.sensores.put(s.getId(), s);
		} catch (IncompatibleConversorException | IdentificadorDuplicado e) {
			System.out.println(e.getMessage());
		}
	}

	public void addSensor(TipoSensor tipo, double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura,
			LocalDateTime ultimaCalibracion, LocalDateTime fechaInstalacion, Estrategia estrategia) {
		Sensor s = null;
		Procesador procesador = new Procesador(new ConversorIdentidad());
		try {
			switch (tipo) {
			case TipoSensor.TEMPERATURA:
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion,
						fechaInstalacion, estrategia, procesador);
				break;
			case TipoSensor.PRESION:
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion,
						fechaInstalacion, estrategia, procesador);
				break;
			case TipoSensor.HUMEDAD:
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion,
						fechaInstalacion, estrategia, procesador);
				break;
			}

			if (this.sensores.containsValue(s))
				throw new IdentificadorDuplicado(s, this.sensores.get(s.getId()));
			else
				this.sensores.put(s.getId(), s);
		} catch (IncompatibleConversorException | IdentificadorDuplicado e) {
			System.out.println(e.getMessage());
		}
	}

	public void addSensor(TipoSensor tipo, double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura,
			LocalDateTime ultimaCalibracion, LocalDateTime fechaInstalacion, Procesador procesador) {
		Sensor s = null;
		try {
			switch (tipo) {
			case TipoSensor.TEMPERATURA:
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion,
						fechaInstalacion, procesador);
				break;
			case TipoSensor.PRESION:
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion,
						fechaInstalacion, procesador);
				break;
			case TipoSensor.HUMEDAD:
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion,
						fechaInstalacion, procesador);
				break;
			}

			if (this.sensores.containsValue(s))
				throw new IdentificadorDuplicado(s, this.sensores.get(s.getId()));
			else
				this.sensores.put(s.getId(), s);
		} catch (IncompatibleConversorException | IdentificadorDuplicado e) {
			System.out.println(e.getMessage());
		}
	}

	public void addSensor(TipoSensor tipo, double offset, double ultimaLectura, LocalDateTime tiempoUltimaLectura,
			LocalDateTime ultimaCalibracion, LocalDateTime fechaInstalacion, Estrategia estrategia,
			Procesador procesador) {
		Sensor s = null;
		try {
			switch (tipo) {
			case TipoSensor.TEMPERATURA:
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion,
						fechaInstalacion, estrategia, procesador);
				break;
			case TipoSensor.PRESION:
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion,
						fechaInstalacion, estrategia, procesador);
				break;
			case TipoSensor.HUMEDAD:
				s = new SensorTemperatura(offset, ultimaLectura, tiempoUltimaLectura, ultimaCalibracion,
						fechaInstalacion, estrategia, procesador);
				break;
			}

			if (this.sensores.containsValue(s))
				throw new IdentificadorDuplicado(s, this.sensores.get(s.getId()));
			else
				this.sensores.put(s.getId(), s);
		} catch (IncompatibleConversorException | IdentificadorDuplicado e) {
			System.out.println(e.getMessage());
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

	public void calibrarSensor(Sensor sensor, double offsetLectura) {
		sensor.setOffsetLectura(offsetLectura);
		this.alertas.get(sensor).clear();
	}
	
	private List<Sensor> sensoresValidos(){
		List<Sensor> validos = new ArrayList<>();
		
		for(Sensor s : this.sensoresRegistrados())
			try {
				s.calibrado();
				validos.add(s);
			} catch (CalibracionCaducada e) {
				System.out.println(e);
				this.alertas.get(s).add(e);
			} catch (LecturaFueraRango e) {
				System.out.println(e);
				this.alertas.get(s).add(e);
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
			} catch (CambioBrusco e) {
				System.out.println(e);
				this.alertas.get(t).add(e);
			}
		});
	}

	/**
	 * Realizar lecturas periódicas de los sensores
	 */
	public void realizarLecturasPeriodicas() {
		TimerTask tarea = new TimerTask() {
			@Override
			public void run() {
				int lecturasActual = contadorLecturas.incrementAndGet();
				if (lecturasActual <= maximoLecturas)
					EstacionMeteorologica.this.realizarLecturas();
				else
					this.cancel();
				timer.purge();
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
		parrafos.add(ubicacion.toString());
		parrafos.add("Número de sensores: " + sensores.size());
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
			listaSensores.add(s.toString());
		Seccion sensores = new Seccion("Sensores activos: ", listaSensores);
		secciones.add(sensores);

		
		List<String> listaAlertas = new ArrayList<>();
		for(Exception e : this.alertas()) listaAlertas.add(e.toString());
		Seccion alertas = new Seccion("Alertas activas: " + this.alertas.size(), listaAlertas);
		secciones.add(alertas);

		return secciones;
	}

}
