package cliente.GestionarUsuario;


import java.util.Vector;

import cliente.MenuPrincipal.MediadorPrincipal;

import comun.DTO.UsuarioDTO;
import comun.i_control.IUsuario;

public class MediadorUsuario {
	
	protected GUIUsuario guiUsuario;
	protected GUIGestionUsuario guiGestionUsuario;
	protected MediadorPrincipal mediadorPrincipal;
	

	public MediadorUsuario(MediadorPrincipal mediadorPrincipal){
		this.mediadorPrincipal = mediadorPrincipal;
	}
	
	public void altaUsuario(){
		guiUsuario = new GUIUsuario(this);
		guiUsuario.setVisible(true);
		mediadorPrincipal.getGui_menu_Principal().setVisible(false);
	}
	
	public void modifUsuario(long id){		
		IUsuario iUsuario = mediadorPrincipal.getCc().getControlUsuario();
		try {
			if (iUsuario.existeUsuario(id)){
				UsuarioDTO usuario = iUsuario.buscarUsuario(id);
				guiUsuario = new GUIUsuario(this,usuario);
				guiUsuario.setVisible(true);
				mediadorPrincipal.getGui_menu_Principal().setVisible(false);
			}
		} catch (Exception e) {
			System.out.println("Error al modificar usuario en la clase MediadorUsuario");
			e.printStackTrace();
		}
	}
	
	public boolean existeMismoNombreDeUsuario(Long id, String nombre_usuario){
		IUsuario iUsuario = mediadorPrincipal.getCc().getControlUsuario();
		boolean result = false;
		try {
			String filtro = "nombre_usuario.trim().equals(\""+nombre_usuario+"\")";
			if (id!=null) filtro +=" && id!="+id;
			if (!iUsuario.obtenerUsuarios(filtro).isEmpty()){
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error al insertar usuario en la clase MediadorUsuario");
			e.printStackTrace();
		}
		return result;
	}
		
	public boolean nuevoUsuario(UsuarioDTO usuario){
		IUsuario iUsuario = mediadorPrincipal.getCc().getControlUsuario();
		boolean result = false;
		try {
			if (!existeMismoNombreDeUsuario(null, usuario.getNombre_usuario())){
				iUsuario.agregarUsuario(usuario);
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error al insertar usuario en la clase MediadorUsuario");
			e.printStackTrace();
		}
		actualizarDatosGestion();
		return result;
	}
	
	public void actualizarDatosGestion(){
		if (guiGestionUsuario != null)
			guiGestionUsuario.actualizarDatos();
	}
	
	
	public void gestionUsuario(){
		guiGestionUsuario = new GUIGestionUsuario(this);
		guiGestionUsuario.setVisible(true);
	}
	
	public Vector<UsuarioDTO> obtenerUsuarios(String filtro){
		IUsuario iControlUsuario = mediadorPrincipal.getCc().getControlUsuario();
		Vector<UsuarioDTO> usuarios = new Vector<UsuarioDTO> ();
		try {
			usuarios = iControlUsuario.obtenerUsuarios(filtro);
		} catch (Exception e) {
			System.out.println("Error al cargar los usuarios en la clase MediadorUsuario");
			e.printStackTrace();
		}
		return usuarios;
	}

	public boolean eliminarUsuario(Long id) {
		IUsuario iControlUsuario = mediadorPrincipal.getCc().getControlUsuario();
		boolean result = false;
		try {
			if (iControlUsuario.existeUsuario(id)){
				iControlUsuario.eliminarUsuario(id);
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error al eliminar usuario en la clase MediadorUsuario");
			e.printStackTrace();
		}
		actualizarDatosGestion();
		return result;
	}



	public boolean modificar(UsuarioDTO usuariomodif) {
		IUsuario iControlUsuario = mediadorPrincipal.getCc().getControlUsuario();
		boolean result = false;
		try {
			if (!existeMismoNombreDeUsuario(usuariomodif.getId(), usuariomodif.getNombre_usuario())){
				iControlUsuario.modificarUsuario(usuariomodif.getId(), usuariomodif);
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error al loguear usuario en la clase MediadorUsuario");
			e.printStackTrace();
		}
		cerrarFormulario();
		return result;

	}

	public void cerrarFormulario() {
		if (guiGestionUsuario != null) guiGestionUsuario.actualizarDatos();
		else mediadorPrincipal.getGui_menu_Principal().setVisible(true);	
	}

	public MediadorPrincipal getMediadorPrincipal() {
		return mediadorPrincipal;
	}

}
