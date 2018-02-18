package service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import clasesDaoEspecificas.*;
import daosjpa.*;
import modelo.*;

@Component
@Transactional
public class TemplateService {
	@Autowired
	TemplateDao uDAOJPA;
	
	public TemplateDao getuDAOJPA() {
		return uDAOJPA;
	}

	
	public void setuDAOJPA(TemplateDAOHibernateJPA uDAOJPA) {
		this.uDAOJPA = uDAOJPA;
	}

	public List<Template> findAll(){
		return uDAOJPA.traerTemplates();
	}

	public Template encontrarTemplate(Integer id) {
		return uDAOJPA.recuperar(id);
	}

	public boolean existe(Template template) {
		Integer id = template.getTemplate_id();
		return uDAOJPA.existe(id);
	}

	public void guardarTemplate(Template template) {
		uDAOJPA.persistir(template);
	}

	public void actualizarTemplate(Template template) {
		uDAOJPA.actualizar(template);
	}

	public void borrarTemplate(Integer id) {
		uDAOJPA.borrar(id);
	}

}
