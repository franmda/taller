package daosjpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import clasesDaoEspecificas.*;
import modelo.*;

@Transactional
@Repository
public class UsuarioRolDAOHibernateJPA extends GenericDAOHibernateJPA<TipoUsuario> implements RolDao{

	public UsuarioRolDAOHibernateJPA() {
		super(TipoUsuario.class);
	}

	@Override
	public TipoUsuario buscarRol(String nombre) {
		Query consulta = this.getEntityManager().createQuery("select r from TipoUsuario r where r.nombreRol =?1");
		consulta.setParameter(1, nombre);
		TipoUsuario resultado = (TipoUsuario) consulta.getSingleResult();
		return resultado;
	}

	@Override
	public List<TipoUsuario> traerRoles() {
		Query consulta = this.getEntityManager().createQuery("select r from TipoUsuario r");
		@SuppressWarnings("unchecked")
		List<TipoUsuario> resultado = (List<TipoUsuario>) consulta.getResultList();
		return resultado;
	}

}
