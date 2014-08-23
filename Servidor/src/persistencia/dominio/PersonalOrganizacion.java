package persistencia.dominio;

import java.sql.Timestamp;

public class PersonalOrganizacion {

	protected Long id;
	protected Persona persona;
	protected Organizacion org;
	protected Timestamp ingreso;
	protected Timestamp salida;
	protected String motivo;
	protected boolean eliminado;
	
	public PersonalOrganizacion(Persona persona, Organizacion org,Timestamp ingreso) {
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
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Organizacion getOrg() {
		return org;
	}
	public void setOrg(Organizacion org) {
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
	public boolean isEliminado() {
		return eliminado;
	}	
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
		
}
