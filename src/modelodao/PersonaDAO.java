package modelodao;

import modelo.ConexionBD;
import modelo.PersonaVO;

import java.sql.*;
import java.util.ArrayList;

public class PersonaDAO {
    private Connection conexion;

    public PersonaDAO() {
        ConexionBD conexionBD = new ConexionBD();
        conexion = conexionBD.getConnection();
    }

    public boolean registrarPersona(PersonaVO persona) {
        String sql = "INSERT INTO personas (documento, nombre, telefono, direccion) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, persona.getDocumento());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getTelefono());
            ps.setString(4, persona.getDireccion());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Error al registrar persona: " + e.getMessage());
            return false;
        }
    }

    public PersonaVO consultarPersona(String documento) {
        String sql = "SELECT * FROM personas WHERE documento = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, documento);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PersonaVO persona = new PersonaVO();
                persona.setDocumento(rs.getString("documento"));
                persona.setNombre(rs.getString("nombre"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setDireccion(rs.getString("direccion"));
                return persona;
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al consultar persona: " + e.getMessage());
        }
        return null;
    }

    public boolean actualizarPersona(PersonaVO persona) {
        String sql = "UPDATE personas SET nombre = ?, telefono = ?, direccion = ? WHERE documento = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getTelefono());
            ps.setString(3, persona.getDireccion());
            ps.setString(4, persona.getDocumento());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar persona: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPersona(String documento) {
        String sql = "DELETE FROM personas WHERE documento = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, documento);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar persona: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<PersonaVO> listarPersonas() {
        ArrayList<PersonaVO> lista = new ArrayList<>();
        String sql = "SELECT * FROM personas";
        try (Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                PersonaVO persona = new PersonaVO(
                    rs.getString("documento"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
                );
                lista.add(persona);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar personas: " + e.getMessage());
        }
        return lista;
    }
}

