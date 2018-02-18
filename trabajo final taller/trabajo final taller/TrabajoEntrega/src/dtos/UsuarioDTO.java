package dtos;

import java.util.ArrayList;
import java.util.List;

import modelo.Cartelera;
import modelo.Template;
import modelo.TipoUsuario;
import modelo.Usuario;

public class UsuarioDTO {
	private Integer usuario_id;
	private String usrName;
	private String pass;
	private String nombre;
	private String apellido;
	private Boolean habilitacionSist;
	private String mail;
	private Long dni;
	private Template template_id;
	private TipoUsuario rol_id;
	private List<CarteleraUserDTO> cartelerasInteresadas;



	@Override
	public String toString() {
		String rol="";
		if(rol_id!=null){
			rol=rol_id.getNombreRol();
		}
		return "Usuario [usuario_id=" + usuario_id + ", usrName=" + usrName
				+ ", pass=" + pass + ", nombre=" + nombre + ", apellido="
				+ apellido + ", habilitacionSist=" + habilitacionSist
				+ ", mail=" + mail + ", dni=" + dni + ", Rol="+ rol + "]" ;
	}

	public UsuarioDTO(Usuario user){
		super();
		this.usuario_id=user.getUsuario_id();
		this.usrName = user.getUsrName();
		this.pass = user.getPass();
		this.nombre = user.getNombre();
		this.apellido = user.getApellido();
		this.habilitacionSist = user.getHabilitacionSist();
		this.mail = user.getMail();
		this.dni = user.getDni();
		this.rol_id = user.getRol_id();
		this.cartelerasInteresadas=new ArrayList<CarteleraUserDTO>();
		if(user.getCartelerasInteresadas()!=null){
			List<Cartelera> carts=(List<Cartelera>) user.getCartelerasInteresadas();
			for(Cartelera c: carts){
				CarteleraUserDTO cDTO=new CarteleraUserDTO(c.getCartelera_id());
				this.cartelerasInteresadas.add(cDTO);		   
			}
		}
	}
	
	public UsuarioDTO(){
	}
	
	public UsuarioDTO(String usrName, String pass, String nombre,
			String apellido, Boolean habilitacionSist, String mail, Long dni, TipoUsuario rol,Template template) {
		super();
		this.usrName = usrName;
		this.pass = pass;
		this.nombre = nombre;
		this.apellido = apellido;
		this.habilitacionSist = habilitacionSist;
		this.mail = mail;
		this.dni = dni;
		this.rol_id = rol;
		this.template_id = template;
	}


	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Boolean getHabilitacionSist() {
		return habilitacionSist;
	}

	public void setHabilitacionSist(Boolean habilitacionSist) {
		this.habilitacionSist = habilitacionSist;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public TipoUsuario getRol_id() {
		return rol_id;
	}

	public void setRol_id(TipoUsuario rol_id) {
		this.rol_id = rol_id;
	}


	public Integer getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Integer usuario_id) {
		this.usuario_id = usuario_id;
	}


	public String rol(){
		return this.getRol_id().getNombreRol();
	}


	public Template getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(Template template_id) {
		this.template_id = template_id;
	}
	
	public List<CarteleraUserDTO> getCartelerasInteresadas() {
		return cartelerasInteresadas;
	}

	public void setCartelerasInteresadas(List<CarteleraUserDTO> cartelerasInteresadas) {
		this.cartelerasInteresadas = cartelerasInteresadas;
	}

}