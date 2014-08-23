package control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import comun.i_control.IFechas;

public class ControlFecha extends UnicastRemoteObject implements IFechas{
	
	public ControlFecha() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;


	public Long getFechaActual() throws Exception{
		return new Date().getTime();
	}

}
