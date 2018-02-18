package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import dtos.CarteleraUserDTO;
import dtos.UsuarioDTO;
import modelo.*;
import modeloSiu.Log;
import service.*;

@Transactional
@RestController
@EnableWebMvc
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UserService userService;
	
	@Autowired
	CarteleraService carteleraService;
	
	

	@RequestMapping(value = "/usuario", method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> listAllUsers() {
		List<UsuarioDTO> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UsuarioDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UsuarioDTO>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDTO> getUser(@PathVariable("id") Integer id) {
		System.out.println("Obteniendo usuario con id " + id);
		UsuarioDTO user = userService.encontrarUsuarioDTO(id);
		System.out.println(user.getCartelerasInteresadas().size());
		for(CarteleraUserDTO u:user.getCartelerasInteresadas()){
			System.out.println(u.getCartelera_id());
		}
		
		if (user == null) {
			System.out.println("Usuario con id " + id + " no encontrado");
			return new ResponseEntity<UsuarioDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UsuarioDTO>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/usuariosConPermisoPublicacion", method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> userPermitidosParaPublicar() {
		List<UsuarioDTO> users = userService.encontrarUsrsConPermisoParaPublicar();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UsuarioDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UsuarioDTO>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/usuario", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody Usuario user, UriComponentsBuilder ucBuilder) {
		System.out.println("Creando el usuario " + user.getNombre());
		userService.guardarUsuario(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/usuario/{id}").buildAndExpand(user.getUsuario_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UsuarioDTO> updateUser(@PathVariable("id") Integer id, @RequestBody Usuario user) {
		System.out.println("Actualizando el usuario " + id);
		UsuarioDTO currentUser = userService.encontrarUsuarioDTO(id);
		if (currentUser == null) {
			System.out.println("User con id " + id + " no encontrado");
			return new ResponseEntity<UsuarioDTO>(HttpStatus.NOT_FOUND);
		}
		currentUser.setNombre(user.getNombre());
		currentUser.setUsrName(user.getUsrName());
		currentUser.setDni(user.getDni());
		userService.actualizarUserDTO(currentUser);

		return new ResponseEntity<UsuarioDTO>(currentUser, HttpStatus.OK);
	}

	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Usuario> deleteUser(@PathVariable("id") Integer id) {
		System.out.println("Obteniendo y eliminando el usuario con id " + id);
		Usuario user = userService.encontrarUsuario(id);
		if (user == null) {
			System.out.println("No es posible eliminar, no se encuentra el usuario con id " + id);
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		userService.borrarUsuario(id);
		return new ResponseEntity<Usuario>(HttpStatus.OK);
	}

	@RequestMapping(value = "/usuario/login", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody Log user) throws Exception {
		System.out.println("Verificando el usuario " + user.getUsrName());
		UsuarioDTO usuario = userService.encontrarUsuario(user);
		if (usuario == null) {
			System.out .println("Usuario o contraseña invalida");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ObjectMapper JSON_MAPPER = new ObjectMapper();
		String jsonString = JSON_MAPPER.writeValueAsString(usuario);
		return ResponseEntity.ok(jsonString);
	}
	
	
	@RequestMapping(value = "/interes/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UsuarioDTO> updateIntereses(@PathVariable("id") Integer id, @RequestBody Cartelera cart) {
		Usuario usuario = userService.encontrarUsuario(id);
		usuario.getCartelerasInteresadas().add(cart);
		userService.actualizarUser(usuario);
		return new ResponseEntity<UsuarioDTO>(new UsuarioDTO(usuario), HttpStatus.OK);
	}
}
