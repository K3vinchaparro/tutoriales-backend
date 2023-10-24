package ud.prueba.tecnica.modelos;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "tutorial")
@Data
public class Tutorial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTutorial;
	
	private String titulo;
	private String descripcion;
	private Date fecha;
	private String foto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tutorial")
	@JsonIgnore
	private List<Comentario> comentatios;
	
	public Tutorial() {}
}
