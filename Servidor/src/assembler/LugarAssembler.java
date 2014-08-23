package assembler;

import comun.DTO.LugarDTO;

import persistencia.AccesoBD;
import persistencia.dominio.Lugar;

public class LugarAssembler {

	private AccesoBD accesoBD;

	public LugarAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public LugarDTO getLugarDTO (Lugar lugar){
		LugarDTO lugarDTO = new LugarDTO(lugar.getNombre(), lugar.getDireccion());
		lugarDTO.setEliminado(lugar.getEliminado());
		lugarDTO.setId(lugar.getId());
		return lugarDTO;
	}
	
	public Lugar getLugar (LugarDTO lugarDTO){
		Lugar lugar;
		if (lugarDTO.getId()!=null) {
			lugar = (Lugar) accesoBD.buscarPorId(Lugar.class, lugarDTO.getId());
			lugar.setNombre(lugarDTO.getNombre());
			lugar.setDireccion(lugarDTO.getDireccion());
			lugar.setEliminado(lugarDTO.getEliminado());
		} else {
			lugar = new Lugar(lugarDTO.getNombre(), lugarDTO.getDireccion());
			lugar.setEliminado(lugarDTO.getEliminado());
		}
		return lugar;
	}
}
