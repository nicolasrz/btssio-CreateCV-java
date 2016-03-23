/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants.Administration;

import appli_etudiants.Connexion;
import appli_etudiants.InterfaceGraphique;
import appli_etudiants.Outils;
import appli_etudiants.classes.Constante;
import com.mysql.jdbc.Connection;
import java.awt.Color;
import java.awt.Dimension;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Nicolas
 */
public class ConnexionAdministration extends javax.swing.JDialog {

    private InterfaceGraphique fenetre;
    private Outils fenOutils;
    private String admin;
    private String recupLogin;
    private String recupPassword;

    public ConnexionAdministration(InterfaceGraphique parent, boolean modal, Outils fenOutils) {
        super(parent, modal);
        initComponents();
        this.fenOutils = fenOutils;
        this.fenetre = (InterfaceGraphique) fenetre;
        this.getContentPane().setBackground(Color.darkGray);

        Dimension size = new Dimension(Constante.largeurJdialog, Constante.hauteurJdialog);
        this.setSize(size);
        this.setLocationRelativeTo(fenetre);


        this.jLabelConnexionReussie.setVisible(false);
        this.jLabelLoginOuPassIncorrect.setVisible(false);
        this.jLabelCvCreeJm.setVisible(false);

        this.jButtonCreerCvTest.setVisible(false);
        this.jButtonAfficherCvDispo.setVisible(false);



    }

