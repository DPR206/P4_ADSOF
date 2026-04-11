package formateadores;

import java.util.List;

public interface IDocumento {
	String getTituloDocumento();

	String getTituloSeccion();

	List<String> getParrafos();

	List<Seccion> getListas();
}
