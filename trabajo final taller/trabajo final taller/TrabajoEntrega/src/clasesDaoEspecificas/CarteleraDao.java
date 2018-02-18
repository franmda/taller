package clasesDaoEspecificas;

import java.util.List;

import modelo.*;
import clasesDao.*;
import dtos.CarteleraDTO;

public interface CarteleraDao extends GenericDao<Cartelera>{

	public Cartelera buscarCartelera(Long num);
	public Cartelera buscarCartelera(String string);
	public List<CarteleraDTO> traerCarteleras();
	public CarteleraDTO recuperarDTO(Integer id);
	public void borrar(Cartelera entity);
}
