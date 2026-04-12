package formateadores;

public class FormateadorHTML {
	public String formatear(IDocumento doc) {

		StringBuilder sb = new StringBuilder();

		sb.append("<!DOCTYPE html>\n<html lang=\"es\">\n<head>\n");
		sb.append("  <title>").append(doc.getTituloSeccion()).append("</title>\n");
		sb.append("</head>\n<body>\n");

		sb.append("  <h1>").append(doc.getTituloSeccion()).append("</h1>\n");

		for (String p : doc.getParrafos()) {
			sb.append("  <p>").append(p).append("</p>\n");
		}

		for (Seccion s : doc.getListas()) {
			if(s.getElementos().size() == 0) continue;
			sb.append("  <p>").append(s.getTitulo()).append("</p>\n");
			sb.append("  <ul>\n");

			for (String e : s.getElementos()) {
				sb.append("    <li>").append(e).append("</li>\n");
			}

			sb.append("  </ul>\n");
		}

		sb.append("</body>\n</html>");

		return sb.toString();
	}
}
