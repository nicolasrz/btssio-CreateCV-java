/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants.cv.Formation;

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
public class PanelIntermediaireFormation extends javax.swing.JPanel {

  
    private InterfaceGraphique fenetre;
    private AfficherFormation fenAfficherFormation;

    public PanelIntermediaireFormation( InterfaceGraphique fenetre) {

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



            ResultSet recupFormation = requete.executeQuery("SELECT * FROM cv_formation WHERE identifiant = '" + identifiant + "' ");

            while (recupFormation.next()) {
                this.AjouterFormation();
                this.fenAfficherFormation.setLabelDiplome(recupFormation.getString("diplome"));
                this.fenAfficherFormation.setLabelPeriode(recupFormation.getString("periode"));
                this.fenAfficherFormation.setLabelLieu(recupFormation.getString("lieu"));
                this.fenAfficherFormation.setLabelId(recupFormation.getString("id_formation"));


            }


        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
        }
    }

    public AfficherFormation getFenAfficherFormation() {
        return this.fenAfficherFormation;
    }

    public void AjouterFormation() {
        fenAfficherFormation = new AfficherFormation(this, fenetre);
        this.fenAfficherFormation.setVisible(true);
        this.fenAfficherFormation.setBounds(0, 0, Constante.largeurCadre, Constante.hauteurCadre);
        this.add(fenAfficherFormation);
    }

    
    public void majFormation() {
        this.removeAll();
        this.recupereValeur();
        this.repaint();
        this.validate();
    }
}
