package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import equipo.*;
import personas.*;

import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.JButton;


public class Main {

    private JFrame frame;
    private JTextField txtNombre;
    private JTextField txtRating;
    private JComboBox<String> cbRoles = new JComboBox<>();
    private JComboBox<Persona> cbIncompatibilidad1 = new JComboBox<>();
    private JComboBox<Persona> cbIncompatibilidad2 = new JComboBox<>();
    private JButton btnAgregarPersona;
    private JTextField txtNuevoRol;
    private ManejoEquipo manejoEquipo = new ManejoEquipo();
	private Font fuente = new Font("Segoe UI", Font.BOLD, 15);
	private Color colorFondo = new Color(175, 221, 254);
	private Color colorBotones = new Color(75, 105, 131);
	private JTextField textFieldCantidad;
    private JTextArea txtPersonas = new JTextArea();  
	private Simulacion simulacion = null;
	private JProgressBar progressBar;
	
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Main() {
        initialize();
    }
    
    private void limpiarFrame() {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(null);
    }
    
    private void actualizarFrame() {
        frame.revalidate();
        frame.repaint();
    }
    
    
    private void inicializarPanelAgregarRol() {     
        limpiarFrame();
        crearCampoRol();
        crearCampoCantidadPersonas();
        crearBotonAgregarOtroRol();
        crearBotonListoRoles();
        actualizarFrame();
    }

    private void crearCampoRol() {
        JLabel titulo = new JLabel("Creacion de rol:");
        titulo.setBounds(300, 2, 280, 50 );
        titulo.setFont(fuente);
        frame.getContentPane().add(titulo);
        
        JLabel labelRol = new JLabel("¿Qué rol deseas crear?:");
        labelRol.setBounds(138, 34, 190, 101);
        labelRol.setFont(fuente);
        frame.getContentPane().add(labelRol);

        txtNuevoRol = new JTextField();
        txtNuevoRol.setBounds(396, 42, 242, 84);
        txtNuevoRol.setFont(fuente);
        txtNuevoRol.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(txtNuevoRol);
    }

    private void crearCampoCantidadPersonas() {
        JLabel lblCantidadDePersonas = new JLabel("Cantidad de personas en este rol: ");
        lblCantidadDePersonas.setBounds(76, 129, 280, 101);
        lblCantidadDePersonas.setFont(fuente);
        frame.getContentPane().add(lblCantidadDePersonas);

        textFieldCantidad = new JTextField();
        textFieldCantidad.setBounds(396, 137, 242, 84);
        textFieldCantidad.setFont(fuente);
        textFieldCantidad.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(textFieldCantidad);
    }

    private void crearBotonAgregarOtroRol() {
        JButton btnAgregarOtroRol = new JButton("Agregar rol");
        btnAgregarOtroRol.setBounds(227, 246, 221, 77);
        btnAgregarOtroRol.setForeground(Color.WHITE);
        btnAgregarOtroRol.setFont(fuente);
        btnAgregarOtroRol.setBackground(colorBotones);
        btnAgregarOtroRol.addActionListener(e -> agregarRol());
        frame.getContentPane().add(btnAgregarOtroRol);
    }

    private void crearBotonListoRoles() {
        JButton btnListo = new JButton("Listo");
        btnListo.setBounds(227, 334, 221, 71);
        btnListo.setBackground(colorBotones);
        btnListo.setForeground(Color.WHITE);
        btnListo.setFont(fuente);
        btnListo.addActionListener(e -> {
            if(manejoEquipo.getRoles().size() == 0) {
                JOptionPane.showMessageDialog(frame, "Debes agregar al menos un rol antes de continuar.");
                return;
            }
            inicializarPanelAgregarPersona();
            System.out.println(manejoEquipo.getRoles());
        });
        frame.getContentPane().add(btnListo);
    }
    
    
    
    private void inicializarPanelAgregarPersona() {
        limpiarFrame();
        crearCampoNombre();
        crearCampoRating();
        mostrarCampoRol();
        crearBotonAgregarPersona();
        crearBotonListo();
        actualizarFrame();
    }


    private void crearCampoNombre() {
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(200, 50, 190, 40);
        lblNombre.setFont(fuente);
        frame.getContentPane().add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(400, 50, 190, 40);
        txtNombre.setFont(fuente);
        frame.getContentPane().add(txtNombre);
    }

    private void crearCampoRating() {
        JLabel lblRating = new JLabel("Rating:");
        lblRating.setBounds(200, 120, 190, 40);
        lblRating.setFont(fuente);
        frame.getContentPane().add(lblRating);

        txtRating = new JTextField();
        txtRating.setBounds(400, 120, 190, 40);
        txtRating.setFont(fuente);
        frame.getContentPane().add(txtRating);
    }

    private void mostrarCampoRol() {
        JLabel lblRol = new JLabel("Rol:");
        lblRol.setBounds(200, 190, 190, 40);
        lblRol.setFont(fuente);
        frame.getContentPane().add(lblRol);

        cbRoles.setBounds(400, 190, 190, 40);
        frame.getContentPane().add(cbRoles);
    }

