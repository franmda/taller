package daosjpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import clasesDaoEspecificas.*;
import dtos.ComentarioDTO;
import modelo.*;

@Repository
public class ComentarioDAOHibernateJPA extends GenericDAOHibernateJPA<Comentario> implements ComentarioDao{

	public ComentarioDAOHibernateJPA() {
		super(Comentario.class);
	}

	@Override
	public List<Comentario> traerComentarios() {
		Query consulta = this.getEntityManager().createQuery("select r from Comentario r");
		@SuppressWarnings("unchecked")
		List<Comentario> resultado = (List<Comentario>) consulta.getResultList();
		return resultado;
	}
	
	@Override
	public void borrar(Comentario entity) {
		entity.setEstado("deshabilitado");
		this.actualizar(entity);
	}

	@Override
	public List<ComentarioDTO> comentariosDePublicacion(Integer id) {
		Query consulta = this.getEntityManager().createQuery("select c from Comentario c where c.pubMComentario.publicacion_id=?1");
		consulta.setParameter(1, id);
		@SuppressWarnings("unchecked")
		List<Comentario> resultado = (List<Comentario>) consulta.getResultList();
		List<ComentarioDTO> resultadoFinal=new ArrayList<ComentarioDTO>();
		for(Comentario c:resultado){
			ComentarioDTO cdto=new ComentarioDTO(c);
			resultadoFinal.add(cdto);
			
		}
		return resultadoFinal;
	}

}
