package com.example.pingpong;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HlavniOkno extends JFrame {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    JLabel labMicek;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    JPanel contentPane;
    int deltaX = 5;
    int deltaY = 5;
    Timer casovac;

    public HlavniOkno() {
        initComponents();
    }

    private void priStiskuBtnPohybMicku(ActionEvent e) {
        Point poziceMicku = labMicek.getLocation();
        int x = poziceMicku.x;
        int y = poziceMicku.y;
        Dimension velikostOkna = contentPane.getSize();
        double oknoX = velikostOkna.getWidth();
        double oknoY = velikostOkna.getHeight();
        System.out.println("sirka " + oknoX);

        x = x + deltaX;
        y = y + deltaY;

        if (x < 0) {
            x = -x;
            deltaX = -deltaX;
        }

        if (y < 0) {
            y = -y;
            deltaY = -deltaY;
        }

        if (x > oknoX) {
            deltaX = -deltaX;
        }

        if (y > oknoY) {
            deltaY = -deltaY;
        }

        poziceMicku.x = x;
        poziceMicku.y = y;
        labMicek.setLocation(poziceMicku);
    }

    private void priOtevreniOkna(WindowEvent e) {
        casovac = new Timer(500, it -> priStiskuBtnPohybMicku(it)); // it používáme, protože "e" už v metodě máme
        casovac.start();
    }

    private void priZavreniOkna(WindowEvent e) {
        casovac.stop();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        labMicek = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PingPong");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                priZavreniOkna(e);
            }
            @Override
            public void windowOpened(WindowEvent e) {
                priOtevreniOkna(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        this.contentPane = (JPanel) this.getContentPane();
        this.contentPane.setBackground(this.getBackground());

        //---- labMicek ----
        labMicek.setIcon(new ImageIcon(getClass().getResource("/com/example/pingpong/micek.png")));
        contentPane.add(labMicek);
        labMicek.setBounds(new Rectangle(new Point(235, 140), labMicek.getPreferredSize()));

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(400, 300);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
}
