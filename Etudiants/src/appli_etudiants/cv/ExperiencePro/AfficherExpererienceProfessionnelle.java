/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants.cv.ExperiencePro;

import appli_etudiants.InterfaceGraphique;
import appli_etudiants.classes.AfficheImage;
import appli_etudiants.classes.Constante;
import com.mysql.jdbc.Connection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author n.ruiz
 */
public class AfficherExpererienceProfessionnelle extends javax.swing.JPanel {

    private InterfaceGraphique fenetre;
    private PanelIntermediaireExpPro fenPanelIntermediaireExpPro;
    private JPanel boutonModifier;
    private JPanel boutonEnregistrer;
    private JPanel boutonSupprimer;

    public AfficherExpererienceProfessionnelle(PanelIntermediaireExpPro parent, InterfaceGraphique fenetre) {
        initComponents();
        this.fenPanelIntermediaireExpPro = (PanelIntermediaireExpPro) parent;
        this.fenetre = fenetre;


        Dimension size = new Dimension(Constante.largeurCadre, Constante.hauteurCadre);
        this.setSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
        this.setMinimumSize(size);

        Color couleurDeFond = new Color(226, 225, 235);
        this.setBackground(couleurDeFond);

        Border blanc = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white);
        this.setBorder(blanc);



        
        this.FieldVisible(false);
        
        
        
        
        
        boutonModifier = new JPanel();
        boutonModifier.setLayout(null);
        boutonModifier.setBounds(780,10,120,30);
        this.add(boutonModifier);
        
        AfficheImage imageBoutonModifier = new AfficheImage("/appli_etudiants/image/btn_modif.png", 0, 0, 120, 30);
        boutonModifier.add(imageBoutonModifier);
        this.EventBoutonModifier();
        
        
        boutonEnregistrer = new JPanel();
        boutonEnregistrer.setLayout(null);
        boutonEnregistrer.setBounds(855,50,32,32);
        this.add(boutonEnregistrer);
        this.boutonEnregistrer.setVisible(false);
        
        AfficheImage imageBoutonEnregistrer = new AfficheImage("/appli_etudiants/image/iconeEnregistrer.png",0,0,32,32);
        boutonEnregistrer.add(imageBoutonEnregistrer);
        this.EventBoutonEnregistrer();
        
        
        boutonSupprimer = new JPanel();
        boutonSupprimer.setLayout(null);
        boutonSupprimer.setBounds(810,50,32,32);
        this.add(boutonSupprimer);
        this.boutonSupprimer.setVisible(false);
        
        AfficheImage imageBoutonSupprimer = new AfficheImage("/appli_etudiants/image/iconeSupprimer.png",0,0,32,32);
        boutonSupprimer.add(imageBoutonSupprimer);
        this.EventBoutonSupprimer();






    }
    
