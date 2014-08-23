package comun.i_control;

import java.rmi.Remote;
import java.util.Vector;

import comun.DTO.RegistroDTO;

public interface IRegistro  extends Remote{

	public Long agregarRegistro(RegistroDTO objeto)throws Exception;
	public void modificarRegistro(Long id,RegistroDTO modificado)throws Exception;
	
	public Vector<RegistroDTO> obtenerRegistros()throws Exception;
	public Vector<RegistroDTO> obtenerRegistros(String filtro)throws Exception;
	public Vector<RegistroDTO> obtenerRegistros(String filtro,String orden, String agrupar)throws Exception;
	
	public boolean existeRegistro(Long id) throws Exception;
	public boolean existeRegistroAbierto(Long idPersOrg, Long lugar) throws Exception;
	
	public RegistroDTO buscarRegistro(Long id) throws Exception;
	public RegistroDTO buscarRegistroAbierto(Long idPersOrg, Long lugar) throws Exception;
}
