package ud.prueba.tecnica.implementaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ud.prueba.tecnica.modelos.Comentario;
import ud.prueba.tecnica.repositorios.ComentarioRepository;
import ud.prueba.tecnica.servicios.ComentarioServicio;

@Service
public class ComentarioImplementacion implements ComentarioServicio{
	
	@Autowired
	private ComentarioRepository comentarioRepository;

	@Override
	public Comentario guardarComentario(Comentario comentario) {
		return comentarioRepository.save(comentario);
	}

	@Override
	public void eliminarComentario(Long idComentario) {
		comentarioRepository.deleteById(idComentario);
	}

	@Override
	public boolean editarComentario(Comentario comentario) {
	    Long idComentario = comentario.getIdComentario();
	    Optional<Comentario> comentarioExistente = comentarioRepository.findById(idComentario);

	    if (comentarioExistente.isPresent()) {
	        Comentario comentarioActualizado = comentarioExistente.get();
	        comentarioActualizado.setContenido(comentario.getContenido());
	        comentarioRepository.save(comentarioActualizado);
	        return true;
	    } 
	    
	    return false;
	}

	@Override
	public List<Comentario> listarComentariosSegunIdTutorial(Long idTutorial) {
		List<Comentario> comentarios = comentarioRepository.listarComentariosSegunIdTutorial(idTutorial);
		return comentarios;
	}

	@Override
	public List<Comentario> listarComentariosSegunIsTutorialYSegunIdUsuario(Long idUsuario, Long idTutorial) {
		List<Comentario> comentarios = comentarioRepository.listarComentariosSegunIdUsuarioYIdTutorial(idUsuario, idTutorial);
		return comentarios;
	}

}