    private void crearBotonAgregarPersona() {
        btnAgregarPersona = new JButton("Agregar Persona");
        btnAgregarPersona.setBounds(227, 260, 221, 50);
        btnAgregarPersona.setForeground(Color.WHITE);
        btnAgregarPersona.setFont(fuente);
        btnAgregarPersona.setBackground(colorBotones);
        btnAgregarPersona.addActionListener(e -> agregarPersona());
        frame.getContentPane().add(btnAgregarPersona);
    }

    private void crearBotonListo() {
        JButton btnListo = new JButton("Listo");
        btnListo.setBounds(227, 320, 221, 50);
        btnListo.setForeground(Color.WHITE);
        btnListo.setFont(fuente);
        btnListo.setBackground(colorBotones);
        btnListo.addActionListener(e -> inicializarPanelIncompatibilidades());
        frame.getContentPane().add(btnListo);
    }

    private void inicializarPanelIncompatibilidades() {
        limpiarFrame();

        cbIncompatibilidad1 = new JComboBox<>(manejoEquipo.getPersonas().toArray(new Persona[0]));
        cbIncompatibilidad2 = new JComboBox<>(manejoEquipo.getPersonas().toArray(new Persona[0]));

        cbIncompatibilidad1.setBounds(80, 120, 250, 40);
        cbIncompatibilidad2.setBounds(400, 120, 250, 40);

        Dimension size = frame.getSize();
        JLabel incompatibilidadTitulo = new JLabel("Seleccione las personas incompatibles");
        int width = incompatibilidadTitulo.getPreferredSize().width;
        incompatibilidadTitulo.setBounds((size.width - width) / 2, 20, 300, 40);
        incompatibilidadTitulo.setFont(fuente);
        
        JLabel labelIncompatible1 = new JLabel("Persona 1:"); 
        labelIncompatible1.setBounds(80, 80, 190, 40);
        labelIncompatible1.setFont(fuente);
        
        JLabel labelIncompatible2 = new JLabel("Persona 2:"); 
        labelIncompatible2.setBounds(400, 80, 190, 40);
        labelIncompatible2.setFont(fuente);

        JButton btnCargarIncompatibilidad = new JButton("Agregar Incompatibilidad");
        btnCargarIncompatibilidad.addActionListener(e -> agregarIncompatibilidad());
        btnCargarIncompatibilidad.setBounds((size.width - width) / 2, 250, 221, 50);
        btnCargarIncompatibilidad.setBackground(colorBotones);
        btnCargarIncompatibilidad.setForeground(Color.WHITE);
        btnCargarIncompatibilidad.setFont(fuente);
        
        JButton btnSolucion = new JButton("Visualizar mejor equipo");
        btnSolucion.setBounds((size.width - width) / 2, 320, 221, 50);
        btnSolucion.addActionListener(e -> inicializarPanelMostrarSolucion());
        btnSolucion.setBackground(colorBotones);
        btnSolucion.setForeground(Color.WHITE);
        btnSolucion.setFont(fuente);

        frame.add(incompatibilidadTitulo);
        frame.add(labelIncompatible1);
        frame.add(labelIncompatible2);
        frame.add(cbIncompatibilidad1);
        frame.add(cbIncompatibilidad2);
        frame.add(btnCargarIncompatibilidad);
        frame.add(btnSolucion);
        actualizarFrame();
    }

