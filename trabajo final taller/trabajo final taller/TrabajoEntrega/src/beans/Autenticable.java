package beans;

import java.util.List;
import modeloSiu.ProfesorSiu;
import modeloSiu.UsuarioSiu;

public interface Autenticable {
	public abstract UsuarioSiu autenticarUsuario(String usuario,String clave);
	public abstract UsuarioSiu retornarAlumno(Integer id);
	public abstract ProfesorSiu autenticarProfesor(String usuario,String clave);
	public abstract ProfesorSiu retornarProfesor(Integer id);
	public List<UsuarioSiu> getUsuariosDelSiu();
	public List<ProfesorSiu> getProfesoresDelSiu();
	

}
