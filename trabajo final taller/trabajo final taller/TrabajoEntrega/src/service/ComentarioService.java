package service;

import java.util.List;

import modelo.Comentario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import clasesDaoEspecificas.ComentarioDao;
import daosjpa.ComentarioDAOHibernateJPA;
import dtos.ComentarioDTO;


@Component
public class ComentarioService {
	@Autowired
	ComentarioDao cDAOJPA;
	
	public ComentarioDao getuDAOJPA() {
		return cDAOJPA;
	}
	
	public void setComentarioDao(ComentarioDAOHibernateJPA uDAOJPA) {
		this.cDAOJPA = uDAOJPA;
	}
	
	public void  borrarComentario(Integer id) {
		cDAOJPA.borrar(id);
	}
	
	public Comentario encontrarComentario(Integer id) {
		return cDAOJPA.recuperar(id);
	}
	
	public Comentario guardarComentario(Comentario com) {
		return cDAOJPA.persistir(com);
	}

	public List<ComentarioDTO> comentariosDePublicacion(Integer id) {
		return cDAOJPA.comentariosDePublicacion(id);
	}

}
