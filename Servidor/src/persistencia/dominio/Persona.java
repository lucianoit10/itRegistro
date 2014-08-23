package persistencia.dominio;

public class Persona implements Comparable<Persona>{
	protected Long id;
	protected String nombre;		//obligatorio
	protected String apellido;
	protected String dni_cuil_cuit;	//obligatorio
	protected String tel_contacto;	//obligatorio
	protected String direccion;
	protected String registro;	
	protected boolean eliminado;		
	
		
	public Persona(String nombre, String apellido,String registro, String dni_cuil_cuit, String tel_contacto, String direccion) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.registro = registro;
		this.tel_contacto= tel_contacto;
		this.dni_cuil_cuit = dni_cuil_cuit;
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getTel_contacto() {
		return tel_contacto;
	}

	public void setTel_contacto(String tel_contacto) {
		this.tel_contacto = tel_contacto;
	}

	public String getDni_cuil_cuit() {
		return dni_cuil_cuit;
	}

	public void setDni_cuil_cuit(String dni_cuil_cuit) {
		this.dni_cuil_cuit = dni_cuil_cuit;
	}

	public boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}	

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", apellido="
				+ apellido + ", dni_cuil_cuit=" + dni_cuil_cuit
				+ ", tel_contacto=" + tel_contacto + ", direccion=" + direccion
				+ ", registro=" + registro + ", eliminado=" + eliminado + "]";
	}

	@Override
	public int compareTo(Persona arg0) {
		int res = this.getApellido().compareTo(arg0.getApellido());
		if (res==0)res = this.getNombre().compareTo(arg0.getNombre());
		return res;
	}
	
}