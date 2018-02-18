package daosjpa;

import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import modelo.*;
import clasesDaoEspecificas.*;

@Repository
public class TemplateDAOHibernateJPA  extends GenericDAOHibernateJPA<Template> implements  TemplateDao{

	public TemplateDAOHibernateJPA() {
		super(Template.class);
	}

	@Override
	public Template buscarTemplate(int id) {
		Query consulta = this.getEntityManager().createQuery("select r from Template r where r.template_id =?1");
		consulta.setParameter(1, id);
		Template resultado = (Template) consulta.getSingleResult();
		return resultado;
	}

	public Template buscarTemplateTrucho() {
		Query consulta = this.getEntityManager().createQuery("select r from Template r");
		@SuppressWarnings("unchecked")
		List<Template> res = (List<Template>) consulta.getResultList();
		return res.get(0);
	}
	
	
	@Override
	public List<Template> traerTemplates() {
		Query consulta = this.getEntityManager().createQuery("select r from Template r");
		@SuppressWarnings("unchecked")
		List<Template> resultado = (List<Template>) consulta.getResultList();
		return resultado;
	}
}
