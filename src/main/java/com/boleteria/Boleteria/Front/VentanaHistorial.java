package com.boleteria.Boleteria.Front;

import javax.swing.*;
import java.net.*;
import java.io.*;
import org.json.*;

public class VentanaHistorial extends JFrame {

    public VentanaHistorial() {

        setTitle("Consultar historial");

        JTextField identificacion = new JTextField();
        JButton btn = new JButton("Buscar");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Identificación"));
        panel.add(identificacion);
        panel.add(btn);

        add(panel);

        setSize(300, 200);
        setVisible(true);

        btn.addActionListener(e -> {

            if (identificacion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese identificación");
                return;
            }

            cargarHistorial(identificacion.getText());
        });
    }

    public void cargarHistorial(String id) {
        try {
            URL url = new URL("http://localhost:8080/compras/historial/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            JSONArray compras = new JSONArray(response.toString());

            // 🔥 abrir nueva ventana con datos
            new VentanaListaCompras(compras);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar historial");
        }
    }
}