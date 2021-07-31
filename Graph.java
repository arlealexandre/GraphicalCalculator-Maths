package package_graph;

import java.util.ArrayList;
import java.util.HashMap;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import package_controleur.Controleur;

public class Graph {
    
    private final int NB_COL = 10;
    private final int NB_LIG = 10;
    
    private HashMap<String, Courbe> fonctions;
    private HashMap<String, Point> points;
        
    private Controleur controleur;
    
    public Graph(Controleur controleur) {
        this.controleur = controleur;
        this.fonctions = new HashMap<>();
        this.points = new HashMap<>();

    }

    public HashMap<String, Courbe> getFonctions() {
        return fonctions;
    }

    public HashMap<String, Point> getPoints() {
        return points;
    }

    public void effacerFonctions() {
        this.getFonctions().clear();
    }
    
    public void effacerPoints() {
        this.getPoints().clear();
    }

    public int getNB_COL() {
        return NB_COL;
    }

    public int getNB_LIG() {
        return NB_LIG;
    }
    
    public void ajouterPoint(String n, double x, double y) {
        this.getPoints().put(n.toUpperCase(), new Point(x,y));
    }
    
    public void tracerFonction(String f) throws ScriptException {
        ScriptEngineManager engine = new ScriptEngineManager();
        ScriptEngine manager = engine.getEngineByName("js");
        Courbe courbe = new Courbe();
        
        for (double i=-this.getNB_COL(); i<=this.getNB_LIG(); i=i+0.1) {
            String x = ""+i+"";
            double result = (double) (manager.eval("var x="+x+"; "+f+";"));
            courbe.getLesPoints().add(new Point(i,result));
        }

        this.fonctions.put(f, courbe);
    }
    
    public void afficherFonctions() {
        
        if (!this.getFonctions().isEmpty()) {
            for (String f : this.getFonctions().keySet()) {
                System.out.println("F(x) = "+f);
            }
        } else {
            System.out.println("Aucune fonction créee.");
        }
         
    }
    
    public void afficherPoints() {
        
        if (!this.getPoints().isEmpty()) {
            for (String p : this.getPoints().keySet()) {
                System.out.println("x = "+this.getPoints().get(p).getX()+" y = "+this.getPoints().get(p).getY());
            }
        } else {
            System.out.println("Aucune fonction créee.");
        }
         
    }
    
    public double getX(String f, int i) {
        return this.getFonctions().get(f).getX(i);
    }
    
    public double getY(String f, int i) {
        return this.getFonctions().get(f).getY(i);
    }
    
    public double getPointX(String p) {
        return this.getPoints().get(p).getX();
    }
    
    public double getPointY(String p) {
        return this.getPoints().get(p).getY();
    }
    
}
