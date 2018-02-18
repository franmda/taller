package dtos;

import modelo.MediaContents;

public class MediaContentDTO {
	private Integer MediaContents_id;
	private String fileName;
	private PublicacionDTO pubMContents;
	
	public MediaContentDTO(MediaContents m){
		this.MediaContents_id=m.getMediaContents_id();
		this.fileName=m.getFileName();
        this.pubMContents=   new PublicacionDTO(m.getPubMContents().getPublicacion_id());
	}
	public Integer getMediaContents_id() {
		return MediaContents_id;
	}
	public void setMediaContents_id(Integer mediaContents_id) {
		MediaContents_id = mediaContents_id;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public PublicacionDTO getPubMContents() {
		return pubMContents;
	}
	public void setPubMContents(PublicacionDTO pubMContents) {
		this.pubMContents = pubMContents;
	}

}
