package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponentsBuilder;

import dtos.PublicacionDTO;
import modelo.*;
import service.*;

@Transactional
@RestController
@EnableWebMvc
@RequestMapping("/publicacion")
public class PublicacionController {

	@Autowired
	PublicacionService publicacionService;

	@RequestMapping(value = "/publicacion", method = RequestMethod.GET)
	public ResponseEntity<List<PublicacionDTO>> listAllPublicacion() {
		List<PublicacionDTO> publicaciones = publicacionService.findAll();
		if (publicaciones.isEmpty()) {
			return new ResponseEntity<List<PublicacionDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PublicacionDTO>>(publicaciones,HttpStatus.OK);
	}

	@RequestMapping(value = "/publicacion/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PublicacionDTO> getPublicacion(@PathVariable("id") Integer id) {
		System.out.println("Obteniendo Publicacion con id " + id);
		Publicacion publicacion = publicacionService.recuperarPublicacion(id);
		
		for(Comentario c:publicacion.getComentarios() ){
			System.out.println("id comentario "+c.getComentario_id());
		}
		if (publicacion == null) {
			System.out.println("Publicacion con id " + id + " no encontrado");
			return new ResponseEntity<PublicacionDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PublicacionDTO>(new PublicacionDTO(publicacion), HttpStatus.OK);
	}

	@RequestMapping(value = "/publicacionesDeCartelera/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PublicacionDTO>> listPublicacionesDeCartelera(@PathVariable("id") Integer id) {
		System.out.println("Obteniendo Publicaciones relacionadas a la Cartelera con id " + id);
		List<PublicacionDTO> publicaciones = publicacionService.encontrarPublicacionesDeCartelera(id);
		if (publicaciones.isEmpty()) {
			return new ResponseEntity<List<PublicacionDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PublicacionDTO>>(publicaciones,HttpStatus.OK);
	}

	@RequestMapping(value = "/publicacion", method = RequestMethod.POST)
	public ResponseEntity<Integer> createPublicacion(@RequestBody Publicacion publicacion, UriComponentsBuilder ucBuilder) {
		System.out.println("Creando Publicacion "+ publicacion.getTituloPublicacion());
		publicacion.setEstado("habilitado");
		publicacionService.guardarPublicacion(publicacion);
		HttpHeaders headers = new HttpHeaders();
		System.out.println(publicacion.getPublicacion_id());
		System.out.println("*************************");
		headers.setLocation(ucBuilder.path("/publicacion/{id}").buildAndExpand(publicacion.getPublicacion_id()).toUri());
		return new ResponseEntity<Integer>(publicacion.getPublicacion_id(), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/publicacion/{id}", method = RequestMethod.PUT)
	public ResponseEntity<PublicacionDTO> updatePublicacion(@PathVariable("id") Integer id, @RequestBody Publicacion publicacion) {
		System.out.println("Actualizando Publicacion " + id);
		Publicacion currentPublicacion = publicacionService.recuperarPublicacion(id);
		if (currentPublicacion == null) {
			System.out.println("publicacion con id " + id + " no encontrada");
			return new ResponseEntity<PublicacionDTO>(HttpStatus.NOT_FOUND);
		}
		currentPublicacion.setTituloPublicacion(publicacion.getTituloPublicacion());
		currentPublicacion.setCuerpo(publicacion.getCuerpo());
		currentPublicacion.setComentariosHabilitados(publicacion.getComentariosHabilitados());
		publicacionService.actualizarPublicacion(currentPublicacion);
		PublicacionDTO pubDto=new PublicacionDTO(currentPublicacion);
		return new ResponseEntity<PublicacionDTO>(pubDto,HttpStatus.OK);
	}

	@RequestMapping(value = "/publicacion/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<PublicacionDTO> deletePublicacion(@PathVariable("id") Integer id) {
		System.out.println("Obteniendo y eliminando Publicacion con id " + id);
		Publicacion publicacion = publicacionService.recuperarPublicacion(id);
		if (publicacion == null) {
			System.out.println("No es posible eliminar, no se encuentra Publicacion con id " + id);
			return new ResponseEntity<PublicacionDTO>(HttpStatus.NOT_FOUND);
		}
		publicacionService.borrarPublicacion(publicacion);
		return new ResponseEntity<PublicacionDTO>(HttpStatus.OK);
	}
}
