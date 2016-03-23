/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants.cv.Langues;

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
public class PanelIntermediaireLangues extends javax.swing.JPanel {

    
    private InterfaceGraphique fenetre;
    private AfficherLangues fenAfficherLangues;
    private int lePremierId;
    private int leDernierId;

    public PanelIntermediaireLangues( InterfaceGraphique fenetre) {
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

      
                ResultSet recupLangues = requete.executeQuery("SELECT * FROM cv_langues WHERE identifiant = '"+identifiant+"' ");
                
              while (recupLangues.next()) {
                    this.AjouterLangues();
                    this.fenAfficherLangues.setLabelLangues(recupLangues.getString("langues"));
                    this.fenAfficherLangues.setLabelDescription(recupLangues.getString("description"));
                    this.fenAfficherLangues.setLabelId(recupLangues.getString("id_langues"));
                }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
        }
    }


    public AfficherLangues getFenAfficherLangues() {
        return this.fenAfficherLangues;
    }

    public void AjouterLangues() {
        fenAfficherLangues = new AfficherLangues(this, fenetre);
        this.fenAfficherLangues.setVisible(true);
        this.fenAfficherLangues.setBounds(0, 0, Constante.largeurCadre, Constante.hauteurCadre);
        this.add(fenAfficherLangues);

    }

    
    public void majLangues(){
        this.removeAll();

        this.recupereValeur();

        this.repaint();
        this.validate();
    }
}
