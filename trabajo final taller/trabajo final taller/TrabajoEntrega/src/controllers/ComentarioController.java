package controllers;

import java.util.List;

import modelo.Comentario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponentsBuilder;

import dtos.ComentarioDTO;
import service.ComentarioService;
import service.PublicacionService;
import service.UserService;

@Transactional
@RestController
@EnableWebMvc
@RequestMapping("/comentarios")
public class ComentarioController {
	@Autowired
	ComentarioService comentarioService;

	@Autowired
	UserService userService;
	
	@Autowired
	PublicacionService publicacionService;
	
	@RequestMapping(value = "/crearComentario", method = RequestMethod.POST)
	public ResponseEntity<Void> createComentario(@RequestBody Comentario com,UriComponentsBuilder ucBuilder) {
		if(com.getPubMComentario()!=null){
			System.out.println("Creando comentario publicacion" + com.getPubMComentario().getPublicacion_id());
			com.setPubMComentario(publicacionService.recuperarPublicacion(com.getPubMComentario().getPublicacion_id()));
		}else{ System.out.println("public era null");}
		
		if(com.getUsuario_id()!=null){
			System.out.println("Creando comentario usuario " + com.getUsuario_id().getUsuario_id());
			com.setUsuario_id(userService.encontrarUsuario(com.getUsuario_id().getUsuario_id()));
		}else{ System.out.println("public era null");}
		
		comentarioService.guardarComentario(com);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/comentario/{id}").buildAndExpand(com.getComentario_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/borrarComentario/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Comentario> deleteComentario(@PathVariable("id") Integer id) {
		System.out.println("Obteniendo y eliminando el comentario con id " + id);
		Comentario com = comentarioService.encontrarComentario(id);
		if (com == null) {
			System.out.println("No es posible eliminar, no se encuentra el comentario con id " + id);
			return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
		}
		comentarioService.borrarComentario(id);
		return new ResponseEntity<Comentario>(HttpStatus.OK);
	}

	@RequestMapping(value = "/comentariosDePublicacion/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<ComentarioDTO>> comentariosDePublicacion(@PathVariable("id") Integer id) {
		System.out.println("Obteniendo comentarios de la publicacion " + id);
		List<ComentarioDTO> comentarios = comentarioService.comentariosDePublicacion(id);
		if (comentarios.isEmpty()) {
			return new ResponseEntity<List<ComentarioDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ComentarioDTO>>(comentarios,HttpStatus.OK);
	}
}
