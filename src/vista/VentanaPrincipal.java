package vista;

import controlador.Coordinador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame implements ActionListener {

    private JButton botonPersonas, botonMascotas;
    private Coordinador coordinador;

    public VentanaPrincipal() {
        setTitle("Veterinaria ABC");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1, 10, 10));

        botonPersonas = new JButton("Gestionar Personas")	;
        botonMascotas = new JButton("Gestionar Mascotas");

        botonPersonas.addActionListener(this);
        botonMascotas.addActionListener(this);

        add(botonPersonas);
        add(botonMascotas);
    }

    public void setCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonPersonas) {
            VentanaPersonas vp = new VentanaPersonas();
            vp.setCoordinador(coordinador);
            vp.setVisible(true);
        } else if (e.getSource() == botonMascotas) {	
            VentanaMascotas vm = new VentanaMascotas();
            vm.setCoordinador(coordinador);
            vm.setVisible(true);
        }
    }
}
