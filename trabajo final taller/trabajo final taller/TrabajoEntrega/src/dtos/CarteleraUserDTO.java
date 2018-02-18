package dtos;

import modelo.Cartelera;

public class CarteleraUserDTO {

	private Integer Cartelera_id;
	private String titulo;
	private Integer prioridad;
	private Integer visibilidad;
	private String tipoCartelera;
	private String portada;

	public CarteleraUserDTO(Cartelera cart) {
		this.Cartelera_id = cart.getCartelera_id();
		this.titulo = cart.getTitulo();
		this.prioridad = cart.getPrioridad();
		this.visibilidad = cart.getPrioridad();
		this.tipoCartelera = cart.getTipoCartelera();
		this.portada = cart.getPortada();
	}

	
	public CarteleraUserDTO(Integer cart_id ){
		this.Cartelera_id=cart_id;
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
}
