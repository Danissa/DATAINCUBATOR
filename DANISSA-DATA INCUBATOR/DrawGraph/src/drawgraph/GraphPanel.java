/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawgraph;

/**
 *
 * @author drodr19
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GraphPanel extends JPanel {

    private int width = 800;
    private int heigth = 400;
    private int padding = 25;
    private int labelPadding = 25;
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color pointColor = new Color(200, 100, 100, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int pointWidth = 4;
    private int numberYDivisions = 20;
    static public int ANS =0;
     private int[] data = {
        0, 2010, 2011, 2012, 2013, 2014, 2015, 2016
    };
    
     
     private String data1 [] = {"0","All", "American Indian", "Asian/PI", "Black", "Hispanic", "Multiracial", "other", "white" };
      private String data2 [] = {"0","Boston", "Chicago", "Columbus", "Denver", "Detroit", "Fort Worth (Tarrant County)", "Houston", "Kansas City", "Las Vegas","Long Beach","Los Angeles", "Miami (Miami-Dade County)","Minneapolis","New York City","Phoenix", "Seattle", "Washington "};
    
    private List<Integer> scores;

    public GraphPanel(List<Integer> scores) {
        this.scores = scores;
    }

    @Override
    protected void paintComponent(Graphics g) {
       
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - scores.get(i)) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }

        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        // create hatch marks and grid lines for y axis.
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (scores.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMinScore() + (getMaxScore() - getMinScore()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < scores.size(); i++) {
            
            if (scores.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (scores.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((scores.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    if (ANS == 1)
                    {
                    String xLabel = data[i]+ "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                    }
                    if (ANS == 2)
                    {
                    String xLabel = data1[i]+ "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                    }
                    if (ANS == 3)
                    {
                    String xLabel = data2[i]+ "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                    }
                    
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }

        // create x and y axes 
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        //g2.setColor(pointColor);
        g2.setPaint(Color.red);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(width, heigth);
//    }
    private double getMinScore() {
        double minScore = Double.MAX_VALUE;
        for (Integer score : scores) {
            minScore = Math.min(minScore, score);
        }
        return minScore;
    }

    private double getMaxScore() {
        double maxScore = Double.MIN_VALUE;
        for (Integer score : scores) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
        invalidate();
        this.repaint();
    }

    public List<Integer> getScores() {
        return scores;
    }

    private static void createAndShowGui() {
        List<Integer> scores = new ArrayList<>();
        
        
        
       
                /*Random random = new Random();
                int maxDataPoints = 40;
                int maxScore = 10;
                for (int i = 0; i < maxDataPoints; i++) {
                scores.add((double) random.nextDouble() * maxScore);
//              scores.add((double) i);
            }*/
            scores.add (0);
            scores.add (69);
            scores.add (110);
            scores.add (103);
            scores.add (83);
            scores.add (56);
            scores.add (33);
            scores.add (5);
        
            GraphPanel mainPanel = new GraphPanel(scores);
            mainPanel.setPreferredSize(new Dimension(800, 600));
            JFrame frame = new JFrame("DrawGraph");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(mainPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
           
                
        
        
        
    }
    private static void createAndShowGui1() {
        List<Integer> scores = new ArrayList<>();
        
        
        
       
                /*Random random = new Random();
                int maxDataPoints = 40;
                int maxScore = 10;
                for (int i = 0; i < maxDataPoints; i++) {
                scores.add((double) random.nextDouble() * maxScore);
//              scores.add((double) i);
            }*/
            scores.add (0);
            scores.add (101);
            scores.add (21);
            scores.add (57);
            scores.add (85);
            scores.add (74);
            scores.add (13);
            scores.add (19);
            scores.add (89);
            GraphPanel mainPanel = new GraphPanel(scores);
            mainPanel.setPreferredSize(new Dimension(800, 600));
            JFrame frame = new JFrame("DrawGraph");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(mainPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
           
                
        
        
        
    }
    private static void createAndShowGui2() {
        List<Integer> scores = new ArrayList<>();
        
        
        
       
                /*Random random = new Random();
                int maxDataPoints = 40;
                int maxScore = 10;
                for (int i = 0; i < maxDataPoints; i++) {
                scores.add((double) random.nextDouble() * maxScore);
//              scores.add((double) i);
            }*/
            scores.add (0);
            scores.add (32);
            scores.add (20);
            scores.add (35);
            scores.add (17);
            scores.add (11);
            scores.add (9);
            scores.add (16);
            scores.add (11);
            scores.add (15);
            scores.add (28);
            scores.add (15);
            scores.add (20);
            scores.add (35);
            scores.add (25);
            scores.add (30);
            scores.add (20);
            scores.add (12);
            GraphPanel mainPanel = new GraphPanel(scores);
            mainPanel.setPreferredSize(new Dimension(800, 600));
            JFrame frame = new JFrame("DrawGraph");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(mainPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
           
                
        
        
        
    }

    public static void main(String[] args) {
        Scanner Cin = new Scanner(System.in); 
                        
                        System.out.println("which data would you like to usee");
                        System.out.println("1. Per Year");
                        System.out.println ("2. Per ethnic");
                        System.out.println ("3. Per city");
                        ANS = Cin.nextInt();
                        switch (ANS) {
                            case 1: SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                            createAndShowGui();
            
                            }
                            });
                            break;
                            case 2: SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                            createAndShowGui1();
            
                            }
                            });
                            break;
                            case 3: SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                            createAndShowGui2();
            
                            }
                            });
                            break; 
                                
                        }
        
      
   }
}