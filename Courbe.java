package package_graph;

import java.util.ArrayList;

public class Courbe {
    
    private ArrayList<Point> lesPoints;
    
    public Courbe() {
        this.lesPoints = new ArrayList<>();
    }

    public ArrayList<Point> getLesPoints() {
        return lesPoints;
    }

    public void setLesPoints(ArrayList<Point> lesPoints) {
        this.lesPoints = lesPoints;
    }
    
    public void effacerCourbe() {
        this.lesPoints.clear();
    }
    
    public double getX(int i) {
        return this.getLesPoints().get(i).getX();
    }
    
    public double getY(int i) {
        return this.getLesPoints().get(i).getY();
    }
    
    
}
