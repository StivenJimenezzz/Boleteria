package com.boleteria.Boleteria.Front;

import javax.swing.*;

public class VentanaCompra extends JFrame {

    public VentanaCompra(long eventoId) {

        setTitle("Comprar boletas");

        JTextField nombre = new JTextField();
        JTextField identificacion = new JTextField();
        JTextField zona = new JTextField();
        JTextField cantidad = new JTextField();

        JComboBox<String> metodoPago = new JComboBox<>(
                new String[]{"TARJETA_CREDITO", "TARJETA_DEBITO", "PSE"}
        );

        JButton btn = new JButton("Comprar");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Nombre"));
        panel.add(nombre);

        panel.add(new JLabel("Identificación"));
        panel.add(identificacion);

        panel.add(new JLabel("Evento ID: " + eventoId));

        panel.add(new JLabel("Zona (A, B, C)"));
        panel.add(zona);

        panel.add(new JLabel("Cantidad"));
        panel.add(cantidad);

        panel.add(new JLabel("Método de pago"));
        panel.add(metodoPago);

        panel.add(btn);

        add(panel);

        setSize(300, 400);
        setVisible(true);

        btn.addActionListener(e -> {

            //  se valida
            if (nombre.getText().isEmpty() ||
                    identificacion.getText().isEmpty() ||
                    zona.getText().isEmpty() ||
                    cantidad.getText().isEmpty()) {

                JOptionPane.showMessageDialog(this, "Completa todos los campos");
                return;
            }

            int cant;

            try {
                cant = Integer.parseInt(cantidad.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Cantidad inválida");
                return;
            }

            String json = "{"
                    + "\"nombreComprador\":\"" + nombre.getText() + "\","
                    + "\"identificacion\":\"" + identificacion.getText() + "\","
                    + "\"eventoId\":" + eventoId + ","
                    + "\"zona\":\"" + zona.getText().toUpperCase() + "\","
                    + "\"cantidad\":" + cant + ","
                    + "\"metodoPago\":\"" + metodoPago.getSelectedItem() + "\""
                    + "}";

            ConexionPOST("http://localhost:8080/compras", json);
        });
    }

    public void ConexionPOST(String urlStr, String json) {
        try {
            java.net.URL url = new java.net.URL(urlStr);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // 🔍 DEBUG CLAVE
            System.out.println("JSON ENVIADO:");
            System.out.println(json);

            java.io.OutputStream os = conn.getOutputStream();
            os.write(json.getBytes());
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();
            System.out.println("HTTP CODE: " + responseCode);

            java.io.BufferedReader br;

            if (responseCode >= 200 && responseCode < 300) {
                br = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream()));
            } else {
                br = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getErrorStream()));
            }

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            System.out.println("RESPUESTA BACKEND:");
            System.out.println(response.toString());

            if (responseCode >= 200 && responseCode < 300) {
                JOptionPane.showMessageDialog(this, "Compra creada ✔");
            } else {
                JOptionPane.showMessageDialog(this, "Error ❌\n" + response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}