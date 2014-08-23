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

import cliente.MenuPrincipal.MediadorPrincipal;

public class Main extends Thread {  
  
    public static void main(String[] args) throws Exception {
        funcionamiento();
    }
    
	@Override
	public void run (){
		try {
	        funcionamiento();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void funcionamiento() throws Exception{
		ClienteConection cc = new ClienteConection();
		cc.iniciar();
		MediadorPrincipal mp = new MediadorPrincipal(cc);
		mp.cargarImagenes();
	}
}