/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants.cv.Formation;

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
public class AfficherFormation extends javax.swing.JPanel {

    private PanelIntermediaireFormation fenPanelInterFormation;
    private InterfaceGraphique fenetre;
    private JPanel boutonModifier;
    private JPanel boutonEnregistrer;
    private JPanel boutonSupprimer;


    /**
     * Creates new form AfficherFormation
     */
    public AfficherFormation(PanelIntermediaireFormation parent, InterfaceGraphique fenetre) {
        initComponents();
        this.fenPanelInterFormation = (PanelIntermediaireFormation) parent;
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

 
        
        this.jLabelId.setVisible(false);
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


            this.jTextFieldDiplome.setVisible(true);
            this.jTextFieldLieu.setVisible(true);
            this.jTextFieldPeriode.setVisible(true);
            this.jTextFieldPeriode.setVisible(true);

        

        } else if (text == false) {
            this.jTextFieldDiplome.setVisible(false);
            this.jTextFieldLieu.setVisible(false);
            this.jTextFieldPeriode.setVisible(false);
            this.jTextFieldPeriode.setVisible(false);
            this.jLabelId.setVisible(false);
        }

    }

    public void setLabelPeriode(String text) {
        this.jLabelPeriode.setText(text);
    }

    public void setLabelDiplome(String text) {
        this.jLabelDiplome.setText(text);
    }

    public void setLabelLieu(String text) {
        this.jLabelLieu.setText(text);
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
    
    
    
    public void Enregistrer(){
         if (this.jLabelId.getText().equals(Constante.labelIdDorigine)) {
            try {

                Class.forName("com.mysql.jdbc.Driver");
                String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
                Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);
                Statement requete = maConnexion.createStatement();

                String identifiant = this.fenetre.getIdentifiant();
                String periode = this.jTextFieldPeriode.getText();
                String diplome = this.jTextFieldDiplome.getText();
                String lieu = this.jTextFieldLieu.getText();
           


                requete.executeUpdate("INSERT INTO  cv_formation"
                        + " VALUES (null , '" + periode + "', '" + diplome + "' , '" + lieu + "' ,  '" + identifiant + "'  ) ");

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
                String lieu = jTextFieldLieu.getText();
                String diplome = jTextFieldDiplome.getText();
                String id = this.jLabelId.getText();

                requete.executeUpdate("UPDATE cv_formation SET periode= '" + periode + "', diplome='" + diplome + "', lieu = '" + lieu + "' "
                        + "WHERE identifiant = '" + identifiant + "' AND id_formation = '" + id + "' ");





            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
            }
        }
        this.boutonEnregistrer.setVisible(false);
        this.FieldVisible(false);
        this.fenPanelInterFormation.majFormation();
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
        

            requete.executeUpdate("DELETE FROM cv_formation WHERE identifiant = '" + identifiant + "' AND id_formation = '" + id + "' ");



        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
        }

        this.fenPanelInterFormation.majFormation();
        
    }
    public void Modifier(){
             this.FieldVisible(true);
        this.boutonEnregistrer.setVisible(true);
        this.boutonSupprimer.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelPeriode = new javax.swing.JLabel();
        jLabelDiplome = new javax.swing.JLabel();
        jLabelLieu = new javax.swing.JLabel();
        jTextFieldPeriode = new javax.swing.JTextField();
        jTextFieldLieu = new javax.swing.JTextField();
        jTextFieldDiplome = new javax.swing.JTextField();
        jLabelId = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(800, 120));
        setMinimumSize(new java.awt.Dimension(800, 120));

        jLabelPeriode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelPeriode.setText("Periode ex: 2003-2004");

        jLabelDiplome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelDiplome.setText("Diplome");

        jLabelLieu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelLieu.setText("Lieu ex: Pau");

        jTextFieldPeriode.setText("Periode ex: 2003-2004");

        jTextFieldLieu.setText("Lieu ex: Pau");
        jTextFieldLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLieuActionPerformed(evt);
            }
        });

        jTextFieldDiplome.setText("Diplome");

        jLabelId.setText("jLabelId");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelDiplome, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelPeriode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldDiplome)
                    .addComponent(jTextFieldPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(231, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPeriode)
                    .addComponent(jLabelLieu)
                    .addComponent(jTextFieldLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDiplome)
                    .addComponent(jTextFieldDiplome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelId)
                .addContainerGap(38, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLieuActionPerformed

    public void BoutonModifierVisible(boolean text) {
        this.boutonModifier.setVisible(text);
    }

    public void BoutonEnregistrerVisible(boolean text) {
        this.boutonEnregistrer.setVisible(text);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelDiplome;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelLieu;
    private javax.swing.JLabel jLabelPeriode;
    private javax.swing.JTextField jTextFieldDiplome;
    private javax.swing.JTextField jTextFieldLieu;
    private javax.swing.JTextField jTextFieldPeriode;
    // End of variables declaration//GEN-END:variables
}
