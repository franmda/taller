package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import clasesDaoEspecificas.UsuarioDao;
import daosjpa.UsuarioDAOHibernateJPA;
import dtos.UsuarioDTO;
import modelo.*;
import modeloSiu.Log;

@Component
@Transactional
public class UserService {
	
	@Autowired
	UsuarioDao uDAOJPA;
	
	public UsuarioDao getuDAOJPA() {
		return uDAOJPA;
	}

	
	public void setuDAOJPA(UsuarioDAOHibernateJPA uDAOJPA) {
		this.uDAOJPA = uDAOJPA;
	}

	public List<UsuarioDTO> findAllUsers(){
		return uDAOJPA.traerUsuarios();
	}
	
	public List<UsuarioDTO> encontrarUsrsConPermisoParaPublicar(){
		return uDAOJPA.traerUsuariosPermitidosParaPublicar();
	}

	public UsuarioDTO encontrarUsuarioDTO(Integer id) {
		return uDAOJPA.recuperarDTO(id);
	}

	public Usuario encontrarUsuario(Integer id) {
		return uDAOJPA.recuperar(id);
	}
	
	public boolean existe(Usuario user) {
		Integer id = user.getUsuario_id();
		return uDAOJPA.existe(id);
	}

	public Usuario guardarUsuario(Usuario user) {
		return uDAOJPA.persistir(user);
	}

	public void actualizarUser(Usuario user) {
		uDAOJPA.actualizar(user);
	}

	public void borrarUsuario(Integer id) {
		uDAOJPA.borrar(id);
	}


	public boolean estaVacio() {
		return uDAOJPA.traerUsuarios().isEmpty();
	}


	public void actualizarUserDTO(UsuarioDTO currentUser) {
		uDAOJPA.actualizar(new Usuario(currentUser));
	}


	public UsuarioDTO encontrarUsuario(Log user) {
		return uDAOJPA.buscarUsuario(user);
	}
	
}
