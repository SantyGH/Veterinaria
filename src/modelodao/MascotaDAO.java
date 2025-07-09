package modelodao;

import modelo.ConexionBD;
import modelo.MascotaVO;
import modelo.PersonaVO;

import java.sql.*;
import java.util.ArrayList;

public class MascotaDAO {
    private Connection conexion;

    public MascotaDAO() {
        ConexionBD conexionBD = new ConexionBD();
        conexion = conexionBD.getConnection();
    }

    public boolean registrarMascota(MascotaVO mascota) {
        PersonaVO duenio = consultarPersona(mascota.getPropietario().getDocumento());
        if (duenio == null) {
            System.out.println("⚠️ El dueño no está registrado en la base de datos.");
            return false;
        }

        String sql = "INSERT INTO mascotas (documento, nombreMascota, especie, raza, edad) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, mascota.getPropietario().getDocumento());
            ps.setString(2, mascota.getNombre());
            ps.setString(3, mascota.getTipo());
            ps.setString(4, mascota.getRaza());
            ps.setInt(5, mascota.getEdad());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Error al registrar mascota: " + e.getMessage());
            return false;
        }
    }

    public MascotaVO consultarMascota(int id) {
        String sql = "SELECT * FROM mascotas WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PersonaVO duenio = consultarPersona(rs.getString("documento"));

                MascotaVO mascota = new MascotaVO();
                mascota.setCodigo(String.valueOf(rs.getInt("id")));
                mascota.setNombre(rs.getString("nombreMascota"));
                mascota.setTipo(rs.getString("especie"));
                mascota.setRaza(rs.getString("raza"));
                mascota.setEdad(rs.getInt("edad"));
                mascota.setPropietario(duenio);

                return mascota;
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al consultar mascota: " + e.getMessage());
        }
        return null;
    }

    public boolean actualizarMascota(MascotaVO mascota) {
        String sql = "UPDATE mascotas SET documento = ?, nombreMascota = ?, especie = ?, raza = ?, edad = ? WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, mascota.getPropietario().getDocumento());
            ps.setString(2, mascota.getNombre());
            ps.setString(3, mascota.getTipo());
            ps.setString(4, mascota.getRaza());
            ps.setInt(5, mascota.getEdad());
            ps.setInt(6, Integer.parseInt(mascota.getCodigo()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar mascota: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarMascota(int id) {
        String sql = "DELETE FROM mascotas WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar mascota: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<MascotaVO> listarMascotas() {
        ArrayList<MascotaVO> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascotas";
        try (Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                PersonaVO duenio = consultarPersona(rs.getString("documento"));

                MascotaVO mascota = new MascotaVO(
                    String.valueOf(rs.getInt("id")),
                    rs.getString("nombreMascota"),
                    rs.getString("especie"),
                    rs.getString("raza"),
                    rs.getInt("edad"),
                    duenio
                );
                lista.add(mascota);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al listar mascotas: " + e.getMessage());
        }
        return lista;
    }

    // Consulta interna del dueño
    private PersonaVO consultarPersona(String documento) {
        String sql = "SELECT * FROM personas WHERE documento = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, documento);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new PersonaVO(
                    rs.getString("documento"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al consultar dueño: " + e.getMessage());
        }
        return null;
    }
}
