package controllers;

import java.util.List;

import modeloSiu.ProfesorSiu;
import modeloSiu.UsuarioSiu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import service.AutenticaUsuarioService;

@RestController
@EnableWebMvc
@RequestMapping("/autenticar")
public class SiuController {

	@Autowired
	AutenticaUsuarioService autenticarService;

	@RequestMapping(value = "/alumnos", method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioSiu>> listAllUsersSiu() {
		List<UsuarioSiu> users = autenticarService.listarAlumnos();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UsuarioSiu>>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<List<UsuarioSiu>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/alumnos/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioSiu> getUserSiu(@PathVariable("id") Integer id) {
		System.out.println("Obteniendo usuario con id " + id);
		UsuarioSiu user = autenticarService.retornarAlumno(id);
		if (user == null) {
			System.out.println("Usuario con id " + id + " no encontrado");
			return new ResponseEntity<UsuarioSiu>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UsuarioSiu>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/profesores", method = RequestMethod.GET)
	public ResponseEntity<List<ProfesorSiu>> listAllProfesores() {
		List<ProfesorSiu> users = autenticarService.listarProfesores();
		if (users.isEmpty()) {
			return new ResponseEntity<List<ProfesorSiu>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProfesorSiu>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/profesores/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProfesorSiu> getProfesor(@PathVariable("id") Integer id) {
		System.out.println("Obteniendo usuario con id " + id);
		ProfesorSiu user = autenticarService.retornarProfesor(id);
		if (user == null) {
			System.out.println("Usuario con id " + id + " no encontrado");
			return new ResponseEntity<ProfesorSiu>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProfesorSiu>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/alumnos/chequearlogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioSiu> chequearAlumno(@RequestBody String alumno,UriComponentsBuilder ucBuilder) {
		System.out.println("Validando usuario con " + alumno);
		JsonObject temp = new Gson().fromJson(alumno,JsonObject.class);
		UsuarioSiu autenticar = autenticarService.autenticarUsuario(temp.get("nombres").getAsString(), temp.get("clave").getAsString());
		if (autenticar==null) {
			System.out.println("Usuario  " + temp.get("nombres").getAsString() + " no encontrado");
			return new ResponseEntity<UsuarioSiu>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<UsuarioSiu>(autenticar,HttpStatus.OK);
	}

	
	@RequestMapping(value = "/profesor/chequearlogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProfesorSiu> chequearProfesor(@RequestBody String profesor,UriComponentsBuilder ucBuilder) {
		System.out.println("Validando usuario con " + profesor);
		JsonObject temp = new Gson().fromJson(profesor,JsonObject.class);
		ProfesorSiu autenticar = autenticarService.autenticarProfesor(temp.get("nombres").getAsString(), temp.get("clave").getAsString());
		if (autenticar==null) {
			System.out.println("Usuario  " + temp.get("nombres").getAsString() + " no encontrado");
			return new ResponseEntity<ProfesorSiu>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<ProfesorSiu>(autenticar,HttpStatus.OK);
	}
}
