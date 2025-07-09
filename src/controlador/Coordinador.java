package controlador;

import modelodao.PersonaDAO;
import modelodao.MascotaDAO;
import modelo.PersonaVO;
import modelo.MascotaVO;

import java.util.ArrayList;

public class Coordinador {
    private PersonaDAO personaDAO;
    private MascotaDAO mascotaDAO;

    public Coordinador() {
        personaDAO = new PersonaDAO();
        mascotaDAO = new MascotaDAO();
    }

    // --- Métodos Personas ---

    public boolean registrarPersona(PersonaVO persona) {
        return personaDAO.registrarPersona(persona);
    }

    public PersonaVO consultarPersona(String documento) {
        return personaDAO.consultarPersona(documento);
    }

    public boolean actualizarPersona(PersonaVO persona) {
        return personaDAO.actualizarPersona(persona);
    }

    public boolean eliminarPersona(String documento) {
        return personaDAO.eliminarPersona(documento);
    }

    public ArrayList<PersonaVO> listarPersonas() {
        return personaDAO.listarPersonas();
    }

    // --- Métodos Mascotas ---

    public boolean registrarMascota(MascotaVO mascota) {
        return mascotaDAO.registrarMascota(mascota);
    }

    public MascotaVO consultarMascota(int id) {
        return mascotaDAO.consultarMascota(id);
    }

    public boolean actualizarMascota(MascotaVO mascota) {
        return mascotaDAO.actualizarMascota(mascota);
    }

    public boolean eliminarMascota(int id) {
        return mascotaDAO.eliminarMascota(id);
    }

    public ArrayList<MascotaVO> listarMascotas() {
        return mascotaDAO.listarMascotas();
    }
}
