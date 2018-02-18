package modelo;

import java.io.Serializable;
import java.util.Date;




import javax.persistence.*;



@Entity
public class Comentario implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="comentario_id")
	private Integer comentario_id;
	
	@Column(name="estado")
	private String estado;
	
	
	
	//CORRECCION_BIEN
	@ManyToOne(optional = false)
	@JoinColumn(name="publicacion_id")
	private Publicacion pubMComentario;
	
	
	@Column(name="msj")
	private String msj;

	@Column(name="dia")
	private Date dia;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="usuario_id")
	private Usuario usuario_id;
	

	
	public Comentario() {
		super();
	}

	public Comentario(Usuario usuario, Date fecha, String comentario) {
		this.msj = comentario;
		this.dia = fecha;
		this.usuario_id = usuario;
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
	public Integer getComentario_id() {
		return comentario_id;
	}
	public void setComentario_id(Integer comentario_id) {
		this.comentario_id = comentario_id;
	}
	
	@Override
	public String toString() {
		String usuario="";
		if(usuario_id!=null){
			usuario=usuario_id.getUsrName();
		}
		return "Comentario [comentario_id=" + comentario_id + ", msj=" + msj
				+ ", dia=" + dia + ", usuario=" + usuario + "]";
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Publicacion getPubMComentario() {
		return pubMComentario;
	}

	public void setPubMComentario(Publicacion pubMComentario) {
		this.pubMComentario = pubMComentario;
	}

	public Usuario getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(Usuario usuario_id) {
		this.usuario_id = usuario_id;
	}


	
	
	

}
