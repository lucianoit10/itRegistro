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
package comun.utils;

import java.awt.Color;
import java.sql.Timestamp;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.border.Border;


public class Utils{

	public static Color colorFondo = new Color(240,248,255);
	public static Color colorPanel = new Color(135,206,250); 
	public static Color colorNegro = new Color(0,0,0);
	public static Color colorTextoDisabled = new Color(0,0,205); 
	public static String tipoLetra = "Century Gothic";
	public static Border b= javax.swing.BorderFactory.createLineBorder(new Color(0,0,205),2);
	
	
	public static final String CODEBASE = "java.rmi.server.codebase";
	
	
	public static void setCodeBase(Class<?> c) {
		String ruta = c.getProtectionDomain().getCodeSource()
					   .getLocation().toString();
		
		String path = System.getProperty(CODEBASE);
		
		if (path != null && !path.isEmpty()) {
			ruta = path + " " + ruta;  
		}
		
		System.setProperty(CODEBASE, ruta);
	}
	
	public Utils(){ 
	}

	/* Toma un String con formato  dd/mm/yyyy y lo convierte al formato yyyy-dd-mm */
	public static String strToDateFormToDB(String fecha){  
		String dateValue = fecha;
		int index = dateValue.indexOf("/");
		String day = dateValue.substring(0,index);
		int index2 = dateValue.indexOf("/",index+1);
		String month = dateValue.substring(index+1,index2);
		String year = dateValue.substring(index2+1,index2+5);
		dateValue=year+"-"+month+"-"+day;
		return dateValue;
	}

	/* Retorna un java.sql.Date de un string con formato dd/mm/aaaa */
	public static java.sql.Date strToSqlDateDB(String fecha){
		return java.sql.Date.valueOf( strToDateFormToDB(fecha) );
	}

	/* Retorna un java.util.Date de un string con formato dd/mm/aaaa y la hora hh:mm */
	public static java.util.Date strToUtilDate(String fecha, String hora){
		String f=strToDateFormToDB(fecha)+" "+hora+":00";
		java.sql.Timestamp dh= java.sql.Timestamp.valueOf( f);
		return (java.util.Date)dh;
	}

	private static String fechaNula(){
		return "---";
	}

	public static java.sql.Date crearFecha(int dia, int mes, int anio){
		@SuppressWarnings("deprecation")
		java.sql.Date diaCreado= new java.sql.Date(anio-1900,mes-1,dia);
		return diaCreado;
	}

	public static Timestamp crearFecha(java.util.Date dia){
		@SuppressWarnings("deprecation")
		Timestamp diaHr=new Timestamp(dia.getYear(),dia.getMonth(),dia.getDate(),dia.getHours(),dia.getMinutes(),dia.getSeconds(),0);
		return diaHr;
	}

	/* Retorna el String dd/mm/aaaa para un java.util.Date */
	public static String getStrUtilDate(java.util.Date date){
		if(date==null)
			return fechaNula();
		String strDate="";

		if(getDia(date)<10)
			strDate+="0"+getDia(date);
		else
			strDate+=getDia(date);
		if(getMes(date)<10)
			strDate+="/"+"0"+getMes(date);
		else
			strDate+="/"+getMes(date);
		strDate+="/"+getAnio(date);
		return strDate;
	}
	
	@SuppressWarnings("deprecation")
	public static String mostrarDiaYMes(int dia, int mes, int anio){
		java.sql.Date date= new java.sql.Date(anio-1900,mes-1,dia);
		String strDate="";
		if(getDia(date)<10)
			strDate+="0"+getDia(date);
		else
			strDate+=getDia(date);
		if(getMes(date)<10)
			strDate+="/"+"0"+getMes(date);
		else
			strDate+="/"+getMes(date);
		strDate += "  - "+nombDia(date.getDay());
		return strDate;
	}
	
	private static String nombDia(int diaSemana){
		if(diaSemana==0) return "Dom";
		if(diaSemana==1) return "Lun";
		if(diaSemana==2) return "Mar";
		if(diaSemana==3) return "Mie";
		if(diaSemana==4) return "Jue";
		if(diaSemana==5) return "Vie";
		if(diaSemana==6) return "Sab";
		return "";
	}

	/* Retorna el String hh:mm para un java.util.Date */
	public static String getHoraUtilDate(java.util.Date date){
		if(date==null)
			return "";
		String strDate="";
		if(getHora(date)<10)
			strDate+="0"+getHora(date);
		else
			strDate+=getHora(date);
		if(getMinutos(date)<10)
			strDate+=":"+"0"+(getMinutos(date));
		else
			strDate+=":"+getMinutos(date);
		return strDate;
	}

