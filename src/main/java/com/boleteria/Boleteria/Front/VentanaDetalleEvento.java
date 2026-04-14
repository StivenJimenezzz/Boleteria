package com.boleteria.Boleteria.Front;

import javax.swing.*;
import org.json.JSONObject;

public class VentanaDetalleEvento extends JFrame {

    public VentanaDetalleEvento(JSONObject evento) {

        setTitle("Detalle del evento");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Nombre: " + evento.getString("nombre")));
        panel.add(new JLabel("Fecha: " + evento.getString("fecha")));
        panel.add(new JLabel("Hora: " + evento.getString("hora")));
        panel.add(new JLabel("Lugar: " + evento.getString("lugar")));
        panel.add(new JLabel("Patrocinador: " + evento.getString("patrocinador")));

        JButton comprar = new JButton("Comprar");
        JButton volver = new JButton("Volver");

        panel.add(comprar);
        panel.add(volver);

        add(panel);

        setSize(300, 300);
        setVisible(true);

        // comprar
        comprar.addActionListener(e -> {
            new VentanaCompra(evento.getLong("id"));
        });

        // cerrar ventana
        volver.addActionListener(e -> dispose());
    }
}