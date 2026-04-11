package formateadores;

import java.util.List;

public class Seccion {

	private String titulo;
	private List<String> elementos;

	public Seccion(String titulo, List<String> elementos) {
		this.titulo = titulo;
		this.elementos = elementos;
	}

	public String getTitulo() {
		return titulo;
	}

	public List<String> getElementos() {
		return elementos;
	}
}
