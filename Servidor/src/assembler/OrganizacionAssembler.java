package assembler;

import comun.DTO.OrganizacionDTO;

import persistencia.AccesoBD;
import persistencia.dominio.Organizacion;

public class OrganizacionAssembler {

	private AccesoBD accesoBD;

	public OrganizacionAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}
	
	public OrganizacionDTO getOrganizacionDTO (Organizacion org){
		OrganizacionDTO orgDTO = new OrganizacionDTO(org.getNombre(), org.getCuit());
		orgDTO.setEliminado(org.isEliminado());
		orgDTO.setId(org.getId());
		return orgDTO;
	}
	
	public Organizacion getOrganizacion (OrganizacionDTO orgDTO){
		Organizacion org;
		if (orgDTO.getId()!= null) {
			org = (Organizacion) accesoBD.buscarPorId(Organizacion.class, orgDTO.getId());
			org.setNombre(orgDTO.getNombre());
			org.setCuit(orgDTO.getCuit());
			org.setEliminado(orgDTO.isEliminado());
		} else {
			org = new Organizacion(orgDTO.getNombre(), orgDTO.getCuit());
			org.setEliminado(orgDTO.isEliminado());
		}
		return org;
	}
}
