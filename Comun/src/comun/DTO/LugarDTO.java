package comun.DTO;

import java.io.Serializable;

public class LugarDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	protected Long id;
	protected String nombre;
	protected String direccion;
	protected boolean eliminado = false;
	
	public LugarDTO(String nombre, String direccion) {
		this.nombre = nombre;
		this.direccion = direccion;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public boolean getEliminado() {
		return eliminado;
	}
	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", direccion="
				+ direccion  + ", eliminado=" + eliminado + "]";
	}
}
