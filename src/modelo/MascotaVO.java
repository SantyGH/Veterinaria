package modelo;

public class MascotaVO {
    private String codigo;
    private String nombre;
    private String tipo;
    private String raza;
    private int edad;
    private PersonaVO propietario; // Relación con PersonaVO

    // Constructor vacío
    public MascotaVO() {}

    // Constructor con parámetros
    public MascotaVO(String codigo, String nombre, String tipo, String raza, int edad, PersonaVO propietario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.edad = edad;
        this.propietario = propietario;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public PersonaVO getPropietario() {
        return propietario;
    }

    public void setPropietario(PersonaVO propietario) {
        this.propietario = propietario;
    }

    // Para mostrar la información fácilmente
    @Override
    public String toString() {
        return "Código: " + codigo +
               "\nNombre: " + nombre +
               "\nTipo: " + tipo +
               "\nRaza: " + raza +
               "\nEdad: " + edad +
               "\n\n--- Datos del Propietario ---\n" +
               propietario.toString();
    }
}
