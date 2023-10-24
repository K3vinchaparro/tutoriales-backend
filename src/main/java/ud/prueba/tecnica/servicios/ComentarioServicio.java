package ud.prueba.tecnica.servicios;

import java.util.List;

import ud.prueba.tecnica.modelos.Comentario;

public interface ComentarioServicio {
	
	public Comentario guardarComentario(Comentario comentario);
	
	public void eliminarComentario(Long idComentario);
	
	public boolean editarComentario(Comentario comentario);
	
	public List<Comentario> listarComentariosSegunIdTutorial(Long idTutorial); 
	
	public List<Comentario> listarComentariosSegunIsTutorialYSegunIdUsuario(Long idUsuario, Long idTutorial);

}
