package formateadores;

public class FormateadorMarkdown {
	public String formatear(IDocumento doc) {

		StringBuilder sb = new StringBuilder();

		sb.append("# ").append(doc.getTituloSeccion()).append("\n\n");

		for (String p : doc.getParrafos()) {
			sb.append(p).append("\n\n");
		}

		for (Seccion s : doc.getListas()) {
			sb.append("## ").append(s.getTitulo()).append("\n");

			for (String e : s.getElementos()) {
				sb.append("- ").append(e).append("\n");
			}

			sb.append("\n");
		}
		return sb.toString();
	}
}
