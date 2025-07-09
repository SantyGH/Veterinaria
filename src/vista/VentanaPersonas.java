package vista;

import controlador.Coordinador;
import modelo.PersonaVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaPersonas extends JFrame implements ActionListener {
    private JTextField campoDocumento, campoNombre, campoTelefono, campoDireccion;
    private JLabel advertenciaDocumento, advertenciaNombre, advertenciaTelefono, advertenciaDireccion;
    private JTextArea areaResultado;
    private JButton btnRegistrar, btnConsultar, btnActualizar, btnEliminar, btnListar;
    private Coordinador coordinador;

    public VentanaPersonas() {
        setTitle("Gestión de Personas");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Imagen de fondo
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("fondo-mascotas.jpg"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        ImageIcon fondo = new ImageIcon(imagenEscalada);
        JLabel fondoLabel = new JLabel(fondo);
        
        // Capas
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(getSize());

        // Panel de formulario
        JPanel panelCampos = new JPanel(new GridLayout(8, 2, 10, 5));
        panelCampos.setBorder(BorderFactory.createTitledBorder("Datos de la Persona"));
        panelCampos.setOpaque(false);

        campoDocumento = new JTextField();
        campoNombre = new JTextField();
        campoTelefono = new JTextField();
        campoDireccion = new JTextField();

        advertenciaDocumento = new JLabel();
        advertenciaDocumento.setForeground(Color.RED);
        advertenciaNombre = new JLabel();
        advertenciaNombre.setForeground(Color.RED);
        advertenciaTelefono = new JLabel();
        advertenciaTelefono.setForeground(Color.RED);
        advertenciaDireccion = new JLabel();
        advertenciaDireccion.setForeground(Color.RED);

        panelCampos.add(new JLabel("Documento:"));
        panelCampos.add(campoDocumento);
        panelCampos.add(new JLabel(""));
        panelCampos.add(advertenciaDocumento);

        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(campoNombre);
        panelCampos.add(new JLabel(""));
        panelCampos.add(advertenciaNombre);

        panelCampos.add(new JLabel("Teléfono:"));
        panelCampos.add(campoTelefono);
        panelCampos.add(new JLabel(""));
        panelCampos.add(advertenciaTelefono);

        panelCampos.add(new JLabel("Dirección:"));
        panelCampos.add(campoDireccion);
        panelCampos.add(new JLabel(""));
        panelCampos.add(advertenciaDireccion);

        // Botones
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

        // Layout personalizado con capas
        panelCampos.setBounds(30, 10, 430, 220);
        panelBotones.setBounds(30, 240, 430, 50);
        scroll.setBounds(30, 300, 430, 140);
        fondoLabel.setBounds(0, 0, 500, 500);

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
        String doc = campoDocumento.getText();

        if (e.getSource() == btnRegistrar) {
            boolean validado = true;
            advertenciaDocumento.setText("");
            advertenciaNombre.setText("");
            advertenciaTelefono.setText("");
            advertenciaDireccion.setText("");

            String docTexto = campoDocumento.getText().trim();
            if (!docTexto.matches("\\d{4}")) {
                advertenciaDocumento.setText("Documento inválido: 4 dígitos numéricos.");
                validado = false;
            }
            if (campoNombre.getText().isEmpty()) {
                advertenciaNombre.setText("Nombre obligatorio.");
                validado = false;
            }
            if (campoTelefono.getText().isEmpty()) {
                advertenciaTelefono.setText("Teléfono obligatorio.");
                validado = false;
            }
            if (campoDireccion.getText().isEmpty()) {
                advertenciaDireccion.setText("Dirección obligatoria.");
                validado = false;
            }

            if (!validado) {
                areaResultado.setText("❌ Por favor corrige los campos marcados.");
                return;
            }

            PersonaVO persona = new PersonaVO(doc, campoNombre.getText(), campoTelefono.getText(), campoDireccion.getText());
            boolean exito = coordinador.registrarPersona(persona);
            if (exito) {
                areaResultado.setText("✅ Persona registrada.");
            } else {
                areaResultado.setText("❌ No se pudo registrar. ¿Ya existe una persona con ese documento?");
            }

        } else if (e.getSource() == btnConsultar) {
            PersonaVO persona = coordinador.consultarPersona(doc);
            if (persona != null) {
                campoNombre.setText(persona.getNombre());
                campoTelefono.setText(persona.getTelefono());
                campoDireccion.setText(persona.getDireccion());
                areaResultado.setText("🔍 Persona encontrada:\n\n" + persona);
            } else {
                areaResultado.setText("⚠️ Persona no encontrada.");
            }

        } else if (e.getSource() == btnActualizar) {
            PersonaVO persona = new PersonaVO(doc, campoNombre.getText(), campoTelefono.getText(), campoDireccion.getText());
            boolean exito = coordinador.actualizarPersona(persona);
            areaResultado.setText(exito ? "✅ Persona actualizada." : "❌ Error al actualizar.");

        } else if (e.getSource() == btnEliminar) {
            boolean exito = coordinador.eliminarPersona(doc);
            areaResultado.setText(exito ? "🗑️ Persona eliminada." : "❌ Error al eliminar.");

        } else if (e.getSource() == btnListar) {
            ArrayList<PersonaVO> personas = coordinador.listarPersonas();
            if (personas.isEmpty()) {
                areaResultado.setText("⚠️ No hay personas registradas.");
            } else {
                StringBuilder resultado = new StringBuilder("📋 Lista de Personas:\n\n");
                for (PersonaVO p : personas) {
                    resultado.append(p).append("\n\n");
                }
                areaResultado.setText(resultado.toString());
            }
        }
    }
}