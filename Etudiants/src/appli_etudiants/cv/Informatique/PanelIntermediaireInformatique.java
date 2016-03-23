/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants.cv.Informatique;

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
public class PanelIntermediaireInformatique extends javax.swing.JPanel {

 
    private InterfaceGraphique fenetre;
    private int lePremierId;
    private int leDernierId;
    private AfficherInformatique fenAfficherInformatique;

    public PanelIntermediaireInformatique( InterfaceGraphique fenetre) {
        this.fenetre = fenetre;
        

        Dimension size = new Dimension(this.getWidth(), Constante.hauteurPanelInter);
        this.setSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
        this.setMinimumSize(size);
        Color couleurConnexion = new Color(8, 0, 83);
        this.setBackground(couleurConnexion);

        this.recupereValeur();
    }

    public void recupereValeur() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
            Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);
            Statement requete = maConnexion.createStatement();

            String identifiant = this.fenetre.getIdentifiant();



            ResultSet recupInformatique = requete.executeQuery("SELECT * FROM cv_informatique WHERE identifiant = '" + identifiant + "' ");

            while (recupInformatique.next()) {
                this.AjouterInformatique();
                this.fenAfficherInformatique.setLabelId(recupInformatique.getString("id_informatique"));
                this.fenAfficherInformatique.setLabelCompetence(recupInformatique.getString("competences"));
                this.fenAfficherInformatique.setLabelDescription(recupInformatique.getString("description"));
               
            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
        }
        
      
    }

    public AfficherInformatique getFenAfficherInformatique() {
        return this.fenAfficherInformatique;
    }

    public void AjouterInformatique() {
        fenAfficherInformatique = new AfficherInformatique(this, fenetre);
        this.fenAfficherInformatique.setVisible(true);
        this.fenAfficherInformatique.setBounds(0, 0, Constante.largeurCadre, Constante.hauteurCadre);
        this.add(fenAfficherInformatique);
    }



    public void majInformatique() {
        this.removeAll();

        this.recupereValeur();

        this.repaint();
        this.validate();
    }
}
