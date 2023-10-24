package ud.prueba.tecnica.servicios;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import ud.prueba.tecnica.modelos.Comentario;
import ud.prueba.tecnica.modelos.Tutorial;

public interface TutorialServicio {

	void init() throws IOException;
	
	String storeImg(MultipartFile file);
	
	Resource loadAsResource(String filename);
	
	public Tutorial guardarTutorial(Tutorial tutorial);

	public void eliminarTutorial(Long idTutorial);
	
	public Tutorial encontrarTutorialPorId(Long idTutorial);
	
	public List<Tutorial> listarTutoriales();
	
	public boolean editarTutorial(Tutorial tutorial);
	
}
