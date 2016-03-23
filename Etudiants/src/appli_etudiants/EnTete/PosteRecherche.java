/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants.EnTete;

import appli_etudiants.InterfaceGraphique;
import appli_etudiants.classes.AfficheImage;
import appli_etudiants.classes.Constante;
import appli_etudiants.classes.ImagePanel;

import com.mysql.jdbc.Connection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Nicolas
 */
public class PosteRecherche extends javax.swing.JPanel {

    private InterfaceGraphique fenetre;
    private JPanel boutonModifier;
    private JPanel boutonEnregistrer;
    private String identifiant;
    private int id;
    private String recupJLabelId;

    public PosteRecherche(InterfaceGraphique fenetre) {
        initComponents();

        Dimension size = new Dimension(Constante.largeurCadre, Constante.hauteurCadre);
        this.setSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
        this.setMinimumSize(size);

        Color couleurDeFond = new Color(226, 225, 235);
        this.setBackground(couleurDeFond);

        this.fenetre = fenetre;

        this.recupJLabelId = "jLabelId";


        this.recupValeur();

   
        this.jLabelId.setVisible(false);

        this.jTextFieldTexteDaccroche.setVisible(false);




        boutonModifier = new JPanel();
        boutonModifier.setLayout(null);
        boutonModifier.setBounds(760, 90, 120, 30);
        this.add(boutonModifier);

        AfficheImage imageBoutonModifier = new AfficheImage("/appli_etudiants/image/btn_modif.png", 0, 0, 120, 30);
        boutonModifier.add(imageBoutonModifier);
        this.EventBoutonModifier();
        
        
        
        boutonEnregistrer = new JPanel();
        boutonEnregistrer.setLayout(null);
        boutonEnregistrer.setBounds(760,120,120,30);
        this.add(boutonEnregistrer);
        boutonEnregistrer.setVisible(false);
        
        AfficheImage imageBoutonEnregistrer = new AfficheImage("/appli_etudiants/image/btn_enregistrer.png", 0, 0, 120, 30);
        boutonEnregistrer.add(imageBoutonEnregistrer);
        this.EventBoutonEnregistrer();
        
        



    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelPosteRecherche = new javax.swing.JLabel();
        jLabelTexteDaccroche = new javax.swing.JLabel();
        jTextFieldTexteDaccroche = new javax.swing.JTextField();
        jLabelId = new javax.swing.JLabel();

        jLabelPosteRecherche.setText("Ex: Développeur Php");

        jLabelTexteDaccroche.setText("Texte d'accroche / Poste Recherché");

        jLabelId.setText("jLabelId");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTexteDaccroche, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelId)
                        .addGap(95, 95, 95)
                        .addComponent(jTextFieldTexteDaccroche, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelPosteRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTexteDaccroche)
                    .addComponent(jTextFieldTexteDaccroche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPosteRecherche)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setLabelTitre(String text) {
        this.jLabelPosteRecherche.setText(text);
    }

    public void serLabelId(String text) {
        this.jLabelId.setText(text);
    }

    public void recupValeur() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
            Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);
            Statement requete = maConnexion.createStatement();

            String identifiant = this.fenetre.getIdentifiant();



            ResultSet recupTitre = requete.executeQuery("SELECT * FROM cv_titre WHERE identifiant = '" + identifiant + "' ");
            while (recupTitre.next()) {
                this.id = (int) recupTitre.getInt(1);
                this.jLabelId.setText(recupTitre.getString(1));
                this.jLabelPosteRecherche.setText(recupTitre.getString("titre"));

            }


        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
        }
        this.jLabelPosteRecherche.setVisible(true);


    }

 

    public void EventBoutonModifier() {


        boutonModifier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {


                boutonModifier.removeAll();

                AfficheImage imageBoutonEnregistrer = new AfficheImage("/appli_etudiants/image/btn_modif_down.png", 0, 0, 120, 30);
                boutonModifier.add(imageBoutonEnregistrer);
                boutonModifier.repaint();
            }
        });



        boutonModifier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {

                boutonModifier.removeAll();

                AfficheImage imageBoutonEnregistrer = new AfficheImage("/appli_etudiants/image/btn_modif.png", 0, 0, 120, 30);
                boutonModifier.add(imageBoutonEnregistrer);
                boutonModifier.repaint();


            
                jTextFieldTexteDaccroche.setVisible(true);

                jTextFieldTexteDaccroche.setText(jLabelPosteRecherche.getText());
                boutonEnregistrer.setVisible(true);



            }
        });

    }
    
    
     public void EventBoutonEnregistrer() {


        boutonEnregistrer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {


                boutonEnregistrer.removeAll();

                AfficheImage imageBoutonEnregistrer = new AfficheImage("/appli_etudiants/image/btn_enregistrer_down.png", 0, 0, 120, 30);
                boutonEnregistrer.add(imageBoutonEnregistrer);
                boutonEnregistrer.repaint();
            }
        });



        boutonEnregistrer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {

                boutonEnregistrer.removeAll();

                AfficheImage imageBoutonEnregistrer = new AfficheImage("/appli_etudiants/image/btn_enregistrer.png", 0, 0, 120, 30);
                boutonEnregistrer.add(imageBoutonEnregistrer);
                boutonEnregistrer.repaint();
                
                
                
                
                Enregistrer();
                
                



            }
        });

    }
     
     public void Enregistrer(){
           if (this.jLabelId.getText().equals(this.recupJLabelId)) {


            try {

                Class.forName("com.mysql.jdbc.Driver");
                String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
                Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);
                Statement requete = maConnexion.createStatement();

                String identifiant = this.fenetre.getIdentifiant();

                String titre = this.jTextFieldTexteDaccroche.getText();


                requete.executeUpdate("INSERT INTO  cv_titre"
                        + " VALUES (null , '" + titre + "'  , '" + identifiant + "' ) ");



            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
            }
        } else {

            try {
         
                Class.forName("com.mysql.jdbc.Driver");
                String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
                Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);


                // requete
                Statement requete = maConnexion.createStatement();
                String identifiant = this.fenetre.getIdentifiant();

                String titre = jTextFieldTexteDaccroche.getText();


                String id = this.jLabelId.getText();

                requete.executeUpdate("UPDATE cv_titre SET titre= '" + titre + "' "
                        + "WHERE identifiant = '" + identifiant + "' AND id_titre = '" + id + "' ");




            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
            }


        }


        this.jTextFieldTexteDaccroche.setVisible(false);
        this.recupValeur();
        boutonEnregistrer.setVisible(false);

     }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelPosteRecherche;
    private javax.swing.JLabel jLabelTexteDaccroche;
    private javax.swing.JTextField jTextFieldTexteDaccroche;
    // End of variables declaration//GEN-END:variables
}
