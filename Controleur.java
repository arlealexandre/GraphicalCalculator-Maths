package package_controleur;

import java.util.ArrayList;
import java.util.Set;
import javax.script.ScriptException;
import package_graph.Graph;
import package_ihm.VuePrincipale;

public class Controleur {
    
    private Graph graph;
    private VuePrincipale ihm;
    
    public Controleur() throws ScriptException {
        this.graph = new Graph(this);
        this.ihm = new VuePrincipale(this,this.graph.getNB_COL(),this.graph.getNB_LIG());
        
        this.graph.afficherFonctions();
    }
    
    public void gererDialogue(Commande cmd) throws ScriptException {
        if (cmd != null) {
            switch (cmd) {
                case PLACER_POINT:
                    this.placerPoint();
                    break;

                case TRACER_FONCT:
                    this.tracerFonction();
                    break;
                    
                case EFFACER_GRAPH:
                    this.effacerGraph();
                    break;

                default:
                    break;
            }
        }
    } 

    public Graph getGraph() {
        return graph;
    }

    public VuePrincipale getIhm() {
        return ihm;
    }
    
    public Set<String> getSetPoints() {
       return this.getGraph().getPoints().keySet();
    }
    
    public Set<String> getSetFonctions() {
        return this.getGraph().getFonctions().keySet();
    }
    
    public void effacerGraph() {
        this.graph.effacerFonctions();
        this.graph.effacerPoints();
        this.ihm.repeindre();
        System.out.println("Graph effacé.");
    }
    
    public int getNB_COL() {
        return this.graph.getNB_COL();
    }
    
    public int getNB_LIG() {
        return this.graph.getNB_LIG();
    }
    
    public void tracerFonction() throws ScriptException {
        this.graph.tracerFonction(this.ihm.getFonction());
        this.ihm.repeindre();
        this.graph.afficherFonctions();
    }
    
    public double getX(String f, int i) {
        return this.graph.getX(f, i);
    }
    
    public double getY(String f, int i) {
        return this.graph.getY(f, i);
    }
    
    public void placerPoint() {
        this.graph.ajouterPoint(this.ihm.getName(), this.ihm.getPointX(), this.ihm.getPointY());
        this.ihm.repeindre();
        this.graph.afficherPoints();
    }
    
    public double getPointX(String p) {
        return this.graph.getPointX(p);
    }
    
    public double getPointY(String p) {
        return this.graph.getPointY(p);
    }
    
}
