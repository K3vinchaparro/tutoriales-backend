package ud.prueba.tecnica.modelos;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "rol")
@Data
public class Rol {
	
	@Id
	private Long idRol;
	private String nombreRol;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rol")
	@JsonIgnore
	private Set<Usuario> usuarios;
	
	public Rol() {}

	public Rol(Long idRol, String nombreRol) {
		this.idRol = idRol;
		this.nombreRol = nombreRol;
	}
	
	

}
