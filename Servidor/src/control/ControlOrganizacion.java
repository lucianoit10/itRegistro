package control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.Vector;

import assembler.OrganizacionAssembler;

import persistencia.AccesoBD;
import persistencia.dominio.Organizacion;

import comun.DTO.OrganizacionDTO;
import comun.i_control.IOrganizacion;


public class ControlOrganizacion extends UnicastRemoteObject  implements IOrganizacion{

	private static final long serialVersionUID = 1L;
	private AccesoBD accesoBD;
	private OrganizacionAssembler assembler;

	
	public ControlOrganizacion(AccesoBD accesoBD) throws RemoteException {
		super();
		this.accesoBD = accesoBD;
		assembler = new OrganizacionAssembler(accesoBD);
	}
	
	@Override
	public Long agregarOrganizacion(OrganizacionDTO orgDTO) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Organizacion o = (Organizacion)accesoBD.hacerPersistente(assembler.getOrganizacion(orgDTO));
			Long id = o.getId();
			accesoBD.concretarTransaccion();
			return id;
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void eliminarOrganizacion(Long id) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Organizacion org = (Organizacion) accesoBD.buscarPorId(Organizacion.class, id);
			org.setEliminado(true);
			accesoBD.hacerPersistente(org);
			accesoBD.concretarTransaccion();
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void modificarOrganizacion(Long id, OrganizacionDTO modificado) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Organizacion org = (Organizacion) accesoBD.buscarPorId(Organizacion.class, id);
			org.setNombre(modificado.getNombre());
			org.setCuit(modificado.getCuit());
			org.setEliminado(modificado.isEliminado());
			accesoBD.hacerPersistente(org);
			accesoBD.concretarTransaccion();
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Vector<OrganizacionDTO> obtenerOrganizaciones() throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Iterator organizaciones = accesoBD.listar(Organizacion.class).iterator();
			Vector<OrganizacionDTO> res = new Vector<OrganizacionDTO>();
			while (organizaciones.hasNext()) {
				Organizacion org = (Organizacion) organizaciones.next();
				res.add(assembler.getOrganizacionDTO(org));
			}
			accesoBD.concretarTransaccion();
			return res;
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Vector<OrganizacionDTO> obtenerOrganizaciones(String filtro)	throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Iterator organizaciones = accesoBD.buscarPorFiltro(Organizacion.class,filtro).iterator();
			Vector<OrganizacionDTO> res = new Vector<OrganizacionDTO>();
			while (organizaciones.hasNext()) {
				Organizacion org = (Organizacion) organizaciones.next();
				res.add(assembler.getOrganizacionDTO(org));
			}
			accesoBD.concretarTransaccion();
			return res;
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Vector<OrganizacionDTO> obtenerOrganizaciones(String filtro,	String orden, String agrupar) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Iterator organizaciones = accesoBD.getObjectosOrdenadosYAgrupados(Organizacion.class,filtro,orden,agrupar).iterator();
			Vector<OrganizacionDTO> res = new Vector<OrganizacionDTO>();
			while (organizaciones.hasNext()) {
				Organizacion org = (Organizacion) organizaciones.next();
				res.add(assembler.getOrganizacionDTO(org));
			}
			accesoBD.concretarTransaccion();
			return res;
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public boolean existeOrganizacion(Long id) throws Exception {
		return buscarOrganizacion(id)!=null;
	}

	@Override
	public boolean existeOrganizacion(String cuit) throws Exception {
		return buscarOrganizacion(cuit)!=null;
	}

	@Override
	public OrganizacionDTO buscarOrganizacion(Long id) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Organizacion o = (Organizacion)accesoBD.buscarPorId(Organizacion.class, id);
			OrganizacionDTO oDTO = assembler.getOrganizacionDTO(o);
			accesoBD.concretarTransaccion();
			return oDTO;
		}catch(Exception e){
			e.printStackTrace();
			accesoBD.rollbackTransaccion();
			return null;
		}
	}

	@Override
	public OrganizacionDTO buscarOrganizacion(String cuit) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Organizacion o = (Organizacion)accesoBD.buscarPorFiltro(Organizacion.class, "cuit.equals(\""+cuit+"\") && eliminado==false").toArray()[0];
			OrganizacionDTO oDTO = assembler.getOrganizacionDTO(o);
			accesoBD.concretarTransaccion();
			return oDTO;
		}catch(Exception e){
			e.printStackTrace();
			accesoBD.rollbackTransaccion();
			return null;
		}
	}
}
