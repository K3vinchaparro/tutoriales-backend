package ud.prueba.tecnica.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ud.prueba.tecnica.modelos.Comentario;
import ud.prueba.tecnica.servicios.ComentarioServicio;

@RestController
@RequestMapping("/comentario")
@CrossOrigin("*")
public class ComentarioControlador {
	
	@Autowired
	private ComentarioServicio comentarioServicio;

	
	@PostMapping("/guardar/")
	public ResponseEntity<Comentario> guardarComentario(@RequestBody Comentario comentario){
		Comentario comentarioGuardado = comentarioServicio.guardarComentario(comentario);
		return ResponseEntity.ok(comentarioGuardado);
	}
	
	@DeleteMapping("/eliminar/{idComentario}")
	public boolean eliminarComentario(@PathVariable("idComentario") Long idComentario) {
		 comentarioServicio.eliminarComentario(idComentario);
		 return true;
	}
	
	@PutMapping("/editar/")
	public boolean editarComentario(@RequestBody Comentario comentario) {
		boolean editado = comentarioServicio.editarComentario(comentario);
		return editado;
	}
	
	@GetMapping("/listarPorTutorial/{idTutorial}")
	public ResponseEntity<List<Comentario>> listarComentariosPorTutorial (@PathVariable("idTutorial") Long idTutorial){
		List<Comentario> comentarios = comentarioServicio.listarComentariosSegunIdTutorial(idTutorial);
		return ResponseEntity.ok(comentarios);
	}
	 
	@GetMapping("/listarPorTutorialYUsuario/{idUsuario}/{idTutorial}")
	public ResponseEntity<List<Comentario>> listarComentariosPorTutorial (@PathVariable("idUsuario") Long idUsuario, @PathVariable("idTutorial") Long idTutorial){
		List<Comentario> comentarios = comentarioServicio.listarComentariosSegunIsTutorialYSegunIdUsuario(idUsuario, idTutorial);
		return ResponseEntity.ok(comentarios);
	}
	
	
}
