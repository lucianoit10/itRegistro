package cliente.GestionarLugar;


import java.util.Vector;

import cliente.MenuPrincipal.MediadorPrincipal;

import comun.DTO.LugarDTO;
import comun.i_control.ILugar;

public class MediadorLugar {
	
	protected GUILugar guiLugar;
	protected GUIGestionLugar guiGestionLugar;
	protected MediadorPrincipal mediadorPrincipal;
	

	public MediadorLugar(MediadorPrincipal mediadorPrincipal){
		this.mediadorPrincipal = mediadorPrincipal;
	}
	
	public void altaLugar(){
		guiLugar = new GUILugar(this);
		guiLugar.setVisible(true);
		mediadorPrincipal.getGui_menu_Principal().setVisible(false);
		if(guiGestionLugar!=null)guiGestionLugar.setVisible(false);
	}
	
	public void modifLugar(long id){		
		ILugar iLugar = mediadorPrincipal.getCc().getControlLugar();
		try {
			if (iLugar.existeLugar(id)){
				LugarDTO l = iLugar.buscarLugar(id);
				guiLugar = new GUILugar(this,l);
				guiLugar.setVisible(true);
				mediadorPrincipal.getGui_menu_Principal().setVisible(false);
				if(guiGestionLugar!=null)guiGestionLugar.setVisible(false);
			}
		} catch (Exception e) {
			System.out.println("Error al modificar Lugar en la clase MediadorUsuario");
			e.printStackTrace();
		}
	}
	
	
	public boolean nuevoLugar(LugarDTO pers){
		ILugar iLugar = mediadorPrincipal.getCc().getControlLugar();
		boolean result = false;
		try {
			iLugar.agregarLugar(pers);
			result = true;
			mediadorPrincipal.cargarComboLugar();
		} catch (Exception e) {
			System.out.println("Error al insertar usuario en la clase MediadorUsuario");
			e.printStackTrace();
		}
		actualizarDatosGestion();
		return result;
	}
	
	public void actualizarDatosGestion(){
		if (guiGestionLugar != null)
			guiGestionLugar.actualizarDatos();
	}
	
	
	public void gestionLugar(){
		guiGestionLugar = new GUIGestionLugar(this);
		guiGestionLugar.setVisible(true);
		mediadorPrincipal.getGui_menu_Principal().setVisible(false);
	}
	
	public Vector<LugarDTO> obtenerLugar(String filtro){
		ILugar iControlLugar = mediadorPrincipal.getCc().getControlLugar();
		Vector<LugarDTO> lugares = new Vector<LugarDTO> ();
		try {
			lugares = iControlLugar.obtenerLugares(filtro);
		} catch (Exception e) {
			System.out.println("Error al cargar los usuarios en la clase MediadorUsuario");
			e.printStackTrace();
		}
		return lugares;
	}


	public boolean eliminarLugar(Long id) {
		ILugar iControlLugar = mediadorPrincipal.getCc().getControlLugar();
		boolean result = false;
		try {
			if (iControlLugar.existeLugar(id)){
				iControlLugar.eliminarLugar(id);
				result = true;
				mediadorPrincipal.cargarComboLugar();
			}
		} catch (Exception e) {
			System.out.println("Error al eliminar usuario en la clase MediadorUsuario");
			e.printStackTrace();
		}
		//actualizarDatosGestion();
		return result;
	}


	public boolean modificar(LugarDTO l) throws Exception {
		ILugar iControlLugar = mediadorPrincipal.getCc().getControlLugar();
		boolean result = false;
		try {
			iControlLugar.modificarLugar(l.getId(), l);
			result = true;
			mediadorPrincipal.cargarComboLugar();
		} catch (Exception e) {
			System.out.println("Error al modifcar Lugar 1 en la clase MediadorUsuario");
			e.printStackTrace();
		}
		cerrarFormulario();
		return result;

	}

	public void cerrarFormulario() throws Exception {
		if (guiGestionLugar != null) {
			guiGestionLugar.actualizarDatos();
			guiGestionLugar.setVisible(true);
		}
		else{
			mediadorPrincipal.getGui_menu_Principal().setVisible(true);	
		}
	}

	public MediadorPrincipal getMediadorPrincipal() {
		return mediadorPrincipal;
	}
	
	

}
