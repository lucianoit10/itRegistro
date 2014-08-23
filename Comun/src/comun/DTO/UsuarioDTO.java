package comun.DTO;

import java.io.Serializable;

public class UsuarioDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected Long id;
	protected String nombre_usuario;
	protected String contrasena;
	protected boolean cambiar_contrasena = false;
	protected int permiso = 0;
	
	/* Permisos:
	 * 0 -> usuario de bajos permisos - cliente
	 * 1 ->	admin de clientes - maquinas - copias
	 * 2 -> administrador global
	 * */
	
	public UsuarioDTO(String nombre_usuario,String contrasena) {
		this.nombre_usuario = nombre_usuario;
		this.contrasena = contrasena;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public int getPermiso() {
		return permiso;
	}

	public void setPermiso(int permiso) {
		this.permiso = permiso;
	}

	public boolean getCambiar_contrasena() {
		return cambiar_contrasena;
	}

	public void setCambiar_contrasena(boolean cambiar_contrasena) {
		this.cambiar_contrasena = cambiar_contrasena;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [nombre_usuario=" + nombre_usuario + ", contrasena="
				+ contrasena + ", cambiar_contrasena=" + cambiar_contrasena
				+ ", permiso=" + permiso + ", id=" + id + "]";
	}
	
}
