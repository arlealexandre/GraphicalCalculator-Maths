package package_ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Arc2D;
import javax.script.ScriptException;
import javax.swing.JPanel;

public class Grille extends JPanel {
    
    private final int DIAM_POINT1 = 10;
    private final int DIAM_POINT2 = 4;
        
    private int nbCol;
    private int nbLig;
    
    private VuePrincipale ihm;
    
    private BasicStroke line;
    
    private Graphics2D g2d;
            
    private Dimension size;
        
    public Grille(VuePrincipale ihm, int nbCol, int nbLig) throws ScriptException {
        super();
        this.ihm = ihm;
        this.nbCol = nbCol*2;
        this.nbLig = nbLig*2;
        
        line = new BasicStroke(2);
   
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g2d = (Graphics2D) g;
        size = getSize();
        
        this.effacer();
        
        if (this.ihm.getItemGrille().isSelected()) {
            this.tracerGrille();
        }
        
        if (this.ihm.getItemAxes().isSelected()) {
            this.tracerAxes();
        }
        
        if (this.ihm.getItemFonctions().isSelected()) {
            this.tracerFonctions();
        }    
        
        if (this.ihm.getItemPoints().isSelected()) {
            this.placerPoints();
        }
                          
    }
        
    private double getCoordX(double valeur) {
        return (size.width/this.nbCol)*(valeur+this.nbCol/2);
    }
    
    private double getCoordY(double valeur) {
        return (size.height/this.nbLig)*(this.nbCol/2-valeur);
    }
    
    private void effacer() {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, size.width, size.height);
    }
    
    private void tracerAxes() {
        g2d.setColor(Color.BLACK);
        g2d.drawLine((int)getCoordX(0), 0, (int)getCoordX(0), (int)size.getHeight());      
        g2d.setColor(Color.BLACK);
        g2d.drawLine(0, (int)getCoordY(0), (int)size.getWidth(), (int)getCoordY(0));
        if (this.ihm.getItemGraduations().isSelected()) {
            this.tracerGraduations();
        }
    }
    
    private void tracerGraduations() {
        for (double i=-nbCol; i<nbCol; i++) {
            Line2D line1 = new Line2D.Double(getCoordX(i), getCoordY(0.2), getCoordX(i), getCoordY(-0.2));
            g2d.setColor(Color.BLACK);
            g2d.draw(line1);
            if (i!=0.0) {
                g2d.setFont(new Font(Font.SERIF,Font.PLAIN,10));
                g2d.drawString(""+i+"", (int)getCoordX(i)-10,(int)getCoordY(0.2)-3);
            }
            for (double j=-nbCol; j<nbCol; j+=0.1) {
                Line2D line2 = new Line2D.Double(getCoordX(j), getCoordY(0.05), getCoordX(j), getCoordY(-0.05));
                g2d.setColor(Color.BLACK);
                g2d.draw(line2);
            }
        }
        for (double i=-nbLig; i<nbLig; i++) {
            Line2D line1 = new Line2D.Double(getCoordX(0.2), getCoordY(i), getCoordX(-0.2), getCoordY(i));
            g2d.setColor(Color.BLACK);
            g2d.draw(line1);
            g2d.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,10));
            g2d.drawString(""+i+"", (int)getCoordX(0.2), (int)getCoordY(i)-10);
            for (double j=-nbCol; j<nbCol; j+=0.1) {
                Line2D line2 = new Line2D.Double(getCoordX(0.05), getCoordY(j), getCoordX(-0.05), getCoordY(j));
                g2d.setColor(Color.BLACK);
                g2d.draw(line2);
            }
        }
    }
    
    private void tracerGrille() {
        for (int i=0; i<size.getWidth(); i++) {
            g2d.setColor(Color.GRAY);
            g2d.drawLine(((int)size.getWidth()/this.nbLig)*i, 0, ((int)size.getWidth()/this.nbLig)*i, (int)size.getHeight());
        }  
        for (int i=0; i<size.height; i++) {
            g2d.setColor(Color.GRAY);
            g2d.drawLine(0, (size.height/this.nbCol)*i, size.width, (size.height/this.nbCol)*i);
        } 
    }
        
    private void placerPoint(String p, double x, double y) {       
        Arc2D point1 = new Arc2D.Double(getCoordX(x)-DIAM_POINT1/2, getCoordY(y)-DIAM_POINT1/2, DIAM_POINT1, DIAM_POINT1, 360, 360, 0);  
        Arc2D point2 = new Arc2D.Double(getCoordX(x)-DIAM_POINT2/2, getCoordY(y)-DIAM_POINT2/2, DIAM_POINT2, DIAM_POINT2, 360, 360, 0);
        g2d.setColor(Color.RED); 
        g2d.draw(point1);
        g2d.setColor(Color.GREEN); 
        g2d.draw(point2);
        g2d.setColor(Color.RED);
        g2d.setFont(new Font(Font.SANS_SERIF,Font.BOLD,12));
        g2d.drawString(p.toUpperCase(), (int)(getCoordX(x)-DIAM_POINT1/2)+12, (int)getCoordY(y)-DIAM_POINT1/2);
    }
    
    private void tracerLigne(double x1, double y1, double x2, double y2) {
        g2d.setStroke(line);
        g2d.setColor(Color.BLUE);
        Line2D line = new Line2D.Double(getCoordX(x1), getCoordY(y1), getCoordX(x2), getCoordY(y2));
        g2d.draw(line);
    }
    
    private void tracerFonctions() {
        for (String f : this.ihm.getSetFonctions()) {
            for (int i=0; i<this.ihm.getControleur().getGraph().getFonctions().get(f).getLesPoints().size()-1; i++) {
                this.tracerLigne(this.ihm.getX(f, i), this.ihm.getY(f, i), this.ihm.getX(f, i+1), this.ihm.getY(f, i+1));
            }
        }
    }
    
    private void placerPoints() {
        for (String p : this.ihm.getSetPoints()) {
            this.placerPoint(p, this.ihm.getPointsX(p), this.ihm.getPointsY(p));
        }
    }

    public Graphics2D getG2d() {
        return g2d;
    }
        
}
