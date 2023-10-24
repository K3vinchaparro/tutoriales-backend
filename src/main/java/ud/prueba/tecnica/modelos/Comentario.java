package ud.prueba.tecnica.modelos;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "comentario")
@Data
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idComentario;
	
	private String contenido;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Tutorial tutorial;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;
	
	public Comentario() {}
}
