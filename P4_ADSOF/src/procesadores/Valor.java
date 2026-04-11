package procesadores;

import java.time.LocalDateTime;

public class Valor {
	private double valor;
	private LocalDateTime fechaLectura;
	
	public Valor(double valor, LocalDateTime fecha) {
		this.valor = valor;
		this.fechaLectura = fecha;
	}

	public double getValor() {
		return valor;
	}

	public LocalDateTime getFechaLectura() {
		return fechaLectura;
	}
}
