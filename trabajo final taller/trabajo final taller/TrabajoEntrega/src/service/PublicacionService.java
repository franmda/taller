package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import clasesDaoEspecificas.*;
import daosjpa.PublicacionDAOHibernateJPA;
import dtos.PublicacionDTO;
import modelo.*;

@Component
@Transactional

public class PublicacionService {
		@Autowired
		PublicacionDao uDAOJPA;
		
		public PublicacionDao getuDAOJPA() {
			return uDAOJPA;
		}

		
		public void setuDAOJPA(PublicacionDAOHibernateJPA uDAOJPA) {
			this.uDAOJPA = uDAOJPA;
		}

		public List<PublicacionDTO> findAll(){
			return uDAOJPA.traerPublicaciones();
		}
		
		public List<PublicacionDTO> encontrarPublicacionesDeCartelera(Integer id){
			return uDAOJPA.encontrarPublicacionesDeCartelera(id);
		}
		


		public PublicacionDTO encontrarPublicacionDTO(Integer id) {
			return uDAOJPA.recuperarPublicacion(id);
		}

		public boolean existe(Publicacion publicacion) {
			Integer id = publicacion.getPublicacion_id();
			return uDAOJPA.existe(id);
		}

		public void guardarPublicacion(Publicacion publicacion) {
			uDAOJPA.persistirLibre(publicacion);
		}

		public void actualizarPublicacion(Publicacion publicacion) {
			uDAOJPA.actualizar(publicacion);
		}

		public void borrarPublicacion(Publicacion publicacion) {
			uDAOJPA.borrar(publicacion);
		}

		public Publicacion recuperarPublicacion(Integer id) {
			return uDAOJPA.recuperar(id);
		}
}
