package ud.prueba.tecnica.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class DefaultHandlerExcepcion extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<String> idNoEncontradoExcepcion(ExcepcionPersonalizada ex){
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
