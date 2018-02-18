package service;

import java.util.List;

import modelo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import clasesDaoEspecificas.*;
import daosjpa.*;

@Component
public class ContactoService {
	
	@Autowired
	ContactoDao uDAOJPA;
	
	public ContactoDao getuDAOJPA() {
		return uDAOJPA;
	}
	
	public void Dao(ContactoDAOHibernateJPA uDAOJPA) {
		this.uDAOJPA = uDAOJPA;
	}


	public List<MediosDeContacto> findAll(){
		return uDAOJPA.traerMedios();
	}

	public MediosDeContacto encontrarMedio(Integer id) {
		return uDAOJPA.recuperar(id);
	}

	public boolean existe(MediosDeContacto contacto) {
		Long id = contacto.getContacto_id();
		return uDAOJPA.existe(id);
	}

	public void guardarMedia(MediosDeContacto contacto) {
		uDAOJPA.persistir(contacto);
	}

	public void actualizarMedio(MediosDeContacto contacto) {
		uDAOJPA.actualizar(contacto);
	}

	public void borrarMedia(Integer id) {
		uDAOJPA.borrar(id);
	}
}
