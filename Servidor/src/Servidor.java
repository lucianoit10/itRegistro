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

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import persistencia.AccesoBD;

import comun.RootAndIp;
import comun.DTO.UsuarioDTO;
import comun.utils.GeneracionDeClaves;
import comun.utils.RMI_Constantes;
import comun.utils.Utils;
import control.*;

public class Servidor {
	private ControlLugar controlLugar;
	private ControlOrganizacion controlOrganizacion;
	private ControlPersona controlPersona;
	private ControlUsuario controlUsuario;
	private ControlPersonalOrganizacion controlPO;
	private ControlUsuarioOrganizacion controlUO;
	private ControlRegistro controlRegistro;
	private ControlFecha controlFechas;
	private String name = "";
	private String ip = "";

	
	public Servidor(AccesoBD abd) throws RemoteException {
		controlLugar = new ControlLugar(abd);
		controlOrganizacion = new ControlOrganizacion(abd);
		controlPersona = new ControlPersona(abd);
		controlUsuario = new ControlUsuario(abd);
		controlPO = new ControlPersonalOrganizacion(abd);
		controlUO = new ControlUsuarioOrganizacion(abd);
		controlRegistro = new ControlRegistro(abd);
		controlFechas = new ControlFecha();
		try {
			if (!(controlUsuario.obtenerUsuarios().size()>0)){
				UsuarioDTO admin = new UsuarioDTO("admin",GeneracionDeClaves.MD5( "admin"));
				admin.setPermiso(2);
				controlUsuario.agregarUsuario(admin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void iniciar() throws Exception {
		RootAndIp.setConf("");
		int port = RootAndIp.getPort();
		
		Registry Naming = LocateRegistry.createRegistry(port);
		Utils.setCodeBase(Servidor.class);
		
		System.out.println("Iniciando servidor !!!");

        ip = RootAndIp.getIp();
        System.out.println("ip localhost"+InetAddress.getLocalHost().toString());
        System.out.println("<<<<<<<<<<<<<<<<<INFO SERVER>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("IP: " + this.ip);
		System.out.println("PUERTO: " + port);
		System.out.println("BASE DE DATOS: " + RootAndIp.getDb());
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		this.name = RMI_Constantes.idRMIPersona;
		Naming.rebind(this.name, this.controlPersona);
		System.out.println("Nombre: " + this.name);

		this.name = RMI_Constantes.idRMIUsuario;
		Naming.rebind(this.name, this.controlUsuario);
		System.out.println("Nombre: " + this.name);
		
		this.name = RMI_Constantes.idRMIOrganizacion;
		Naming.rebind(this.name, this.controlOrganizacion);
		System.out.println("Nombre: " + this.name);

		this.name = RMI_Constantes.idRMILugar;
		Naming.rebind(this.name, this.controlLugar);
		System.out.println("Nombre: " + this.name);

		this.name = RMI_Constantes.idRMIPersonalOrganizacion;
		Naming.rebind(this.name, this.controlPO);
		System.out.println("Nombre: " + this.name);

		this.name = RMI_Constantes.idRMIUsuarioOrganizacion;
		Naming.rebind(this.name, this.controlUO);
		System.out.println("Nombre: " + this.name);

		this.name = RMI_Constantes.idRMIRegistro;
		Naming.rebind(this.name, this.controlRegistro);
		System.out.println("Nombre: " + this.name);		

		this.name = RMI_Constantes.idRMIFechas;
		Naming.rebind(this.name, this.controlFechas);
		System.out.println("Nombre: " + this.name);
		
		System.out.println("Listo para conexiones");
		int exit = 0;
		synchronized (this) {
			if(exit!=1)
				this.wait();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
/*
 * 		GETS Y SETS DE CONTROLES
 * */

	public ControlLugar getControlLugar() {
		return controlLugar;
	}

	public void setControlLugar(ControlLugar controlLugar) {
		this.controlLugar = controlLugar;
	}

	public ControlOrganizacion getControlOrganizacion() {
		return controlOrganizacion;
	}

	public void setControlOrganizacion(ControlOrganizacion controlOrganizacion) {
		this.controlOrganizacion = controlOrganizacion;
	}

	public ControlPersona getControlPersona() {
		return controlPersona;
	}

	public void setControlPersona(ControlPersona controlPersona) {
		this.controlPersona = controlPersona;
	}

	public ControlUsuario getControlUsuario() {
		return controlUsuario;
	}

	public void setControlUsuario(ControlUsuario controlUsuario) {
		this.controlUsuario = controlUsuario;
	}

	public ControlPersonalOrganizacion getControlPO() {
		return controlPO;
	}

	public void setControlPO(ControlPersonalOrganizacion controlPO) {
		this.controlPO = controlPO;
	}

	public ControlRegistro getControlRegistro() {
		return controlRegistro;
	}

	public void setControlRegistro(ControlRegistro controlRegistro) {
		this.controlRegistro = controlRegistro;
	}

	public ControlFecha getControlFechas() {
		return controlFechas;
	}

	public void setControlFechas(ControlFecha controlFechas) {
		this.controlFechas = controlFechas;
	}

	public ControlUsuarioOrganizacion getControlUO() {
		return controlUO;
	}

	public void setControlUO(ControlUsuarioOrganizacion controlUO) {
		this.controlUO = controlUO;
	}

}
