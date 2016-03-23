/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants.cv.ExperiencePro;

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
 * @author n.ruiz
 */
public class PanelIntermediaireExpPro extends javax.swing.JPanel {


    private InterfaceGraphique fenetre;
    private AfficherExpererienceProfessionnelle fenAfficherExpPro;

    public PanelIntermediaireExpPro(InterfaceGraphique fenetre, AfficherExpererienceProfessionnelle fenAfficherExpPro) {
      
        this.fenetre = fenetre;
        this.fenAfficherExpPro=fenAfficherExpPro;


        Dimension size = new Dimension(this.getWidth(), Constante.hauteurPanelInter);
        this.setSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
        this.setMinimumSize(size);

        
        Color couleurConnexion = new Color(8, 0, 83);
        this.setBackground(couleurConnexion);
        this.recupereValeur();
    }

    /**
     * <p>Selectionne tout de la table cv_experience_professionnelle ou l'identifiant = l'utilisateur connecté <br>
     * il appelle la méthode AjouterExpPro()<br>
     * Une fois les valeurs récupérées il met les données dans le jLabel correspondant </p>
     */
    public void recupereValeur() {


        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
            Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);
            Statement requete = maConnexion.createStatement();

            String identifiant = this.fenetre.getIdentifiant();



            ResultSet recupExpPro = requete.executeQuery("SELECT * from cv_experience_professionnelle where identifiant = '" + identifiant + "' ");

            while (recupExpPro.next()) {
                this.AjouterExpPro();
                this.fenAfficherExpPro.setLabelDescriptionExperience(recupExpPro.getString("description"));
                this.fenAfficherExpPro.setLabelEmploi(recupExpPro.getString("emploi"));
                this.fenAfficherExpPro.setLabelEntreprise(recupExpPro.getString("entreprise"));
                this.fenAfficherExpPro.setLabelLieu(recupExpPro.getString("lieu"));
                this.fenAfficherExpPro.setLabelPeriode(recupExpPro.getString("periode"));
                this.fenAfficherExpPro.setLabelId(recupExpPro.getString("id_experience_professionnelle"));


            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
        }



    }
    
    /**
     * <p>Accesseur sur fenAfficherExpPro de type AfficherExpererienceProfessionnelle</p>
     * @return this.fenAfficherExpPro
     */

    public AfficherExpererienceProfessionnelle getFenAfficherExpPro() {
        return this.fenAfficherExpPro;
    }

    /**
     * <p>Créer un un nouveau panel avec des Jlabel et jTextField <br>
     * Le panel est visible, l'emplacement se fait automatiquement<br>
     * On l'ajoute dans le panel intermediaire qui sert d'affichage des tous les jPanel qui contiennent les données</p>
     */
    public void AjouterExpPro() {
        fenAfficherExpPro = new AfficherExpererienceProfessionnelle(this, fenetre);
        this.fenAfficherExpPro.setVisible(true);
        this.fenAfficherExpPro.setBounds(0, 0, Constante.largeurCadre, Constante.hauteurCadre);
        this.add(fenAfficherExpPro);

    }

    /**
     * <p>Appel des méthodes:
     * <ul>
     * <li>removeAll() </li>
     * <li>recupereValeur()</li>
     * <li>repaint()</li>
     * <li>validate()</li>
     * </ul>
     * </p>
     * 
     */
    public void majExpPro() {
        this.removeAll();
        this.recupereValeur();
        this.repaint();
        this.validate();
    }
}
