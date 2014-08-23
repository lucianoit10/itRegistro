package assembler;

import comun.DTO.UsuarioOrganizacionDTO;

import persistencia.AccesoBD;
import persistencia.dominio.UsuarioOrganizacion;

public class UsuarioOrganizacionAssembler {

	AccesoBD accesoBD;
	UsuarioAssembler userAssembler;
	OrganizacionAssembler orgAssembler;
	
	public UsuarioOrganizacionAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
		userAssembler = new UsuarioAssembler(accesoBD);
		orgAssembler  = new OrganizacionAssembler(accesoBD);
	}
	
	public UsuarioOrganizacionDTO getUsuarioOrganizacionDTO (UsuarioOrganizacion userOrg){
		UsuarioOrganizacionDTO uoDTO = new UsuarioOrganizacionDTO(userAssembler.getUsuarioDTO(userOrg.getUsuario()), orgAssembler.getOrganizacionDTO(userOrg.getOrg()));
		uoDTO.setId(userOrg.getId());
		return uoDTO;
	}

	public UsuarioOrganizacion getUsuarioOrganizacion(UsuarioOrganizacionDTO userOrg) {
		UsuarioOrganizacion uo;
		if (userOrg.getId()!=null) {
			uo = (UsuarioOrganizacion) accesoBD.buscarPorId(UsuarioOrganizacion.class, userOrg.getId());
			uo.setUsuario(userAssembler.getUsuario(userOrg.getUsuario()));
			uo.setOrg(orgAssembler.getOrganizacion(userOrg.getOrg()));
		} else {
			uo = new UsuarioOrganizacion(userAssembler.getUsuario(userOrg.getUsuario()), orgAssembler.getOrganizacion(userOrg.getOrg()));
		}
		return uo;
	}
	
}
