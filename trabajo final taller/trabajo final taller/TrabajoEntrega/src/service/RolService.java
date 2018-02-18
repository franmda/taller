package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import clasesDaoEspecificas.*;
import daosjpa.*;
import modelo.*;

@Component
@Transactional
public class RolService {

			@Autowired
			RolDao uDAOJPA;
			
			public RolDao getuDAOJPA() {
				return uDAOJPA;
			}

			
			public void setuDAOJPA(UsuarioRolDAOHibernateJPA uDAOJPA) {
				this.uDAOJPA = uDAOJPA;
			}

			public List<TipoUsuario> findAll(){
				return uDAOJPA.traerRoles();
			}

			public TipoUsuario encontrarTipoUsuario(Integer id) {
				return uDAOJPA.recuperar(id);
			}

			public boolean existe(TipoUsuario rol) {
				Long id = rol.getId();
				return uDAOJPA.existe(id);
			}

			public void guardarTipoUsuario(TipoUsuario rol) {
				uDAOJPA.persistir(rol);
			}

			public void actualizarTipoUsuario(TipoUsuario rol) {
				uDAOJPA.actualizar(rol);
			}

			public void borrarTipoUsuario(Integer id) {
				uDAOJPA.borrar(id);
			}
}
