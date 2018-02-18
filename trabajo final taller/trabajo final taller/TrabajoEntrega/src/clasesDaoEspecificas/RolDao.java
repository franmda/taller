package clasesDaoEspecificas;

import java.util.List;

import modelo.*;
import clasesDao.GenericDao;

public interface RolDao extends GenericDao<TipoUsuario>{

	public TipoUsuario buscarRol(String string);
	public List<TipoUsuario> traerRoles();
}
