package dtos;

import modelo.Comentario;

import java.util.Date;

public class ComentarioDTO {
	private Integer comentario_id;
	

	private String estado;
	private String msj;
	private Date dia;
	private String usuario;
	private PublicacionDTO pubMComentario;
	
	public ComentarioDTO(Comentario c){
		this.comentario_id=c.getComentario_id();
		this.estado=c.getEstado();
		this.msj=c.getMsj();
		this.dia=c.getDia();
		this.usuario=c.getUsuario_id().getUsrName();
		this.pubMComentario= new PublicacionDTO(c.getPubMComentario().getPublicacion_id());
		
	}
	
	public PublicacionDTO getPubMComentario() {
		return pubMComentario;
	}

	public void setPubMComentario(PublicacionDTO pubMComentario) {
		this.pubMComentario = pubMComentario;
	}

	public Integer getComentario_id() {
		return comentario_id;
	}

	public void setComentario_id(Integer comentario_id) {
		this.comentario_id = comentario_id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMsj() {
		return msj;
	}

	public void setMsj(String msj) {
		this.msj = msj;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
