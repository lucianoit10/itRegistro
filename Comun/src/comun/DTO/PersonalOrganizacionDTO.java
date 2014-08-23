package comun.DTO;

import java.io.Serializable;
import java.sql.Timestamp;

public class PersonalOrganizacionDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	protected Long id;
	protected PersonaDTO persona;
	protected OrganizacionDTO org;
	protected Timestamp ingreso;
	protected Timestamp salida;
	protected String motivo;
	protected boolean eliminado;
	
	public PersonalOrganizacionDTO(PersonaDTO persona, OrganizacionDTO org,Timestamp ingreso) {
		this.persona = persona;
		this.org = org;
		this.ingreso = ingreso;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PersonaDTO getPersona() {
		return persona;
	}
	public void setPersona(PersonaDTO persona) {
		this.persona = persona;
	}
	public OrganizacionDTO getOrg() {
		return org;
	}
	public void setOrg(OrganizacionDTO org) {
		this.org = org;
	}
	public Timestamp getIngreso() {
		return ingreso;
	}
	public void setIngreso(Timestamp ingreso) {
		this.ingreso = ingreso;
	}
	public Timestamp getSalida() {
		return salida;
	}
	public void setSalida(Timestamp salida) {
		this.salida = salida;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public boolean isEliminado() {
		return eliminado;
	}
	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	
	
}
