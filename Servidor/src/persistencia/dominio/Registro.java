package persistencia.dominio;

import java.sql.Timestamp;

public class Registro {
	protected Long id;
	protected PersonalOrganizacion personalOrg;
	protected Lugar lugar;
	protected Timestamp ingreso;
	protected Timestamp salida;
	protected String descripcion;
	
	public Registro (PersonalOrganizacion persOrg, Lugar lugar,Timestamp ingreso, String desc) {
		this.personalOrg = persOrg;
		this.lugar = lugar;
		this.ingreso = ingreso;
		descripcion = desc;
	}

	public PersonalOrganizacion getPersonalOrg() {
		return personalOrg;
	}

	public void setPersonalOrg(PersonalOrganizacion personalOrg) {
		this.personalOrg = personalOrg;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
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

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
