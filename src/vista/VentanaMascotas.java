package vista;

import controlador.Coordinador;
import modelo.MascotaVO;
import modelo.PersonaVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaMascotas extends JFrame implements ActionListener {
    private JTextField campoId, campoNombre, campoEspecie, campoRaza, campoEdad, campoDocumentoDueño;
    private JLabel advertenciaNombre, advertenciaEspecie, advertenciaRaza, advertenciaEdad, advertenciaDocumentoDueño;
    private JTextArea areaResultado;
    private JButton btnRegistrar, btnConsultar, btnActualizar, btnEliminar, btnListar;
    private Coordinador coordinador;

    public VentanaMascotas() {
        setTitle("Gestión de Mascotas");
        setSize(550, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Imagen de fondo escalada
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("fondo-mascotas2.jpg"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(550, 500, Image.SCALE_SMOOTH);
        ImageIcon fondo = new ImageIcon(imagenEscalada);
        JLabel fondoLabel = new JLabel(fondo);
        fondoLabel.setBounds(0, 0, 550, 500);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(getSize());

        JPanel panelCampos = new JPanel(new GridLayout(11, 2, 10, 5));
        panelCampos.setBorder(BorderFactory.createTitledBorder("Datos de la Mascota"));
        panelCampos.setOpaque(false);

        campoId = new JTextField();
        campoNombre = new JTextField();
        campoEspecie = new JTextField();
        campoRaza = new JTextField();
        campoEdad = new JTextField();
        campoDocumentoDueño = new JTextField();

        advertenciaNombre = new JLabel();
        advertenciaNombre.setForeground(Color.RED);
        advertenciaEspecie = new JLabel();
        advertenciaEspecie.setForeground(Color.RED);
        advertenciaRaza = new JLabel();
        advertenciaRaza.setForeground(Color.RED);
        advertenciaEdad = new JLabel();
        advertenciaEdad.setForeground(Color.RED);
        advertenciaDocumentoDueño = new JLabel();
        advertenciaDocumentoDueño.setForeground(Color.RED);

        panelCampos.add(new JLabel("ID (solo para consultar/editar):"));
        panelCampos.add(campoId);

        panelCampos.add(new JLabel("Nombre de la mascota:"));
        panelCampos.add(campoNombre);
        panelCampos.add(new JLabel(""));
        panelCampos.add(advertenciaNombre);

        panelCampos.add(new JLabel("Especie:"));
        panelCampos.add(campoEspecie);
        panelCampos.add(new JLabel(""));
        panelCampos.add(advertenciaEspecie);

        panelCampos.add(new JLabel("Raza:"));
        panelCampos.add(campoRaza);
        panelCampos.add(new JLabel(""));
        panelCampos.add(advertenciaRaza);

        panelCampos.add(new JLabel("Edad:"));
        panelCampos.add(campoEdad);
        panelCampos.add(new JLabel(""));
        panelCampos.add(advertenciaEdad);

        panelCampos.add(new JLabel("Documento del dueño:"));
        panelCampos.add(campoDocumentoDueño);
        panelCampos.add(new JLabel(""));
        panelCampos.add(advertenciaDocumentoDueño);

        JPanel panelBotones = new JPanel(new GridLayout(1, 5, 5, 5));
        btnRegistrar = new JButton("Registrar");
        btnConsultar = new JButton("Consultar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnListar = new JButton("Listar");

        btnRegistrar.addActionListener(this);
        btnConsultar.addActionListener(this);
        btnActualizar.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnListar.addActionListener(this);

        panelBotones.setOpaque(false);
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setOpaque(false);
        JScrollPane scroll = new JScrollPane(areaResultado);
        scroll.setBorder(BorderFactory.createTitledBorder("Resultado"));
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        panelCampos.setBounds(30, 10, 490, 280);
        panelBotones.setBounds(30, 300, 490, 50);
        scroll.setBounds(30, 360, 490, 100);

        layeredPane.add(fondoLabel, new Integer(0));
        layeredPane.add(panelCampos, new Integer(1));
        layeredPane.add(panelBotones, new Integer(1));
        layeredPane.add(scroll, new Integer(1));

        setContentPane(layeredPane);
    }

    public void setCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String idTexto = campoId.getText().trim();

        if (e.getSource() == btnRegistrar) {
            advertenciaNombre.setText("");
            advertenciaEspecie.setText("");
            advertenciaRaza.setText("");
            advertenciaEdad.setText("");
            advertenciaDocumentoDueño.setText("");

            boolean validado = true;
            if (campoNombre.getText().isEmpty()) {
                advertenciaNombre.setText("Nombre obligatorio.");
                validado = false;
            }
            if (campoEspecie.getText().isEmpty()) {
                advertenciaEspecie.setText("Especie obligatoria.");
                validado = false;
            }
            if (campoRaza.getText().isEmpty()) {
                advertenciaRaza.setText("Raza obligatoria.");
                validado = false;
            }
            if (campoEdad.getText().isEmpty()) {
                advertenciaEdad.setText("Edad obligatoria.");
                validado = false;
            } else {
                try {
                    int edad = Integer.parseInt(campoEdad.getText());
                    if (edad < 0) {
                        advertenciaEdad.setText("Edad no puede ser negativa.");
                        validado = false;
                    }
                } catch (NumberFormatException ex) {
                    advertenciaEdad.setText("Edad debe ser numérica.");
                    validado = false;
                }
            }
            if (campoDocumentoDueño.getText().isEmpty()) {
                advertenciaDocumentoDueño.setText("Documento del dueño obligatorio.");
                validado = false;
            }

            if (!validado) {
                areaResultado.setText("❌ Corrige los campos marcados.");
                return;
            }

            PersonaVO propietario = new PersonaVO();
            propietario.setDocumento(campoDocumentoDueño.getText());

            MascotaVO mascota = new MascotaVO(
                null,
                campoNombre.getText(),
                campoEspecie.getText(),
                campoRaza.getText(),
                Integer.parseInt(campoEdad.getText()),
                propietario
            );
            boolean exito = coordinador.registrarMascota(mascota);
            areaResultado.setText(exito ? "✅ Mascota registrada." : "❌ Error al registrar (¿Dueño no registrado?).");

        } else if (e.getSource() == btnConsultar) {
            try {
                int id = Integer.parseInt(idTexto);
                MascotaVO mascota = coordinador.consultarMascota(id);
                if (mascota != null) {
                    campoNombre.setText(mascota.getNombre());
                    campoEspecie.setText(mascota.getTipo());
                    campoRaza.setText(mascota.getRaza());
                    campoEdad.setText(String.valueOf(mascota.getEdad()));
                    campoDocumentoDueño.setText(mascota.getPropietario().getDocumento());
                    areaResultado.setText("🔍 Mascota encontrada:\n\n" + mascota);
                } else {
                    areaResultado.setText("⚠️ Mascota no encontrada.");
                }
            } catch (NumberFormatException ex) {
                areaResultado.setText("❌ ID inválido.");
            }

        } else if (e.getSource() == btnActualizar) {
            try {
                int id = Integer.parseInt(idTexto);
                PersonaVO propietario = new PersonaVO();
                propietario.setDocumento(campoDocumentoDueño.getText());

                MascotaVO mascota = new MascotaVO(
                    String.valueOf(id),
                    campoNombre.getText(),
                    campoEspecie.getText(),
                    campoRaza.getText(),
                    Integer.parseInt(campoEdad.getText()),
                    propietario
                );
                boolean exito = coordinador.actualizarMascota(mascota);
                areaResultado.setText(exito ? "✅ Mascota actualizada." : "❌ Error al actualizar.");
            } catch (NumberFormatException ex) {
                areaResultado.setText("❌ ID inválido.");
            }

        } else if (e.getSource() == btnEliminar) {
            try {
                int id = Integer.parseInt(idTexto);
                boolean exito = coordinador.eliminarMascota(id);
                areaResultado.setText(exito ? "🗑️ Mascota eliminada." : "❌ Error al eliminar.");
            } catch (NumberFormatException ex) {
                areaResultado.setText("❌ ID inválido.");
            }

        } else if (e.getSource() == btnListar) {
            ArrayList<MascotaVO> mascotas = coordinador.listarMascotas();
            if (mascotas.isEmpty()) {
                areaResultado.setText("⚠️ No hay mascotas registradas.");
            } else {
                StringBuilder resultado = new StringBuilder("📋 Lista de Mascotas:\n\n");
                for (MascotaVO m : mascotas) {
                    resultado.append(m).append("\n\n");
                }
                areaResultado.setText(resultado.toString());
            }
        }
    }
}