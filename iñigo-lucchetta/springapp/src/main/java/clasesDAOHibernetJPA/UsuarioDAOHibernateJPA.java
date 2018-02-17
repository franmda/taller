package clasesDAOHibernetJPA;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import clasesDAO.UsuarioDAO;
import modelo.Usuario;


@Repository
public class UsuarioDAOHibernateJPA extends GenericDAOHibernateJPA<Usuario> implements UsuarioDAO {

	public UsuarioDAOHibernateJPA() {
		super(Usuario.class);
	}
	
	public Usuario recuperarConId (Long id) {
		return this.recuperar(id);
	}

	@Override
	public Usuario recuperarByUSername(String usuario) {
		Query consulta = this.getEntityManager().createQuery("select u from Usuario u where u.nombre = :usuario");
		consulta.setParameter("usuario", usuario);
		return (Usuario) consulta.getSingleResult();
		
	}
}
