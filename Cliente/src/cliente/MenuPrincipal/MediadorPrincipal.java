/********************************************************
  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *********************************************************/
package cliente.MenuPrincipal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import cliente.ClienteConection;
import cliente.Login.GUILogin;
import cliente.apariencia.Apariencia;
import cliente.apariencia.Lite;
import cliente.apariencia.Minimal;
import cliente.apariencia.PorDefecto;
import cliente.utils.Constantes;

import cliente.GestionarLugar.MediadorLugar;
import cliente.GestionarOrganizacion.MediadorOrganizacion;
import cliente.GestionarPersona.MediadorPersona;
import cliente.GestionarPersonalOrganizacion.MediadorPersonalOrganizacion;
import cliente.GestionarRegistro.MediadorRegistro;
import cliente.GestionarUsuario.MediadorUsuario;
import cliente.GestionarUsuarioOrganizacion.MediadorUsuarioOrganizacion;
import comun.DTO.LugarDTO;
import comun.DTO.OrganizacionDTO;
import comun.DTO.PersonaDTO;
import comun.DTO.PersonalOrganizacionDTO;
import comun.DTO.RegistroDTO;
import comun.DTO.UsuarioDTO;
import comun.i_control.ILugar;
import comun.i_control.IOrganizacion;
import comun.i_control.IPersona;
import comun.i_control.IPersonalOrganizacion;
import comun.i_control.IRegistro;
import comun.i_control.IUsuario;
import comun.utils.GeneracionDeClaves;

public class MediadorPrincipal{

	protected ClienteConection cc; 
	protected GUIMenu_Principal gui_menu_Principal;
	protected GUILogin gui_login;
	protected GUICambiarApariencia cambiarApariencia;
	protected UsuarioDTO usuario;
	protected MediadorUsuario mediadorUsuario;
	protected MediadorPersona mediadorPersona;
	protected MediadorLugar mediadorLugar;
	protected MediadorOrganizacion mediadorOrg;
	protected MediadorPersonalOrganizacion mediadorPersonalOrg;
	protected MediadorUsuarioOrganizacion mediadorUsuarioOrg;
	protected MediadorRegistro mediadorRegistro;
	protected Apariencia apariencia;
	protected int aparienciaSeleccionada;
	
	public MediadorPrincipal(ClienteConection cc) throws Exception{
		this.cc = cc;
		setApariencia();		
		gui_menu_Principal = new GUIMenu_Principal(this);
		cargarComboOrg();
		cargarComboLugar();
	}
	public void cargarImagenes(){
		gui_menu_Principal.cargarImagenes();
		gui_menu_Principal.setVisible(true);
		
	}
	
