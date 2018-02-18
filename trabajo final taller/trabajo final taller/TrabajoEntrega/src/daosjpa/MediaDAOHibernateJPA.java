package daosjpa;


import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import modelo.*;
import clasesDaoEspecificas.*;

@Repository
public class MediaDAOHibernateJPA  extends GenericDAOHibernateJPA<MediaContents> implements MediaDao {

	public MediaDAOHibernateJPA() {
		super(MediaContents.class);
	}

	@Override
	public List<MediaContents> traerMedia() {
		Query consulta = this.getEntityManager().createQuery("select r from MediaContents r");
		@SuppressWarnings("unchecked")
		List<MediaContents> resultado = (List<MediaContents>) consulta.getResultList();
		return resultado;
	}
}
