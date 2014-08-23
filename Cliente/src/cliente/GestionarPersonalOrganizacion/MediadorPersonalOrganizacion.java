package cliente.GestionarPersonalOrganizacion;


import java.util.Collection;
import java.util.LinkedList;
import java.util.Vector;

import cliente.MenuPrincipal.MediadorPrincipal;
import cliente.utils.Constantes;

import comun.DTO.OrganizacionDTO;
import comun.DTO.PersonaDTO;
import comun.DTO.PersonalOrganizacionDTO;
import comun.DTO.UsuarioOrganizacionDTO;
import comun.i_control.IOrganizacion;
import comun.i_control.IPersona;
import comun.i_control.IPersonalOrganizacion;
import comun.i_control.IUsuarioOrganizacion;

public class MediadorPersonalOrganizacion {
	
	protected GUIPersonalOrganizacion guiPersOrg;
	protected GUIGestionPersonalOrganizacion guiGestionPersOrg;
	protected MediadorPrincipal mediadorPrincipal;
	

	public MediadorPersonalOrganizacion(MediadorPrincipal mediadorPrincipal){
		this.mediadorPrincipal = mediadorPrincipal;
	}
	
	public void altaAsig(Long idOrg, Long idPers) throws Exception{
		IOrganizacion iOrg = mediadorPrincipal.getCc().getControlOrganizacion();
		IPersona iPers = mediadorPrincipal.getCc().getControlPersona();
		OrganizacionDTO org = iOrg.buscarOrganizacion(idOrg);
		PersonaDTO pers = iPers.buscarPersona(idPers);
		guiPersOrg = new GUIPersonalOrganizacion(this,org,pers);
		guiPersOrg.setVisible(true);
		mediadorPrincipal.getGui_menu_Principal().setVisible(false);
		if(guiGestionPersOrg!=null)guiGestionPersOrg.setVisible(false);
	}
	
	public void modifAsig(long id){		
		IPersonalOrganizacion iPO = mediadorPrincipal.getCc().getControlPO();
		try {
			if (iPO.existePersonalOrganizacion(id)){
				PersonalOrganizacionDTO po = iPO.buscarPersonalOrganizacion(id);
				guiPersOrg = new GUIPersonalOrganizacion(this,po,false);
				guiPersOrg.setVisible(true);
				mediadorPrincipal.getGui_menu_Principal().setVisible(false);
				if(guiGestionPersOrg!=null)guiGestionPersOrg.setVisible(false);
			}
		} catch (Exception e) {
			System.out.println("Error al modificar persona en la clase MediadorUsuario");
			e.printStackTrace();
		}
	}

	public void eliminarAsig(Long id) {
		IPersonalOrganizacion iPO = mediadorPrincipal.getCc().getControlPO();
		try {
			if (iPO.existePersonalOrganizacion(id)){
				PersonalOrganizacionDTO po = iPO.buscarPersonalOrganizacion(id);
				guiPersOrg = new GUIPersonalOrganizacion(this,po,true);
				guiPersOrg.setVisible(true);
				mediadorPrincipal.getGui_menu_Principal().setVisible(false);
				if(guiGestionPersOrg!=null)guiGestionPersOrg.setVisible(false);
			}
		} catch (Exception e) {
			System.out.println("Error al modificar persona en la clase MediadorUsuario");
			e.printStackTrace();
		}
	}

	
	public Long getIdOrg (String org){
		if (org!=null){
			String [] split = org.split(":");
			String id = split[split.length-1].split("]")[0];
			return Long.parseLong(id);
		}else
			return null;
	}
	
	public String[] cargarOrganizaciones (){
		IUsuarioOrganizacion iPO = mediadorPrincipal.getCc().getControlUO();
		try {
			String filtro = "usuario.id=="+mediadorPrincipal.getUsuario().getId().toString()+" && org.eliminado==false";
			Vector<UsuarioOrganizacionDTO> orgs = iPO.obtenerUsuariosOrganizacion(filtro);
			String[] res = new String[orgs.size()];
			if (!orgs.isEmpty()){
				for (int i=0; i< orgs.size();i++){
					String org = orgs.elementAt(i).getOrg().getNombre()+" [ID:"+orgs.elementAt(i).getOrg().getId()+"]";
					res[i]=org;
				}
			}else{
				res = new String[1];
				res[0]=Constantes.comboVacio;
			}
			return res;
		} catch (Exception e) {
			System.out.println("Error al cargar tablaAsignados");
			e.printStackTrace();
		}	
		return (String[]) new LinkedList<String>().toArray();
	}
	
	
	
