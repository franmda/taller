package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponentsBuilder;

import dtos.CarteleraDTO;
import modelo.*;
import service.*;

@Transactional
@RestController
@EnableWebMvc
@RequestMapping("/cartelera")
public class CarteleraController {

	@Autowired
	CarteleraService carteleraService;

	@RequestMapping(value = "/cartelera", method = RequestMethod.GET)
	public ResponseEntity<List<CarteleraDTO>> listAllCarteleras() {
		List<CarteleraDTO> carts = carteleraService.findAll();
		if (carts.isEmpty()) {
			return new ResponseEntity<List<CarteleraDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CarteleraDTO>>(carts, HttpStatus.OK);
	}

	@RequestMapping(value = "/cartelera/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CarteleraDTO> getCartelera(@PathVariable("id") Integer id) {
		System.out.println("Obteniendo Cartelera con id " + id);
		CarteleraDTO cartelera = carteleraService.encontrarCarteleraDTO(id);
		if (cartelera == null) {
			System.out.println("Cartelera con id " + id + " no encontrada");
			return new ResponseEntity<CarteleraDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CarteleraDTO>(cartelera, HttpStatus.OK);
	}

	@RequestMapping(value = "/cartelera", method = RequestMethod.POST)
	public ResponseEntity<Void> createCartelera(@RequestBody Cartelera cartelera, UriComponentsBuilder ucBuilder) {
		System.out.println("Creando Cartelera " + cartelera.getTitulo());
		carteleraService.guardarCartelera(cartelera);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/cartelera/{id}").buildAndExpand(cartelera.getCartelera_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/cartelera/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Cartelera> updateCartelera(@PathVariable("id") Integer id, @RequestBody Cartelera cartelera) {
		System.out.println("Actualizando la cartelera " + id);
		Cartelera currentCartelera = carteleraService.encontrarCartelera(id);
		if (currentCartelera == null) {
			System.out.println("cartelera con id " + id + " no encontrada");
			return new ResponseEntity<Cartelera>(HttpStatus.NOT_FOUND);
		}
		currentCartelera.setTitulo(cartelera.getTitulo());
		currentCartelera.setPrioridad(cartelera.getPrioridad());
		currentCartelera.setTipoCartelera(cartelera.getTipoCartelera());
		carteleraService.actualizarCartelera(cartelera);
		return new ResponseEntity<Cartelera>(currentCartelera, HttpStatus.OK);
	}

	@RequestMapping(value = "/cartelera/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Cartelera> deleteCartelera(@PathVariable("id") Integer id) {
		System.out.println("Obteniendo y eliminando Cartelera con id " + id);
		Cartelera cartelera = carteleraService.encontrarCartelera(id);
		if (cartelera == null) {
			System.out.println("No es posible eliminar, no se encuentra Cartelera con id " + id);
			return new ResponseEntity<Cartelera>(HttpStatus.NOT_FOUND);
		}
		carteleraService.borrarCartelera(id);
		return new ResponseEntity<Cartelera>(HttpStatus.OK);
	}

}