package modelo;

import javax.persistence.*;

@Entity
public class MediosDeContacto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "contacto_id")
	private Long contacto_id;

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name="estado")
	private String estado;
	

	
	//CORRECCION_BIEN
	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id")
	private Usuario usuarioMDC;

	public MediosDeContacto() {
	}

	public MediosDeContacto(String nombre) {
		this.nombre = nombre;
	}

	public Long getContacto_id() {
		return contacto_id;
	}

	public void setContacto_id(Long contacto_id) {
		this.contacto_id = contacto_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getUsuarioMDC() {
		return usuarioMDC;
	}

	public void setUsuarioMDC(Usuario usuarioMDC) {
		this.usuarioMDC = usuarioMDC;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
