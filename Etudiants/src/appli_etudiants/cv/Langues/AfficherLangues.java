/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants.cv.Langues;

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
public class AfficherLangues extends javax.swing.JPanel {

    private PanelIntermediaireLangues fenPanelinterLangues;
    private InterfaceGraphique fenetre;
    private JPanel boutonModifier;
    private JPanel boutonSupprimer;
    private JPanel boutonEnregistrer;

    public AfficherLangues(PanelIntermediaireLangues parent, InterfaceGraphique fenetre) {
        initComponents();
        this.fenetre = fenetre;
        this.fenPanelinterLangues = (PanelIntermediaireLangues) parent;


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

    public void setLabelLangues(String text) {
        this.jLabelLangues.setText(text);
    }

    public void setLabelDescription(String text) {
        this.jLabelDescription.setText(text);
    }

    public void setLabelId(String text) {
        this.jLabelId.setText(text);
    }

    public void FieldVisible(boolean text) {
        if (text == true) {


            this.jTextFieldDescription.setVisible(true);
            this.jTextFieldLangues.setVisible(true);



        } else if (text == false) {
            this.jTextFieldDescription.setVisible(false);
            this.jTextFieldLangues.setVisible(false);

            this.jLabelId.setVisible(false);
        }

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
                String langues = this.jTextFieldLangues.getText();
                String description = this.jTextFieldDescription.getText();
          



                requete.executeUpdate("INSERT INTO  cv_langues"
                        + " VALUES (null , '" + langues + "', '" + description + "' ,  '" + identifiant + "'  ) ");

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

                String langues = jTextFieldLangues.getText();
                String description = jTextFieldDescription.getText();

                String id = this.jLabelId.getText();

                requete.executeUpdate("UPDATE cv_langues SET langues= '" + langues + "', description = '" + description + "' "
                        + "WHERE identifiant = '" + identifiant + "' AND id_langues = '" + id + "' ");





            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
            }
        }

        this.boutonEnregistrer.setVisible(false);
        this.FieldVisible(false);
        this.fenPanelinterLangues.majLangues();
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
        

            requete.executeUpdate("DELETE FROM cv_langues WHERE identifiant = '" + identifiant + "' AND id_langues = '" + id + "' ");



        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
        }
              
              this.fenPanelinterLangues.majLangues();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelLangues = new javax.swing.JLabel();
        jLabelDescription = new javax.swing.JLabel();
        jLabelId = new javax.swing.JLabel();
        jTextFieldLangues = new javax.swing.JTextField();
        jTextFieldDescription = new javax.swing.JTextField();

        jLabelLangues.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelLangues.setText("Langues ex: Anglais");

        jLabelDescription.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelDescription.setText("Description ex: Niveau intermédiaire");

        jLabelId.setText("jLabelId");

        jTextFieldLangues.setText("Langues ex: Anglais");

        jTextFieldDescription.setText("Description ex: Niveau intermédiaire");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLangues, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(jLabelId)
                .addGap(180, 180, 180)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(jTextFieldLangues))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelLangues)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelDescription)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 3, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelId, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldLangues, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97))))
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
    private javax.swing.JLabel jLabelDescription;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelLangues;
    private javax.swing.JTextField jTextFieldDescription;
    private javax.swing.JTextField jTextFieldLangues;
    // End of variables declaration//GEN-END:variables
}
