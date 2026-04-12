package procesadores;

import java.time.LocalDateTime;

/**
 * Esta clase representa un valor procesado
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera.
 * @version 1.0 Nombre del fichero: Valor.java
 * 
 */
public class Valor {
	private double valor;
	private LocalDateTime fechaLectura;
	
	/**
	 * Crea un nuevo valor procesado
	 * @param valor Valor númerico que se ha procesado
	 * @param fecha Fecha en la que se ha procesado
	 */
	public Valor(double valor, LocalDateTime fecha) {
		this.valor = valor;
		this.fechaLectura = fecha;
	}

	/**
	 * Devuelve el valor númerico procesado
	 * @return Valor númerico
	 */
	public double getValor() {
		return valor;
	}

	/**
	 * Devuelve la fecha en la que se procesó
	 * @return Fecha de procesamiento
	 */
	public LocalDateTime getFechaLectura() {
		return fechaLectura;
	}
	
	@Override
	public String toString() {
		return valor + "";
	}
}
