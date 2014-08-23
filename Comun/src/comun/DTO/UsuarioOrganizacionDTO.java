package comun.DTO;

import java.io.Serializable;

public class UsuarioOrganizacionDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	protected Long id;
	protected UsuarioDTO usuario;
	protected OrganizacionDTO org;
	
	public UsuarioOrganizacionDTO(UsuarioDTO usuario, OrganizacionDTO org) {
		this.usuario = usuario;
		this.org = org;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	public OrganizacionDTO getOrg() {
		return org;
	}
	public void setOrg(OrganizacionDTO org) {
		this.org = org;
	}
}
