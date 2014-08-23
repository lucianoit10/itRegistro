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
package cliente;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import comun.RootAndIp;
import comun.i_control.IFechas;
import comun.i_control.ILugar;
import comun.i_control.IOrganizacion;
import comun.i_control.IPersona;
import comun.i_control.IPersonalOrganizacion;
import comun.i_control.IRegistro;
import comun.i_control.IUsuario;
import comun.i_control.IUsuarioOrganizacion;
import comun.utils.RMI_Constantes;

public class ClienteConection {
	//Se deben poner como atributos todos los controladores que se quieran cargar
	private ILugar controlLugar;
	private IOrganizacion controlOrganizacion;
	private IPersona controlPersona;
	private IUsuario controlUsuario;
	private IPersonalOrganizacion controlPO;
	private IUsuarioOrganizacion controlUO;
	private IRegistro controlRegistro;
	private IFechas controlFechas;
	
	public ClienteConection (){
	}
	
	public void iniciar() throws Exception {

		comun.RootAndIp.setConf("");
        String ip = RootAndIp.getIp();
        int port = RootAndIp.getPort();
		
        Registry Naming = LocateRegistry.getRegistry(ip,port);
		String nombreServer = "";
		
		nombreServer = RMI_Constantes.idRMILugar;
		setControlLugar((ILugar)Naming.lookup(nombreServer));

		nombreServer = RMI_Constantes.idRMIOrganizacion;
		setControlOrganizacion((IOrganizacion)Naming.lookup(nombreServer));
		
		nombreServer = RMI_Constantes.idRMIPersona;
		setControlPersona((IPersona)Naming.lookup(nombreServer));

		nombreServer = RMI_Constantes.idRMIUsuario;
		setControlUsuario((IUsuario)Naming.lookup(nombreServer));

		nombreServer = RMI_Constantes.idRMIPersonalOrganizacion;
		setControlPO((IPersonalOrganizacion)Naming.lookup(nombreServer));

		nombreServer = RMI_Constantes.idRMIUsuarioOrganizacion;
		setControlUO((IUsuarioOrganizacion)Naming.lookup(nombreServer));
		
		nombreServer = RMI_Constantes.idRMIRegistro;
		setControlRegistro((IRegistro)Naming.lookup(nombreServer));

		nombreServer = RMI_Constantes.idRMIFechas;
		setControlFechas((IFechas)Naming.lookup(nombreServer));
	}

	public ILugar getControlLugar() {
		return controlLugar;
	}

	private void setControlLugar(ILugar controlLugar) {
		this.controlLugar = controlLugar;
	}

	public IOrganizacion getControlOrganizacion() {
		return controlOrganizacion;
	}

	private void setControlOrganizacion(IOrganizacion controlOrganizacion) {
		this.controlOrganizacion = controlOrganizacion;
	}

	public IPersona getControlPersona() {
		return controlPersona;
	}

	private void setControlPersona(IPersona controlPersona) {
		this.controlPersona = controlPersona;
	}

	public IUsuario getControlUsuario() {
		return controlUsuario;
	}

	private void setControlUsuario(IUsuario controlUsuario) {
		this.controlUsuario = controlUsuario;
	}

	public IPersonalOrganizacion getControlPO() {
		return controlPO;
	}

	private void setControlPO(IPersonalOrganizacion controlPO) {
		this.controlPO = controlPO;
	}

	public IRegistro getControlRegistro() {
		return controlRegistro;
	}

	private void setControlRegistro(IRegistro controlRegistro) {
		this.controlRegistro = controlRegistro;
	}

	public IFechas getControlFechas() {
		return controlFechas;
	}

	public void setControlFechas(IFechas controlFechas) {
		this.controlFechas = controlFechas;
	}

	public IUsuarioOrganizacion getControlUO() {
		return controlUO;
	}

	public void setControlUO(IUsuarioOrganizacion controlUO) {
		this.controlUO = controlUO;
	}
	
}
