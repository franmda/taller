package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import clasesDAO.UsuarioDAO;
import modelo.Usuario;

@Component
public class UserService {
	
	@Autowired
	UsuarioDAO usuarioDAO;
	
	public Usuario guardarUsuario (Usuario anUser) {
		return usuarioDAO.persistir(anUser); 
	}
	
	public Usuario buscar (Long id) {
		return usuarioDAO.recuperar(id);
	}
	
	public Usuario getUserByNombre(String username) {
		return usuarioDAO.recuperarByUSername(username);
	}
}