    public void testConnexionAdmin() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
            Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);
            Statement requete = maConnexion.createStatement();

            String login = this.jTextFieldLogin.getText();
            String password = this.jPasswordFieldPass.getText();


            //application du cryptage md5 au password
            // ici on appelle md5 membre static de la classe outils
            password = Outils.md5(password);

            ResultSet recupAdmin = requete.executeQuery("SELECT * FROM utilisateurs WHERE identifiant = '" + login + "' "
                    + "AND mot_de_passe = '" + password + "' ");




            while (recupAdmin.next()) {
                this.recupLogin = recupAdmin.getString("identifiant");
                this.recupPassword = recupAdmin.getString("mot_de_passe");

            }

            if (login.equals(this.recupLogin) && password.equals(this.recupPassword)) {

                this.jButtonConnexion.setVisible(false);
                this.jLabelConnexionReussie.setVisible(true);
                this.jLabelLoginOuPassIncorrect.setVisible(false);

                this.jButtonCreerCvTest.setVisible(true);
                this.jButtonAfficherCvDispo.setVisible(true);

                this.jTextFieldLogin.setVisible(false);
                this.jPasswordFieldPass.setVisible(false);

            } else {
                this.jLabelLoginOuPassIncorrect.setVisible(true);
                this.jLabelConnexionReussie.setVisible(false);


            }










        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelModeAdmin = new javax.swing.JLabel();
        jTextFieldLogin = new javax.swing.JTextField();
        jPasswordFieldPass = new javax.swing.JPasswordField();
        jButtonConnexion = new javax.swing.JButton();
        jLabelConnexionReussie = new javax.swing.JLabel();
        jLabelLoginOuPassIncorrect = new javax.swing.JLabel();
        jButtonCreerCvTest = new javax.swing.JButton();
        jButtonAfficherCvDispo = new javax.swing.JButton();
        jLabelCvCreeJm = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabelModeAdmin.setForeground(new java.awt.Color(240, 240, 240));
        jLabelModeAdmin.setText("Mode admin");

        jTextFieldLogin.setText("Login...");

        jPasswordFieldPass.setText("aaaaaa");

        jButtonConnexion.setText("Connexion");
        jButtonConnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConnexionActionPerformed(evt);
            }
        });

        jLabelConnexionReussie.setForeground(java.awt.Color.red);
        jLabelConnexionReussie.setText("Connexion Réussie");

        jLabelLoginOuPassIncorrect.setForeground(java.awt.Color.red);
        jLabelLoginOuPassIncorrect.setText("Login ou Password incorrect");

        jButtonCreerCvTest.setText("Créer un Cv Test");
        jButtonCreerCvTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreerCvTestActionPerformed(evt);
            }
        });

        jButtonAfficherCvDispo.setText("Afficher les Cv Disponibles");

        jLabelCvCreeJm.setForeground(new java.awt.Color(0, 153, 0));
        jLabelCvCreeJm.setText("Cv créé jm/jm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelLoginOuPassIncorrect, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(734, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelConnexionReussie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelModeAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldLogin)
                                    .addComponent(jPasswordFieldPass, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jButtonConnexion)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonCreerCvTest, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonAfficherCvDispo))
                            .addComponent(jLabelCvCreeJm, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(86, 86, 86))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelModeAdmin)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCreerCvTest)
                    .addComponent(jButtonAfficherCvDispo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordFieldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonConnexion)
                    .addComponent(jLabelCvCreeJm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelConnexionReussie)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelLoginOuPassIncorrect)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConnexionActionPerformed
        this.testConnexionAdmin();
    }//GEN-LAST:event_jButtonConnexionActionPerformed

    private void jButtonCreerCvTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreerCvTestActionPerformed
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
            Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);
            Statement requete = maConnexion.createStatement();

            String identifiant = "Jm";
            String password = "jm";

            password = Outils.md5(password);

            requete.executeUpdate("DELETE FROM utilisateurs WHERE identifiant = '" + identifiant + "' ");
            requete.executeUpdate("INSERT INTO utilisateurs VALUES ('" + identifiant + "', '" + password + "', "
                    + "'Sarrat' , 'Jean-Marc' , 'jm.sr@gmail.com' "
                    + ", '0664929496', '17/12/1990' , "
                    + " '14 rue des vignes', 'Barbazan-Debat' , '65690', 'France'  )");



            requete.executeUpdate("INSERT INTO  cv_titre"
                    + " VALUES (null  , 'Consultant Business Informatique',  '" + identifiant + "'  ) ");



            requete.executeUpdate("INSERT INTO cv_experience_professionnelle VALUES(null , 'Mai 2011 - Septembre 2011' , "
                    + " 'Stagiaire' , 'Reprise et amélioration de la BDD interne ' ,"
                    + " 'Louis Vuitton',"
                    + " 'Paris' , '" + identifiant + "' )");



            requete.executeUpdate("INSERT INTO cv_formation VALUES (null , '2010-2013' , 'Ingénieur des Systèmes d Information option Business Intelligence', 'EFFREI Paris' , '" + identifiant + "'   )");




            requete.executeUpdate("INSERT INTO cv_langues VALUES (null , 'Anglais' , ' TOEIC 940', '" + identifiant + "') ");




            requete.executeUpdate("INSERT INTO cv_langues VALUES (null, 'Allemand', 'Niveau scolaire - 5 ans', '" + identifiant + "') ");




            requete.executeUpdate("INSERT INTO cv_informatique VALUES (null , 'Système d exploitation' , 'Environnement Windows, Mac, Linux connus' ,  '" + identifiant + "' ) ");






            requete.executeUpdate("INSERT INTO cv_informatique VALUES (null, 'Bureautique' , 'Maitrise de la suite Office' ,  '" + identifiant + "' ) ");










            requete.executeUpdate("INSERT INTO cv_centres_dinteret VALUES (null , 'Les nouvelles technologies', 'Passion pour la robotique  et la domotique plus particulièrement', '" + identifiant + "' ) ");



            requete.executeUpdate("INSERT INTO cv_centres_dinteret VALUES (null, 'Sport', 'Pratique du judo en club, et du foot entre amis', '" + identifiant + "' ) ");

            this.jLabelCvCreeJm.setVisible(true);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonCreerCvTestActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAfficherCvDispo;
    private javax.swing.JButton jButtonConnexion;
    private javax.swing.JButton jButtonCreerCvTest;
    private javax.swing.JLabel jLabelConnexionReussie;
    private javax.swing.JLabel jLabelCvCreeJm;
    private javax.swing.JLabel jLabelLoginOuPassIncorrect;
    private javax.swing.JLabel jLabelModeAdmin;
    private javax.swing.JPasswordField jPasswordFieldPass;
    private javax.swing.JTextField jTextFieldLogin;
    // End of variables declaration//GEN-END:variables
}
