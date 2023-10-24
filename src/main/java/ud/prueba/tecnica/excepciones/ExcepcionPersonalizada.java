package ud.prueba.tecnica.excepciones;

public class ExcepcionPersonalizada extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String message;

	public ExcepcionPersonalizada(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}