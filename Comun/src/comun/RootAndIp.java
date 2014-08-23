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

public class RootAndIp {

	/* Valores por defecto - verificar el conf.ini */
	private static String ip="";
	private static int port= 43210;
	private static String db="";
	private static String path_manual = "";
	
	public static void setConf(String nameFile) {
		if ((nameFile == null)||(nameFile.trim().length() == 0)){
			nameFile = "conf.ini";
		}
		IniFile ini = new IniFile(nameFile);
		ip = ini.getParameters("ip");
		db = ini.getParameters("db");
		port = Integer.parseInt(ini.getParameters("port"));
		path_manual = ini.getParameters("path_manual");
	}

	public static String getIp() {
		return ip;
	}

	public static void setIp(String ip) {
		RootAndIp.ip = ip;
	}

	public static String getDb() {
		return db;
	}

	public static void setDb(String db) {
		RootAndIp.db = db;
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		RootAndIp.port = port;
	}

	public static String getPath_manual() {
		return path_manual;
	}

	public static void setPath_manual(String path_manual) {
		RootAndIp.path_manual = path_manual;
	}
	
}

