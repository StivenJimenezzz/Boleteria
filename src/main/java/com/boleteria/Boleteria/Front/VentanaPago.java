package com.boleteria.Boleteria.Front;

import javax.swing.*;

public class VentanaPago extends JFrame {

    public VentanaPago() {

        setTitle("Confirmar Pago");

        JTextField identificacion = new JTextField();

        JButton btn = new JButton("Pagar");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Identificación (usada como idCompra)"));
        panel.add(identificacion);

        panel.add(btn);

        add(panel);

        setSize(300, 200);
        setVisible(true);

        btn.addActionListener(e -> {


            if (identificacion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese la identificación");
                return;
            }

            String comprobante = "PAGO" + identificacion.getText();

            String json = "{"
                    + "\"idCompra\":\"" + identificacion.getText() + "\","
                    + "\"numeroComprobante\":\"" + comprobante + "\""
                    + "}";

            ConexionPOST("http://localhost:8080/compras/pago", json);
        });
    }

    public void ConexionPOST(String urlStr, String json) {
        try {
            java.net.URL url = new java.net.URL(urlStr);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // 🔍 DEBUG
            System.out.println("JSON ENVIADO: " + json);

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

            System.out.println("RESPUESTA BACKEND: " + response.toString());

            if (responseCode >= 200 && responseCode < 300) {
                JOptionPane.showMessageDialog(this,
                        "Pago realizado ✔");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Error en el pago ❌\n" + response.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}