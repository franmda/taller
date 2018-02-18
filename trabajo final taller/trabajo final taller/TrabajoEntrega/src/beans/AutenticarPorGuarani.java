package beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import modeloSiu.ProfesorSiu;
import modeloSiu.UsuarioSiu;

@Component
public class AutenticarPorGuarani implements Autenticable {
	private List<UsuarioSiu> usuariosDelSiu;
	private List<ProfesorSiu> profesoresDelSiu;

	public AutenticarPorGuarani(){
		this.usuariosDelSiu=new ArrayList<UsuarioSiu>();
		this.profesoresDelSiu=new ArrayList<ProfesorSiu>();
		
		
		//lista de usuarios
		for (int i=0;i<10;i++){
			String s=String.valueOf(i);
			String s1= new StringBuilder("A").append(s).toString();
			UsuarioSiu nuevoUsuario=new UsuarioSiu(i,s1,s1,"lalala",s1);

			usuariosDelSiu.add(nuevoUsuario);
								
		}
		
		//lista de profesores
		String s1= "p1";
		List<String> aniosp1=new ArrayList<String>();
		aniosp1.add("primero");
		aniosp1.add("segundo");
		ProfesorSiu nuevoProfesor=new ProfesorSiu(1,s1,"lalala",aniosp1,s1);
		profesoresDelSiu.add(nuevoProfesor);
		
		
		s1= "p2";
		List<String> aniosp2=new ArrayList<String>();
		aniosp2.add("segundo");
		nuevoProfesor=new ProfesorSiu(2,s1,"lalala",aniosp2,s1);
		profesoresDelSiu.add(nuevoProfesor);
		
		s1= "p3";
		List<String> aniosp3=new ArrayList<String>();
		aniosp3.add("tercero");
		nuevoProfesor=new ProfesorSiu(3,s1,"lalala",aniosp3,s1);
		profesoresDelSiu.add(nuevoProfesor);
		
		s1= "p4";
		List<String> aniosp4=new ArrayList<String>();
		aniosp4.add("cuarto");
		aniosp4.add("quinto");
		nuevoProfesor=new ProfesorSiu(4,s1,"lalala",aniosp4,s1);
		profesoresDelSiu.add(nuevoProfesor);
		
		s1= "p5";
		List<String> aniosp5=new ArrayList<String>();
		aniosp5.add("quinto");
		nuevoProfesor=new ProfesorSiu(5,s1,"lalala",aniosp5,s1);
		profesoresDelSiu.add(nuevoProfesor);
		
	}

	@Override
	public UsuarioSiu autenticarUsuario(String usuario, String clave) {
		System.out.print(usuario);
		for (UsuarioSiu temp : this.getUsuariosDelSiu()) {
			if(temp.getNombres().equals(usuario)&temp.getClave().equals(clave)){
				
				return temp;
			}
		}
		return null;
	}

	@Override
	public UsuarioSiu retornarAlumno(Integer id) {
		for (UsuarioSiu temp : this.getUsuariosDelSiu()) {
			if(temp.getId().equals(id)){
				return temp;
			}
		}
		return null;
	}

	@Override
	public ProfesorSiu autenticarProfesor(String usuario, String clave) {
		for (ProfesorSiu temp : this.getProfesoresDelSiu()) {
			if(temp.getNombres().equals(usuario)&temp.getClave().equals(clave)){
				return temp;
			}
		}
		return null;
	}

	@Override
	public ProfesorSiu retornarProfesor(Integer id) {
		for (ProfesorSiu temp : this.getProfesoresDelSiu()) {
			if(temp.getId().equals(id)){
				return temp;
			}
		}
		return null;
	}

	@Override
	public List<UsuarioSiu> getUsuariosDelSiu() { 
		return usuariosDelSiu;
	}

	@Override
	public List<ProfesorSiu> getProfesoresDelSiu() {
		return profesoresDelSiu;
	}
}
