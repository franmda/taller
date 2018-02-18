package clasesDaoEspecificas;

import java.util.List;

import modelo.*;
import clasesDao.GenericDao;

public interface ContactoDao  extends GenericDao<MediosDeContacto> {

	public MediosDeContacto buscarContacto(String string);

	public List<MediosDeContacto>  traerMedios();

}
