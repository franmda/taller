package clasesDaoEspecificas;

import java.util.List;

import modelo.*;
import clasesDao.GenericDao;
import dtos.PublicacionDTO;

public interface PublicacionDao extends GenericDao<Publicacion>{

	public Publicacion buscarPublicacion();
	public List<PublicacionDTO> traerPublicaciones();
	public List<PublicacionDTO> encontrarPublicacionesDeCartelera(Integer id);
	public PublicacionDTO recuperarPublicacion(Integer id);
	public void borrar(Publicacion entity);
}
