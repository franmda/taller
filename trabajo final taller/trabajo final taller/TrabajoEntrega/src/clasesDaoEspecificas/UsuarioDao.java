package clasesDaoEspecificas;

import java.util.List;

import clasesDao.GenericDao;
import dtos.UsuarioDTO;
import modelo.*;
import modeloSiu.Log;

public interface UsuarioDao extends GenericDao<Usuario> {

	public Usuario loginUsuario(String nombre, String pass);
    public Usuario buscarUsuario(Long documento);
    public List<UsuarioDTO> traerUsuarios();
    public UsuarioDTO recuperarDTO(Integer id);
	public UsuarioDTO buscarUsuario(Log user);
	public List<UsuarioDTO> traerUsuariosPermitidosParaPublicar();
}
