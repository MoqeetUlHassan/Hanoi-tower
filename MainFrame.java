package com.raiayy.towerofhanoi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MainFrame extends JFrame implements ActionListener, ChangeListener {

    private JLabel labelNumOfDisks;
    private JLabel labelinformation;
    private JSpinner spinnerNumOfDisks;
    private JButton buttonHome;
    private Drawing drawing;

    public MainFrame() {
        super("Tower of Hanoi | Rai, Moqeet and Maida");
        configureWindow();
        initializeComponentes();
        this.setVisible(true);
    }

    private void configureWindow() {
        this.setSize(680, 400);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeComponentes() {

        JPanel panelInferior = new JPanel();

        labelNumOfDisks = new JLabel("Number of Discs");
        panelInferior.add(labelNumOfDisks);

        spinnerNumOfDisks = new JSpinner(new SpinnerNumberModel(3, 1, 8, 1));
        spinnerNumOfDisks.addChangeListener(this);
        panelInferior.add(spinnerNumOfDisks);

        buttonHome = new JButton("Start Simulation");
        buttonHome.addActionListener(this);
        panelInferior.add(buttonHome);

        labelinformation = new JLabel("Hanoi Tower Puzzle Solved!");
        labelinformation.setForeground(Color.green);
        labelinformation.setVisible(false);
        panelInferior.add(labelinformation);

        add(panelInferior, BorderLayout.SOUTH);
        drawing = new Drawing(3, this);
        add(drawing, BorderLayout.CENTER);


    }

    public void actionPerformed(ActionEvent e) {
        if (buttonHome.getText().equals("Pause")) {
            drawing.pauseAnimation();
            buttonHome.setText("Continue");
        } else {
            if (buttonHome.getText().equals("Start again")) {
                drawing = new Drawing(Integer.parseInt(spinnerNumOfDisks.getValue().toString()), this);
                add(drawing, BorderLayout.CENTER);
                buttonHome.setText("Start");
                labelinformation.setVisible(false);
                this.setVisible(true);
            } else {
                drawing.initialAnimation();
                buttonHome.setText("Pause");
            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        drawing.pauseAnimation();
        buttonHome.setText("Start");
        labelinformation.setVisible(false);
        drawing = new Drawing(Integer.parseInt(spinnerNumOfDisks.getValue().toString()), this);
        add(drawing, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void resolutionCompleted() {
        buttonHome.setText("Start again");
        labelinformation.setVisible(true);
    }
}
