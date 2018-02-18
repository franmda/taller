package modelo;



import javax.persistence.*;


@Entity
public class TipoUsuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "rol_id")
	private Long id;
	
	@Column(name = "nombreRol")
	private String nombreRol;
	
	@Column(name="estado")
	private String estado;
	
	
	
	public TipoUsuario(){}
	
	public TipoUsuario(String nombre) {
		this.nombreRol= nombre;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreRol() {
		return nombreRol;
	}
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	

}
