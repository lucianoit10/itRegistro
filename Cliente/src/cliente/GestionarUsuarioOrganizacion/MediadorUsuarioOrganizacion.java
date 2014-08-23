package cliente.GestionarUsuarioOrganizacion;


import java.util.Collection;
import java.util.LinkedList;
import java.util.Vector;

import cliente.MenuPrincipal.MediadorPrincipal;
import cliente.utils.Constantes;

import comun.DTO.OrganizacionDTO;
import comun.DTO.UsuarioDTO;
import comun.DTO.UsuarioOrganizacionDTO;
import comun.i_control.IOrganizacion;
import comun.i_control.IUsuario;
import comun.i_control.IUsuarioOrganizacion;

public class MediadorUsuarioOrganizacion {
	
	protected GUIGestionUsuarioOrganizacion guiGestionPersOrg;
	protected MediadorPrincipal mediadorPrincipal;
	

	public MediadorUsuarioOrganizacion(MediadorPrincipal mediadorPrincipal){
		this.mediadorPrincipal = mediadorPrincipal;
	}
	
	public boolean altaAsig(Long idOrg, Long idUser) throws Exception{
		IUsuarioOrganizacion iUO = mediadorPrincipal.getCc().getControlUO();
		IOrganizacion iOrg = mediadorPrincipal.getCc().getControlOrganizacion();
		IUsuario iUser = mediadorPrincipal.getCc().getControlUsuario();
		OrganizacionDTO org = iOrg.buscarOrganizacion(idOrg);
		UsuarioDTO user = iUser.buscarUsuario(idUser);
		if (!iUO.existeUsuarioOrganizacion(idUser, idOrg)){
			UsuarioOrganizacionDTO userOrg = new UsuarioOrganizacionDTO(user,org);
			iUO.agregarUsuarioOrganizacion(userOrg);
			return true;
		}
		return true;
	}
	
	public boolean esElAdmin(long id){
		IUsuarioOrganizacion iUO = mediadorPrincipal.getCc().getControlUO();
		try {
			if (iUO.existeUsuarioOrganizacion(id)){
				return iUO.buscarUsuarioOrganizacion(id).getId()==1;
			}
		} catch (Exception e) {
			System.out.println("Error al modificar persona en la clase MediadorUsuario");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean eliminar(long id){		
		IUsuarioOrganizacion iUO = mediadorPrincipal.getCc().getControlUO();
		try {
			if (iUO.existeUsuarioOrganizacion(id)){
				iUO.eliminarUsuarioOrganizacion(id);
				return true;
			}
		} catch (Exception e) {
			System.out.println("Error al modificar persona en la clase MediadorUsuario");
			e.printStackTrace();
		}
		return false;
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
			IUsuarioOrganizacion iPO = mediadorPrincipal.getCc().getControlUO();
			try {
				String filtro = "org.id=="+idOrg;
				Vector<UsuarioOrganizacionDTO> pers = iPO.obtenerUsuariosOrganizacion(filtro);
				Vector <Vector<String>> res = new Vector<Vector<String>>();
				if (!pers.isEmpty()){
					for (int i=0; i< pers.size();i++){
						Vector<String> row = new Vector<String> ();
						
						row.add(pers.elementAt(i).getId().toString());
						row.add(pers.elementAt(i).getUsuario().getNombre_usuario());
						
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
			IUsuarioOrganizacion iPO = mediadorPrincipal.getCc().getControlUO();
			try {
				String filtro = "org.id=="+idOrg;
				Vector<UsuarioOrganizacionDTO> pers = iPO.obtenerUsuariosOrganizacion(filtro);
				Collection<String> asociados = new LinkedList<String>();
				if (!pers.isEmpty()){
					for (int i=0; i< pers.size();i++){						
						asociados.add(pers.elementAt(i).getUsuario().getId().toString());
					}
				}

				Vector<Vector<String>> res = new Vector<Vector<String>>();
				if (!asociados.isEmpty()){
					IUsuario iP = mediadorPrincipal.getCc().getControlUsuario();
					Vector<UsuarioDTO> persAux = iP.obtenerUsuarios();
					for (int i = 0; i < persAux.size(); i++) {
						if (!asociados.contains(persAux.elementAt(i).getId().toString())){
							Vector<String> row = new Vector<String> ();
							
							row.add(persAux.elementAt(i).getId().toString());
							row.add(persAux.elementAt(i).getNombre_usuario());
							
							res.add(row);
						}
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
	
	
	public void actualizarDatosGestion(){
		if (guiGestionPersOrg != null)
			guiGestionPersOrg.actualizarDatos();
	}
	
	
	public void gestionAsig(){
		guiGestionPersOrg = new GUIGestionUsuarioOrganizacion(this);
		mediadorPrincipal.getGui_menu_Principal().setVisible(false);
		guiGestionPersOrg.setVisible(true);
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
