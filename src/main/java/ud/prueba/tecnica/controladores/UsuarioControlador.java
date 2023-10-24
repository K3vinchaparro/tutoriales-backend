package ud.prueba.tecnica.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ud.prueba.tecnica.modelos.Usuario;
import ud.prueba.tecnica.servicios.UsuarioServicio;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioControlador {
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@PostMapping("/guardar/")
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) throws Exception {
		Usuario usuarioGuardado = usuarioServicio.guardarUsuario(usuario);
		return ResponseEntity.ok(usuarioGuardado);
	}
}
