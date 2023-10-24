package ud.prueba.tecnica.implementaciones;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ud.prueba.tecnica.modelos.Tutorial;
import ud.prueba.tecnica.repositorios.TutorialRepository;
import ud.prueba.tecnica.servicios.TutorialServicio;


@Service
public class TutorialImplementacion implements TutorialServicio{
	
	@Value("${media.location}")
	private String mediaLocation;
	
	private Path rootLocation;
	
	@Autowired
	private TutorialRepository tutorialRepository;

	
	
	private String generarNombreUnicoImagen() {
		int numero = (int)(Math.random()*100000);
        String nombreImagen = numero+".png";
        return nombreImagen;
	}



	@Override
	@PostConstruct
	public void init() throws IOException {
		rootLocation = Paths.get(mediaLocation);
		Files.createDirectories(rootLocation);
	}



	@Override
	public String storeImg(MultipartFile file) {
		try{
			if(file.isEmpty()) {
				throw new RuntimeException("No hay archivo");
			}
			
			String filename = generarNombreUnicoImagen();
			Path destinationFile = rootLocation.resolve(Paths.get(filename))
					.normalize().toAbsolutePath();
			
			try (InputStream inputStream = file.getInputStream()){
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}
			
			return filename;
		} catch (IOException e) {
			throw new RuntimeException("Fallo en cargar imagen", e);
		}
	}



	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource((file.toUri()));
			
			if(resource.exists() || resource.isReadable()) {
				return resource;
			}else {
				throw new RuntimeException("No se puede leer el archivo " + filename);
				
			}
		}catch(MalformedURLException e) {
			throw new RuntimeException("No se puede leer el archivo ", e);
		}
	}



	@Override
	public Tutorial guardarTutorial(Tutorial tutorial) {
		LocalDate localDate = LocalDate.now();
	    java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
	    tutorial.setFecha(sqlDate);
	    
	    Tutorial tutorialLocal = tutorialRepository.save(tutorial);
	    return tutorialLocal;
	}




	@Override
	public void eliminarTutorial(Long idTutorial) {
		
		tutorialRepository.deleteById(idTutorial);
	}



	@Override
	public Tutorial encontrarTutorialPorId(Long idTutorial) {
		return tutorialRepository.findByIdTutorial(idTutorial);
	}



	@Override
	public List<Tutorial> listarTutoriales() {
		return tutorialRepository.findAll();
	}



	@Override
	public boolean editarTutorial(Tutorial tutorial) {
		Long idTutorial = tutorial.getIdTutorial();
		Optional<Tutorial> tutorialExistente = tutorialRepository.findById(idTutorial);
		
		if(tutorialExistente.isPresent()) {
			Tutorial tutorialActualizado = tutorialExistente.get();
			tutorialActualizado.setTitulo(tutorial.getTitulo());
			tutorialActualizado.setDescripcion(tutorial.getDescripcion());
			tutorialRepository.save(tutorialActualizado);
			return true;
		}
		
		return false;
	}

}
