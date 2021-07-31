package package_ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import package_controleur.Commande;
import package_controleur.Controleur;

public class VuePrincipale {
    
    private JFrame fenetre;
    
    private VueFonctions vueF;
    private VuePoints vueP;
    
    private JMenuBar navigation;
    
    private JMenu menuFichier;
    private JMenuItem itemQuitter;
    private JMenuItem itemEffacer;
    
    private JMenu menuAffichage;
    private JCheckBox itemAxes;
    private JCheckBox itemGrille;
    private JCheckBox itemGraduations;
    private JCheckBox itemFonctions;
    private JCheckBox itemPoints;
    
    private JMenu menuAjouter;
    private JMenuItem itemAjouterPoint;
    private JMenuItem itemAjouterFonction;
    
    private Grille grille;
    
    private Controleur controleur;
    
    private int nbCol;
    private int nbLig;
    
    public VuePrincipale(Controleur controleur, int nbCol, int nbLig) throws ScriptException {
        
        this.controleur = controleur;
        this.vueF = new VueFonctions(this);
        this.vueP = new VuePoints(this);
        this.nbCol = nbCol;
        this.nbLig = nbLig;
        
        this.initMain();
        this.initListeners();
        
        
        
    }
    
    private void initMain() throws ScriptException {
        
        /* Déclaration de la fenêtre ******************************************/
        fenetre = new JFrame("Calculatrice graphique");
        fenetre.setLayout(new BorderLayout());
        this.configureWindow(fenetre);
         
        /* Barre de navigation ************************************************/
        navigation = new JMenuBar();
        
        menuFichier = new JMenu("Fichier");
        itemEffacer = new JMenuItem("Effacer");
        itemQuitter = new JMenuItem("Quitter");
        menuFichier.add(itemEffacer);
        menuFichier.addSeparator();
        menuFichier.add(itemQuitter);
        navigation.add(menuFichier);
        
        menuAffichage = new JMenu("Affichage");
        itemAxes = new JCheckBox("Axes");
        itemAxes.setSelected(true);
        menuAffichage.add(itemAxes);
        itemGrille = new JCheckBox("Grille");
        itemGrille.setSelected(true);
        menuAffichage.add(itemGrille);
        itemGraduations = new JCheckBox("Graduations");
        itemGraduations.setSelected(true);
        menuAffichage.add(itemGraduations);
        
        itemPoints = new JCheckBox("Points");
        itemPoints.setSelected(true);
        menuAffichage.addSeparator();
        menuAffichage.add(itemPoints);
        
        itemFonctions = new JCheckBox("Fonctions");
        itemFonctions.setSelected(true);
        menuAffichage.add(itemFonctions);
        navigation.add(menuAffichage);
        
        menuAjouter = new JMenu("Ajouter");
        itemAjouterPoint = new JMenuItem("Point");
        itemAjouterFonction = new JMenuItem("Fonction");
        menuAjouter.add(itemAjouterPoint);
        menuAjouter.add(itemAjouterFonction);
        navigation.add(menuAjouter);
        
        /* Graph **************************************************************/
        grille = new Grille(this,this.nbCol,this.nbLig);
        
        /* Ajouts à la fenêtre ************************************************/
        fenetre.setJMenuBar(navigation);
        fenetre.add(grille, BorderLayout.CENTER);
        
        /* Affichage **********************************************************/
        fenetre.setVisible(true);
        
    }
    
    private void initListeners() {
        
        itemQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
                
        itemEffacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    int a = JOptionPane.showConfirmDialog(fenetre, "Etes-vous sûr de vouloir effacer le graph ?");
                    if (a==JOptionPane.YES_OPTION) {
                        controleur.gererDialogue(Commande.EFFACER_GRAPH);
                    }
                    
                } catch (ScriptException ex) {
                    Logger.getLogger(VuePrincipale.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
                
        itemAxes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                repeindre();
            }
        });
        
        itemGrille.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                repeindre();
            }
        });
        
        itemGraduations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                repeindre();
            }
        });
        
        itemFonctions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                repeindre();
            }
        });
        
        itemPoints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                repeindre();
            }
        });
        
        itemAjouterPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vueP.showVuePoints();
            }
        });
        
        itemAjouterFonction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                vueF.showVueFonction();
            }
        });
        
    }
    
    private void configureWindow(JFrame window) {
        window.setSize(800, 800);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void repeindre() {
        this.grille.repaint();
    }
    
    public String getFonction() {
        return this.vueF.getFonction();
    }

    public Controleur getControleur() {
        return controleur;
    }
        
    public Set<String> getSetFonctions() {
        return this.controleur.getSetFonctions();
    }
    
    public Set<String> getSetPoints() {
        return this.controleur.getSetPoints();
    }
    
    public double getX(String f, int i) {
        return this.getControleur().getX(f, i);
    }
    
    public double getY(String f, int i) {
        return this.getControleur().getY(f, i);
    }

    public JCheckBox getItemAxes() {
        return itemAxes;
    }
    
    public JCheckBox getItemGrille() {
        return itemGrille;
    }

    public JCheckBox getItemGraduations() {
        return itemGraduations;
    }

    public JCheckBox getItemFonctions() {
        return itemFonctions;
    }

    public JCheckBox getItemPoints() {
        return itemPoints;
    }
    
    public String getName() {
        return this.vueP.getName();
    }
    
    public double getPointX() {
        return this.vueP.getX();
    }
    
    public double getPointY() {
        return this.vueP.getY();
    }
    
    public double getPointsX(String p) {
        return this.controleur.getPointX(p);
    }
    
    public double getPointsY(String p) {
        return this.controleur.getPointY(p);
    }

    public JFrame getFenetre() {
        return fenetre;
    }
    
    
}
