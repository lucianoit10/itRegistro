package assembler;

import comun.DTO.UsuarioDTO;

import persistencia.AccesoBD;
import persistencia.dominio.Usuario;

public class UsuarioAssembler {

	private AccesoBD accesoBD;

	public UsuarioAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}
	
	public UsuarioDTO getUsuarioDTO(Usuario user) {
		UsuarioDTO usuarioDTO = new UsuarioDTO( user.getNombre_usuario(),user.getContrasena());
		usuarioDTO.setCambiar_contrasena(user.getCambiar_contrasena());
		usuarioDTO.setPermiso(user.getPermiso());
		usuarioDTO.setId(user.getId());
		return usuarioDTO;
	}

	public Usuario getUsuario(UsuarioDTO usuarioDTO) {
		Usuario user;
		if (usuarioDTO.getId()!=null){
			user =(Usuario) accesoBD.buscarPorId(Usuario.class, usuarioDTO.getId());
			user.setNombre_usuario(usuarioDTO.getNombre_usuario());
			user.setContrasena(usuarioDTO.getContrasena());
			user.setCambiar_contrasena(usuarioDTO.getCambiar_contrasena());
			user.setPermiso(usuarioDTO.getPermiso());
		}
		else {
			user = new Usuario(usuarioDTO.getNombre_usuario(),usuarioDTO.getContrasena());
			user.setCambiar_contrasena(usuarioDTO.getCambiar_contrasena());
			user.setPermiso(usuarioDTO.getPermiso());
		}
		return user;
	}
	
}
