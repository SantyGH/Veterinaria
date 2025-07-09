package principal;

import controlador.Coordinador;
import vista.VentanaPrincipal;

public class Principal {
    public static void main(String[] args) {
        Coordinador coordinador = new Coordinador();

        VentanaPrincipal vp = new VentanaPrincipal();
        vp.setCoordinador(coordinador);
        vp.setVisible(true);
    }
}
