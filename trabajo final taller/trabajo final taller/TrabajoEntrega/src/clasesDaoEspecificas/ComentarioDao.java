package clasesDaoEspecificas;

import java.util.List;

import modelo.*;
import clasesDao.GenericDao;
import dtos.ComentarioDTO;

public interface ComentarioDao extends GenericDao<Comentario>{

	public List<Comentario> traerComentarios();

	public List<ComentarioDTO> comentariosDePublicacion(Integer id);
}
