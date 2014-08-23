package cliente.GestionarPersona;


import java.util.Vector;

import cliente.MenuPrincipal.MediadorPrincipal;

import comun.DTO.PersonaDTO;
import comun.i_control.IPersona;

public class MediadorPersona {
	
	protected GUIPersona guiPersona;
	protected GUIGestionPersona guiGestionPersona;
	protected MediadorPrincipal mediadorPrincipal;
	

	public MediadorPersona(MediadorPrincipal mediadorPrincipal){
		this.mediadorPrincipal = mediadorPrincipal;
	}
	
	public void altaPersona(){
		guiPersona = new GUIPersona(this);
		guiPersona.setVisible(true);
		mediadorPrincipal.getGui_menu_Principal().setVisible(false);
		if(guiGestionPersona!=null)guiGestionPersona.setVisible(false);
	}
	
	public void modifPersona(long id){		
		IPersona iPersona = mediadorPrincipal.getCc().getControlPersona();
		try {
			if (iPersona.existePersona(id)){
				PersonaDTO pers = iPersona.buscarPersona(id);
				guiPersona = new GUIPersona(this,pers);
				guiPersona.setVisible(true);
				mediadorPrincipal.getGui_menu_Principal().setVisible(false);
				if(guiGestionPersona!=null)guiGestionPersona.setVisible(false);
			}
		} catch (Exception e) {
			System.out.println("Error al modificar persona en la clase MediadorUsuario");
			e.printStackTrace();
		}
	}
	
	public boolean existeMismoDNI(Long id, String dni){
		IPersona iPersona = mediadorPrincipal.getCc().getControlPersona();
		boolean result = false;
		try {
			String filtro = "dni_cuil_cuit.toUpperCase().equals(\""+dni+"\")";
			if (id!=null) filtro +=" && id!="+id;
			if (!iPersona.obtenerPersonas(filtro).isEmpty()){
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error al insertar persona en la clase MediadorUsuario");
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean nuevoPersona(PersonaDTO pers){
		IPersona iPersona = mediadorPrincipal.getCc().getControlPersona();
		boolean result = false;
		try {
			if (!existeMismoDNI(null, pers.getDni_cuil_cuit())){
				iPersona.agregarPersona(pers);
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
		if (guiGestionPersona != null)
			guiGestionPersona.actualizarDatos();
	}
	
	
	public void gestioPersona(){
		guiGestionPersona = new GUIGestionPersona(this);
		guiGestionPersona.setVisible(true);
	}
	
	public Vector<PersonaDTO> obtenerPersonas(String filtro){
		IPersona iControlPersona = mediadorPrincipal.getCc().getControlPersona();
		Vector<PersonaDTO> usuarios = new Vector<PersonaDTO> ();
		try {
			usuarios = iControlPersona.obtenerPersonas(filtro);
		} catch (Exception e) {
			System.out.println("Error al cargar los usuarios en la clase MediadorUsuario");
			e.printStackTrace();
		}
		return usuarios;
	}

	public boolean eliminarPersona(Long id) {
		IPersona iControlPersona = mediadorPrincipal.getCc().getControlPersona();
		boolean result = false;
		try {
			if (iControlPersona.existePersona(id)){
				iControlPersona.eliminarPersona(id);
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error al eliminar usuario en la clase MediadorUsuario");
			e.printStackTrace();
		}
		actualizarDatosGestion();
		return result;
	}



	public boolean modificar(PersonaDTO persmodif) {
		IPersona iControlPersona = mediadorPrincipal.getCc().getControlPersona();
		boolean result = false;
		try {
			if (!existeMismoDNI(persmodif.getId(), persmodif.getDni_cuil_cuit())){
				iControlPersona.modificarPersona(persmodif.getId(), persmodif);
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error al modifcar persona 1 en la clase MediadorUsuario");
			e.printStackTrace();
		}
		cerrarFormulario();
		return result;

	}

	public void cerrarFormulario() {
		if (guiGestionPersona != null) {
			guiGestionPersona.actualizarDatos();
			guiGestionPersona.setVisible(true);
		}
		else mediadorPrincipal.getGui_menu_Principal().setVisible(true);	
	}

	public MediadorPrincipal getMediadorPrincipal() {
		return mediadorPrincipal;
	}

}
