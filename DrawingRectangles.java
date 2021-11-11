package drawingrectangles;

import java.awt.*;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.*;
import javax.swing.*;

public class DrawingRectangles extends JFrame{

    GraphicsPanel drawPanel = new GraphicsPanel();
    JButton drawButton = new JButton();
    JButton fillButton = new JButton();
    JButton clearButton = new JButton();
    static Rectangle2D.Double myRectangle;
    static RoundRectangle2D.Double myRoundRectangle;
    static boolean isRound = false;
    static boolean isDrawn = false;
    static boolean isFilled = false;
    static int fillRed, fillGreen, fillBlue;
    Random myRandom = new Random();
    public static void main(String[] args) {

        new DrawingRectangles().show();
    }

    public DrawingRectangles() throws HeadlessException {
        setTitle("Drawing Rectagnes");
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitForm(e);
            }
            
});
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();
        drawPanel.setPreferredSize(new Dimension(300, 200));
        drawPanel.setBackground(Color.WHITE);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(drawPanel, gridConstraints);
        drawButton.setText("Draw Rectangle");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        getContentPane().add(drawButton, gridConstraints);
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawButtonActionPerformed(e);
            }
        });
        fillButton.setText("Fill Rectangle");
        fillButton.setEnabled(false);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        gridConstraints.insets = new Insets(5, 0, 0, 0);
        getContentPane().add(fillButton, gridConstraints);
        fillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillButtonActionPerformed(e);
            }
        });
        clearButton.setText("Clear Rectangle");
        clearButton.setEnabled(false);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 3;
        gridConstraints.insets = new Insets(5, 0, 5, 0);
        getContentPane().add(clearButton, gridConstraints);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearButtonActionPerformed(e);
            }
        });
        pack();
    }
    void drawButtonActionPerformed(ActionEvent e){
        int w = (myRandom.nextInt(71)+ 20) * drawPanel.getWidth()/100;
        int h = (myRandom.nextInt(71)+ 20) * drawPanel.getHeight()/100;
        int x = (int)(0.5*(drawPanel.getWidth() - w));
        int y = (int)(0.5*(drawPanel.getHeight() - h));
        if(myRandom.nextInt(100)<=49){
            isRound = true;
            int cw = (myRandom.nextInt(21)+ 10)* drawPanel.getWidth()/100;
            int ch = (myRandom.nextInt(21)+ 10) * drawPanel.getHeight()/ 100;
            myRoundRectangle = new RoundRectangle2D.Double(x, y, w, h, cw, ch);
            
        }else{
            isRound = false;
            myRectangle = new Rectangle2D.Double(x, y, w, h);
            
        }
        isDrawn = true;
        isFilled = false;
        drawButton.setEnabled(false);
        fillButton.setEnabled(true);
        clearButton.setEnabled(true);
        drawPanel.repaint();
    }
    void fillButtonActionPerformed(ActionEvent e){
        isFilled = true;
        drawButton.setEnabled(false);
        fillRed = myRandom.nextInt(256);
        fillGreen = myRandom.nextInt(256);
        fillBlue = myRandom.nextInt(256);
        drawPanel.repaint();
    }
    void clearButtonActionPerformed(ActionEvent e){
        isDrawn = false;
        isFilled = false;
        drawButton.setEnabled(true);
        fillButton.setEnabled(false);
        clearButton.setEnabled(false);
        drawPanel.repaint();
    }
    
    void exitForm(WindowEvent e){
        System.exit(0);
    }
    class GraphicsPanel extends JPanel{

        public GraphicsPanel() {
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2D = (Graphics2D)g;
            super.paintComponent(g2D);
            if(DrawingRectangles.isFilled){
                g2D.setPaint(new Color(DrawingRectangles.fillRed, DrawingRectangles.fillGreen, DrawingRectangles.fillBlue));
                if(DrawingRectangles.isRound){
                    g2D.fill(DrawingRectangles.myRoundRectangle);
                }else{
                    g2D.fill(DrawingRectangles.myRectangle);
                }
            }
            if(DrawingRectangles.isDrawn){
                g2D.setStroke(new BasicStroke(3));
                g2D.setPaint(Color.BLACK);
                if(DrawingRectangles.isRound){
                    g2D.draw(DrawingRectangles.myRoundRectangle);
                }else{
                    g2D.draw(DrawingRectangles.myRectangle);
                }
            }
            g2D.dispose();
        }
        
        
    }
}
