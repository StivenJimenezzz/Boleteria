package com.boleteria.Boleteria.Front;

import javax.swing.*;
import java.net.*;
import java.io.*;

public class VentanaCrearEvento extends JFrame {

    public VentanaCrearEvento() {

        setTitle("Crear evento (Admin)");

        JTextField nombre = new JTextField();
        JTextField fecha = new JTextField();
        JTextField hora = new JTextField();
        JTextField lugar = new JTextField();
        JTextField patrocinador = new JTextField();

        JTextField boletasA = new JTextField();
        JTextField boletasB = new JTextField();
        JTextField boletasC = new JTextField();

        JButton btnCrear = new JButton("Crear evento");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Nombre"));
        panel.add(nombre);

        panel.add(new JLabel("Fecha (YYYY-MM-DD)"));
        panel.add(fecha);

        panel.add(new JLabel("Hora"));
        panel.add(hora);

        panel.add(new JLabel("Lugar"));
        panel.add(lugar);

        panel.add(new JLabel("Patrocinador"));
        panel.add(patrocinador);

        panel.add(new JLabel("Boletas Zona A"));
        panel.add(boletasA);

        panel.add(new JLabel("Boletas Zona B"));
        panel.add(boletasB);

        panel.add(new JLabel("Boletas Zona C"));
        panel.add(boletasC);

        panel.add(btnCrear);

        add(panel);

        setSize(400, 500);
        setVisible(true);

        // RQ 01 - crear evento
        btnCrear.addActionListener(e -> {

            try {
                URL url = new URL("http://localhost:8080/eventos");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                String json = "{"
                        + "\"nombre\":\"" + nombre.getText() + "\","
                        + "\"fecha\":\"" + fecha.getText() + "\","
                        + "\"hora\":\"" + hora.getText() + "\","
                        + "\"lugar\":\"" + lugar.getText() + "\","
                        + "\"patrocinador\":\"" + patrocinador.getText() + "\","
                        + "\"boletasA\":" + boletasA.getText() + ","
                        + "\"boletasB\":" + boletasB.getText() + ","
                        + "\"boletasC\":" + boletasC.getText()
                        + "}";

                OutputStream os = conn.getOutputStream();
                os.write(json.getBytes());
                os.flush();
                os.close();

                int code = conn.getResponseCode();
                System.out.println("HTTP CODE: " + code);

                if (code == 200 || code == 201) {
                    JOptionPane.showMessageDialog(this, "Evento guardado en BD ✔");
                } else {
                    JOptionPane.showMessageDialog(this, "Error HTTP: " + code);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}