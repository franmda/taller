package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import modelo.Usuario;
import service.UserService;

@RestController
public class UsuarioRestController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/usuarios", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Usuario user, UriComponentsBuilder ucBuilder) {
		System.out.println("Creando el usuario");
		userService.guardarUsuario(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/usuarios/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> getUser(@PathVariable("id") long id) {
		System.out.println("Obteniendo usuario con id " + id);
		Usuario user = userService.buscar(id);
		if (user == null) {
			System.out.println("Usuario con id " + id + " no encontrado");
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/autenticacion", method = RequestMethod.POST)
	 public ResponseEntity<?> autentificarUsuario(@RequestBody Usuario user, UriComponentsBuilder ucBuilder)
	 {
		 System.out.println(user.getNombre());
		 System.out.println(user.getClave());
		 if (user.getNombre() != "" && user.getClave() != "") {
			Usuario usuarioencontrado =  userService.getUserByNombre(user.getNombre());
			System.out.println(usuarioencontrado.getId());
			
			if (usuarioencontrado != null) {
				if (usuarioencontrado.getClave().equals( user.getClave())) {
					String token = usuarioencontrado.getId().toString() + "123456";
					System.out.println("todo ok");
					System.out.println(token);
					return new ResponseEntity<String>(token,HttpStatus.OK);
				}
				else {
					System.out.println("password incorrecta");
					return new ResponseEntity<>(HttpStatus.FORBIDDEN);
				}
			}
			else {
				System.out.println("usuario no encontrado");
				 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
		 }
		 else {
			 System.out.println("Vacios los campos");
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		 }	 
	 }
	
}
