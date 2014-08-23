package persistencia.dominio;

public class Organizacion implements Comparable<Organizacion> {

	protected Long id;
	protected String nombre;
	protected String cuit;
	protected boolean eliminado;
	
	
	public Organizacion(String nombre, String cuit) {
		this.nombre = nombre;
		this.cuit = cuit;
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
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public boolean isEliminado() {
		return eliminado;
	}
	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	@Override
	public int compareTo(Organizacion arg0) {
		return this.getNombre().compareTo(arg0.getNombre());
	}
	
}
