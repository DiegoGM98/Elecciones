package gui;

import javax.swing.*;
import java.awt.event.*;

public class VentanaConsultaResultados extends JFrame implements ActionListener {

    private JButton botonConsultar;

    public VentanaConsultaResultados() {
        super("Consulta de Resultados");
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        botonConsultar = new JButton("Consultar Resultados");
        botonConsultar.setBounds(150, 100, 200, 30);
        add(botonConsultar);
        botonConsultar.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonConsultar) {
            // Lógica para consultar resultados
            JOptionPane.showMessageDialog(this, "Resultados consultados exitosamente");
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
            dispose();
        }
    }
}

