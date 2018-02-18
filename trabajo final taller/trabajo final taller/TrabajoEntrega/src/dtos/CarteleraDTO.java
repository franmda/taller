package dtos;

import java.util.ArrayList;
import java.util.List;

import modelo.Cartelera;
import modelo.Comentario;
import modelo.Usuario;

public class CarteleraDTO {
	private Integer Cartelera_id;
	private String titulo;
	private Integer prioridad;
	private Integer visibilidad;
	private String tipoCartelera;
	private String portada;
	private List<UsuarioDTO> usuariosConPermisos;
	private List<UsuarioDTO> usuariosInteresados;



	public CarteleraDTO(Cartelera cart) {
		this.Cartelera_id = cart.getCartelera_id();
		this.titulo = cart.getTitulo();
		this.prioridad = cart.getPrioridad();
		this.visibilidad = cart.getPrioridad();
		this.tipoCartelera = cart.getTipoCartelera();
		this.portada = cart.getPortada();
		this.usuariosConPermisos=new ArrayList<UsuarioDTO>();
		List<Usuario> users=(List<Usuario>) cart.getUsuariosConPermisos();
		 for(Usuario u: users){
			   UsuarioDTO udto=new UsuarioDTO(u);
			   this.usuariosConPermisos.add(udto);
		   }
		 
		 this.usuariosInteresados=new ArrayList<UsuarioDTO>();
		 List<Usuario> usersInteresados=(List<Usuario>) cart.getUsuariosInteresados();
		 for(Usuario u: usersInteresados){
			   UsuarioDTO udto=new UsuarioDTO(u);
			   this.usuariosInteresados.add(udto);
			   
			   
		   }
	}

	
	public CarteleraDTO(Integer cart_id ){
		this.Cartelera_id=cart_id;
	}
	
	
	public List<UsuarioDTO> getUsuariosConPermisos() {
		return usuariosConPermisos;
	}

	public void setUsuariosConPermisos(List<UsuarioDTO> usuariosConPermisos) {
		this.usuariosConPermisos = usuariosConPermisos;
	}

	public Integer getCartelera_id() {
		return this.Cartelera_id;
	}

	public void setCartelera_id(Integer cartelera_id) {
		this.Cartelera_id = cartelera_id;
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

	public Integer getVisibilidad() {
		return visibilidad;
	}

	public void setVisibilidad(Integer visibilidad) {
		this.visibilidad = visibilidad;
	}

	public String getTipoCartelera() {
		return tipoCartelera;
	}

	public void setTipoCartelera(String tipoCartelera) {
		this.tipoCartelera = tipoCartelera;
	}

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}
	
	public List<UsuarioDTO> getUsuariosInteresados() {
		return usuariosInteresados;
	}

	public void setUsuariosInteresados(List<UsuarioDTO> usuariosInteresados) {
		this.usuariosInteresados = usuariosInteresados;
	}
}