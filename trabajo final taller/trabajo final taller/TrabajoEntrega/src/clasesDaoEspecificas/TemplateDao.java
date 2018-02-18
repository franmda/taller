package clasesDaoEspecificas;

import java.util.List;

import modelo.*;
import clasesDao.GenericDao;

public interface TemplateDao  extends GenericDao<Template> {

	public Template buscarTemplate(int i);
	public Template buscarTemplateTrucho();
	public List<Template> traerTemplates();

}
