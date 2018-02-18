package modelo;

import javax.persistence.*;

import org.hibernate.annotations.Type;

@Entity
public class MediaContents {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MediaContents_id")
	private Integer MediaContents_id;
	

	
	@Column(name = "fileName")
	private String fileName;
	
	
	//CORRECCION_BIEN
	@ManyToOne(optional = false)
	@JoinColumn(name="publicacion_id")
	private Publicacion pubMContents;

	public MediaContents(){}

	public MediaContents(String fileName,  Publicacion pub) {
		this.fileName = fileName;

		this.pubMContents=pub;
	}






	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getMediaContents_id() {
		return MediaContents_id;
	}
	public void setMediaContents_id(Integer mediaContents_id) {
		MediaContents_id = mediaContents_id;
	}


	public void setPublicacionMConts(Publicacion publicacionMConts) {
		this.pubMContents = publicacionMConts;
	}



	public Publicacion getPubMContents() {
		return pubMContents;
	}

	public void setPubMContents(Publicacion pubMContents) {
		this.pubMContents = pubMContents;
	}

}
