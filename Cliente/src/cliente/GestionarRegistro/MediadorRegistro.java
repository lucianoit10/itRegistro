package cliente.GestionarRegistro;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.Vector;

import cliente.MenuPrincipal.MediadorPrincipal;
import cliente.utils.Constantes;

import comun.DTO.LugarDTO;
import comun.DTO.PersonaDTO;
import comun.DTO.PersonalOrganizacionDTO;
import comun.DTO.RegistroDTO;
import comun.DTO.UsuarioOrganizacionDTO;
import comun.i_control.ILugar;
import comun.i_control.IPersona;
import comun.i_control.IPersonalOrganizacion;
import comun.i_control.IRegistro;
import comun.i_control.IUsuarioOrganizacion;

public class MediadorRegistro {

	protected GuiFiltroRegistro guiFiltro;
	protected MediadorPrincipal mediadorPrincipal;
	protected String hsDelFiltro="";
	
	public MediadorRegistro(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal = mediadorPrincipal;
	}
	
	public void consultarRegistro (){
		try {
			guiFiltro = new GuiFiltroRegistro(this);
			guiFiltro.cargarDatos();
			guiFiltro.setVisible(true);
			mediadorPrincipal.getGui_menu_Principal().setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[] cargarOrganizaciones (){
		IUsuarioOrganizacion iPO = mediadorPrincipal.getCc().getControlUO();
		try {
			String filtro = "usuario.id=="+mediadorPrincipal.getUsuario().getId().toString()+" && org.eliminado==false";
			Vector<UsuarioOrganizacionDTO> orgs = iPO.obtenerUsuariosOrganizacion(filtro);
			String[] res = new String[orgs.size()+1];
			res[0]=Constantes.comboVacio;
			if (!orgs.isEmpty()){
				for (int i=0; i< orgs.size();i++){
					String org = orgs.elementAt(i).getOrg().getNombre()+" [ID:"+orgs.elementAt(i).getOrg().getId()+"]";
					res[i+1]=org;
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
	

	public Collection<String> misOrganizaciones (boolean incluirElim){
		IUsuarioOrganizacion iPO = mediadorPrincipal.getCc().getControlUO();
		try {
			String filtro = "usuario.id=="+mediadorPrincipal.getUsuario().getId().toString();
			if (!incluirElim) filtro+=" && org.eliminado==false";
			Vector<UsuarioOrganizacionDTO> orgs = iPO.obtenerUsuariosOrganizacion(filtro);
			Collection <String> res = new LinkedList<String>();
			if (!orgs.isEmpty()){
				for (int i=0; i< orgs.size();i++){
					String org =orgs.elementAt(i).getOrg().getId().toString();
					res.add(org);
				}
			}
			return res;
		} catch (Exception e) {
			System.out.println("Error al cargar tablaAsignados");
			e.printStackTrace();
		}	
		return (Collection<String>) new LinkedList<String>();
	}
	
	
	public String[] cargarLugares() throws Exception{
		ILugar iLugar = mediadorPrincipal.getCc().getControlLugar();
		Vector<LugarDTO> lugares = iLugar.obtenerLugares("eliminado==false");
		//Collection.sort
		//+" [ID: "+mediadorPrincipal.getUsuario().getId().toString()+" ]";
		String[] res = new String[lugares.size()+1];
		res[0]=Constantes.comboVacio;
		if (!lugares.isEmpty()){
			for (int i=0; i< lugares.size();i++){
				res[i+1] = lugares.elementAt(i).getNombre()+" [ID:"+lugares.elementAt(i).getId().toString()+"]";
			}
		}
		return res;
	}
	
	public String[] cargarPersonas() throws Exception{
		IPersona iPers = mediadorPrincipal.getCc().getControlPersona();
		Vector<PersonaDTO> personas = iPers.obtenerPersonas("eliminado==false");
		//Collection.sort
		//+" [ID: "+mediadorPrincipal.getUsuario().getId().toString()+" ]";
		String[] res = new String[personas.size()+1];
		res[0]=Constantes.comboVacio;
		if (!personas.isEmpty()){
			for (int i=0; i< personas.size();i++){
				res[i+1] = personas.elementAt(i).getApellido()+", "+ personas.elementAt(i).getNombre()+" [ID:"+personas.elementAt(i).getId().toString()+"]";
			}
		}
		return res;
	}


	public String[] cargarPersonas(String org) throws Exception{
		IPersonalOrganizacion iPersOrg = mediadorPrincipal.getCc().getControlPO();
		Vector<PersonalOrganizacionDTO> persOrg = iPersOrg.obtenerPersonalesOrganizacion("org.id=="+getIds(org)+" && eliminado==false");
		//Collection.sort
		//+" [ID: "+mediadorPrincipal.getUsuario().getId().toString()+" ]";
		String[] res = new String[persOrg.size()+1];
		res[0]=Constantes.comboVacio;
		if (!persOrg.isEmpty()){
			for (int i=0; i< persOrg.size();i++){
				res[i+1] =persOrg.elementAt(i).getPersona().getApellido()+", "+  persOrg.elementAt(i).getPersona().getNombre()+" [ID:"+persOrg.elementAt(i).getPersona().getId().toString()+"]";
			}
		}
		return res;
	}
	
	
	public String obtenerFiltro (Date entrada,Date salida, String org, String pers, String lugar, boolean incluirElim){
		String filtro = "";
		boolean construccionPreviaFiltro=false;
		if (!incluirElim) {
			filtro = "personalOrg.org.eliminado==false && personalOrg.persona.eliminado==false && lugar.eliminado==false";
			construccionPreviaFiltro=true;
		}
		if (!org.equals(Constantes.comboVacio)){
			if (construccionPreviaFiltro) filtro +=" && ";
			filtro+="personalOrg.org.id=="+getIds(org);
		}
		if (!pers.equals(Constantes.comboVacio)){
			if (construccionPreviaFiltro) filtro +=" && ";
			filtro+="personalOrg.persona.id=="+getIds(pers);
		}
		if (!lugar.equals(Constantes.comboVacio)){
			if (construccionPreviaFiltro) filtro +=" && ";
			filtro+="lugar.id=="+getIds(lugar);
		}
		/*if (entrada!=null){
			if (construccionPreviaFiltro) filtro +=" && ";
			filtro+="entrada>="+entrada.getTime();
			if (salida!=null) filtro+=" && salida>="+entrada.getTime();
		}
		if (salida!=null){
			if (construccionPreviaFiltro) filtro +=" && ";
			filtro+="entrada<="+salida.getTime()+" && salida<="+salida.getTime();
		}*/
		return filtro;
	}
	
	public Vector<Vector<String>> resultadoFiltroLibre (Date entrada,Date salida, String org, String pers, String lugar, boolean incluirElim, boolean omitirAbiertos) throws Exception{
		
		IRegistro iRegistro = mediadorPrincipal.getCc().getControlRegistro();
		Vector<RegistroDTO> registros = iRegistro.obtenerRegistros(obtenerFiltro(entrada, salida, org, pers, lugar, incluirElim));
		Vector<Vector<String>> res = new Vector<Vector<String>>();
		Collection<String> misOrg = misOrganizaciones(incluirElim);
		for (int i = 0; i < registros.size(); i++) {
			if (misOrg.contains(registros.get(i).getPersonalOrg().getOrg().getId().toString())){
				Vector<String> row = new Vector<String>();
				// ------------------------------------------ 
				//chequeo de fechas
				boolean incorporar = true;
				if (entrada!=null && registros.get(i).getIngreso().before(entrada)) incorporar=false;
				if (salida!=null){
					if (registros.get(i).getSalida()!=null && registros.get(i).getIngreso().after(salida) && registros.get(i).getSalida().after(salida))  incorporar=false;
					else if(registros.get(i).getIngreso().after(salida) && (new Date()).after(salida))  incorporar=false;
				}
				if (incorporar){
					PersonaDTO p = registros.get(i).getPersonalOrg().getPersona();
					//DIA
					row.add(p.getApellido()+", "+p.getNombre());
					row.add(p.getDni_cuil_cuit());
					row.add(registros.get(i).getIngreso().toString());
					boolean esAbierto=false;
					if (registros.get(i).getSalida()!=null){// si esta cerrado calcual la diferencia
						row.add(registros.get(i).getSalida().toString());
						row.add(calcHs(new Date(registros.get(i).getIngreso().getTime()),new Date(registros.get(i).getSalida().getTime())));
					}else{// si esta abierto calcula la diferencia con el dia y hs actual
						java.sql.Date actual =  new java.sql.Date (new java.util.Date().getTime());
						row.add(actual.toString());
						row.add(calcHs(new Date(registros.get(i).getIngreso().getTime()),actual));
						esAbierto=true;
					}
					row.add(registros.get(i).getDescripcion());	
					if (!(omitirAbiertos&&esAbierto))
						res.add(row);
				}
			}
		}
		return res;
	}
	

	public Vector<Vector<String>> resultadoFiltroXPersona (Date entrada,Date salida, String org, String pers, String lugar, boolean incluirElim, boolean omitirAbiertos) throws Exception{
		
		IRegistro iRegistro = mediadorPrincipal.getCc().getControlRegistro();
		Vector<RegistroDTO> registros = iRegistro.obtenerRegistros(obtenerFiltro(entrada, salida, org, pers, lugar, incluirElim),"personalOrg.persona.id descending","");
		Vector<Vector<String>> res = new Vector<Vector<String>>();
		Collection<String> misOrg = misOrganizaciones(incluirElim);
		int j = 0; 
		while(j< registros.size()){
			if (misOrg.contains(registros.get(j).getPersonalOrg().getOrg().getId().toString())){
				Vector<String> row = new Vector<String>();
				PersonaDTO p = registros.get(j).getPersonalOrg().getPersona();
				//DIA
				row.add(p.getApellido()+", "+p.getNombre());
				row.add(p.getDni_cuil_cuit());
				Vector<String> calc = new Vector<String>();
				while (j<registros.size()){
					if (misOrg.contains(registros.get(j).getPersonalOrg().getOrg().getId().toString())){
						boolean incorporar = true;
						if (entrada!=null && registros.get(j).getIngreso().before(entrada)) incorporar=false;
						if (salida!=null){
							if (registros.get(j).getSalida()!=null && registros.get(j).getIngreso().after(salida) && registros.get(j).getSalida().after(salida))  incorporar=false;
							else if(registros.get(j).getIngreso().after(salida) && (new Date()).after(salida))  incorporar=false;
						}
						if (incorporar){
							boolean esAbierto=false;
							String c = ""; 
							if (registros.get(j).getSalida()!=null){// si esta cerrado calcual la diferencia
								c =calcHs(new Date(registros.get(j).getIngreso().getTime()),new Date(registros.get(j).getSalida().getTime()));
							}else{// si esta abierto calcula la diferencia con el dia y hs actual
								java.sql.Date actual =  new java.sql.Date (new java.util.Date().getTime());
								c= calcHs(new Date(registros.get(j).getIngreso().getTime()),actual);
								esAbierto=true;
							}
							if (!(omitirAbiertos&&esAbierto))
								calc.add(c);
						}
					}
					j++;
					if(j>=registros.size() || (registros.get(j).getPersonalOrg().getPersona().getId()!=registros.get(j-1).getPersonalOrg().getPersona().getId()))break;
				}
				row.add(calcSumaHs(calc));
				res.add(row);
			}else j++;
		}
		return res;
	}
	

	@SuppressWarnings("deprecation")
	public Vector<Vector<String>> resultadoFiltroXDia (Date entrada,Date salida, String org, String pers, String lugar, boolean incluirElim, boolean omitirAbiertos) throws Exception{
		
		IRegistro iRegistro = mediadorPrincipal.getCc().getControlRegistro();
		Vector<RegistroDTO> registros = iRegistro.obtenerRegistros(obtenerFiltro(entrada, salida, org, pers, lugar, incluirElim),"ingreso ascending","");
		Vector<Vector<String>> res = new Vector<Vector<String>>();
		Collection<String> misOrg = misOrganizaciones(incluirElim);
		int j = 0; 
		while(j< registros.size()){
			if (misOrg.contains(registros.get(j).getPersonalOrg().getOrg().getId().toString())){
				Vector<String> row = new Vector<String>();
				row.add((registros.get(j).getIngreso().getDay()+10)+"/"+(registros.get(j).getIngreso().getMonth()+1)+"/"+(registros.get(j).getIngreso().getYear()+1900));
				Vector<String> calc = new Vector<String>();
				while (j<registros.size()){
					if (misOrg.contains(registros.get(j).getPersonalOrg().getOrg().getId().toString())){
						boolean incorporar = true;
						if (entrada!=null && registros.get(j).getIngreso().before(entrada)) incorporar=false;
						if (salida!=null){
							if (registros.get(j).getSalida()!=null && registros.get(j).getIngreso().after(salida) && registros.get(j).getSalida().after(salida))  incorporar=false;
							else if(registros.get(j).getIngreso().after(salida) && (new Date()).after(salida))  incorporar=false;
						}
						if (incorporar){
							boolean esAbierto=false;
							String c = ""; 
							if (registros.get(j).getSalida()!=null){// si esta cerrado calcual la diferencia
								c =calcHs(new Date(registros.get(j).getIngreso().getTime()),new Date(registros.get(j).getSalida().getTime()));
							}else{// si esta abierto calcula la diferencia con el dia y hs actual
								java.sql.Date actual =  new java.sql.Date (new java.util.Date().getTime());
								c= calcHs(new Date(registros.get(j).getIngreso().getTime()),actual);
								esAbierto=true;
							}
							if (!(omitirAbiertos&&esAbierto))
								calc.add(c);
						}
					}
					j++;
					boolean condicion = j>=registros.size() || 
										   		(registros.get(j).getIngreso().getYear()!=registros.get(j-1).getIngreso().getYear()||
										   		 registros.get(j).getIngreso().getMonth()!=registros.get(j-1).getIngreso().getMonth()||
												 registros.get(j).getIngreso().getDay()!=registros.get(j-1).getIngreso().getDay());
					if(condicion)break;
				}
				row.add(calcSumaHs(calc));
				res.add(row);
			}else j++;
		}
		return res;
	}

	
@SuppressWarnings("deprecation")
public Vector<Vector<String>> resultadoFiltroXMes (Date entrada,Date salida, String org, String pers, String lugar, boolean incluirElim, boolean omitirAbiertos) throws Exception{
		
		IRegistro iRegistro = mediadorPrincipal.getCc().getControlRegistro();
		Vector<RegistroDTO> registros = iRegistro.obtenerRegistros(obtenerFiltro(entrada, salida, org, pers, lugar, incluirElim),"ingreso ascending","");
		Vector<Vector<String>> res = new Vector<Vector<String>>();
		Collection<String> misOrg = misOrganizaciones(incluirElim);
		int j = 0; 
		while(j< registros.size()){
			if (misOrg.contains(registros.get(j).getPersonalOrg().getOrg().getId().toString())){
				Vector<String> row = new Vector<String>();
				row.add(Constantes.meses[registros.get(j).getIngreso().getMonth()]+" de "+(registros.get(j).getIngreso().getYear()+1900));
				Vector<String> calc = new Vector<String>();
				while (j<registros.size()){
					if (misOrg.contains(registros.get(j).getPersonalOrg().getOrg().getId().toString())){
						boolean incorporar = true;
						if (entrada!=null && registros.get(j).getIngreso().before(entrada)) incorporar=false;
						if (salida!=null){
							if (registros.get(j).getSalida()!=null && registros.get(j).getIngreso().after(salida) && registros.get(j).getSalida().after(salida))  incorporar=false;
							else if(registros.get(j).getIngreso().after(salida) && (new Date()).after(salida))  incorporar=false;
						}
						if (incorporar){
							boolean esAbierto=false;
							String c = ""; 
							if (registros.get(j).getSalida()!=null){// si esta cerrado calcual la diferencia
								c =calcHs(new Date(registros.get(j).getIngreso().getTime()),new Date(registros.get(j).getSalida().getTime()));
							}else{// si esta abierto calcula la diferencia con el dia y hs actual
								java.sql.Date actual =  new java.sql.Date (new java.util.Date().getTime());
								c= calcHs(new Date(registros.get(j).getIngreso().getTime()),actual);
								esAbierto=true;
							}
							if (!(omitirAbiertos&&esAbierto))
								calc.add(c);
						}
					}
					j++;
					boolean condicion = j>=registros.size() || 
										   		(registros.get(j).getIngreso().getYear()!=registros.get(j-1).getIngreso().getYear()||
										   		 registros.get(j).getIngreso().getMonth()!=registros.get(j-1).getIngreso().getMonth());
					if(condicion)break;
				}
				row.add(calcSumaHs(calc));
				res.add(row);
			}else j++;
		}
		return res;
	}
	
	private String calcHs(Date fechaInicio, Date fechaLlegada) {
		
		// tomamos la instancia del tipo de calendario
		Calendar calendarInicio = Calendar.getInstance();
		Calendar calendarFinal = Calendar.getInstance();
		
		// Configramos la fecha del calendatio, tomando los valores del date que generamos en el parse
		calendarInicio.setTime(fechaInicio);
		calendarFinal.setTime(fechaLlegada);
		
		 // obtenemos el valor de las fechas en milisegundos
		long milisegundos1 = calendarInicio.getTimeInMillis();
		long milisegundos2 = calendarFinal.getTimeInMillis();
		
		 // tomamos la diferencia
		long diferenciaMilisegundos = milisegundos2 - milisegundos1;
		
		// calcular la diferencia en minutos
		long diffSeg =  Math.abs (diferenciaMilisegundos / 1000);
		long restoSegs = diffSeg%60;
		
		// calcular la diferencia en minutos
		long diffMinutos =  Math.abs (diferenciaMilisegundos / (60 * 1000));
		long restominutos = diffMinutos%60;
		
		// calcular la diferencia en horas
		long diffHoras =   (diferenciaMilisegundos / (60 * 60 * 1000));
		
		String seg =String.valueOf(restoSegs);
		if (seg.length()==1) seg = "0"+seg;

		String min =String.valueOf(restominutos);
		if (min.length()==1) min = "0"+min;

		String hs =String.valueOf(diffHoras);
		if (hs.length()==1) hs = "0"+hs;
		// devolvemos el resultado en un string
		return String.valueOf(hs + ":" + min+ ":" + seg);
	}
	
	
	public String calcSumaHs (Vector<String> vHs){

		int hs =0;
		int min=0;
		int seg=0;
		
		for (int i = 0; i < vHs.size(); i++) {
			String [] parse= vHs.get(i).split(":");
			hs += Integer.parseInt(parse[0]);
			min+= Integer.parseInt(parse[1]);
			seg+= Integer.parseInt(parse[2]);
		}
		min += seg/60;
		seg = seg%60;
		
		hs +=min/60;
		min =min%60;
		
		String sseg =String.valueOf(seg);
		if (sseg.length()==1) sseg = "0"+sseg;

		String smin =String.valueOf(min);
		if (smin.length()==1) smin = "0"+smin;

		String shs =String.valueOf(hs);
		if (shs.length()==1) shs = "0"+shs;
		// devolvemos el resultado en un string
		return String.valueOf(shs + ":" + smin+ ":" + sseg);
	}

	public Long getIds (String org){
		if (org!=null){
			String [] split = org.split(":");
			String id = split[split.length-1].split("]")[0];
			return Long.parseLong(id);
		}else
			return null;
	}

	public MediadorPrincipal getMediadorPrincipal() {
		return mediadorPrincipal;
	}
	
	
}
