package gui;

import javax.swing.*;
import java.awt.event.*;

public class VentanaRegistroVotante extends JFrame implements ActionListener {

    private JTextField campoNombre;
    private JTextField campoDNI;
    private JButton botonRegistrar;

    public VentanaRegistroVotante() {
        super("Registro de Votante");
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        JLabel etiquetaNombre = new JLabel("Nombre:");
        etiquetaNombre.setBounds(50, 50, 100, 30);
        add(etiquetaNombre);

        campoNombre = new JTextField();
        campoNombre.setBounds(150, 50, 200, 30);
        add(campoNombre);

        JLabel etiquetaDNI = new JLabel("DNI:");
        etiquetaDNI.setBounds(50, 100, 100, 30);
        add(etiquetaDNI);

        campoDNI = new JTextField();
        campoDNI.setBounds(150, 100, 200, 30);
        add(campoDNI);

        botonRegistrar = new JButton("Registrar Votante");
        botonRegistrar.setBounds(150, 150, 200, 30);
        add(botonRegistrar);
        botonRegistrar.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonRegistrar) {
            String nombre = campoNombre.getText();
            String dni = campoDNI.getText();
            // Lógica para registrar votante
            JOptionPane.showMessageDialog(this, "Votante registrado exitosamente");
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
            dispose();
        }
    }
}

