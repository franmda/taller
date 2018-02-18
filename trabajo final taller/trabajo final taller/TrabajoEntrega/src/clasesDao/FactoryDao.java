package clasesDao;

import clasesDaoEspecificas.*;
import daosjpa.*;

public class FactoryDao {

	public static UsuarioDao getUsuarioDao() {
		return new UsuarioDAOHibernateJPA();
	}

	public static PublicacionDao getPublicacionDao() {
		return new PublicacionDAOHibernateJPA();
	}

	public static CarteleraDao getCarteleraDao() {
		return new CarteleraDAOHibernateJPA();
	}

	public static ComentarioDao getComentarioDao() {
		return new ComentarioDAOHibernateJPA();
	}

	public static RolDao getRolDao() {
		return new UsuarioRolDAOHibernateJPA();
	}

	public static ContactoDao getContactoDao() {
		return new ContactoDAOHibernateJPA();
	}

	public static TemplateDao getTempleteDao() {
		return new TemplateDAOHibernateJPA();
	}

	public static MediaDao getMediaDao() {
		return new MediaDAOHibernateJPA();
	}

}
