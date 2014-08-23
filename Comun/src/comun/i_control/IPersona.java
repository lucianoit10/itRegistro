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
package comun.i_control;

import java.rmi.Remote;
import java.util.Vector;

import comun.DTO.PersonaDTO;

public interface IPersona extends Remote{
	
	public Long agregarPersona(PersonaDTO objeto)throws Exception;
	public void eliminarPersona(Long id)throws Exception;
	public void modificarPersona(Long id,PersonaDTO modificado)throws Exception;
	
	public Vector<PersonaDTO> obtenerPersonas()throws Exception;
	public Vector<PersonaDTO> obtenerPersonas(String filtro)throws Exception;
	public Vector<PersonaDTO> obtenerPersonas(String filtro,String orden, String agrupar)throws Exception;
	
	public boolean existePersona(Long id) throws Exception;
	
	public PersonaDTO buscarPersona(Long id) throws Exception;
}
