package modeloSiu;

import java.util.List;

public class ProfesorSiu {
	
	private Integer id;
	private String nombres;
	private String apellidos;
	private List<String> anios;
	private String clave;
	
	public ProfesorSiu(Integer id, String nombres, String apellidos,
			List<String> anios,String clave) {
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.anios = anios;
		this.clave=clave;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public List<String> getAnios() {
		return anios;
	}
	public void setAnios(List<String> anios) {
		this.anios = anios;
	}


	public String getClave() {
		return clave;
	}


	public void setClave(String clave) {
		this.clave = clave;
	}
	

}
