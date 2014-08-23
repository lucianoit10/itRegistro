package assembler;

import comun.DTO.RegistroDTO;

import persistencia.AccesoBD;
import persistencia.dominio.Registro;

public class RegistroAssembler {

	AccesoBD accesoBD;
	LugarAssembler lugarAssembler;
	PersonalOrganizacionAssembler persOrgAssembler;
	
	public RegistroAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
		lugarAssembler = new LugarAssembler(accesoBD);
		persOrgAssembler = new PersonalOrganizacionAssembler(accesoBD);
	}
	
	public RegistroDTO getRegistroDTO (Registro reg){
		RegistroDTO regDTO = new RegistroDTO(	persOrgAssembler.getPersonalOrganizacionDTO(reg.getPersonalOrg()), 
												lugarAssembler.getLugarDTO(reg.getLugar()), reg.getIngreso(),reg.getDescripcion());
		regDTO.setSalida(reg.getSalida());
		regDTO.setId(reg.getId());
		return regDTO;
	}
	
	public Registro getRegistro (RegistroDTO regDTO){
		Registro reg;
		if (regDTO.getId()!=null) {
			reg = (Registro) accesoBD.buscarPorId(Registro.class, regDTO.getId());
			reg.setPersonalOrg(persOrgAssembler.getPersonalOrganizacion(regDTO.getPersonalOrg()));
			reg.setLugar(lugarAssembler.getLugar(regDTO.getLugar()));
			reg.setIngreso(regDTO.getIngreso());
			reg.setSalida(regDTO.getSalida());
			reg.setDescripcion(regDTO.getDescripcion());
		} else {
			reg = new Registro(	persOrgAssembler.getPersonalOrganizacion(regDTO.getPersonalOrg()), 
								lugarAssembler.getLugar(regDTO.getLugar()), regDTO.getIngreso(),regDTO.getDescripcion());
			reg.setSalida(regDTO.getSalida());
		}
		return reg;
	}
}
