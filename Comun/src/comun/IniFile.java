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
package comun;

import java.util.*;
import java.io.*;

public class IniFile {
	private String nameFile = "";  
	private Properties p = null;   

	public IniFile() { 
		p = new Properties();
	}

	public IniFile(String nameFile) {
		this.p = new Properties();
		this.nameFile = nameFile;
		this.setNameFile(this.nameFile);
	}

	/* Leer el parametro del archivo seteado */
	public String getParameters(String nombreParametro){
		return p.getProperty(nombreParametro).trim();
	}

	public void setNameFile(String nameFile) {
		try {
			this.nameFile = nameFile;
			p.load(new FileInputStream(this.nameFile));
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public Set keySet(){
		return p.keySet();
	}

	public void listar(){
		p.list(System.out);
	}

	public static void main(String[] args) {
		// Leer el archivo de configuracion
		String archivoIni="conf.ini";
		IniFile iniFile = new IniFile();
		iniFile.setNameFile(archivoIni);
		System.out.println("Archivo Inicio: "+archivoIni);
	}

}