	public boolean modifApariencia (int index){
		if (index<0 && index>=Constantes.apariencias.length)index=0;
		String aEscribir = Constantes.apariencias[index];
		try{
			FileWriter w = new FileWriter(Constantes.interfaceFile);
			BufferedWriter bw = new BufferedWriter(w);
			PrintWriter wr = new PrintWriter(bw);  
			wr.write(aEscribir);
			wr.close();
			bw.close();
			aparienciaSeleccionada=index;
			return true;
		}catch(Exception e){
			return false;
		}		
	}
	
	
	private void setApariencia() {
		String skin = Constantes.apariencias[0];
	    try {
		    FileReader fr = new FileReader(Constantes.interfaceFile);
		    BufferedReader br = new BufferedReader(fr);
		    skin=br.readLine().trim();
		    fr.close();
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    	skin = Constantes.apariencias[0];
	    }
		if (skin.equals(Constantes.apariencias[0])) {
			apariencia = new PorDefecto();
			apariencia.cargarApariencia1();
			aparienciaSeleccionada=0;
		} else {
			if (skin.equals(Constantes.apariencias[1])) {
				apariencia = new PorDefecto();
				apariencia.cargarApariencia2();
				aparienciaSeleccionada=1;
			} else {
				if (skin.equals(Constantes.apariencias[2])) {
					apariencia = new Minimal();
					apariencia.cargarApariencia1();
					aparienciaSeleccionada=2;
				} else {
					if (skin.equals(Constantes.apariencias[3])) {
						apariencia = new Minimal();
						apariencia.cargarApariencia2();
						aparienciaSeleccionada=3;
					} else {
						if (skin.equals(Constantes.apariencias[4])) {
							apariencia = new Lite();
							apariencia.cargarApariencia1();
							aparienciaSeleccionada=4;
						} else {
							apariencia = new Lite();
							apariencia.cargarApariencia2();
							aparienciaSeleccionada=5;
						}
					}
				}
			}
		}	
	}
	
	
	public boolean acceso(String usuario, String contrasenia) throws Exception {
		boolean result = false;
		try{
			IUsuario iControlUsuario = cc.getControlUsuario();
			UsuarioDTO u = iControlUsuario.login(usuario, GeneracionDeClaves.MD5(contrasenia));
			if (u!=null){
				setUsuario(u);
				gui_menu_Principal.cargar_menu();
				gui_menu_Principal.setVisible(true);
				result = true;
			}
		}catch (Exception e){
			e.printStackTrace();
			result=false;
		}
		return result;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void cargarComboOrg() throws Exception{
		IOrganizacion iOrg = cc.getControlOrganizacion();
		LinkedList<OrganizacionDTO> orgs = new LinkedList<OrganizacionDTO>(iOrg.obtenerOrganizaciones("eliminado==false"));
		//Collection.sort
		//+" [ID: "+mediadorPrincipal.getUsuario().getId().toString()+" ]";
		LinkedList<String> res = new LinkedList<String>();
		for (OrganizacionDTO org : orgs) {
			res.add(org.getNombre()+" [ID:"+org.getId().toString()+"]");
		}
		gui_menu_Principal.comboOrg= new DefaultComboBoxModel(res.toArray());
		gui_menu_Principal.comboBox_Org.setModel(gui_menu_Principal.comboOrg);
		gui_menu_Principal.comboBox_Org.repaint();
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void cargarComboLugar() throws Exception{
		ILugar iLugar = cc.getControlLugar();
		LinkedList<LugarDTO> lugares = new LinkedList<LugarDTO>(iLugar.obtenerLugares("eliminado==false"));
		//Collection.sort
		//+" [ID: "+mediadorPrincipal.getUsuario().getId().toString()+" ]";
		LinkedList<String> res = new LinkedList<String>();
		for (LugarDTO lugar : lugares) {
			res.add(lugar.getNombre()+" [ID:"+lugar.getId().toString()+"]");
		}
		gui_menu_Principal.comboLugar= new DefaultComboBoxModel(res.toArray());
		gui_menu_Principal.comboBox_lugar.setModel(gui_menu_Principal.comboLugar);
		gui_menu_Principal.comboBox_lugar.repaint();
	}
	
	public void lanzar_login(){
		gui_login = new GUILogin(this);
		gui_login.setVisible(true);
		gui_menu_Principal.setVisible(false);
	}

	public void reiniciar() {
		gui_login.dispose();
		setUsuario(null);
		gui_menu_Principal.cargar_menu();
		gui_menu_Principal.setVisible(true);
	}
	
	public void salir() {
		if(gui_login!=null) gui_login.dispose();
		gui_menu_Principal.dispose();
		System.exit(0);
	}
	
	/*GETS A SETS*/
	public ClienteConection getCc() {
		return cc;
	}

	public void setCc(ClienteConection cc) {
		this.cc = cc;
	}

	public GUIMenu_Principal getGui_menu_Principal() {
		return gui_menu_Principal;
	}

	public void setGui_menu_Principal(GUIMenu_Principal gui_menu_Principal) {
		this.gui_menu_Principal = gui_menu_Principal;
	}

	public GUILogin getGui_login() {
		return gui_login;
	}

	public void setGui_login(GUILogin gui_login) {
		this.gui_login = gui_login;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	
	public Apariencia getApariencia() {
		return apariencia;
	}


	public void altaUsuario(){
		mediadorUsuario = new MediadorUsuario(this);
		mediadorUsuario.altaUsuario();
	}

	public void gestionUsuario(){
		mediadorUsuario = new MediadorUsuario(this);
		mediadorUsuario.gestionUsuario();
	}
	
	public void modificarPerfil(){
		mediadorUsuario = new MediadorUsuario(this);
		mediadorUsuario.modifUsuario(usuario.getId());
	}
	
	public void altaPersona(){
		mediadorPersona = new MediadorPersona(this);
		mediadorPersona.altaPersona();
	}
	
	public void gestionPersona(){
		mediadorPersona = new MediadorPersona(this);
		mediadorPersona.gestioPersona();
	}

	public void altaOrg(){
		mediadorOrg=new MediadorOrganizacion(this);
		mediadorOrg.altaOrganizacion();
	}

	public void gestionOrganizacion(){
		mediadorOrg=new MediadorOrganizacion(this);
		mediadorOrg.gestionOrganizacion();
	}
	
	public void altaLugar(){
		mediadorLugar = new MediadorLugar(this);
		mediadorLugar.altaLugar();
	}

	public void gestionarLugar(){
		mediadorLugar = new MediadorLugar(this);
		mediadorLugar.gestionLugar();
	}

	public void gestionPersonalOrg() {
		mediadorPersonalOrg = new MediadorPersonalOrganizacion(this);
		mediadorPersonalOrg.gestionAsig();
	}

	public void gestionUsuarioOrganizacion() {
		mediadorUsuarioOrg = new MediadorUsuarioOrganizacion(this);
		mediadorUsuarioOrg.gestionAsig();
	}
	
	public void consultarRegistro() {
		mediadorRegistro = new MediadorRegistro(this);
		mediadorRegistro.consultarRegistro();
	}
	
	public void cambiarApariencia() {
		cambiarApariencia = new GUICambiarApariencia(this);
		cambiarApariencia.setVisible(true);
		gui_menu_Principal.setVisible(false);
	}

	public void fichar(String org, String lugar,String dni, String datosExtra) {
		ILugar iL = cc.getControlLugar();
		IPersona iP = cc.getControlPersona();
		IPersonalOrganizacion iPO = cc.getControlPO();
		IRegistro iReg = cc.getControlRegistro();
		Long idOrg =getIds(org);
		Long idLugar =getIds(lugar);
		try {
			LugarDTO l = iL.buscarLugar(idLugar);
			if (l!=null){
				Vector<PersonaDTO>pers= iP.obtenerPersonas("dni_cuil_cuit.toUpperCase().trim().equals(\""+dni.toUpperCase().trim()+"\") && eliminado==false");
				if (pers!=null && !pers.isEmpty()){
					PersonaDTO p = (PersonaDTO) pers.toArray()[0];
					PersonalOrganizacionDTO po= iPO.buscarPersonalOrganizacion(p.getId(), idOrg);
					if (po!=null){
						Timestamp momento=new java.sql.Timestamp((new java.util.Date()).getTime());
						if(iReg.existeRegistroAbierto(po.getId(), idLugar)){
							RegistroDTO reg = iReg.buscarRegistroAbierto(po.getId(), idLugar);
							reg.setSalida(momento);
							if(datosExtra!=null && !datosExtra.trim().equals("")){
								if (reg.getDescripcion().trim().equals(""))
									datosExtra = "Salida: "+datosExtra;
								else
									datosExtra = "\nSalida: "+datosExtra;
							}else{datosExtra="";}
							reg.setDescripcion(reg.getDescripcion()+datosExtra);
							iReg.modificarRegistro(reg.getId(), reg);
							gui_menu_Principal.lanzarJdialog(1, momento.toString());
						}else{
							if(datosExtra!=null && !datosExtra.trim().equals("")){
								datosExtra = "Entrada: "+datosExtra;
							}else{datosExtra="";}
							RegistroDTO reg = new RegistroDTO(po, l, momento, datosExtra);
							iReg.agregarRegistro(reg);
							gui_menu_Principal.lanzarJdialog(0, momento.toString());
						}
						limpiarFormulario();
					}else{
						gui_menu_Principal.lanzarJdialog(2,"");}
				}else{
					gui_menu_Principal.lanzarJdialog(3,"");}
			}else{
				gui_menu_Principal.lanzarJdialog(4,"");}
		} catch (Exception e) {
			e.printStackTrace();

			gui_menu_Principal.lanzarJdialog(5,"");
		}	
	}
	
	public void limpiarFormulario(){
		gui_menu_Principal.txtpnDatosExtra.setText("");
		gui_menu_Principal.txtIngreseElDni.setText("");
	}

	public Long getIds (String org){
		if (org!=null){
			String [] split = org.split(":");
			String id = split[split.length-1].split("]")[0];
			return Long.parseLong(id);
		}else
			return null;
	}
}
