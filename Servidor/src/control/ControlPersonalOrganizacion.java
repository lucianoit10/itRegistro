package control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.Vector;

import assembler.PersonalOrganizacionAssembler;

import comun.DTO.PersonalOrganizacionDTO;
import comun.i_control.IPersonalOrganizacion;

import persistencia.AccesoBD;
import persistencia.dominio.PersonalOrganizacion;


public class ControlPersonalOrganizacion extends UnicastRemoteObject implements IPersonalOrganizacion{
	
	private static final long serialVersionUID = 1L;
	private AccesoBD accesoBD;
	private PersonalOrganizacionAssembler assembler;
	

	public ControlPersonalOrganizacion(AccesoBD accesoBD) throws RemoteException {
		super();
		this.accesoBD =accesoBD;
		assembler = new PersonalOrganizacionAssembler(accesoBD);
	}


	@Override
	public Long agregarPersonalOrganizacion(PersonalOrganizacionDTO poDTO)	throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			PersonalOrganizacion po = (PersonalOrganizacion) accesoBD.hacerPersistente(assembler.getPersonalOrganizacion(poDTO));
			Long id = po.getId();
			accesoBD.concretarTransaccion();
			return id;
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}


	@Override
	public void eliminarPersonalOrganizacion(Long id) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			PersonalOrganizacion po = (PersonalOrganizacion) accesoBD.buscarPorId(PersonalOrganizacion.class, id);
			po.setEliminado(true);
			accesoBD.hacerPersistente(po);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}


	@Override
	public Vector<PersonalOrganizacionDTO> obtenerPersonalesOrganizacion()throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Iterator pos = accesoBD.listar(PersonalOrganizacion.class).iterator();
			accesoBD.concretarTransaccion();
			Vector<PersonalOrganizacionDTO> res = new Vector<PersonalOrganizacionDTO>();
			while (pos.hasNext()) {
				PersonalOrganizacion po = (PersonalOrganizacion) pos.next();
				res.add(assembler.getPersonalOrganizacionDTO(po));
			}
			return res;
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}


	@Override
	public Vector<PersonalOrganizacionDTO> obtenerPersonalesOrganizacion(String filtro) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Iterator pos = accesoBD.buscarPorFiltro(PersonalOrganizacion.class,filtro).iterator();
			Vector<PersonalOrganizacionDTO> res = new Vector<PersonalOrganizacionDTO>();
			while (pos.hasNext()) {
				PersonalOrganizacion po = (PersonalOrganizacion) pos.next();
				res.add(assembler.getPersonalOrganizacionDTO(po));
			}
			accesoBD.concretarTransaccion();
			return res;
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}


	@Override
	public Vector<PersonalOrganizacionDTO> obtenerPersonalesOrganizacion(String filtro, String orden, String agrupar) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Iterator pos = accesoBD.getObjectosOrdenadosYAgrupados(PersonalOrganizacion.class,filtro,orden,agrupar).iterator();
			Vector<PersonalOrganizacionDTO> res = new Vector<PersonalOrganizacionDTO>();
			while (pos.hasNext()) {
				PersonalOrganizacion po = (PersonalOrganizacion) pos.next();
				res.add(assembler.getPersonalOrganizacionDTO(po));
			}
			accesoBD.concretarTransaccion();
			return res;
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}


	@Override
	public boolean existePersonalOrganizacion(Long id) throws Exception {
		return buscarPersonalOrganizacion(id)!=null;
	}


	@Override
	public boolean existePersonalOrganizacion(Long idPers, Long idOrg)throws Exception {
		return buscarPersonalOrganizacion(idPers,idOrg)!=null;
	}


	@Override
	public PersonalOrganizacionDTO buscarPersonalOrganizacion(Long id)throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			PersonalOrganizacion po = (PersonalOrganizacion) accesoBD.buscarPorId(PersonalOrganizacion.class, id);
			PersonalOrganizacionDTO poDTO = assembler.getPersonalOrganizacionDTO(po);
			accesoBD.concretarTransaccion();
			return poDTO;
		} catch (Exception e) {
			e.printStackTrace();
			accesoBD.rollbackTransaccion();
			return null;
		}
	}

	@Override
	public PersonalOrganizacionDTO buscarPersonalOrganizacion(Long idPers,Long idOrg) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			PersonalOrganizacion po = (PersonalOrganizacion) accesoBD.buscarPorFiltro(PersonalOrganizacion.class, "persona.id=="+idPers+" && persona.eliminado==false && org.id=="+idOrg).toArray()[0];
			PersonalOrganizacionDTO poDTO = assembler.getPersonalOrganizacionDTO(po);
			accesoBD.concretarTransaccion();
			return poDTO;
		} catch (Exception e) {
			e.printStackTrace();
			accesoBD.rollbackTransaccion();
			return null;
		}		
	}


	@Override
	public void modificarPersonalOrganizacion(Long id,PersonalOrganizacionDTO persmodif) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			PersonalOrganizacion po = (PersonalOrganizacion) accesoBD.buscarPorId(PersonalOrganizacion.class, id);
			po.setIngreso(persmodif.getIngreso());
			po.setSalida(persmodif.getSalida());
			po.setMotivo(persmodif.getMotivo());
			po.setEliminado(persmodif.isEliminado());
			accesoBD.hacerPersistente(po);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}
	
}
