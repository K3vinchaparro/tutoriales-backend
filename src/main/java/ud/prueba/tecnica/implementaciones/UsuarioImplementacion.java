package ud.prueba.tecnica.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ud.prueba.tecnica.excepciones.ExcepcionPersonalizada;
import ud.prueba.tecnica.modelos.Rol;
import ud.prueba.tecnica.modelos.Usuario;
import ud.prueba.tecnica.repositorios.UsuarioRepository;
import ud.prueba.tecnica.servicios.UsuarioServicio;

@Service
public class UsuarioImplementacion implements UsuarioServicio{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEnconder;

	@Override
	public Usuario guardarUsuario(Usuario usuario) {
		
		Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
		
		if(usuarioLocal != null) {
			throw new ExcepcionPersonalizada("Usuario ya existe");
		}else {
			usuario.setPassword(passwordEnconder.encode(usuario.getPassword()));
			
			Rol rol = new Rol();
			rol.setIdRol(2L);
			rol.setNombreRol("NORMAL");
			
			usuario.setRol(rol);
			
			usuarioLocal = usuarioRepository.save(usuario);
		}
		
		return usuarioLocal;
		
	}

}
