package control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.Vector;

import assembler.LugarAssembler;

import persistencia.AccesoBD;
import persistencia.dominio.Lugar;

import comun.DTO.LugarDTO;
import comun.i_control.ILugar;

public class ControlLugar extends UnicastRemoteObject implements ILugar{

	
	private static final long serialVersionUID = 1L;
	private AccesoBD accesoBD;
	private LugarAssembler assembler;
	
	public ControlLugar(AccesoBD accesoBD) throws RemoteException {
		super();
		this.accesoBD = accesoBD;
		assembler = new LugarAssembler(accesoBD);
	}

	@Override
	public Long agregarLugar(LugarDTO lugarDTO) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Lugar l =((Lugar)accesoBD.hacerPersistente(assembler.getLugar(lugarDTO)));
			Long id = l.getId();
			accesoBD.concretarTransaccion();
			return id;
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void eliminarLugar(Long id) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Lugar aElim = (Lugar) accesoBD.buscarPorId(Lugar.class, id);
			aElim.setEliminado(true);
			accesoBD.hacerPersistente(aElim);
			accesoBD.concretarTransaccion();
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void modificarLugar(Long id, LugarDTO modificado) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Lugar l = (Lugar) accesoBD.buscarPorId(Lugar.class, id);
			l.setNombre(modificado.getNombre());
			l.setDireccion(modificado.getDireccion());
			l.setEliminado(modificado.getEliminado());
			accesoBD.hacerPersistente(l);
			accesoBD.concretarTransaccion();
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Vector<LugarDTO> obtenerLugares() throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Iterator lugares = accesoBD.listar(Lugar.class).iterator();
			Vector<LugarDTO> res = new Vector<LugarDTO>();
			while (lugares.hasNext()) {
				Lugar lugar = (Lugar) lugares.next();
				res.add(assembler.getLugarDTO(lugar));
			}
			accesoBD.concretarTransaccion();
			return res;
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Vector<LugarDTO> obtenerLugares(String filtro) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Iterator lugares = accesoBD.buscarPorFiltro(Lugar.class, filtro).iterator();
			Vector<LugarDTO> res = new Vector<LugarDTO>();
			while (lugares.hasNext()) {
				Lugar lugar = (Lugar) lugares.next();
				res.add(assembler.getLugarDTO(lugar));
			}
			accesoBD.concretarTransaccion();
			return res;
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Vector<LugarDTO> obtenerLugares(String filtro, String orden,
			String agrupar) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Iterator lugares = accesoBD.getObjectosOrdenadosYAgrupados(Lugar.class, filtro, orden, agrupar).iterator();
			Vector<LugarDTO> res = new Vector<LugarDTO>();
			while (lugares.hasNext()) {
				Lugar lugar = (Lugar) lugares.next();
				res.add(assembler.getLugarDTO(lugar));
			}
			accesoBD.concretarTransaccion();
			return res;
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public boolean existeLugar(Long id) throws Exception {
		return buscarLugar(id)!=null;
	}

	@Override
	public LugarDTO buscarLugar(Long id) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Lugar lugar = (Lugar) accesoBD.buscarPorId(Lugar.class, id);
			LugarDTO lDTO = assembler.getLugarDTO(lugar);
			accesoBD.concretarTransaccion();
			return lDTO;
		}catch(Exception e){
			e.printStackTrace();
			accesoBD.rollbackTransaccion();
			return null;
		}
	}

}
