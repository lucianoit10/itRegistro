package control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.Vector;

import comun.DTO.UsuarioDTO;
import comun.i_control.IUsuario;

import assembler.UsuarioAssembler;
import persistencia.AccesoBD;
import persistencia.dominio.Usuario;

public class ControlUsuario extends UnicastRemoteObject implements IUsuario{
	
	private static final long serialVersionUID = 1L;
	private AccesoBD accesoBD;
	private UsuarioAssembler assembler;

	public ControlUsuario(AccesoBD accesoBD) throws RemoteException {
		super();
		this.accesoBD = accesoBD;
		assembler = new UsuarioAssembler(accesoBD);
	}

	@Override
	public Long agregarUsuario(UsuarioDTO user) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Usuario u = (Usuario) accesoBD.hacerPersistente(assembler.getUsuario(user));
			Long id = u.getId();
			accesoBD.concretarTransaccion();
			return id;
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void eliminarUsuario(Long id) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Usuario u = (Usuario) accesoBD.buscarPorId(Usuario.class, id);
			accesoBD.eliminar(u);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}		
	}

	@Override
	public void modificarUsuario(Long id, UsuarioDTO modificado)throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Usuario u = (Usuario) accesoBD.buscarPorId(Usuario.class, id);
			u.setNombre_usuario(modificado.getNombre_usuario());
			u.setCambiar_contrasena(modificado.getCambiar_contrasena());
			if(!modificado.getContrasena().isEmpty())
				u.setContrasena(modificado.getContrasena());
			u.setPermiso(modificado.getPermiso());
			accesoBD.hacerPersistente(u);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}		
	}

	@Override
	public Vector<UsuarioDTO> obtenerUsuarios() throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Iterator users = accesoBD.listar(Usuario.class).iterator();
			Vector<UsuarioDTO> res = new Vector<UsuarioDTO>();
			while (users.hasNext()) {
				Usuario u = (Usuario) users.next();
				res.add(assembler.getUsuarioDTO(u));
			}
			accesoBD.concretarTransaccion();
			return res;
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Vector<UsuarioDTO> obtenerUsuarios(String filtro) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Iterator users = accesoBD.buscarPorFiltro(Usuario.class,filtro).iterator();
			Vector<UsuarioDTO> res = new Vector<UsuarioDTO>();
			while (users.hasNext()) {
				Usuario u = (Usuario) users.next();
				res.add(assembler.getUsuarioDTO(u));
			}
			accesoBD.concretarTransaccion();
			return res;
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Vector<UsuarioDTO> obtenerUsuarios(String filtro, String orden,String agrupar) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Iterator users = accesoBD.getObjectosOrdenadosYAgrupados(Usuario.class,filtro,orden,agrupar).iterator();
			Vector<UsuarioDTO> res = new Vector<UsuarioDTO>();
			while (users.hasNext()) {
				Usuario u = (Usuario) users.next();
				res.add(assembler.getUsuarioDTO(u));
			}
			accesoBD.concretarTransaccion();
			return res;
		}catch(Exception e){
			accesoBD.rollbackTransaccion();
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public boolean existeUsuario(Long id) throws Exception {
		return buscarUsuario(id)!= null;
	}

	@Override
	public UsuarioDTO buscarUsuario(Long id) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Usuario u = (Usuario) accesoBD.buscarPorId(Usuario.class, id);
			UsuarioDTO res = assembler.getUsuarioDTO(u);
			accesoBD.concretarTransaccion();
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			accesoBD.rollbackTransaccion();
			return null;
		}		
	}

	@Override
	public UsuarioDTO login(String user_name, String pass) throws Exception {
		try {
			accesoBD.iniciarTransaccion();
			Usuario u = (Usuario) accesoBD.buscarPorFiltro(Usuario.class, "nombre_usuario.equals(\""+user_name+"\") && contrasena.equals(\""+pass+"\")").toArray()[0];
			UsuarioDTO res = assembler.getUsuarioDTO(u);
			accesoBD.concretarTransaccion();
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			accesoBD.rollbackTransaccion();
			return null;
		}		
	}
	
}
