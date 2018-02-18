package daosjpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import clasesDaoEspecificas.*;
import modelo.*;
import dtos.PublicacionDTO;

import java.util.ArrayList;

@Repository
public class PublicacionDAOHibernateJPA extends GenericDAOHibernateJPA<Publicacion> implements PublicacionDao{

	public PublicacionDAOHibernateJPA() {
		super(Publicacion.class);
	}

	@Override
	public Publicacion buscarPublicacion() {
		Query consulta = this.getEntityManager().createQuery("select p from Publicacion p where r.estado=?1");
		consulta.setParameter(1, "habilitado");
		@SuppressWarnings("unchecked")
		List<Publicacion> res = (List<Publicacion>) consulta.getResultList();
		return res.get(0);
	}

	@Override
	public List<PublicacionDTO> traerPublicaciones() {
		Query consulta = this.getEntityManager().createQuery("select r from Publicacion r where r.estado=?1");
		consulta.setParameter(1, "habilitado");
		@SuppressWarnings("unchecked")
		List<Publicacion> resultado = (List<Publicacion>) consulta.getResultList();
		List<PublicacionDTO> resultadoFinal=new ArrayList<PublicacionDTO>();
		for(Publicacion p:resultado){
			PublicacionDTO pdto=new PublicacionDTO(p);
			resultadoFinal.add(pdto);
			
		}
		return resultadoFinal;
	}

	
	
	@Override
	public List<PublicacionDTO> encontrarPublicacionesDeCartelera(Integer id) {
		Query consulta = this.getEntityManager().createQuery("select p from Publicacion p where p.cartPub.Cartelera_id =?1 AND p.estado=?2");
		consulta.setParameter(1, id);
		consulta.setParameter(2, "habilitado");
		@SuppressWarnings("unchecked")
		List<Publicacion> resultado = (List<Publicacion>) consulta.getResultList();
		List<PublicacionDTO> resultadoFinal=new ArrayList<PublicacionDTO>();
		for(Publicacion p:resultado){
			PublicacionDTO pdto=new PublicacionDTO(p);
			resultadoFinal.add(pdto);
			
		}
		return resultadoFinal;
	}

	@Override
	public PublicacionDTO recuperarPublicacion(Integer id) {
		return new PublicacionDTO(this.recuperar(id));
	}

	@Override
	public void borrar(Publicacion entity) {
		entity.setEstado("deshabilitado");
		this.actualizar(entity);
	}

}
