/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants.cv.CentresDinteret;

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
 * @author Nicolas
 */
public class AfficherCentresDinteret extends javax.swing.JPanel {

    private InterfaceGraphique fenetre;
    private PanelIntermediaireCentresDinteret fenPanelInterCentresDinteret;
    private JPanel boutonModifier;
    private JPanel boutonEnregistrer;
    private JPanel boutonSupprimer;
 

    public AfficherCentresDinteret(PanelIntermediaireCentresDinteret parent, InterfaceGraphique fenetre) {
        initComponents();
        this.fenetre = fenetre;
        this.fenPanelInterCentresDinteret = (PanelIntermediaireCentresDinteret) parent;

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
            this.jTextFieldcentresDinteret.setVisible(true);

        } else if (text == false) {
            this.jTextFieldDescription.setVisible(false);
            this.jTextFieldcentresDinteret.setVisible(false);
            this.jLabelId.setVisible(false);

        }


    }

    public void setLabelCentresDinteret(String text) {
        this.jLabelCentresDinteret.setText(text);
    }

    public void setLabelDescription(String text) {
        this.jLabelDescription.setText(text);
    }

    public void setLabelId(String text) {
        this.jLabelId.setText(text);
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
                
                  FieldVisible(true);
                  boutonEnregistrer.setVisible(true);
                  boutonSupprimer.setVisible(true);

          

                
            }
        });

    }
     
     
     
     
     
     
     
     
     
     
      public void EventBoutonEnregistrer() {


        boutonEnregistrer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {


                
                 FieldVisible(false);
                  boutonModifier.setVisible(false);
                  boutonSupprimer.setVisible(false);
                

                  Enregistrer();

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
                String centresDinteret = jTextFieldcentresDinteret.getText();
                String description = jTextFieldDescription.getText();
        


                requete.executeUpdate("INSERT INTO  cv_centres_dinteret"
                        + " VALUES (null , '" + centresDinteret + "', '" + description + "' , '" + identifiant + "' ) ");


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

                String centresDinteret = jTextFieldcentresDinteret.getText();
                String description = jTextFieldDescription.getText();

                String id = this.jLabelId.getText();

                requete.executeUpdate("UPDATE cv_centres_dinteret SET centres_dinteret= '" + centresDinteret + "', cd_description='" + description + "' "
                        + "WHERE identifiant = '" + identifiant + "' AND id_centres_dinteret = '" + id + "' ");




            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
            }
        }



  this.fenPanelInterCentresDinteret.majCentreDinteret();
      }
      
      
      
      
   public void EventBoutonSupprimer() {


        boutonSupprimer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {


                
                 FieldVisible(false);
                  boutonModifier.setVisible(false);
                  boutonSupprimer.setVisible(false);
                

                  Supprimer();

            }
        });



    }
        
        
        private void Supprimer(){
              try {

            Class.forName("com.mysql.jdbc.Driver");

            String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
            Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);


            // requete
            Statement requete = maConnexion.createStatement();
            String identifiant = this.fenetre.getIdentifiant();

            String id = this.jLabelId.getText();
        

            requete.executeUpdate("DELETE FROM cv_centres_dinteret WHERE identifiant = '" + identifiant + "' AND id_centres_dinteret = '" + id + "' ");



        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
        }

  this.fenPanelInterCentresDinteret.majCentreDinteret();
        }
      
      
      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelCentresDinteret = new javax.swing.JLabel();
        jLabelDescription = new javax.swing.JLabel();
        jTextFieldcentresDinteret = new javax.swing.JTextField();
        jLabelId = new javax.swing.JLabel();
        jTextFieldDescription = new javax.swing.JTextField();

        jLabelCentresDinteret.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelCentresDinteret.setText("Centres d Interêts ex: Technologies ");

        jLabelDescription.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelDescription.setText("Description ex: domotique ");

        jTextFieldcentresDinteret.setText("Centres d Interêts ex: Technologies ");

        jLabelId.setText("jLabelId");

        jTextFieldDescription.setText("Description ex: domotique ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelCentresDinteret, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelId)
                        .addGap(0, 104, Short.MAX_VALUE))
                    .addComponent(jLabelDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(146, 146, 146))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldcentresDinteret, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelCentresDinteret)
                        .addComponent(jLabelId))
                    .addComponent(jTextFieldcentresDinteret, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDescription)
                    .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(121, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    public void BoutonModifierVisible(boolean text) {
        this.boutonModifier.setVisible(text);
    }

    public void BoutonEnregistrerVisible(boolean text) {
        this.boutonEnregistrer.setVisible(text);
    }
    public String getJlabelId(){
        return this.jLabelId.getText();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelCentresDinteret;
    private javax.swing.JLabel jLabelDescription;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JTextField jTextFieldDescription;
    private javax.swing.JTextField jTextFieldcentresDinteret;
    // End of variables declaration//GEN-END:variables
}