	public static String getHoraMSUtilDate(java.util.Date date){
		if(date==null)
			return "";
		String strDate="";
		if(getHora(date)<10)
			strDate+="0"+getHora(date);
		else
			strDate+=getHora(date);
		if(getMinutos(date)<10)
			strDate+=":"+"0"+(getMinutos(date));
		else
			strDate+=":"+getMinutos(date);
		if(getSegundos(date)<10)
			strDate+=":"+"0"+(getSegundos(date));
		else
			strDate+=":"+getSegundos(date);
		return strDate;
	}

	@SuppressWarnings("deprecation")
	public static int getDia(Date fecha){
		return fecha.getDate();
	}

	@SuppressWarnings("deprecation")
	public static int getMes(Date fecha){
		return fecha.getMonth()+1;
	}

	@SuppressWarnings("deprecation")
	public static int getAnio(Date fecha){
		return fecha.getYear()+1900;
	}

	@SuppressWarnings("deprecation")
	public static int getHora(Date fecha){
		return fecha.getHours();
	}

	@SuppressWarnings("deprecation")
	public static int getMinutos(Date fecha){
		return fecha.getMinutes();
	}

	@SuppressWarnings("deprecation")
	public static int getSegundos(Date fecha){
		return fecha.getSeconds();
	}

	public static String mayuscula(String string){
		String aux, auxDos;
		StringTokenizer tokenizer = new StringTokenizer(string, " ");
		String stringCapitalizado = "";
		while (tokenizer.hasMoreElements()) {
			aux = new String(tokenizer.nextToken());
			auxDos = new String (aux.substring(0,1).toUpperCase());
			stringCapitalizado += auxDos+aux.substring(1,aux.length()).toLowerCase()+" ";
		}
		return stringCapitalizado.trim();
	}

	/* Este metodo nos dice si "entrada" es una subcadena de "baseDeDatos"
	 *  por ejemplo:
	 *      entrada="laura"  baseDeDatos="laura Contreras" =>    TRUE
	 *      entrada="laura beatriz"  baseDeDatos="contreras laura beatriz" =>    TRUE */
	public static boolean comienza(String baseDeDatos, String entrada) {
		/* ENTRADA DE DATOS */
		String[] cadena;
		StringTokenizer tokenizerBD = new StringTokenizer(baseDeDatos, " ");
		int cantTokensBD = tokenizerBD.countTokens();
		StringTokenizer tokenizerEntrada = new StringTokenizer(entrada, " ");
		int cantTokensEntrada = tokenizerEntrada.countTokens();
		if (cantTokensEntrada == 0) {
			return true;
		} else if (cantTokensEntrada == 1) {
			boolean encontrado = false;
			for (int j=0; j<cantTokensBD && !encontrado; j++) {
				if (tokenizerBD.nextToken().toUpperCase().startsWith(entrada.trim().toUpperCase()) ) {
					encontrado = true;
				}
			}
			return encontrado;
		} else {
			boolean encontrado = false;
			cadena = new String[cantTokensBD];
			for (int i=0; i<cantTokensBD; i++) {
				cadena[i] = new String(tokenizerBD.nextToken());
			}
			for (int j=0; j<(cadena.length-1) && !encontrado; j++) {
				String aux = "";
				for (int k=j; k<cadena.length; k++) {
					aux += cadena[k]+" ";
				}  
				encontrado = aux.toUpperCase().startsWith(entrada.trim().toUpperCase());
			}
			return encontrado;
		}
	}

	/* Verifica si el formato de un String es de tipo Double */
	public static boolean esDouble(String string) {
		int cantp=0;
		int pos=0;
		while (pos<string.length()) {
			if(string.charAt(pos)=='.')
				cantp++;
			pos++;
		}
		if(cantp<=1)
			return true;
		else
			return false;
	}

	/* Verifica si el formato de un String es de tipo (-)numero */
	public static boolean esNumeroPosNeg(String string) {
		int cantp=0;
		int pos=0;
		int indiceSigno=0;
		while (pos<string.length()) {
			if(string.charAt(pos)=='-'){
				cantp++;
				indiceSigno=pos;
			}	
			pos++;
		}
		if(cantp<=1){
			if(indiceSigno==0)
				return true;
			else
				return false;
		}else
			return false;
	}

	
}