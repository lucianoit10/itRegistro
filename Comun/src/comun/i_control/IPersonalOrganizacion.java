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

import comun.DTO.PersonalOrganizacionDTO;

public interface IPersonalOrganizacion extends Remote{
	
	public Long agregarPersonalOrganizacion(PersonalOrganizacionDTO objeto)throws Exception;
	public void eliminarPersonalOrganizacion(Long id)throws Exception;
	
	public Vector<PersonalOrganizacionDTO> obtenerPersonalesOrganizacion()throws Exception;
	public Vector<PersonalOrganizacionDTO> obtenerPersonalesOrganizacion(String filtro)throws Exception;
	public Vector<PersonalOrganizacionDTO> obtenerPersonalesOrganizacion(String filtro,String orden, String agrupar)throws Exception;
	
	public boolean existePersonalOrganizacion(Long id) throws Exception;
	public boolean existePersonalOrganizacion(Long idPers,Long idOrg) throws Exception;
	
	public PersonalOrganizacionDTO buscarPersonalOrganizacion(Long id) throws Exception;
	public PersonalOrganizacionDTO buscarPersonalOrganizacion(Long idPers,Long idOrg) throws Exception;
	public void modificarPersonalOrganizacion(Long id,	PersonalOrganizacionDTO persmodif) throws Exception;

}
