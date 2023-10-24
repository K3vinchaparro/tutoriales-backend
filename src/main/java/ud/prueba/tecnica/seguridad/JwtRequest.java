package ud.prueba.tecnica.seguridad;

import lombok.Data;

@Data
public class JwtRequest  {

	private String username;
	private String password;
	
	public JwtRequest() {}

	public JwtRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}	
	
}
