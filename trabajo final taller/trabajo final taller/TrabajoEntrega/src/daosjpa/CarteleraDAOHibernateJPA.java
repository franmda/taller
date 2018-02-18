package daosjpa;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import clasesDaoEspecificas.*;
import dtos.CarteleraDTO;
import modelo.*;

@Repository
public class CarteleraDAOHibernateJPA extends GenericDAOHibernateJPA<Cartelera> implements  CarteleraDao{

	public CarteleraDAOHibernateJPA() {
		super(Cartelera.class);
	}

	@Override
	public Cartelera buscarCartelera(Long id) {
		Query consulta = this.getEntityManager().createQuery("select c from Cartelera c where c.Cartelera_id =?1");
		consulta.setParameter(1, id);
		Cartelera resultado = (Cartelera) consulta.getSingleResult();
		return resultado;
	}

	@Override
	public Cartelera buscarCartelera(String nombre) {
		Query consulta = this.getEntityManager().createQuery("select c from Cartelera c where c.titulo =?1");
		consulta.setParameter(1, nombre);
		Cartelera resultado = (Cartelera) consulta.getSingleResult();
		return resultado;
	}

	/*SE utilizò un intermediario para devolver las carteleras porque sino 
	  REST se quedarìa en un bucle con las relaciones Many to Many*/
	@Override
	public List<CarteleraDTO> traerCarteleras() {
		Query consulta = this.getEntityManager().createQuery("select r from Cartelera r where r.estado=?1");
	    consulta.setParameter(1, "habilitado");
		@SuppressWarnings("unchecked")
		List<Cartelera> resultado = (List<Cartelera>) consulta.getResultList();
		List<CarteleraDTO> resultadoFinal=new ArrayList<CarteleraDTO>();
		for(Cartelera u:resultado){
			CarteleraDTO udto=new CarteleraDTO(u);
			resultadoFinal.add(udto);
		}
		return resultadoFinal;
	}

	@Override
	public CarteleraDTO recuperarDTO(Integer id) {
		return new CarteleraDTO(this.recuperar(id));
	}
	
	@Override
	public void borrar(Cartelera entity) {
		entity.setEstado("deshabilitado");
		this.actualizar(entity);
	}

}
