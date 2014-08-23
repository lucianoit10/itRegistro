package persistencia.dominio;

public class UsuarioOrganizacion {

	protected Long id;
	protected Usuario usuario;
	protected Organizacion org;
	
	public UsuarioOrganizacion(Usuario usuario, Organizacion org) {
		this.usuario = usuario;
		this.org = org;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Organizacion getOrg() {
		return org;
	}
	public void setOrg(Organizacion org) {
		this.org = org;
	}
}