    private void agregarIncompatibilidad() {
        Persona persona1 = (Persona) cbIncompatibilidad1.getSelectedItem();
        Persona persona2 = (Persona) cbIncompatibilidad2.getSelectedItem();
        if (persona1 != null && persona2 != null) {
            if (!persona1.equals(persona2)) {
                manejoEquipo.agregarIncompatibilidad(new Incompatibilidad(persona1, persona2));
            	JOptionPane.showMessageDialog(null, "¡Se agregó la incompatibilidad!");
            } else {
                JOptionPane.showMessageDialog(frame, "Las personas deben ser diferentes", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
//    Mostrar Solucion
    
    private void inicializarPanelMostrarSolucion() {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(null);
        
        JButton btnSolucion = new JButton("Mostrar Equipo Completo");
        btnSolucion.setBounds(227, 220, 221, 50);
        btnSolucion.setForeground(Color.WHITE);
        btnSolucion.setFont(fuente);
        btnSolucion.setBackground(colorBotones);
        btnSolucion.addActionListener(e -> cargarSolucion());
        frame.getContentPane().add(btnSolucion);
        
        actualizarFrame();
    }
    
    private void cargarSolucion() {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.weightx = 1;
        gbc.weighty = 1;

        JLabel labelPersonas = new JLabel("Trabajadores");
        labelPersonas.setFont(fuente);
        labelPersonas.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel labelSolucion = new JLabel("El mejor equipo es");
        labelSolucion.setFont(fuente);
        labelSolucion.setHorizontalAlignment(JLabel.CENTER); 
        
        JLabel labelIncompatibilidades = new JLabel("Incompatibilidades");
        labelIncompatibilidades.setFont(fuente);
        labelIncompatibilidades.setHorizontalAlignment(JLabel.CENTER); 

        JPanel panelPersonas = new JPanel();
        panelPersonas.setLayout(new BoxLayout(panelPersonas, BoxLayout.Y_AXIS));
        panelPersonas.setBackground(colorBotones);

        JPanel panelSolucion = new JPanel(new GridBagLayout());
        panelSolucion.setBackground(colorBotones);

        JPanel panelIncompatibilidades = new JPanel();
        panelIncompatibilidades.setLayout(new BoxLayout(panelIncompatibilidades, BoxLayout.Y_AXIS));
        panelIncompatibilidades.setBackground(colorBotones);

        cargarPanelPersonas(panelPersonas);
        cargarPanelIncompatibilidades(panelIncompatibilidades);

        JScrollPane scrollPersonas = new JScrollPane(panelPersonas);
        JScrollPane scrollSolucion = new JScrollPane(panelSolucion);
        JScrollPane scrollIncompatibilidades = new JScrollPane(panelIncompatibilidades);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.getContentPane().add(labelPersonas, gbc);

        gbc.gridx = 1;
        frame.getContentPane().add(labelSolucion, gbc);

        gbc.gridx = 2;
        frame.getContentPane().add(labelIncompatibilidades, gbc);

        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        frame.getContentPane().add(scrollPersonas, gbc);

        gbc.gridx = 1;
        progressBar = new JProgressBar();
        simulacion = new Simulacion(panelSolucion, progressBar, 0, manejoEquipo);
        simulacion.execute();
        frame.getContentPane().add(scrollSolucion, gbc);

        gbc.gridx = 2;
        frame.getContentPane().add(scrollIncompatibilidades, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;  
        gbc.weighty = 0;
        frame.getContentPane().add(progressBar, gbc);
        actualizarFrame();
    }
    
    private void cargarPanelPersonas(JPanel panelPersonas) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (Persona persona : manejoEquipo.getPersonas()) {
            JLabel labelPersona = new JLabel(persona.toString(), SwingConstants.CENTER);
            labelPersona.setOpaque(true);
            labelPersona.setBackground(colorBotones);  
            labelPersona.setForeground(Color.WHITE);
            labelPersona.setFont(fuente);
            panelPersonas.add(labelPersona, gbc);  
        }
        panelPersonas.revalidate();
    }

    private void cargarPanelIncompatibilidades(JPanel panelIncompatibilidades) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (Incompatibilidad incompatibilidad : manejoEquipo.getIncompatibilidades()) {
            Persona persona1 = incompatibilidad.getPersona1();
            Persona persona2 = incompatibilidad.getPersona2();
            JLabel labelIncompatibilidad = new JLabel(persona1.getNombre() + "-" + persona1.getRol() + " <-> " + 
                                                      persona2.getNombre() + "-" + persona2.getRol(), SwingConstants.CENTER);
            labelIncompatibilidad.setOpaque(true);
            labelIncompatibilidad.setBackground(colorBotones);  
            labelIncompatibilidad.setForeground(Color.WHITE);
            labelIncompatibilidad.setFont(fuente);
            panelIncompatibilidades.add(labelIncompatibilidad, gbc);  
        }
        panelIncompatibilidades.revalidate();
    }

    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 754, 476);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(colorFondo);
        inicializarPanelAgregarRol();

    }

    private void agregarPersona() {
        String rol = (String) cbRoles.getSelectedItem();
        String nombre = txtNombre.getText();
        int rating;
        try {
            rating = Integer.parseInt(txtRating.getText());
            if(rating > 5 || rating < 0) {
            	JOptionPane.showMessageDialog(frame, "Rating debe ser mayor a 0 o menor a 5");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Rating debe ser un número");
            return;
        }
        try {
            Persona persona = new Persona(nombre, rol, rating);
            manejoEquipo.agregarPersona(persona);
            txtPersonas.append(persona.toString() + "\n"); 
            cbIncompatibilidad1.addItem(persona);
            cbIncompatibilidad2.addItem(persona);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "La cantidad no es un numero valido");
            return;
        }
        txtNombre.setText("");
        txtRating.setText("");
    }
    
    
    private void agregarRol() {
        String nuevoRol = txtNuevoRol.getText();
        String cantidad = textFieldCantidad.getText();
        if(nuevoRol.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "El rol no puede estar vacío");
            return;
        }
        if(Integer.parseInt(cantidad) < 0) {
            JOptionPane.showMessageDialog(frame, "La cantidad de personas necesaria debe ser mayor a 0");
            return;
        }
        try {
            manejoEquipo.agregarRol(new Rol(nuevoRol, Integer.parseInt(cantidad)));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "La cantidad no es un numero valido");
            return;
        }
        txtNuevoRol.setText("");
        textFieldCantidad.setText("");
        cbRoles.addItem(nuevoRol);
    }
}