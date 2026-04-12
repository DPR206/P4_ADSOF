package formateadores;

import java.util.List;


public interface IDocumento {
	/**
	 * Obtiene el título del documento
	 * @return string con el título
	 */
	String getTituloDocumento();

	/**
	 * Obtiene el título de la sección
	 * @return string con el título de la sección
	 */
	String getTituloSeccion();

	/**
	 * Obtiene una lista con los párrafos
	 * @return lista de párrafos en formato string
	 */
	List<String> getParrafos();

	/**
	 * Obtiene una lista con las secciones
	 * @return lista de las secciones
	 */
	List<Seccion> getListas();
}
