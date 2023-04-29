package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SpinWheel extends JPanel implements ActionListener {
    private JButton spinButton;
    private Random random;
    private int position = 0;
    private static List<Color> colorOfficial;
    private static List<String> labelOfficial;

    public SpinWheel() {
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.black);
        random = new Random();
        spinButton = new JButton("Spin");
        spinButton.addActionListener(this);
        add(spinButton);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        position = random.nextInt(360);
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        final int circle360 = 360;
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        int radius = Math.min(x, y) / 2;
        g.setColor(Color.BLACK);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
        int labelIndex = 0;

        for (int i = 0; i < circle360; i += circle360 / colorOfficial.size()) {
            g.setColor(colorOfficial.get(labelIndex % colorOfficial.size()));
            g.fillArc(x - radius, y - radius, 2 * radius, 2 * radius, i, circle360 / colorOfficial.size());
            //g.setColor(Color.yellow);
            //g.drawString(labelOfficial.get(labelIndex % labelOfficial.size()), x, y);
            labelIndex++;
        }
        g.setColor(Color.BLACK);
        g.drawLine(x, y, (int) (x + radius * Math.cos(Math.toRadians(position))),
                (int) (y + radius * Math.sin(Math.toRadians(position))));

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select the colors for wheel by number?");

        List<Color> colors = new ArrayList<>(java.util.List.of(new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PINK, Color.CYAN, Color.MAGENTA, Color.GRAY}));
        List<String> labels = new ArrayList<>(java.util.List.of(new String[]{"RED", "BLUE", "GREEN", "YELLOW", "ORANGE", "PINK", "CYAN", "MAGENTA", "GRAY"}));

        colorOfficial = new ArrayList<>();
        labelOfficial = new ArrayList<>();
        int x = -1;
        while (x != 0) {

            for (int i = 0; i < colors.size(); i++) {
                System.out.println(i + 1 + "-" + labels.get(i));
            }
            System.out.println(0 + "-" + " Exit");
            x = sc.nextInt();
            if (x < 0 || x > labels.size()) {
                System.out.println("Invalid");
            } else if (x > 0 && x <= labels.size()) {
                labelOfficial.add(labels.get(x - 1));
                colorOfficial.add(colors.get(x - 1));
                labels.remove(x - 1);
                colors.remove(x - 1);
            }
            System.out.println();
        }

        JFrame frame = new JFrame("Spin Wheel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SpinWheel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}