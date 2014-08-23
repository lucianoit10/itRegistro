package assembler;

import comun.DTO.PersonaDTO;

import persistencia.AccesoBD;
import persistencia.dominio.Persona;

public class PersonaAssembler {

	private AccesoBD accesoBD;

	public PersonaAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}
	public PersonaDTO getPersonaDTO(Persona persona) {
		PersonaDTO personaDTO = new PersonaDTO(persona.getNombre(), persona.getApellido(), persona.getRegistro(), persona.getDni_cuil_cuit(), persona.getTel_contacto(),persona.getDireccion());
		personaDTO.setEliminado(persona.getEliminado());
		personaDTO.setId(persona.getId());
		return personaDTO;
	}

	public Persona getPersona(PersonaDTO personaDTO) {
		Persona persona;
		if (personaDTO.getId()!=null){
			persona =(Persona) accesoBD.buscarPorId(Persona.class, personaDTO.getId());
			persona.setNombre(personaDTO.getNombre());
			persona.setApellido(personaDTO.getApellido());
			persona.setRegistro(personaDTO.getRegistro());
			persona.setDni_cuil_cuit(personaDTO.getDni_cuil_cuit());
			persona.setTel_contacto(personaDTO.getTel_contacto());
			persona.setDireccion(personaDTO.getDireccion());
			persona.setEliminado(personaDTO.getEliminado());
		}
		else {
			persona = new Persona(personaDTO.getNombre(), personaDTO.getApellido(), personaDTO.getRegistro(), personaDTO.getDni_cuil_cuit(), personaDTO.getTel_contacto(),personaDTO.getDireccion());
			persona.setEliminado(personaDTO.getEliminado());
		}
		return persona;
	}
	
}
