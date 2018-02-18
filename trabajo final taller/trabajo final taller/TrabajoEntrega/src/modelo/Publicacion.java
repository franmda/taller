package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.google.gson.Gson;



@Entity
public class Publicacion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="publicacion_id")
	private Integer publicacion_id;
	
	@Column(name="tituloPublicacion")
	private String tituloPublicacion;
	
	@Column(name="cuerpo")
	private String cuerpo;
	
	@Column(name="fecha")
	private Date fecha;
	
	@Column(name="comentariosHabilitados")
	private Boolean comentariosHabilitados;
	
	@Column(name="estado")
	private String estado;
	

	@ManyToOne(optional=false)
	@JoinColumn(name="usuario_id")
	private Usuario usuario_id;
	
	
	//CORRECCION_BIEN
	@ManyToOne(optional=false)
	@JoinColumn(name="Cartelera_id")
	private Cartelera cartPub;	

	
	//CORRECCION_BIEN
	@OneToMany(mappedBy="pubMContents")
	private List<MediaContents> mediaContents;
	
	//CORRECCION_BIEN
	@OneToMany(mappedBy="pubMComentario")
	private List<Comentario> comentarios;
	
	public Publicacion(String tituloPublicacion, String cuerpo, Date fecha, Boolean comentariosHabilitados,Usuario user,Cartelera cartelera) {
		this.tituloPublicacion = tituloPublicacion;
		this.cuerpo = cuerpo;
		this.fecha = fecha;
		this.comentariosHabilitados = comentariosHabilitados;
		this.usuario_id=user;
		this.cartPub = cartelera;
		this.mediaContents = new ArrayList<MediaContents>();
	}

    public Publicacion(String json){
    	System.out.println("******************************************");
    	System.out.println(json);
    	Gson gson = new Gson();
    	Publicacion pub=gson.fromJson(json, Publicacion.class);
    	this.tituloPublicacion=pub.getTituloPublicacion();
    	this.cuerpo=pub.getCuerpo();
    	this.fecha=pub.getFecha();
    	this.comentariosHabilitados=pub.getComentariosHabilitados();
    	this.usuario_id=pub.getUsuario_id();
    	this.cartPub=pub.getCartPub();
    }

	public Publicacion(){}

	
	
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

	public Integer getPublicacion_id() {
		return publicacion_id;
	}
	public void setPublicacion_id(Integer publicacion_id) {
		this.publicacion_id = publicacion_id;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public String getTituloPublicacion() {
		return tituloPublicacion;
	}
	
	public void setTituloPublicacion(String tituloPublicacion) {
		this.tituloPublicacion = tituloPublicacion;
	}



	public void agregarMedia(MediaContents media) {
		getMediaContents().add(media);
	}



	public Usuario getUsuario_id() {
		return usuario_id;
	}



	public void setUsuario_id(Usuario usuario_id) {
		this.usuario_id = usuario_id;
	}



	public Cartelera getCartelera_id() {
		return cartPub;
	}



	public void setCartelera_id(Cartelera cartelera_id) {
		cartPub = cartelera_id;
	}



	public List<MediaContents> getMediaContents() {
		return mediaContents;
	}



	public void setMediaContents(List<MediaContents> mediaContents) {
		this.mediaContents = mediaContents;
	}



	public void agregarComentario(Comentario comentario) {
		getComentarios().add(comentario);
		
	}



	public List<Comentario> getComentarios() {
		return comentarios;
	}



	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	@Override
	public String toString() {
		String usuario="";
		if(usuario_id!=null){
			usuario=usuario_id.getUsrName();
		}
		String publicacion="";
		if(cartPub!=null){
			publicacion=cartPub.getTitulo();
		}
		return "Publicacion [publicacion_id=" + publicacion_id
				+ ", tituloPublicacion=" + tituloPublicacion + ", cuerpo="
				+ cuerpo + ", fecha=" + fecha + ", comentariosHabilitados="
				+ comentariosHabilitados + ", usuario_id=" + usuario
				+ ", Cartelera_id=" + publicacion + "]";
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public Cartelera getCartPub() {
		return cartPub;
	}



	public void setCartPub(Cartelera cartPub) {
		this.cartPub = cartPub;
	}
}
