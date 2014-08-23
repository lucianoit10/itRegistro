package control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.Vector;

import assembler.RegistroAssembler;

import persistencia.AccesoBD;
import persistencia.dominio.Registro;

import comun.DTO.RegistroDTO;
import comun.i_control.IRegistro;

public class ControlRegistro  extends UnicastRemoteObject implements IRegistro{
	
	private static final long serialVersionUID = 1L;
	private AccesoBD accesoBD;
	private RegistroAssembler assembler;
	
	public ControlRegistro (AccesoBD accesoBD) throws RemoteException{
		super();
		this.accesoBD= accesoBD;
		assembler = new RegistroAssembler(accesoBD);
	}

	@Override
	public Long agregarRegistro(RegistroDTO regDTO) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Registro r = (Registro)accesoBD.hacerPersistente(assembler.getRegistro(regDTO));
			Long id = r.getId();
			accesoBD.concretarTransaccion();
			return id;
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void modificarRegistro(Long id, RegistroDTO modificado)throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Registro mod = assembler.getRegistro(modificado);
			Registro r = (Registro) accesoBD.buscarPorId(Registro.class, id);
			r.setPersonalOrg(mod.getPersonalOrg());
			r.setLugar(mod.getLugar());
			r.setIngreso(mod.getIngreso());
			r.setSalida(mod.getSalida());
			r.setDescripcion(mod.getDescripcion());
			accesoBD.hacerPersistente(r);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Vector<RegistroDTO> obtenerRegistros() throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Iterator registros = accesoBD.listar(Registro.class).iterator();
			Vector<RegistroDTO> res= new Vector<RegistroDTO>();
			while (registros.hasNext()) {
				Registro r = (Registro) registros.next();
				res.add(assembler.getRegistroDTO(r));
			}
			accesoBD.concretarTransaccion();
			return res;
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Vector<RegistroDTO> obtenerRegistros(String filtro) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Iterator registros = accesoBD.buscarPorFiltro(Registro.class, filtro).iterator();
			Vector<RegistroDTO> res= new Vector<RegistroDTO>();
			while (registros.hasNext()) {
				Registro r = (Registro) registros.next();
				res.add(assembler.getRegistroDTO(r));
			}
			accesoBD.concretarTransaccion();
			return res;
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Vector<RegistroDTO> obtenerRegistros(String filtro, String orden,String agrupar) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Iterator registros = accesoBD.getObjectosOrdenadosYAgrupados(Registro.class, filtro, orden, agrupar).iterator();
			Vector<RegistroDTO> res= new Vector<RegistroDTO>();
			while (registros.hasNext()) {
				Registro r = (Registro) registros.next();
				res.add(assembler.getRegistroDTO(r));
			}
			accesoBD.concretarTransaccion();
			return res;
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}			
	}

	@Override
	public boolean existeRegistro(Long id) throws Exception {
		return buscarRegistro(id)!=null;
	}

	@Override
	public boolean existeRegistroAbierto(Long idPersOrg, Long lugar)throws Exception {
		return buscarRegistroAbierto(idPersOrg, lugar)!=null;
	}

	@Override
	public RegistroDTO buscarRegistro(Long id) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Registro r = (Registro) accesoBD.buscarPorId(Registro.class, id);
			RegistroDTO rDTO = assembler.getRegistroDTO(r);
			accesoBD.concretarTransaccion();
			return rDTO;
		} catch (Exception e) {
			e.printStackTrace();
			accesoBD.rollbackTransaccion();
			return null;
		}
	}

	@Override
	public RegistroDTO buscarRegistroAbierto(Long idPersOrg, Long lugar)
			throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Registro r = (Registro) accesoBD.buscarPorFiltro(Registro.class, "personalOrg.id=="+idPersOrg+"&& lugar.id=="+lugar+" && salida==null").toArray()[0];
			RegistroDTO rDTO = assembler.getRegistroDTO(r);
			accesoBD.concretarTransaccion();
			return rDTO;
		} catch (Exception e) {
			e.printStackTrace();
			accesoBD.rollbackTransaccion();
			return null;
		}
	}

}
