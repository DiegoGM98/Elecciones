package gui;

import javax.swing.*;
import java.awt.event.*;

public class VentanaPrincipal extends JFrame implements ActionListener {

    private JButton botonRegistrarCandidato;
    private JButton botonRegistrarVotante;
    private JButton botonConsultarResultados;

    public VentanaPrincipal() {
        super("Sistema de Elecciones Transparentes");
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarBotonRegistrarCandidato();
        generarBotonRegistrarVotante();
        generarBotonConsultarResultados();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(null);
        setVisible(true);
    }

    private void generarBotonRegistrarCandidato() {
        botonRegistrarCandidato = new JButton("Registrar Candidato");
        botonRegistrarCandidato.setBounds(50, 50, 300, 50);
        add(botonRegistrarCandidato);
        botonRegistrarCandidato.addActionListener(this);
    }

    private void generarBotonRegistrarVotante() {
        botonRegistrarVotante = new JButton("Registrar Votante");
        botonRegistrarVotante.setBounds(50, 120, 300, 50);
        add(botonRegistrarVotante);
        botonRegistrarVotante.addActionListener(this);
    }

    private void generarBotonConsultarResultados() {
        botonConsultarResultados = new JButton("Consultar Resultados");
        botonConsultarResultados.setBounds(50, 190, 300, 50);
        add(botonConsultarResultados);
        botonConsultarResultados.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonRegistrarCandidato) {
            VentanaRegistroCandidato ventanaRegistroCandidato = new VentanaRegistroCandidato();
            dispose();
        }
        if (e.getSource() == botonRegistrarVotante) {
            VentanaRegistroVotante ventanaRegistroVotante = new VentanaRegistroVotante();
            dispose();
        }
        if (e.getSource() == botonConsultarResultados) {
            VentanaConsultaResultados ventanaConsultaResultados = new VentanaConsultaResultados();
            dispose();
        }
    }
}
