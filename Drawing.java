package com.raiayy.towerofhanoi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Drawing extends JPanel implements ActionListener {

    private int notrecords;
    private String message;
    private int[] buffers;
    private int movementActual;
    private Image[] records;
    private int x, y;
    private int record;
    private int nm;
    private Movement[] movements;
    private Position[] positions;
    private Timer timer;
    private boolean movementCompleted;
    private int step;
    private static final int speed = 1;
    private static final int LIMIT_records = 8;
    private static final int LIMIT_towers = 3;
    private MainFrame core;

    public Drawing(int notrecords, MainFrame core) {
        this.notrecords = notrecords;
        this.core = core;
        configurarPanel();
        initializeComponentes();
        initializeComponentesDeanimation();
        timer = new Timer(speed, this);
    }

    private void configurarPanel() {
        setDoubleBuffered(true);
        setBackground(Color.black);
    }

    private void initializeComponentes() {
        records = new Image[LIMIT_records + 1];
        for (int i = 1; i <= LIMIT_records; i++) {
            ImageIcon ii = new ImageIcon(this.getClass().getResource("/com/raiayy/towerofhanoi/images/" + i + ".png"));
            records[i] = ii.getImage();
        }
    }

    private void initializeComponentesDeanimation() {
        nm = 0;
        buffers = new int[LIMIT_towers + 1];
        buffers[1] = notrecords;
        buffers[2] = 0;
        buffers[3] = 0;
        record = 1;
        movements = new Movement[(int) Math.pow(2, notrecords)];
        algoritmoHanoi(notrecords, 1, 2, 3); 
        positions = new Position[9];
        for (int i = 1; i <= notrecords; i++) {
            int w = notrecords - i + 1;
            positions[i] = new Position(positionXrecord(i, 1), positionYrecord(w));
        }
        x = positions[1].getX();
        y = positions[1].getY();
        movementActual = 1;
        movementCompleted = false;
        step = 1;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        for (int i = notrecords; i >= 1; i--) {
            g2.drawImage(records[i], positions[i].getX(), positions[i].getY(), this);
        }
        g2.drawString("Tower A", 115, 320);
        g2.drawString("Tower B", 315, 320);
        g2.drawString("Tower C", 515, 320);
        g2.drawString(message, 225, 15);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void algoritmoHanoi(int n, int origin, int temporary, int destination) {
        if (n == 0) {
            return;
        }		
        algoritmoHanoi(n - 1, origin, destination, temporary);
        nm++; 
        movements[nm] = new Movement(n, origin, destination);
        message = ("number of moves required to solve are : " + (int)(Math.pow(2, notrecords)-1) );
        algoritmoHanoi(n - 1, temporary, origin, destination);
    }

    public void actionPerformed(ActionEvent e) {
        switch (step) {
            case 1: 
                if (y > 30) { 
                    y--;
                    positions[record].setY(y);
                } else {
                    if (movements[movementActual].getTowerOrigin() < movements[movementActual].gettowerdestination()) {
                        step = 2; 
                    } else {
                        step = 3; 
                    }
                }
                break;
            case 2: 
                if (x < positionXrecord(record, movements[movementActual].gettowerdestination())) { 
                    x++;
                    positions[record].setX(x);
                } else {
                    step = 4;
                }
                break;
            case 3: 
                if (x > positionXrecord(record, movements[movementActual].gettowerdestination())) { 
                    x--;
                    positions[record].setX(x);
                } else {
                    step = 4;
                }
                break;
            case 4: 
                int level = buffers[movements[movementActual].gettowerdestination()] + 1;
                if (y < positionYrecord(level)) {
                    y++;
                    positions[record].setY(y);
                } else {
                    movementCompleted = true;
                }
                break;
        }
        if (movementCompleted) {
            step = 1;
            buffers[movements[movementActual].gettowerdestination()]++;
            buffers[movements[movementActual].getTowerOrigin()]--;
            movementActual++;
            if (movementActual == (int) Math.pow(2, notrecords)) {
                timer.stop();
                core.resolutionCompleted();
            } else {
                movementCompleted = false;
                record = movements[movementActual].getrecord();
                x = positions[record].getX();
                y = positions[record].getY();
            }
        }
        repaint();
    }

    public static int positionXrecord(int record, int tower) {
        int k = (tower - 1) * 200;
        switch (record) {
            case 1:
                return 110 + k;
            case 2:
                return 100 + k;
            case 3:
                return 90 + k;
            case 4:
                return 80 + k;
            case 5:
                return 70 + k;
            case 6:
                return 60 + k;
            case 7:
                return 50 + k;
            case 8:
                return 40 + k;
        }
        return 0;
    }

    public static int positionYrecord(int level) {
        switch (level) {
            case 1:
                return 260;
            case 2:
                return 233;
            case 3:
                return 206;
            case 4:
                return 179;
            case 5:
                return 152;
            case 6:
                return 125;
            case 7:
                return 98;
            case 8:
                return 71;
        }
        return 0;
    }

    public void initialAnimation() {
        timer.restart();
    }

    public void pauseAnimation() {
        timer.stop();
    }
}
