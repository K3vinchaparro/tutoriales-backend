package ud.prueba.tecnica.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ud.prueba.tecnica.modelos.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

	@Query("SELECT c FROM Comentario c WHERE c.tutorial.idTutorial = :idTutorial")
    List<Comentario> listarComentariosSegunIdTutorial(@Param("idTutorial") Long idTutorial);
	
	@Query("SELECT c FROM Comentario c WHERE c.usuario.idUsuario = :idUsuario AND c.tutorial.idTutorial = :idTutorial")
	List<Comentario> listarComentariosSegunIdUsuarioYIdTutorial(@Param("idUsuario") Long idUsuario, @Param("idTutorial") Long idTutorial);

}