	public Vector<Vector<String>> tablaDeAsignadosOrg (Long idOrg){
		if (idOrg!=null){
			IPersonalOrganizacion iPO = mediadorPrincipal.getCc().getControlPO();
			try {
				String filtro = "persona.eliminado==false && org.id=="+idOrg+" && salida==null";
				Vector<PersonalOrganizacionDTO> pers = iPO.obtenerPersonalesOrganizacion(filtro);
				Vector <Vector<String>> res = new Vector<Vector<String>>();
				if (!pers.isEmpty()){
					for (int i=0; i< pers.size();i++){
						Vector<String> row = new Vector<String> ();
						
						row.add(pers.elementAt(i).getId().toString());
						row.add(pers.elementAt(i).getPersona().getApellido());
						row.add(pers.elementAt(i).getPersona().getNombre());
						row.add(pers.elementAt(i).getPersona().getDni_cuil_cuit());
						row.add(pers.elementAt(i).getIngreso().toString());
						
						res.add(row);
					}
				}
				return res;
			} catch (Exception e) {
				System.out.println("Error al cargar tablaAsignados");
				e.printStackTrace();
			}	
		}
		return new Vector<Vector<String>>();
	}
	
	public Vector<Vector<String>> tablaDeSinAsignarOrg (Long idOrg){
		if (idOrg!=null){
			IPersonalOrganizacion iPO = mediadorPrincipal.getCc().getControlPO();
			try {
				String filtro = "persona.eliminado==false && org.id=="+idOrg+" && salida==null";
				Vector<PersonalOrganizacionDTO> pers = iPO.obtenerPersonalesOrganizacion(filtro);
				Collection<String> asociados = new LinkedList<String>();
				if (!pers.isEmpty()){
					for (int i=0; i< pers.size();i++){						
						asociados.add(pers.elementAt(i).getPersona().getId().toString());
					}
				}

				Vector<Vector<String>> res = new Vector<Vector<String>>();
				filtro = "eliminado==false";
				IPersona iP = mediadorPrincipal.getCc().getControlPersona();
				Vector<PersonaDTO> persAux = iP.obtenerPersonas(filtro);
				for (int i = 0; i < persAux.size(); i++) {
					if (!asociados.contains(persAux.elementAt(i).getId().toString())){
						Vector<String> row = new Vector<String> ();
						
						row.add(persAux.elementAt(i).getId().toString());
						row.add(persAux.elementAt(i).getApellido());
						row.add(persAux.elementAt(i).getNombre());
						row.add(persAux.elementAt(i).getDni_cuil_cuit());
						
						res.add(row);
					}
				}
				return res;
			} catch (Exception e) {
				System.out.println("Error al insertar persona en la clase MediadorUsuario");
				e.printStackTrace();
			}	
		}
		return new Vector<Vector<String>>();
	}
	
	public boolean nuevoAsig(PersonalOrganizacionDTO po){
		IPersonalOrganizacion iPO = mediadorPrincipal.getCc().getControlPO();
		boolean result = false;
		try {
			iPO.agregarPersonalOrganizacion(po);
			result = true;
		} catch (Exception e) {
			System.out.println("Error al insertar usuario en la clase MediadorUsuario");
			e.printStackTrace();
		}
		actualizarDatosGestion();
		return result;
	}
	
	public void actualizarDatosGestion(){
		if (guiGestionPersOrg != null)
			guiGestionPersOrg.actualizarDatos();
	}
	
	
	public void gestionAsig(){
		guiGestionPersOrg = new GUIGestionPersonalOrganizacion(this);
		mediadorPrincipal.getGui_menu_Principal().setVisible(false);
		guiGestionPersOrg.setVisible(true);
	}
	
	public Vector<PersonalOrganizacionDTO> obtenerAsig(String filtro){
		IPersonalOrganizacion iControlPO = mediadorPrincipal.getCc().getControlPO();
		Vector<PersonalOrganizacionDTO> asig = new Vector<PersonalOrganizacionDTO> ();
		try {
			asig = iControlPO.obtenerPersonalesOrganizacion(filtro);
		} catch (Exception e) {
			System.out.println("Error al cargar las asignaciones en la clase MediadorUsuario");
			e.printStackTrace();
		}
		return asig;
	}

	public boolean modificar_elim(PersonalOrganizacionDTO persmodif) {
		IPersonalOrganizacion iPO = mediadorPrincipal.getCc().getControlPO();
		boolean result = false;
		try {
			iPO.modificarPersonalOrganizacion(persmodif.getId(), persmodif);
			result = true;	
		} catch (Exception e) {
			System.out.println("Error al modifcar persona 1 en la clase MediadorUsuario");
			e.printStackTrace();
		}
		cerrarFormulario();
		return result;
	}

	public void cerrarFormulario() {
		if (guiGestionPersOrg != null) {
			guiGestionPersOrg.actualizarDatos();
			guiGestionPersOrg.setVisible(true);
		}
		else mediadorPrincipal.getGui_menu_Principal().setVisible(true);	
	}

	public MediadorPrincipal getMediadorPrincipal() {
		return mediadorPrincipal;
	}

}
