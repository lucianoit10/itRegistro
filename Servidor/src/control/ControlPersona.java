package control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.Vector;

import comun.DTO.PersonaDTO;
import comun.i_control.IPersona;

import assembler.PersonaAssembler;
import persistencia.AccesoBD;
import persistencia.dominio.Persona;

public class ControlPersona extends UnicastRemoteObject implements IPersona{
	
	private static final long serialVersionUID = 1L;
	private AccesoBD accesoBD;
	private PersonaAssembler assembler;
	
	public ControlPersona(AccesoBD accesoBD) throws RemoteException {
		super();
		this.accesoBD = accesoBD;
		assembler = new PersonaAssembler(accesoBD);
	}

	@Override
	public Long agregarPersona(PersonaDTO persDTO) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Persona p = (Persona)accesoBD.hacerPersistente(assembler.getPersona(persDTO));
			Long id = p.getId();
			accesoBD.concretarTransaccion();
			return id;
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void eliminarPersona(Long id) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Persona p = (Persona)accesoBD.buscarPorId(Persona.class, id);
			p.setEliminado(true);
			accesoBD.hacerPersistente(p);
			accesoBD.concretarTransaccion();
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}		
	}

	@Override
	public void modificarPersona(Long id, PersonaDTO modificado)throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Persona p = (Persona)accesoBD.buscarPorId(Persona.class, id);
			p.setNombre(modificado.getNombre());
			p.setApellido(modificado.getApellido());
			p.setDni_cuil_cuit(modificado.getDni_cuil_cuit());
			p.setRegistro(modificado.getRegistro());
			p.setTel_contacto(modificado.getTel_contacto());
			p.setEliminado(modificado.getEliminado());
			accesoBD.hacerPersistente(p);
			accesoBD.concretarTransaccion();
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Vector<PersonaDTO> obtenerPersonas() throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Iterator personas = accesoBD.listar(Persona.class).iterator();
			Vector<PersonaDTO> res = new Vector<PersonaDTO>();
			while (personas.hasNext()) {
				Persona p = (Persona) personas.next();
				res.add(assembler.getPersonaDTO(p));
			}
			accesoBD.concretarTransaccion();
			return res;
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Vector<PersonaDTO> obtenerPersonas(String filtro) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Iterator personas = accesoBD.buscarPorFiltro(Persona.class,filtro).iterator();
			Vector<PersonaDTO> res = new Vector<PersonaDTO>();
			while (personas.hasNext()) {
				Persona p = (Persona) personas.next();
				res.add(assembler.getPersonaDTO(p));
			}
			accesoBD.concretarTransaccion();
			return res;
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Vector<PersonaDTO> obtenerPersonas(String filtro, String orden,String agrupar) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Iterator personas = accesoBD.getObjectosOrdenadosYAgrupados(Persona.class,filtro,orden,agrupar).iterator();
			Vector<PersonaDTO> res = new Vector<PersonaDTO>();
			while (personas.hasNext()) {
				Persona p = (Persona) personas.next();
				res.add(assembler.getPersonaDTO(p));
			}
			accesoBD.concretarTransaccion();
			return res;
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public boolean existePersona(Long id) throws Exception {
		return buscarPersona(id)!=null;
	}

	@Override
	public PersonaDTO buscarPersona(Long id) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Persona p = (Persona)accesoBD.buscarPorId(Persona.class, id);
			PersonaDTO pDTO = assembler.getPersonaDTO(p);
			accesoBD.concretarTransaccion();
			return pDTO;
		}catch(Exception e){
			e.printStackTrace();
			accesoBD.rollbackTransaccion();
			return null;
		}		
	}
	
	
}
