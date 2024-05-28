package gui;

import javax.swing.*;
import java.awt.event.*;

public class VentanaRegistroCandidato extends JFrame implements ActionListener {

    private JTextField campoNombre;
    private JTextField campoPartido;
    private JButton botonRegistrar;

    public VentanaRegistroCandidato() {
        super("Registro de Candidato");
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        JLabel etiquetaNombre = new JLabel("Nombre:");
        etiquetaNombre.setBounds(50, 50, 100, 30);
        add(etiquetaNombre);

        campoNombre = new JTextField();
        campoNombre.setBounds(150, 50, 200, 30);
        add(campoNombre);

        JLabel etiquetaPartido = new JLabel("Partido:");
        etiquetaPartido.setBounds(50, 100, 100, 30);
        add(etiquetaPartido);

        campoPartido = new JTextField();
        campoPartido.setBounds(150, 100, 200, 30);
        add(campoPartido);

        botonRegistrar = new JButton("Registrar Candidato");
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
            String partido = campoPartido.getText();
            // Lógica para registrar candidato
            JOptionPane.showMessageDialog(this, "Candidato registrado exitosamente");
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
            dispose();
        }
    }
}

