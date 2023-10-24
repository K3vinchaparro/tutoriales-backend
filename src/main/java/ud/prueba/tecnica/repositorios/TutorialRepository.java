package ud.prueba.tecnica.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import ud.prueba.tecnica.modelos.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long>{
	
	Tutorial findByIdTutorial(Long idTutorial);

}
