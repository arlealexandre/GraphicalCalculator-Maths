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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import package_controleur.Commande;

public class VueFonctions {
    
    private JFrame fenetre;
    
    private VuePrincipale vueP;
    
    private JPanel panelPrincipal;
    
    private JTextField fonction;
    private JButton ajouter;
    
    private Border initBorder;
    private Border wrongBorder;
    
    public VueFonctions(VuePrincipale vueP) {
        this.vueP = vueP;
        this.initMain();
        this.configureWindow(fenetre);
        this.initListeners();
    }
    
    private void initMain() {
        
        /* Déclaration fenêtre ************************************************/
        fenetre = new JFrame("Ajouter une fonction");
        fenetre.setLayout(new BorderLayout());
        
        /* Panel principal ****************************************************/
        panelPrincipal = new JPanel();
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        fonction = new JTextField();
        fonction.setColumns(10);
        initBorder = fonction.getBorder();
        wrongBorder = BorderFactory.createLineBorder(Color.RED, 2);
        ajouter = new JButton("Ajouter");
        
        panelPrincipal.add(new JLabel("F(x) = "));
        panelPrincipal.add(fonction);
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
                    if (!(fonction.getText().isEmpty()) && (fonction.getText().contains("x"))) {
                        vueP.getControleur().gererDialogue(Commande.TRACER_FONCT);
                        emptyField();
                    } else {
                        fonction.setBorder(wrongBorder);
                    }
                } catch (ScriptException ex) {
                    Logger.getLogger(VueFonctions.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        fonction.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                fonction.setBorder(initBorder);
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
        window.setSize(300, 80);
        window.setResizable(false);
    }
    
    public String getFonction() {
        return this.fonction.getText();
    }
    
    public void showVueFonction() {
        this.fenetre.setVisible(true);
    }
    
    public void emptyField() {
        this.fonction.setText("");
    }
        
}