/**
 * <p>Methode permettant de rendre <u> TOUS les champs</u> visibles ou non
 * @param text  </p>
 */
    public void FieldVisible(boolean text) {
        if (text == true) {
            this.jTextFieldDescription.setVisible(true);
            this.jTextFieldEmploi.setVisible(true);
            this.jTextFieldEntreprise.setVisible(true);
            this.jTextFieldPeriode.setVisible(true);
            this.jTextFieldLieu.setVisible(true);




        } else if (text == false) {
            this.jTextFieldDescription.setVisible(false);
            this.jTextFieldEmploi.setVisible(false);
            this.jTextFieldEntreprise.setVisible(false);
            this.jTextFieldPeriode.setVisible(false);
            this.jTextFieldLieu.setVisible(false);
            this.jLabelId.setVisible(false);
        }
    }
    
    // bouton enregistrer          
    public void EventBoutonEnregistrer(){
        
        boutonEnregistrer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {


                
                 FieldVisible(false);
                  boutonModifier.setVisible(false);
                  boutonSupprimer.setVisible(false);
                

                  Enregistrer();

            }
        });
    }
    /**
     * <p>Permet d'enregistrer les données dans la base .</p>
     */
    
    public void Enregistrer(){
         if (this.jLabelId.getText().equals(Constante.labelIdDorigine)) {


            try {
                Class.forName("com.mysql.jdbc.Driver");
                String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
                Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);
                Statement requete = maConnexion.createStatement();

                String identifiant = this.fenetre.getIdentifiant();
                String description = this.jTextFieldDescription.getText();
                String emploi = this.jTextFieldEmploi.getText();
                String periode = this.jTextFieldPeriode.getText();
                String lieu = this.jTextFieldLieu.getText();
                String entreprise = this.jTextFieldEntreprise.getText();


                requete.executeUpdate("INSERT INTO  cv_experience_professionnelle"
                        + " VALUES (null  , '" + periode + "', '" + emploi + "' , '" + description + "' , '" + entreprise + "' , '" + lieu + "' , '" + identifiant + "'  ) ");

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

                String periode = jTextFieldPeriode.getText();
                String emploi = jTextFieldEmploi.getText();
                String description = jTextFieldDescription.getText();
                String entreprise = jTextFieldEntreprise.getText();
                String lieu = jTextFieldLieu.getText();
                String id = this.jLabelId.getText();

                requete.executeUpdate("UPDATE cv_experience_professionnelle SET periode= '" + periode + "', emploi='" + emploi + "', description = '" + description + "', "
                        + "entreprise= '" + entreprise + "', lieu = '" + lieu + "' WHERE identifiant = '" + identifiant + "' AND id_experience_professionnelle = '" + id + "' ");

            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
            }

        }




         this.boutonEnregistrer.setVisible(false);
    
        this.FieldVisible(false);
        this.fenPanelIntermediaireExpPro.majExpPro();
    }
    
    
    
   /**
    *<p> Evenement lors du clic sur le bouton Modifier<br>
    * Changement des images sur le bouton lors du down et up<br>
    * Les champs deviennent visibles, et les boutons enregistrer et modifier deviennent visibles également<br></p>
    */
    public void EventBoutonModifier(){
        
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
                
                  FieldVisible(true);
                  boutonEnregistrer.setVisible(true);
                  boutonSupprimer.setVisible(true);
            }});
        
    }
    /**
     * <p>Le champs deviennent visibles<br>
     * le bouton enregistrer devient visible<br>
     * lebouton supprimer devient visible</p>
     */
    public void Modifier(){
            this.FieldVisible(true);
            this.boutonEnregistrer.setVisible(true);
            this.boutonSupprimer.setVisible(true);
      
    }
    
    
    
    
    
    
    
    
    
    
    
    /**
     * <p>Evenement lors du clic sur le bouton Supprimer<br>
     * Les champs deviennent non visibles <br>
     * le bouton modifier devient non visible<br>
     * le bouton supprimer devient non visible</p>
     */
    public void EventBoutonSupprimer(){
        
        boutonSupprimer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {


                
                 FieldVisible(false);
                  boutonModifier.setVisible(false);
                  boutonSupprimer.setVisible(false);
                

                  Supprimer();

            }
        });

    }
    
    
    /**
     * <p>Supprimer les données du champs cv_experience_professionnelle<br>
     * avec la ligne correspondante à l'id </p>
     */
    public void Supprimer(){
        
    try {

            Class.forName("com.mysql.jdbc.Driver");

            String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
            Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);


            // requete
            Statement requete = maConnexion.createStatement();
            String identifiant = this.fenetre.getIdentifiant();

            String id = this.jLabelId.getText();


            requete.executeUpdate("DELETE FROM cv_experience_professionnelle WHERE identifiant = '" + identifiant + "' AND id_experience_professionnelle = '" + id + "' ");



        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
        }

        this.fenPanelIntermediaireExpPro.majExpPro();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelPeriodeExperience = new javax.swing.JLabel();
        jLabelEntreprise = new javax.swing.JLabel();
        jLabelEmploi = new javax.swing.JLabel();
        jLabelDescriptionExperience = new javax.swing.JLabel();
        jLabelLieuExperience = new javax.swing.JLabel();
        jTextFieldPeriode = new javax.swing.JTextField();
        jTextFieldEntreprise = new javax.swing.JTextField();
        jTextFieldEmploi = new javax.swing.JTextField();
        jTextFieldDescription = new javax.swing.JTextField();
        jTextFieldLieu = new javax.swing.JTextField();
        jLabelId = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1054, 120));
        setMinimumSize(new java.awt.Dimension(1054, 120));

        jLabelPeriodeExperience.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelPeriodeExperience.setText("Periode ex: 2003-2004");

        jLabelEntreprise.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelEntreprise.setText("Entreprise ex: Microsoft");

        jLabelEmploi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelEmploi.setText("Emploi ex: Développeur");

        jLabelDescriptionExperience.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelDescriptionExperience.setText("Description: Développemen de site web");

        jLabelLieuExperience.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelLieuExperience.setText("Lieu ex: Pau");

        jTextFieldPeriode.setText("Periode ex: 2003-2004");

        jTextFieldEntreprise.setText("Entreprise ex: Microsoft");

        jTextFieldEmploi.setText("Emploi ex: Développeur");
        jTextFieldEmploi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmploiActionPerformed(evt);
            }
        });

        jTextFieldDescription.setText("Description: Développemen de site web");

        jTextFieldLieu.setText("Lieu ex: Pau");

        jLabelId.setText("jLabelId");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelEmploi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelPeriodeExperience, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabelEntreprise, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelDescriptionExperience, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelLieuExperience, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addComponent(jLabelId)))
                .addGap(127, 127, 127)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEmploi, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jTextFieldEntreprise, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(291, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPeriodeExperience)
                    .addComponent(jLabelEntreprise)
                    .addComponent(jTextFieldPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEntreprise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEmploi)
                    .addComponent(jTextFieldEmploi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDescriptionExperience)
                    .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLieuExperience)
                    .addComponent(jTextFieldLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Cette méthode est à supprimer !
    private void jTextFieldEmploiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmploiActionPerformed
    }//GEN-LAST:event_jTextFieldEmploiActionPerformed

    /**
     * <p> Choix de la visibilité du bouton Modifier grâce au paramètre<br>
     * @param text<p> 
     */
    public void BoutonModifierVisible(boolean text) {
        this.boutonModifier.setVisible(text);
    }
    /**
     * Choix de la visibilité du bouton Enregistrer grâce au paramètre<br>
     * @param text </p>
     */

    public void BoutonEnregistrerVisible(boolean text) {
        this.boutonEnregistrer.setVisible(text);
    }

    /**
     * <p>mutateur sur le label Description Experience</p>
     * @param text 
     */
    public void setLabelDescriptionExperience(String text) {
        this.jLabelDescriptionExperience.setText(text);
    }
    
    /**
     * <p>Mutateur sur le label Emploi</p>
     * @param text 
     */

    public void setLabelEmploi(String text) {
        this.jLabelEmploi.setText(text);
    }
    
    /**
     * <p>Mutateur sur le label Entreprise</p>
     * @param text 
     */

    public void setLabelEntreprise(String text) {
        this.jLabelEntreprise.setText(text);
    }
    

/**
 * <p>Mutateur sur le label Lieu </p>
 * @param text 
 */
    public void setLabelLieu(String text) {
        this.jLabelLieuExperience.setText(text);
    }
    
    
/**
 * <p>Mutateur sur le label Periode </p>
 * @param text 
 */
    public void setLabelPeriode(String text) {
        this.jLabelPeriodeExperience.setText(text);
    }

    /**
     * <p>Mutateur sur le label Id </p>
     * @param text 
     */
    public void setLabelId(String text) {
        this.jLabelId.setText(text);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelDescriptionExperience;
    private javax.swing.JLabel jLabelEmploi;
    private javax.swing.JLabel jLabelEntreprise;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelLieuExperience;
    private javax.swing.JLabel jLabelPeriodeExperience;
    private javax.swing.JTextField jTextFieldDescription;
    private javax.swing.JTextField jTextFieldEmploi;
    private javax.swing.JTextField jTextFieldEntreprise;
    private javax.swing.JTextField jTextFieldLieu;
    private javax.swing.JTextField jTextFieldPeriode;
    // End of variables declaration//GEN-END:variables
}
