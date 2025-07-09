package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	private String nombreBd="veterinaria";
	private String usuario="root";
	private String password="";
	private String url="jdbc:mysql://localhost:3306/"+nombreBd+"?useUnicode=true&use"
			+ "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
			+ "serverTimezone=UTC";

	Connection conn=null;
	//constructor de la clase
	public ConexionBD(){
		try {
			//obtener el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//obtener la conexion
			conn=DriverManager.getConnection(url,usuario,password);
			if (conn!=null) {
				System.out.println("Conexion Exitosa  a la BD: "+nombreBd);
			}else{
				System.out.println("******************NO SE PUDO CONECTAR "+nombreBd);
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println("ocurre una ClassNotFoundException : "+e.getMessage());
		} catch (SQLException e) {
			System.out.println("ocurre una SQLException: "+e.getMessage());
			System.out.println("Verifique que Mysql esté encendido...");
		}
	}
	public Connection getConnection(){
		return conn;
	}
	public void desconectar(){
		conn=null;
	}
}