package com.boleteria.Boleteria.Front;

import javax.swing.*;
import java.net.*;
import java.io.*;
import org.json.*;

public class VentanaEventos extends JFrame {

    JPanel panel;

    public VentanaEventos() {

        setTitle("Eventos disponibles");

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JScrollPane scroll = new JScrollPane(panel);
        add(scroll);

        setSize(400, 400);

        cargarEventos();

        setVisible(true);
    }

    public void cargarEventos() {

        try {
            URL url = new URL("http://localhost:8080/eventos/disponibles");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() < 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder responseBuilder = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                responseBuilder.append(line);
            }

            String response = responseBuilder.toString().trim();

            System.out.println("RESPONSE: " + response);

            JSONArray eventos = new JSONArray(response);

            SwingUtilities.invokeLater(() -> {

                panel.removeAll();

                if (eventos.length() == 0) {
                    panel.add(new JLabel("No hay eventos disponibles"));
                }

                for (int i = 0; i < eventos.length(); i++) {

                    JSONObject evento = eventos.getJSONObject(i);

                    String nombre = evento.optString("nombre", "Evento sin nombre");

                    JButton btn = new JButton(nombre);

                    JSONObject eventoFinal = evento;

                    btn.addActionListener(e -> {
                        new VentanaDetalleEvento(eventoFinal);
                    });

                    panel.add(btn);
                }

                panel.revalidate();
                panel.repaint();
            });

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}