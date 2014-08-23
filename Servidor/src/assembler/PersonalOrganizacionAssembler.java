package assembler;

import comun.DTO.PersonalOrganizacionDTO;

import persistencia.AccesoBD;
import persistencia.dominio.PersonalOrganizacion;

public class PersonalOrganizacionAssembler {

	AccesoBD accesoBD;
	PersonaAssembler personalAssembler;
	OrganizacionAssembler orgAssembler;
	
	public PersonalOrganizacionAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
		personalAssembler = new PersonaAssembler(accesoBD);
		orgAssembler = new OrganizacionAssembler(accesoBD);
	}
	
	public PersonalOrganizacionDTO getPersonalOrganizacionDTO (PersonalOrganizacion persOrg){
		PersonalOrganizacionDTO poDTO = new PersonalOrganizacionDTO(personalAssembler.getPersonaDTO(persOrg.getPersona()), orgAssembler.getOrganizacionDTO(persOrg.getOrg()), persOrg.getIngreso());
		poDTO.setSalida(persOrg.getSalida());
		poDTO.setMotivo(persOrg.getMotivo());
		poDTO.setEliminado(persOrg.isEliminado());
		poDTO.setId(persOrg.getId());
		return poDTO;
	}

	public PersonalOrganizacion getPersonalOrganizacion(PersonalOrganizacionDTO personalOrg) {
		PersonalOrganizacion po;
		if (personalOrg.getId()!=null) {
			po = (PersonalOrganizacion) accesoBD.buscarPorId(PersonalOrganizacion.class, personalOrg.getId());
			po.setPersona(personalAssembler.getPersona(personalOrg.getPersona()));
			po.setOrg(orgAssembler.getOrganizacion(personalOrg.getOrg()));
			po.setIngreso(personalOrg.getIngreso());
			po.setSalida(personalOrg.getSalida());
			po.setMotivo(personalOrg.getMotivo());
			po.setEliminado(personalOrg.isEliminado());
		} else {
			po = new PersonalOrganizacion(personalAssembler.getPersona(personalOrg.getPersona()), orgAssembler.getOrganizacion(personalOrg.getOrg()),personalOrg.getIngreso());
			po.setSalida(personalOrg.getSalida());
			po.setMotivo(personalOrg.getMotivo());
			po.setEliminado(personalOrg.isEliminado());
		}
		return po;
	}
	
}
