package control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.Vector;

import assembler.UsuarioOrganizacionAssembler;

import comun.DTO.UsuarioOrganizacionDTO;
import comun.i_control.IUsuarioOrganizacion;

import persistencia.AccesoBD;
import persistencia.dominio.UsuarioOrganizacion;


public class ControlUsuarioOrganizacion extends UnicastRemoteObject implements IUsuarioOrganizacion{
	
	private static final long serialVersionUID = 1L;
	private AccesoBD accesoBD;
	private UsuarioOrganizacionAssembler assembler;
	

	public ControlUsuarioOrganizacion(AccesoBD accesoBD) throws RemoteException {
		super();
		this.accesoBD =accesoBD;
		assembler = new UsuarioOrganizacionAssembler(accesoBD);
	}


	@Override
	public Long agregarUsuarioOrganizacion(UsuarioOrganizacionDTO uoDTO)	throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			UsuarioOrganizacion uo = (UsuarioOrganizacion) accesoBD.hacerPersistente(assembler.getUsuarioOrganizacion(uoDTO));
			Long id = uo.getId();
			accesoBD.concretarTransaccion();
			return id;
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}


	@Override
	public void eliminarUsuarioOrganizacion(Long id) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			UsuarioOrganizacion uo = (UsuarioOrganizacion) accesoBD.buscarPorId(UsuarioOrganizacion.class, id);
			accesoBD.eliminar(uo);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}


	@Override
	public Vector<UsuarioOrganizacionDTO> obtenerUsuariosOrganizacion()throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Iterator pos = accesoBD.listar(UsuarioOrganizacion.class).iterator();
			accesoBD.concretarTransaccion();
			Vector<UsuarioOrganizacionDTO> res = new Vector<UsuarioOrganizacionDTO>();
			while (pos.hasNext()) {
				UsuarioOrganizacion uo = (UsuarioOrganizacion) pos.next();
				res.add(assembler.getUsuarioOrganizacionDTO(uo));
			}
			return res;
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}


	@Override
	public Vector<UsuarioOrganizacionDTO> obtenerUsuariosOrganizacion(String filtro) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Iterator pos = accesoBD.buscarPorFiltro(UsuarioOrganizacion.class,filtro).iterator();
			Vector<UsuarioOrganizacionDTO> res = new Vector<UsuarioOrganizacionDTO>();
			while (pos.hasNext()) {
				UsuarioOrganizacion po = (UsuarioOrganizacion) pos.next();
				res.add(assembler.getUsuarioOrganizacionDTO(po));
			}
			accesoBD.concretarTransaccion();
			return res;
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}


	@Override
	public Vector<UsuarioOrganizacionDTO> obtenerUsuariosOrganizacion(String filtro, String orden, String agrupar) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Iterator pos = accesoBD.getObjectosOrdenadosYAgrupados(UsuarioOrganizacion.class,filtro,orden,agrupar).iterator();
			Vector<UsuarioOrganizacionDTO> res = new Vector<UsuarioOrganizacionDTO>();
			while (pos.hasNext()) {
				UsuarioOrganizacion po = (UsuarioOrganizacion) pos.next();
				res.add(assembler.getUsuarioOrganizacionDTO(po));
			}
			accesoBD.concretarTransaccion();
			return res;
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}


	@Override
	public boolean existeUsuarioOrganizacion(Long id) throws Exception {
		return buscarUsuarioOrganizacion(id)!=null;
	}


	@Override
	public boolean existeUsuarioOrganizacion(Long idPers, Long idOrg)throws Exception {
		return buscarUsuarioOrganizacion(idPers,idOrg)!=null;
	}


	@Override
	public UsuarioOrganizacionDTO buscarUsuarioOrganizacion(Long id)throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			UsuarioOrganizacion uo = (UsuarioOrganizacion) accesoBD.buscarPorId(UsuarioOrganizacion.class, id);
			UsuarioOrganizacionDTO uoDTO = assembler.getUsuarioOrganizacionDTO(uo);
			accesoBD.concretarTransaccion();
			return uoDTO;
		} catch (Exception e) {
			e.printStackTrace();
			accesoBD.rollbackTransaccion();
			return null;
		}
	}

	@Override
	public UsuarioOrganizacionDTO buscarUsuarioOrganizacion(Long idPers,Long idOrg) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			UsuarioOrganizacion po = (UsuarioOrganizacion) accesoBD.buscarPorFiltro(UsuarioOrganizacion.class, "usuario.id=="+idPers+" && org.id=="+idOrg).toArray()[0];
			UsuarioOrganizacionDTO poDTO = assembler.getUsuarioOrganizacionDTO(po);
			accesoBD.concretarTransaccion();
			return poDTO;
		} catch (Exception e) {
			e.printStackTrace();
			accesoBD.rollbackTransaccion();
			return null;
		}		
	}

	
}
