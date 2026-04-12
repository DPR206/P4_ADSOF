package formateadores;

/**
 * Esta clase representa un formateador a Markdown
 * 
 * @author Claudia Saiz Escribano y Duna Puente Romera.
 * @version 1.0 Nombre del fichero: FormateadorMarkdown.java
 * 
 */
public class FormateadorMarkdown {
	
	/**
	 * Crea un formateador a HTML
	 */
	public FormateadorMarkdown() {
	}

	/**
	 * Devuelve el IDocumento en formato Markdown
	 * @param doc Documento que se quiere formatear
	 * @return string con el documento formateado
	 */
	public String formatear(IDocumento doc) {

		StringBuilder sb = new StringBuilder();

		sb.append("# ").append(doc.getTituloSeccion()).append("\n\n");

		for (String p : doc.getParrafos()) {
			sb.append(p).append("\n\n");
		}

		for (Seccion s : doc.getListas()) {
			if(s.getElementos().size() == 0) continue;
			sb.append("## ").append(s.getTitulo()).append("\n");

			for (String e : s.getElementos()) {
				sb.append("- ").append(e).append("\n");
			}

			sb.append("\n");
		}
		return sb.toString();
	}
}
