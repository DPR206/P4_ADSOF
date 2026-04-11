package excepciones;

public class IncompatibleConversorException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public IncompatibleConversorException(String message) {
		super(message);
	}
}
