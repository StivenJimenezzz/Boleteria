package com.boleteria.Boleteria.Front;

import javax.swing.*;

public class AplicacionPrincipal {

    public static void main(String[] args) {

        JFrame ventana = new JFrame("Sistema de Boletería");

        JLabel titulo = new JLabel("🎟 MENÚ PRINCIPAL", SwingConstants.CENTER);

        JButton btnEventos = new JButton("Ver eventos");
        JButton btnCrearEvento = new JButton("Crear evento (Admin)");
        JButton btnComprar = new JButton("Comprar boletas");
        JButton btnPago = new JButton("Confirmar pago");
        JButton btnHistorial = new JButton("Historial de compras");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(titulo);
        panel.add(btnEventos);
        panel.add(btnCrearEvento);
        panel.add(btnComprar);
        panel.add(btnPago);
        panel.add(btnHistorial);

        ventana.add(panel);

        ventana.setSize(400, 300);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);


        // ver eventos
        btnEventos.addActionListener(e -> new VentanaEventos());

        // crear evento
        btnCrearEvento.addActionListener(e -> new VentanaCrearEvento());

        // comprar
        btnComprar.addActionListener(e -> new VentanaEventos());

        // pago
        btnPago.addActionListener(e -> new VentanaPago());

        // historial
        btnHistorial.addActionListener(e -> new VentanaHistorial());
    }
}