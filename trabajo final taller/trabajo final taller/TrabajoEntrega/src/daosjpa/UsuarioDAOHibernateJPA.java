package daosjpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import clasesDaoEspecificas.UsuarioDao;
import dtos.UsuarioDTO;
import modelo.*;
import modeloSiu.Log;

@Transactional
@Repository
public class UsuarioDAOHibernateJPA extends GenericDAOHibernateJPA<Usuario>
		implements UsuarioDao {
	public UsuarioDAOHibernateJPA() {
		super(Usuario.class);
	}

	/**
	 * esté método es a modo de ejemplo, se agregaría para cosas particulares de
	 * la entidad Persona
	 **/


	@Override
	public Usuario loginUsuario(String nombre, String pass) {
		return null;
	}

	@Override
	public UsuarioDTO recuperarDTO(Integer id){
		return new UsuarioDTO(this.recuperar(id));
	}
	
	@Override
	public Usuario buscarUsuario(Long documento) {
		Query consulta = this.getEntityManager().createQuery("select u from Usuario u where u.dni =?1");
				consulta.setParameter(1, documento);
				@SuppressWarnings("unchecked")
				List<Usuario> res = (List<Usuario>) consulta.getResultList();
				if (!res.isEmpty()){
					return res.get(0);	   
				}else{
					return null;
				}
	}

	@Override
	public List<UsuarioDTO> traerUsuarios() {
		Query consulta = this.getEntityManager().createQuery("select r from Usuario r where r.estado=?1");
		consulta.setParameter(1, "habilitado");
		@SuppressWarnings("unchecked")
		List<Usuario> resultado = (List<Usuario>) consulta.getResultList();
		List<UsuarioDTO> resultadoFinal=new ArrayList<UsuarioDTO>();
		for(Usuario u:resultado){
			UsuarioDTO udto=new UsuarioDTO(u);
			resultadoFinal.add(udto);
		}
		return resultadoFinal;
	}
	
	@Override
	public void borrar(Usuario entity) {
		entity.setEstado("deshabilitado");
		this.actualizar(entity);
	}

	@Override
	public UsuarioDTO buscarUsuario(Log user) {
		Query consulta = this.getEntityManager().createQuery("select u from Usuario u where u.usrName =?1 AND u.pass=?2 AND u.estado=?3");
		consulta.setParameter(1, user.getUsrName());
		consulta.setParameter(2, user.getPass());
		consulta.setParameter(3, "habilitado");
		@SuppressWarnings("unchecked")
		List<Usuario> res = (List<Usuario>) consulta.getResultList();
		if (!res.isEmpty()){
			return new UsuarioDTO(res.get(0));	   
		}else{
			return null;
		}
	}

	@Override
	public List<UsuarioDTO> traerUsuariosPermitidosParaPublicar() {
	    Query consulta = this.getEntityManager().createQuery("select u from Usuario u where u.rol_id.nombreRol <> ?1 AND u.estado=?2");
	    consulta.setParameter(1, "comun");
	    consulta.setParameter(2, "habilitado");
		@SuppressWarnings("unchecked")
		List<Usuario> resultado = (List<Usuario>) consulta.getResultList();
		List<UsuarioDTO> resultadoFinal=new ArrayList<UsuarioDTO>();
		for(Usuario u:resultado){
			UsuarioDTO udto=new UsuarioDTO(u);
			resultadoFinal.add(udto);
		}
		return resultadoFinal;
	}
}
