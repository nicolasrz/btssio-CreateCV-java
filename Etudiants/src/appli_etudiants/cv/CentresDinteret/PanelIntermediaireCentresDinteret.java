/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants.cv.CentresDinteret;

import appli_etudiants.InterfaceGraphique;
import appli_etudiants.classes.Constante;
import com.mysql.jdbc.Connection;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Nicolas
 */
public class PanelIntermediaireCentresDinteret extends javax.swing.JPanel {
private InterfaceGraphique fenetre;

private AfficherCentresDinteret fenAfficherCentresDinteret;


    public PanelIntermediaireCentresDinteret( InterfaceGraphique fenetre) {
        this.fenetre = fenetre;
    
        
        Dimension size = new Dimension(this.getWidth(),Constante.hauteurPanelInter);
        this.setSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
        this.setMinimumSize(size);
        
        this.recupereValeur();
        
       Color couleurConnexion = new Color(8, 0, 83);
        this.setBackground(couleurConnexion);
        
    
    
    }
    
    public void recupereValeur(){
        try {
                Class.forName("com.mysql.jdbc.Driver");
                String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
                Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);
                Statement requete = maConnexion.createStatement();

                String identifiant = this.fenetre.getIdentifiant();

           
          
             //tant que le premier id n'atteint pas le dernier id
             // je crée, et j'affiche AfficherExp avec les incentres_dinterets correspondants à l'id
           
                     ResultSet recupCentresDinteret = requete.executeQuery("SELECT * FROM cv_centres_dinteret "
                     + "WHERE identifiant = '"+identifiant+"' ");
           
                     
                        
                        
                                          
                            while (recupCentresDinteret.next()){
                            this.AjouterCentresDinteret();
                            this.fenAfficherCentresDinteret.setLabelCentresDinteret(recupCentresDinteret.getString("centres_dinteret"));
                            this.fenAfficherCentresDinteret.setLabelDescription(recupCentresDinteret.getString("cd_description"));
                            this.fenAfficherCentresDinteret.setLabelId(recupCentresDinteret.getString("id_centres_dinteret"));
                           
                            }
                     

                            
                                  
                       
                
                       
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
        }
    }

    
    public AfficherCentresDinteret getFenAfficherCentresDinteret(){
        return this.fenAfficherCentresDinteret;
    }
    
    
    public void AjouterCentresDinteret(){
fenAfficherCentresDinteret = new AfficherCentresDinteret(this,fenetre);
this.fenAfficherCentresDinteret.setVisible(true);
this.fenAfficherCentresDinteret.setBounds(0,0,Constante.largeurCadre,Constante.hauteurCadre);
this.add(fenAfficherCentresDinteret);
        
    }
    
    public void majCentreDinteret(){
        this.removeAll();

        this.recupereValeur();

        this.repaint();
        this.validate();
    }
    
}
