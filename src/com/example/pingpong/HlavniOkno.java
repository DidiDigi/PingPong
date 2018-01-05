package com.example.pingpong;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class HlavniOkno extends JFrame {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    JLabel labMicek;
    JToolBar toolBar;
    JButton btnStart;
    JSlider sliderSpeed;
    JButton btnStop;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    JPanel contentPane;
    int deltaX = SPEED_INIT;
    int deltaY = SPEED_INIT;
    Timer casovac;
    Boolean pauza = false;
    static final int SPEED_MIN = 1;
    static final int SPEED_MAX = 31;
    static final int SPEED_INIT = 16;    //deltaX = deltaY
    JSlider speedValue;


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

        if ((x + 2*labMicek.getWidth()) >= oknoX) {
            deltaX = -deltaX;
        }

        if ((y + 2*labMicek.getHeight()) >= oknoY) {
            deltaY = -deltaY;
        }

        poziceMicku.x = x;
        poziceMicku.y = y;
        labMicek.setLocation(poziceMicku);
    }

    private void priOtevreniOkna(WindowEvent e) {
        casovac = new Timer(500, it -> priStiskuBtnPohybMicku(it)); // it používáme, protože "e" už v metodě máme
        casovac.start();
        speedValue = new JSlider(JSlider.HORIZONTAL, SPEED_MIN, SPEED_MAX, SPEED_INIT);
        speedValue.addChangeListener(this::sliderSpeedStateChanged);

    }

    private void priZavreniOkna(WindowEvent e) {
        casovac.stop();
    }

    private void priStiskuBtnStart(ActionEvent e) {
        casovac.start();
    }

    private void priStiskuBtnStop(ActionEvent e) {
        casovac.stop();
    }

    private void sliderSpeedStateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();

        //Turn on labels at major tick marks.
        speedValue.setMajorTickSpacing(10);
        speedValue.setMinorTickSpacing(1);
        speedValue.setPaintTicks(true);
        speedValue.setPaintLabels(true);
        if (!source.getValueIsAdjusting()) {
            int speed = (int)source.getValue();

            if (deltaX < 0) { deltaX = -speed; }
               else { deltaX = speed; }

            if (deltaY < 0) { deltaY = -speed; }
               else { deltaY = speed; }
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        labMicek = new JLabel();
        toolBar = new JToolBar();
        btnStart = new JButton();
        sliderSpeed = new JSlider();
        btnStop = new JButton();

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

        //======== toolBar ========
        {

            //---- btnStart ----
            btnStart.setText("Start");
            btnStart.addActionListener(e -> priStiskuBtnStart(e));
            toolBar.add(btnStart);

            //---- sliderSpeed ----
            sliderSpeed.addChangeListener(e -> sliderSpeedStateChanged(e));
            toolBar.add(sliderSpeed);

            //---- btnStop ----
            btnStop.setText("Stop");
            btnStop.addActionListener(e -> priStiskuBtnStop(e));
            toolBar.add(btnStop);
        }
        contentPane.add(toolBar);
        toolBar.setBounds(0, 0, 385, toolBar.getPreferredSize().height);

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
