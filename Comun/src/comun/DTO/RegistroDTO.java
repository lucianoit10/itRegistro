package comun.DTO;

import java.io.Serializable;
import java.sql.Timestamp;

public class RegistroDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected Long id;
	protected PersonalOrganizacionDTO personalOrg;
	protected LugarDTO lugar;
	protected Timestamp ingreso;
	protected Timestamp salida;
	protected String descripcion;
	
	public RegistroDTO(PersonalOrganizacionDTO persOrg, LugarDTO lugar,Timestamp ingreso,String desc) {
		this.personalOrg = persOrg;
		this.lugar = lugar;
		this.ingreso = ingreso;
		descripcion = desc;
	}

	public PersonalOrganizacionDTO getPersonalOrg() {
		return personalOrg;
	}

	public void setPersonalOrg(PersonalOrganizacionDTO personalOrg) {
		this.personalOrg = personalOrg;
	}
	
	public LugarDTO getLugar() {
		return lugar;
	}

	public void setLugar(LugarDTO lugar) {
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
