package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import modeloSiu.ProfesorSiu;
import modeloSiu.UsuarioSiu;
import beans.Autenticable;
import beans.AutenticarPorGuarani;

@Component
public class AutenticaUsuarioService {
	@Autowired
	private Autenticable autenticador;
	

	public void setAutenticador(AutenticarPorGuarani autenticar) {
		this.autenticador = autenticar;
	}

	private Autenticable getAutenticador() {
		return autenticador;
	}

	public UsuarioSiu autenticarUsuario(String usuario, String clave) {
		System.out.println("Comienza el proceso de autenticación");
		UsuarioSiu aut = this.getAutenticador().autenticarUsuario(usuario, clave);
		System.out.println("Finaliza el proceso de autenticación.");
		return aut;
	}

	public ProfesorSiu autenticarProfesor(String profe, String clave) {
		System.out.println("Comienza el proceso de autenticación");
		ProfesorSiu aut = this.getAutenticador().autenticarProfesor(profe, clave);
		System.out.println("Finaliza el proceso de autenticación.");
		return aut;
	}

	public UsuarioSiu retornarAlumno(Integer id) {
		return this.getAutenticador().retornarAlumno(id);
	}

	public ProfesorSiu retornarProfesor(Integer id) {
		return this.getAutenticador().retornarProfesor(id);
	}

	public List<UsuarioSiu> listarAlumnos() {
		return this.getAutenticador().getUsuariosDelSiu();
	}

	public List<ProfesorSiu> listarProfesores() {
		return this.getAutenticador().getProfesoresDelSiu();
	}

}
