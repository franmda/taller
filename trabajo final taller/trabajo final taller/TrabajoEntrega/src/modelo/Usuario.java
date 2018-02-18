package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dtos.UsuarioDTO;

//import com.fasterxml.jackson.annotation.JsonIgnore;

@EnableTransactionManagement
@Entity
public class Usuario {
	@Id
	@Column(name="usuario_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer usuario_id;
	
	@Column(name="usrName")
	private String usrName;

	@Column(name="estado")
	private String estado="habilitado";
	
	@Column(name="pass")
	private String pass;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="habilitacionSist")
	private Boolean habilitacionSist;
	
	@Column(name="mail")
	private String mail;
	
	@Column(name="dni")
	private Long dni;
	
	@OneToMany(mappedBy="usuarioMDC")
	private List<MediosDeContacto> mediosDeContacto;
	
	@OneToMany(mappedBy="usrTemplate")
	private List<Template> templatesMios;

	@ManyToOne(optional=false)
	@JoinColumn(name="rol_id")
	private TipoUsuario rol_id;
	
	//@JsonIgnore
	@ManyToMany
	@JoinTable(name="usuarioInteresados_Carteleras",
	 joinColumns=@JoinColumn(name="user_id",
	 referencedColumnName="usuario_id"),
	 inverseJoinColumns=@JoinColumn(name="Cartelera_id",
	 referencedColumnName="Cartelera_id"))
	private List<Cartelera> cartelerasInteresadas;

	
	
	
	
	
	
	
	
	
	public Usuario(){
		
	}
	
	public Usuario(String usrName, String pass, String nombre,
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
		this.estado="habilitado";
		this.mediosDeContacto = new ArrayList<MediosDeContacto> ();
		this.cartelerasInteresadas = new ArrayList<Cartelera> ();
	}


	public Usuario(UsuarioDTO user) {
		super();
		this.usuario_id=user.getUsuario_id();
		this.usrName = user.getUsrName();
		this.pass = user.getPass();
		this.nombre = user.getNombre();
		this.apellido = user.getApellido();
		this.habilitacionSist = user.getHabilitacionSist();
		this.mail = user.getMail();
		this.estado="habilitado";
		this.dni = user.getDni();
		this.rol_id = user.getRol_id();

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

	public List<MediosDeContacto> getMediosDeContacto() {
		return mediosDeContacto;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void setMediosDeContacto(List<MediosDeContacto> mediosDeContacto) {
		this.mediosDeContacto = mediosDeContacto;
	}

	public String rol(){
		return this.getRol_id().getNombreRol();
	}

	public void agregarContacto(MediosDeContacto medio) {
		getMediosDeContacto().add(medio);
		
	}



	public void agregarInteres(Cartelera cartelera) {
		getCartelerasInteresadas().add(cartelera);
	}


	public List<Cartelera> getCartelerasInteresadas() {
		return cartelerasInteresadas;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)	
	public void setCartelerasInteresadas(List<Cartelera> cartelerasInteresadas) {
		this.cartelerasInteresadas = cartelerasInteresadas;
	}
	
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
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

	public List<Template> getTemplatesMios() {
		return templatesMios;
	}

	public void setTemplatesMios(List<Template> templatesMios) {
		this.templatesMios = templatesMios;
	}


}