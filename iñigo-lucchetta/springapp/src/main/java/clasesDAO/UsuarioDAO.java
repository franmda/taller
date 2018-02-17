package clasesDAO;

import modelo.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {
	
	public Usuario recuperarConId (Long id);
	public Usuario recuperarByUSername(String username);
}
