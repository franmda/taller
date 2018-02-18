package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import clasesDaoEspecificas.*;
import daosjpa.*;
import dtos.CarteleraDTO;
import modelo.*;

@Component
@Transactional
public class CarteleraService {
	
	@Autowired
	CarteleraDao uDAOJPA;
	
	public CarteleraDTO encontrarCarteleraDTO(Integer id) {
		return uDAOJPA.recuperarDTO(id);
	}
	
	public CarteleraDao getuDAOJPA() {
		return uDAOJPA;
	}

	
	public void setuDAOJPA(CarteleraDAOHibernateJPA uDAOJPA) {
		this.uDAOJPA = uDAOJPA;
	}

	public List<CarteleraDTO> findAll(){
		return uDAOJPA.traerCarteleras();
	}

	public Cartelera encontrarCartelera(Integer id) {
		return uDAOJPA.recuperar(id);
	}

	public boolean existe(Cartelera cartelera) {
		Integer id = cartelera.getCartelera_id();
		return uDAOJPA.existe(id);
	}

	public void guardarCartelera(Cartelera cartelera) {
		uDAOJPA.persistir(cartelera);
	}

	public void actualizarCartelera(Cartelera cartelera) {
		uDAOJPA.actualizar(cartelera);
	}

	public void borrarCartelera(Integer id) {
		uDAOJPA.borrar(id);
	}
	
}
