package com.boleteria.Boleteria.Front;

import javax.swing.*;
import org.json.*;

public class VentanaListaCompras extends JFrame {

    public VentanaListaCompras(JSONArray compras) {

        setTitle("Historial de compras");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JScrollPane scroll = new JScrollPane(panel);
        add(scroll);

        for (int i = 0; i < compras.length(); i++) {

            JSONObject c = compras.getJSONObject(i);

            String info = "ID: " + c.getInt("id")
                    + " | Evento: " + c.getJSONObject("evento").optString("nombre", "N/A")
                    + " | Zona: " + c.getString("zona")
                    + " | Cantidad: " + c.getInt("cantidad")
                    + " | Estado: " + c.getString("estado");

            JLabel label = new JLabel(info);

            panel.add(label);
        }

        setSize(500, 400);
        setVisible(true);
    }
}