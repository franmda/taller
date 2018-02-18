package service;

import java.util.List;
import modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import clasesDaoEspecificas.*;
import daosjpa.*;


@Component
public class MediaService {
	@Autowired
	MediaDao uDAOJPA;

	public MediaDao getuDAOJPA() {
		return uDAOJPA;
	}

	public void setuDAOJPA(MediaDAOHibernateJPA uDAOJPA) {
		this.uDAOJPA = uDAOJPA;
	}

	public List<MediaContents> findAll(){
		return uDAOJPA.traerMedia();
	}

	public MediaContents encontrarMedia(Integer id) {
		return uDAOJPA.recuperar(id);
	}

	public boolean existe(MediaContents media) {
		Integer id = media.getMediaContents_id();
		return uDAOJPA.existe(id);
	}

	public void guardarMedia(MediaContents media) {
		uDAOJPA.persistir(media);
	}

	public void actualizarMedia(MediaContents media) {
		uDAOJPA.actualizar(media);
	}

	public void borrarMedia(Integer id) {
		uDAOJPA.borrar(id);
	}
}
