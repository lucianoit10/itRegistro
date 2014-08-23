package cliente.GestionarOrganizacion;

import java.util.Vector;

import cliente.MenuPrincipal.MediadorPrincipal;

import comun.DTO.OrganizacionDTO;
import comun.DTO.UsuarioOrganizacionDTO;
import comun.i_control.IOrganizacion;
import comun.i_control.IUsuarioOrganizacion;

public class MediadorOrganizacion {
	
	protected GUIOrganizacion guiOrganizacion;
	protected GUIGestionOrganizacion guiGestionOrganizacion;
	protected MediadorPrincipal mediadorPrincipal;
	

	public MediadorOrganizacion(MediadorPrincipal mediadorPrincipal){
		this.mediadorPrincipal = mediadorPrincipal;
	}
	
	public void altaOrganizacion(){
		guiOrganizacion = new GUIOrganizacion(this);
		guiOrganizacion.setVisible(true);
		mediadorPrincipal.getGui_menu_Principal().setVisible(false);
		if(guiGestionOrganizacion!=null)guiGestionOrganizacion.setVisible(false);
	}
	
	public void modifOrganizacion(long id){		
		IOrganizacion iOrganizacion = mediadorPrincipal.getCc().getControlOrganizacion();
		try {
			if (iOrganizacion.existeOrganizacion(id)){
				OrganizacionDTO pers = iOrganizacion.buscarOrganizacion(id);
				guiOrganizacion = new GUIOrganizacion(this,pers);
				guiOrganizacion.setVisible(true);
				mediadorPrincipal.getGui_menu_Principal().setVisible(false);
				if(guiGestionOrganizacion!=null)guiGestionOrganizacion.setVisible(false);
			}
		} catch (Exception e) {
			System.out.println("Error al modificar Organizacion en la clase MediadorUsuario");
			e.printStackTrace();
		}
	}
	
	public boolean existeMismoCUIT(Long id, String dni){
		IOrganizacion iOrganizacion = mediadorPrincipal.getCc().getControlOrganizacion();
		boolean result = false;
		try {
			String filtro = "cuit.toUpperCase().equals(\""+dni+"\")";
			if (id!=null) filtro +=" && id!="+id;
			if (!iOrganizacion.obtenerOrganizaciones(filtro).isEmpty()){
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error al insertar Organizacion en la clase MediadorUsuario");
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean nuevoOrganizacion(OrganizacionDTO pers){
		IOrganizacion iOrganizacion = mediadorPrincipal.getCc().getControlOrganizacion();
		boolean result = false;
		try {
			if (!existeMismoCUIT(null, pers.getCuit())){
				iOrganizacion.agregarOrganizacion(pers);
				OrganizacionDTO org = iOrganizacion.buscarOrganizacion(pers.getCuit());
				UsuarioOrganizacionDTO po = new UsuarioOrganizacionDTO(mediadorPrincipal.getUsuario(), org);
				IUsuarioOrganizacion iPO = mediadorPrincipal.getCc().getControlUO();
				iPO.agregarUsuarioOrganizacion(po);
				result = true;
				mediadorPrincipal.cargarComboOrg();
			}
		} catch (Exception e) {
			System.out.println("Error al insertar usuario en la clase MediadorUsuario");
			e.printStackTrace();
		}
		//actualizarDatosGestion();
		return result;
	}
	
	public void actualizarDatosGestion(){
		if (guiGestionOrganizacion != null)
			guiGestionOrganizacion.actualizarDatos();
	}
	
	
	public void gestionOrganizacion(){
		guiGestionOrganizacion = new GUIGestionOrganizacion(this);
		guiGestionOrganizacion.setVisible(true);
		mediadorPrincipal.getGui_menu_Principal().setVisible(false);
	}
	
	public Vector<OrganizacionDTO> obtenerOrganizacion(String filtro){
		IOrganizacion iControlOrg = mediadorPrincipal.getCc().getControlOrganizacion();
		Vector<OrganizacionDTO> org = new Vector<OrganizacionDTO> ();
		try {
			org = iControlOrg.obtenerOrganizaciones(filtro);
		} catch (Exception e) {
			System.out.println("Error al cargar los usuarios en la clase MediadorUsuario");
			e.printStackTrace();
		}
		return org;
	}


	public boolean eliminarOrganizacion(Long id) {
		IOrganizacion iControlOrg = mediadorPrincipal.getCc().getControlOrganizacion();
		boolean result = false;
		try {
			if (iControlOrg.existeOrganizacion(id)){
				iControlOrg.eliminarOrganizacion(id);
				result = true;
				mediadorPrincipal.cargarComboOrg();
			}
		} catch (Exception e) {
			System.out.println("Error al eliminar usuario en la clase MediadorUsuario");
			e.printStackTrace();
		}
		//actualizarDatosGestion();
		return result;
	}


	public boolean modificar(OrganizacionDTO persmodif) throws Exception {
		IOrganizacion iControlOrganizacion = mediadorPrincipal.getCc().getControlOrganizacion();
		boolean result = false;
		try {
			if (!existeMismoCUIT(persmodif.getId(), persmodif.getCuit())){
				iControlOrganizacion.modificarOrganizacion(persmodif.getId(), persmodif);
				result = true;
				mediadorPrincipal.cargarComboOrg();
			}
		} catch (Exception e) {
			System.out.println("Error al modifcar Organizacion 1 en la clase MediadorUsuario");
			e.printStackTrace();
		}
		cerrarFormulario();
		return result;

	}

	public void cerrarFormulario() throws Exception {
		if (guiGestionOrganizacion != null) {
			guiGestionOrganizacion.actualizarDatos();
			guiGestionOrganizacion.setVisible(true);
		}
		else{
			mediadorPrincipal.getGui_menu_Principal().setVisible(true);	
		}	
	}

	public MediadorPrincipal getMediadorPrincipal() {
		return mediadorPrincipal;
	}

	
}
