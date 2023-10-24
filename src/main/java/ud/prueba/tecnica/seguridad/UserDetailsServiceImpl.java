package ud.prueba.tecnica.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ud.prueba.tecnica.excepciones.ExcepcionPersonalizada;
import ud.prueba.tecnica.modelos.Usuario;
import ud.prueba.tecnica.repositorios.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = this.usuarioRepository.findByUsername(username);
		if(usuario == null) {
			throw new ExcepcionPersonalizada("Usuario no encontrado por username");
		}
		return usuario;
	}

}