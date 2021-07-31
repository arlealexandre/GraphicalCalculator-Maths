package package_ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import package_controleur.Commande;

public class VuePoints {
    
    private JFrame fenetre;
    
    private VuePrincipale vueP;
    
    private JPanel panelPrincipal;
    
    private JButton ajouter;
    private JTextField champNom;
    private JTextField champX;
    private JTextField champY;
    
    private Border initBorderNom;
    private Border initBorderX;
    private Border initBorderY;
    private Border wrongBorder;
    
    public VuePoints(VuePrincipale vueP) {
        this.vueP = vueP;
        this.initMain();
        this.configureWindow(fenetre);
        this.initListeners();
    }
    
    private void initMain() {
        
        /* Déclaration fenêtre ************************************************/
        fenetre = new JFrame("Ajouter un point");
        fenetre.setLayout(new BorderLayout());
        
        /* Panel principal ****************************************************/
        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        champNom = new JTextField();
        champNom.setColumns(5);
        initBorderNom = champNom.getBorder();
        champX = new JTextField();
        champX.setColumns(5);
        initBorderX = champX.getBorder();
        champY = new JTextField();
        champY.setColumns(5);
        initBorderY = champY.getBorder();
        ajouter = new JButton("Ajouter");
        
        wrongBorder = BorderFactory.createLineBorder(Color.RED, 2);
        
        panelPrincipal.add(new JLabel("Nom : "));
        panelPrincipal.add(champNom);
        
        panelPrincipal.add(new JLabel("x = "));
        panelPrincipal.add(champX);
        
        panelPrincipal.add(new JLabel("y = "));
        panelPrincipal.add(champY);
        
        panelPrincipal.add(ajouter);
                
        /* Ajouts à la fenêtre ************************************************/
        fenetre.add(panelPrincipal, BorderLayout.NORTH);
        
        /* Affichage **********************************************************/
        fenetre.setVisible(false);
    }
    
    private void initListeners() {
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (!(champNom.getText().isEmpty()) && !(vueP.getSetPoints().contains(champNom.getText().toUpperCase()))) {
                        if (!(champX.getText().isEmpty())) {
                            if (!(champY.getText().isEmpty())) {
                                vueP.getControleur().gererDialogue(Commande.PLACER_POINT);
                                emptyField();
                            } else {
                                champY.setBorder(wrongBorder);
                            }
                        } else {
                            champX.setBorder(wrongBorder);
                        } 
                    } else {
                        champNom.setBorder(wrongBorder);
                    }
                    
                } catch (ScriptException ex) {
                    Logger.getLogger(VueFonctions.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        champNom.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                champNom.setBorder(initBorderNom);
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }
            
        });
        
        champX.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                champX.setBorder(initBorderX);
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }
            
        });
        
        champY.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                champY.setBorder(initBorderY);
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
            }
            
        });
    }
    
    private void configureWindow(JFrame window) {
        window.setSize(500, 80);
        window.setResizable(false);
    }
    
    public String getName() {
        return this.champNom.getText();
    }
    
    public double getX() {
        return Double.parseDouble(this.champX.getText());
    }
    
    public double getY() {
        return Double.parseDouble(this.champY.getText());
    }

    public void showVuePoints() {
        this.fenetre.setVisible(true);
    }
    
    public void emptyField() {
        this.champNom.setText("");
        this.champX.setText("");
        this.champY.setText("");
    }
}
