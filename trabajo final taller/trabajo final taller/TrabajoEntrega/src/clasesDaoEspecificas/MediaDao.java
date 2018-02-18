package clasesDaoEspecificas;

import java.util.List;

import modelo.*;
import clasesDao.GenericDao;

public interface MediaDao  extends GenericDao<MediaContents>{

	public List<MediaContents> traerMedia();
}
