package dtos;

import modelo.Comentario;
import modelo.MediaContents;
import modelo.Publicacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PublicacionDTO {

   private Integer publicacion_id;
   private String tituloPublicacion;
   private String cuerpo;
   private Date fecha;
   private Boolean comentariosHabilitados;
   private String estado;
   private List<MediaContentDTO> mediaContents;
   private List<ComentarioDTO> comentarios;
   private UsuarioDTO usuario;

   public PublicacionDTO(Publicacion pub){
	   this.publicacion_id=pub.getPublicacion_id();
	   this.tituloPublicacion=pub.getTituloPublicacion();
	   this.cuerpo=pub.getCuerpo();
	   this.fecha=pub.getFecha();
	   this.comentariosHabilitados=pub.getComentariosHabilitados();
	   this.estado=pub.getEstado();
	   this.comentarios=new ArrayList<ComentarioDTO>();
	   this.setUsuario(new UsuarioDTO(pub.getUsuario_id()));
	   List<Comentario> coms=(List<Comentario>) pub.getComentarios();
	   for(Comentario c: coms){
		   ComentarioDTO cdto=new ComentarioDTO(c);
		   this.comentarios.add(cdto); 
	   }
	   
	   this.mediaContents=new ArrayList<MediaContentDTO>();
	   List<MediaContents> mediaConts=(List<MediaContents>) pub.getMediaContents();
	   for(MediaContents mc:mediaConts){
		   MediaContentDTO mcdto=new MediaContentDTO(mc);
		   this.mediaContents.add(mcdto);
	   }
	   
	   
	   
   }
   public PublicacionDTO(Integer id){
	   this.publicacion_id=id;
   }
   public Integer getPublicacion_id() {
		return publicacion_id;
	}

	public void setPublicacion_id(Integer publicacion_id) {
		this.publicacion_id = publicacion_id;
	}

	public String getTituloPublicacion() {
		return tituloPublicacion;
	}

	public void setTituloPublicacion(String tituloPublicacion) {
		this.tituloPublicacion = tituloPublicacion;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Boolean getComentariosHabilitados() {
		return comentariosHabilitados;
	}

	public void setComentariosHabilitados(Boolean comentariosHabilitados) {
		this.comentariosHabilitados = comentariosHabilitados;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<MediaContentDTO> getMediaContents() {
		return mediaContents;
	}

	public void setMediaContents(List<MediaContentDTO> mediaContents) {
		this.mediaContents = mediaContents;
	}

	public List<ComentarioDTO> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentarioDTO> comentarios) {
		this.comentarios = comentarios;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}


}
