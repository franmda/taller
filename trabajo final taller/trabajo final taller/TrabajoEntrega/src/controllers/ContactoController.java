package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponentsBuilder;

import modelo.*;
import service.ContactoService;

@Transactional
@RestController
@EnableWebMvc
@RequestMapping("/contacto")
public class ContactoController {
	
	@Autowired
	ContactoService contactoService;

	// Recupero todos los usuarios

	@RequestMapping(value = "/contacto", method = RequestMethod.GET)
	public ResponseEntity<List<MediosDeContacto>> listAllContactos() {
		List<MediosDeContacto> contacto = contactoService.findAll();
		if (contacto.isEmpty()) {
			return new ResponseEntity<List<MediosDeContacto>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<MediosDeContacto>>(contacto, HttpStatus.OK);
	}


	@RequestMapping(value = "/contacto/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MediosDeContacto> getMedioContacto(@PathVariable("id") Integer id) {
		System.out.println("Obteniendo medio de contacto con id " + id);
		MediosDeContacto medio = contactoService.encontrarMedio(id);
		if (medio == null) {
			System.out.println("Medio de contacto con id " + id + " no encontrado");
			return new ResponseEntity<MediosDeContacto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MediosDeContacto>(medio, HttpStatus.OK);
	}


	@RequestMapping(value = "/contacto", method = RequestMethod.POST)
	public ResponseEntity<Void> createContacto(@RequestBody MediosDeContacto medio, UriComponentsBuilder ucBuilder) {
		System.out.println("Creando el medio de contacto " + medio.getNombre());
		contactoService.guardarMedia(medio);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/contacto/{id}").buildAndExpand(medio.getContacto_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/contacto/{id}", method = RequestMethod.PUT)
	public ResponseEntity<MediosDeContacto> updateContacto(@PathVariable("id") Integer id, @RequestBody MediosDeContacto medio) {
		System.out.println("Actualizando el medio " + id);
		MediosDeContacto currentMedio = contactoService.encontrarMedio(id);
		if (currentMedio == null) {
			System.out.println("Medio de contacto con " + id + " no encontrado");
			return new ResponseEntity<MediosDeContacto>(HttpStatus.NOT_FOUND);
		}
		currentMedio.setNombre(medio.getNombre());
		currentMedio.setContacto_id(medio.getContacto_id());
		contactoService.actualizarMedio(currentMedio);
		return new ResponseEntity<MediosDeContacto>(currentMedio, HttpStatus.OK);
	}

	@RequestMapping(value = "/contacto/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<MediosDeContacto> deleteContacto(@PathVariable("id") Integer id) {
		System.out.println("Obteniendo y eliminando el Medio de contacto con id " + id);
		MediosDeContacto medios = contactoService.encontrarMedio(id);
		if (medios == null) {
			System.out.println("No es posible eliminar, no se encuentra el medio de contacto con id " + id);
			return new ResponseEntity<MediosDeContacto>(HttpStatus.NOT_FOUND);
		}
		contactoService.borrarMedia(id);
		return new ResponseEntity<MediosDeContacto>(HttpStatus.OK);
	}
}