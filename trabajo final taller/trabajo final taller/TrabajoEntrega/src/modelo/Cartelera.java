package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import dtos.CarteleraDTO;
import dtos.UsuarioDTO;

@Entity
public class Cartelera {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Cartelera_id")
	private Integer Cartelera_id;

	@Column(name = "portada")
	private String portada;
	
	@Column(name = "estado")
	private String estado;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "prioridad")
	private Integer prioridad;

	@Column(name = "visibilidad")
	private Boolean visibilidad;

	@Column(name = "tipoCartelera")
	private String tipoCartelera;

	@ManyToMany
	@JoinTable(name = "permisosUsuario_Carteleras", joinColumns = @JoinColumn(name = "Cartelera_id", referencedColumnName = "Cartelera_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id"))
	private List<Usuario> usuariosConPermisos;

	@ManyToMany
	@JoinTable(name = "usuarioInteresados_Carteleras", joinColumns = @JoinColumn(name = "Cartelera_id", referencedColumnName = "Cartelera_id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "usuario_id"))
	private List<Usuario> usuariosInteresados;

	// CORRECCION_BIEN
	@OneToMany(mappedBy = "cartPub")
	private List<Publicacion> publicaciones;

	public List<Usuario> getUsuariosConPermisos() {
		return usuariosConPermisos;
	}

	public void setUsuariosConPermisos(List<Usuario> usuariosConPermisos) {
		this.usuariosConPermisos = usuariosConPermisos;
	}

	public List<Usuario> getUsuariosInteresados() {
		return usuariosInteresados;
	}

	public void setUsuariosInteresados(List<Usuario> usuariosInteresados) {
		this.usuariosInteresados = usuariosInteresados;
	}

	public Cartelera() {
	}

	public Cartelera(String titulo, Integer prioridad, Boolean visibilidad,
			String tipoCartelera, String portada) {
		this.titulo = titulo;
		this.prioridad = prioridad;
		this.visibilidad = visibilidad;
		this.tipoCartelera = tipoCartelera;
		this.portada = portada;
		this.usuariosConPermisos = new ArrayList<Usuario>();
		this.usuariosInteresados = new ArrayList<Usuario>();
	}
	
	
	public Cartelera(CarteleraDTO cart) {
		this.titulo = cart.getTitulo();
		this.prioridad = cart.getPrioridad();
		this.tipoCartelera = cart.getTipoCartelera();
		this.portada = cart.getPortada();
		this.usuariosConPermisos = new ArrayList<Usuario>();
		this.usuariosInteresados = new ArrayList<Usuario>();
		if(cart.getUsuariosConPermisos()!=null){
			for(UsuarioDTO u:cart.getUsuariosConPermisos()){
				this.usuariosConPermisos.add(new Usuario(u));
			}
		}
		if(cart.getUsuariosInteresados()!=null){
			for(UsuarioDTO u:cart.getUsuariosInteresados()){
				this.usuariosInteresados.add(new Usuario(u));
			}
		}
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public Boolean getVisibilidad() {
		return visibilidad;
	}

	public void setVisibilidad(Boolean visibilidad) {
		this.visibilidad = visibilidad;
	}

	public String getTipoCartelera() {
		return tipoCartelera;
	}

	public void setTipoCartelera(String tipoCartelera) {
		this.tipoCartelera = tipoCartelera;
	}

	public void agregarUsuarioPermitido(Usuario user) {
		getUsuariosConPermisos().add(user);
	}

	public Integer getCartelera_id() {
		return Cartelera_id;
	}

	public void setCartelera_id(Integer cartelera_id) {
		Cartelera_id = cartelera_id;
	}

	@Override
	public String toString() {
		return "Cartelera [Cartelera_id=" + Cartelera_id + ", titulo=" + titulo
				+ ", prioridad=" + prioridad + ", visibilidad=" + visibilidad
				+ ", tipoCartelera=" + tipoCartelera + ", portada=" + portada
				+ "]";
	}

	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}


}
