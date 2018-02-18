package controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponentsBuilder;
import modelo.*;
import service.*;

@Transactional
@RestController
@EnableWebMvc
@RequestMapping("/rol")
public class TipoUsuarioController {
		@Autowired
		RolService rolService;

		@RequestMapping(value = "/rol", method = RequestMethod.GET)
		public ResponseEntity<List<TipoUsuario>> listAllRols() {
			List<TipoUsuario> rol = rolService.findAll();
			if (rol.isEmpty()) {
				return new ResponseEntity<List<TipoUsuario>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<TipoUsuario>>(rol, HttpStatus.OK);
		}

		@RequestMapping(value = "/rol/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<TipoUsuario> getRol(@PathVariable("id") Integer id) {
			System.out.println("Obteniendo el rol con id " + id);
			TipoUsuario rol = rolService.encontrarTipoUsuario(id);
			if (rol == null) {
				System.out.println("Rol con id " + id + " no encontrado");
				return new ResponseEntity<TipoUsuario>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<TipoUsuario>(rol, HttpStatus.OK);
		}

		@RequestMapping(value = "/rol", method = RequestMethod.POST)
		public ResponseEntity<Void> createRol(@RequestBody TipoUsuario rol,UriComponentsBuilder ucBuilder) {
			System.out.println("Creando el rol " + rol.getNombreRol());
			rolService.guardarTipoUsuario(rol);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/rol/{id}").buildAndExpand(rol.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}

		@RequestMapping(value = "/rol/{id}", method = RequestMethod.PUT)
		public ResponseEntity<TipoUsuario> updateRol( @PathVariable("id") Integer id, @RequestBody TipoUsuario rol) {
			System.out.println("Actualizando el rol " + id);
			TipoUsuario currentRol = rolService.encontrarTipoUsuario(id);
			if (currentRol == null) {
				System.out.println("rol con id " + id + " no encontrado");
				return new ResponseEntity<TipoUsuario>(HttpStatus.NOT_FOUND);
			}
			currentRol.setNombreRol(rol.getNombreRol());
			currentRol.setId(rol.getId());
			rolService.actualizarTipoUsuario(currentRol);
			return new ResponseEntity<TipoUsuario>(currentRol, HttpStatus.OK);
		}

		@RequestMapping(value = "/rol/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<TipoUsuario> deleteRol(@PathVariable("id") Integer id) {
			System.out.println("Obteniendo y eliminando el rol con id " + id);
			TipoUsuario rol = rolService.encontrarTipoUsuario(id);
			if (rol == null) {
				System.out.println("No es posible eliminar, no se encuentra el tipo usuario con id " + id);
				return new ResponseEntity<TipoUsuario>(HttpStatus.NOT_FOUND);
			}
			rolService.borrarTipoUsuario(id);
			return new ResponseEntity<TipoUsuario>(HttpStatus.OK);
		}
}
