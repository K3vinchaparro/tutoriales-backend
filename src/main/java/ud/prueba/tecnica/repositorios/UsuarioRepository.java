package ud.prueba.tecnica.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import ud.prueba.tecnica.modelos.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);
	
}
