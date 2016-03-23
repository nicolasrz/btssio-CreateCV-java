/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants.cv.Informatique;

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
public class AfficherInformatique extends javax.swing.JPanel {

    private PanelIntermediaireInformatique fenPanelInterInformatique;
    private InterfaceGraphique fenetre;
    private JPanel boutonModifier;
    private JPanel boutonSupprimer;
    private JPanel boutonEnregistrer;


    public AfficherInformatique(PanelIntermediaireInformatique parent, InterfaceGraphique fenetre) {
        initComponents();
        this.fenetre = fenetre;
        this.fenPanelInterInformatique = (PanelIntermediaireInformatique) parent;

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

    public void FieldVisible(boolean text) {
        if (text == true) {


            this.jTextFieldDescription.setVisible(true);
            this.jTextFieldCompetences.setVisible(true);

            this.jTextFieldDescription.setText(this.jLabelDescription.getText());
            this.jTextFieldCompetences.setText(this.jLabelCompetences.getText());
        } else if (text == false) {
            this.jTextFieldDescription.setVisible(false);
            this.jTextFieldCompetences.setVisible(false);

            this.jLabelId.setVisible(false);
        }


    }

    public void setLabelCompetence(String text) {
        this.jLabelCompetences.setText(text);
    }

    public void setLabelDescription(String Text) {
        this.jLabelDescription.setText(Text);
    }

    public void setLabelId(String text) {
        this.jLabelId.setText(text);
    }
    
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
    
    public void Enregistrer(){
          if (this.jLabelId.getText().equals(Constante.labelIdDorigine)) {
            try {

                Class.forName("com.mysql.jdbc.Driver");
                String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
                Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);
                Statement requete = maConnexion.createStatement();

                String identifiant = this.fenetre.getIdentifiant();
                String competences = this.jTextFieldCompetences.getText();
                String description = this.jTextFieldDescription.getText();
          



                requete.executeUpdate("INSERT INTO  cv_informatique"
                        + " VALUES (null , '" + competences + "', '" + description + "' ,  '" + identifiant + "'  ) ");

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

                String description = jTextFieldDescription.getText();
                String competences = jTextFieldCompetences.getText();

                String id = this.jLabelId.getText();

                requete.executeUpdate("UPDATE cv_informatique SET description= '" + description + "', competences='" + competences + "' "
                        + "WHERE identifiant = '" + identifiant + "' AND id_informatique = '" + id + "' ");





            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
            }
            
        }
        this.boutonEnregistrer.setVisible(false);
        this.FieldVisible(false);
        this.fenPanelInterInformatique.majInformatique();
        
    }
    public void Modifier(){
          this.FieldVisible(true);
        this.boutonEnregistrer.setVisible(true);
        this.boutonSupprimer.setVisible(true);
    }
    public void Supprimer(){
           try {

            Class.forName("com.mysql.jdbc.Driver");

            String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
            Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);


            // requete
            Statement requete = maConnexion.createStatement();
            String identifiant = this.fenetre.getIdentifiant();

            String id = this.jLabelId.getText();


            requete.executeUpdate("DELETE FROM cv_informatique WHERE identifiant = '" + identifiant + "' AND id_informatique = '" + id + "' ");



        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
        }
           this.fenPanelInterInformatique.majInformatique();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelCompetences = new javax.swing.JLabel();
        jLabelDescription = new javax.swing.JLabel();
        jLabelId = new javax.swing.JLabel();
        jTextFieldCompetences = new javax.swing.JTextField();
        jTextFieldDescription = new javax.swing.JTextField();

        jLabelCompetences.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelCompetences.setText("Compétences ex: Administation serveur  linux");

        jLabelDescription.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelDescription.setText("Description x: DHCP SAMBA BIND");

        jLabelId.setText("jLabelId");

        jTextFieldCompetences.setText("Compétences ex: Administation serveur  linux");

        jTextFieldDescription.setText("Description x: DHCP SAMBA BIND");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabelCompetences, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                        .addComponent(jLabelDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabelId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldCompetences, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(177, 177, 177))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCompetences)
                    .addComponent(jTextFieldCompetences, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDescription)
                    .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jLabelId)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public void BoutonModifierVisible(boolean text) {
        this.boutonModifier.setVisible(text);
    }

    public void BoutonEnregistrerVisible(boolean text) {
        this.boutonEnregistrer.setVisible(text);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelCompetences;
    private javax.swing.JLabel jLabelDescription;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JTextField jTextFieldCompetences;
    private javax.swing.JTextField jTextFieldDescription;
    // End of variables declaration//GEN-END:variables
}
