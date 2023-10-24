package ud.prueba.tecnica.controladores;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ud.prueba.tecnica.modelos.Comentario;
import ud.prueba.tecnica.modelos.Tutorial;
import ud.prueba.tecnica.servicios.TutorialServicio;

@RestController
@RequestMapping("/tutorial")
@CrossOrigin("*")
public class TutorialControlador {
	
	@Autowired
	private TutorialServicio tutorialServicio;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
    private ResourceLoader resourceLoader;
	
	
	@GetMapping("{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException{
		Resource file = tutorialServicio.loadAsResource(filename);
		String contentType = Files.probeContentType(file.getFile().toPath());
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_TYPE, contentType)
				.body(file);
	}
	
	
	@PostMapping(value="/guardar/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public boolean guardarTutorial(
			@RequestParam(value="foto", required=false) MultipartFile[] foto,
			@RequestPart(name = "tutorial") String tutorial) throws JsonMappingException, JsonProcessingException {
		Tutorial tutorialCreado = new ObjectMapper().readValue(tutorial, Tutorial.class);
		
		
		
		if(foto != null) {
			tutorialCreado.setFoto(manejoDeImagenes(foto[foto.length-1]));
			tutorialServicio.guardarTutorial(tutorialCreado);
			return true;
		}
		
		return false;
		
	} 
	
	@DeleteMapping(value="/eliminar/{idTutorial}")
	public void eliminarTutorial(@PathVariable("idTutorial")Long idTutorial) throws IOException{
		if(tutorialServicio.encontrarTutorialPorId(idTutorial).getFoto() != null) {
			borrarArchivos(tutorialServicio.encontrarTutorialPorId(idTutorial).getFoto());
		}
		tutorialServicio.eliminarTutorial(idTutorial);
	}
	
	@GetMapping("/listar/")
	public ResponseEntity<List<Tutorial>> listarTutoriales(){
		List<Tutorial> tutoriales =  tutorialServicio.listarTutoriales();
		return ResponseEntity.ok(tutoriales);
	}
	
	@PutMapping("/editar/")
	public boolean editarComentario(@RequestBody Tutorial tutorial) {
		boolean editado = tutorialServicio.editarTutorial(tutorial);
		return editado;
	}
	
	public String manejoDeImagenes(MultipartFile multipartFile) {
		String path = tutorialServicio.storeImg(multipartFile);
		String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
		String url = ServletUriComponentsBuilder
				.fromHttpUrl(host)
				.path("/tutorial/")
				.path(path)
				.toUriString();
		
		return url;
	}
	
	public void borrarArchivos(String archivoUrl) throws IOException {
		Resource resource = resourceLoader.getResource("classpath:/");
        String rootPath = resource.getURL().getPath();
        
        URL url = new URL(archivoUrl);
        String fileName = url.getFile();
        fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        System.out.println(fileName);
		
		File archivo = new File(rootPath+"/mediafiles/"+fileName);
		
		if(archivo.exists()) {
			archivo.delete();
		}else {
			System.out.println("No existe ese archivo");
		}
	}
}
