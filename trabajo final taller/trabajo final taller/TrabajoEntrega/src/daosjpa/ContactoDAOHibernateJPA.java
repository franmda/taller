package daosjpa;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import clasesDaoEspecificas.*;
import modelo.*;

@Repository
public class ContactoDAOHibernateJPA extends GenericDAOHibernateJPA<MediosDeContacto> implements ContactoDao {

	public ContactoDAOHibernateJPA() {
		super(MediosDeContacto.class);
	}

	@Override
	public MediosDeContacto buscarContacto(String nombre) {
		Query consulta = this.getEntityManager().createQuery("select r from MediosDeContacto r where r.nombre =?1");
		consulta.setParameter(1, nombre);
		MediosDeContacto resultado = (MediosDeContacto) consulta.getSingleResult();
		return resultado;
	}

	@Override
	public List<MediosDeContacto> traerMedios() {
		Query consulta = this.getEntityManager().createQuery("select r from MediosDeContacto r");
		@SuppressWarnings("unchecked")
		List<MediosDeContacto> resultado = (List<MediosDeContacto>) consulta.getResultList();
		return resultado;
	}

		
}
