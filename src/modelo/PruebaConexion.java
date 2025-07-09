package modelo;

public class PruebaConexion {
    public static void main(String[] args) {
        ConexionBD conexion = new ConexionBD();
        conexion.getConnection();
    }
}
