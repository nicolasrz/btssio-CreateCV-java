/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants;

import appli_etudiants.classes.AfficheImage;
import appli_etudiants.classes.Constante;
import com.mysql.jdbc.Connection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
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
public class InformationsGenerales extends javax.swing.JPanel {

     private JPanel logoInformationsGenerales;
    private JPanel separateur;
    private InterfaceGraphique fenetre;
    private String identifiant;
    private JPanel boutonModifier;
    private JPanel fieldDessin;
    private JPanel boutonEnregistrer;
    private UtilisateurConnecte fenUtilisateurConnecte;
    private PanelPrincipal fenPanelPrincipal;

    

    public InformationsGenerales(InterfaceGraphique fenetre,PanelPrincipal parent) {
        initComponents();
        this.fenPanelPrincipal=(PanelPrincipal) parent;
        this.fenetre = fenetre;
        this.identifiant = this.fenetre.getIdentifiant();

        Dimension taille = new Dimension(Constante.largeurFenetre, 350);
        this.setSize(taille);
        this.setPreferredSize(taille);
        this.setMaximumSize(taille);
        this.setMinimumSize(taille);

        Color couleurDeFond = new Color(226, 225, 235);
        this.setBackground(couleurDeFond);

       recupInformationsGenerales();
       this.SetTextFieldInformations();
      
      
        fieldDessin = new JPanel();
        fieldDessin.setBounds(1, 1, 1,1);
        fieldDessin.setBackground(couleurDeFond);
        this.add(fieldDessin);



        this.FieldVisible(false);


        logoInformationsGenerales = new JPanel();
        logoInformationsGenerales.setBounds(40, 30, 220, 240);
        logoInformationsGenerales.setLayout(null);
        this.add(logoInformationsGenerales);

        AfficheImage imageLogoInformationsGenerales = new AfficheImage("/appli_etudiants/image/picto_profil_up.png", 0, 0, 230, 250);
        logoInformationsGenerales.add(imageLogoInformationsGenerales);




        separateur = new JPanel();
        separateur.setBounds(290, 30, 4, 250);
        separateur.setLayout(null);
        this.add(separateur);

        AfficheImage imageSeparateur = new AfficheImage("/appli_etudiants/image/bande-15.png", 0, 0, 4, 250);
        separateur.add(imageSeparateur);

        


        boutonModifier = new JPanel();
        boutonModifier.setLayout(null);
        boutonModifier.setBounds(582, 290, 177, 45);
        this.add(boutonModifier);

        AfficheImage imageBoutonModifier = new AfficheImage("/appli_etudiants/image/btn_modif.png", 0, 0, 177, 45);
        boutonModifier.add(imageBoutonModifier);
       


        this.EventBoutonModifier();


        boutonEnregistrer = new JPanel();
        boutonEnregistrer.setLayout(null);
        boutonEnregistrer.setBounds(770, 290, 177, 45);
        
        this.add(boutonEnregistrer);
       
        AfficheImage imageBoutonEnregistrer = new AfficheImage("/appli_etudiants/image/btn_enregistrer.png", 0, 0, 177, 45);
        boutonEnregistrer.add(imageBoutonEnregistrer);

        this.EventBoutonEnregistrer();


    }

    public void SetTextFieldInformations() {
        this.jTextFieldAdresse.setText(this.jLabelAdresse1.getText());
        this.jTextFieldCodePostal.setText(this.jLabelCodePostal1.getText());
        this.jTextFieldDateDeNaissance.setText(this.jLabelDateDeNaissance1.getText());
        this.jTextFieldEmail.setText(this.jLabelEmail1.getText());
        this.jTextFieldNom.setText(this.jLabelNom1.getText());
        this.jTextFieldPays.setText(this.jLabelPays1.getText());
        this.jTextFieldPrenom.setText(this.jLabelPrenom1.getText());
        this.jTextFieldTelephone.setText(this.jLabelTelephone1.getText());
        this.jTextFieldVille.setText(this.jLabelVille1.getText());

    }

    public void EnregistrerInformations() {
        if (this.jTextFieldAdresse.getText().length() == 0 || this.jTextFieldCodePostal.getText().length() == 0
                || this.jTextFieldDateDeNaissance.getText().length() == 0 || this.jTextFieldEmail.getText().length() == 0
                || this.jTextFieldNom.getText().length() == 0 || this.jTextFieldPays.getText().length() == 0
                || this.jTextFieldPrenom.getText().length() == 0 || this.jTextFieldTelephone.getText().length() == 0
                || this.jTextFieldVille.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Erreur de saisie, tous les  champs doivent être renseignés.");
        } else {


            try {

                Class.forName("com.mysql.jdbc.Driver");
                String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
                Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);
                Statement requete = maConnexion.createStatement();
                String identifiant = this.fenetre.getIdentifiant();

                requete.executeUpdate("UPDATE utilisateurs SET nom='" + this.jTextFieldNom.getText() + "', prenom='" + this.jTextFieldPrenom.getText() + "',"
                        + "courriel='" + this.jTextFieldEmail.getText() + "', num_de_telephone='" + this.jTextFieldTelephone.getText() + "',"
                        + "date_de_naissance='" + this.jTextFieldDateDeNaissance.getText() + "', adresse='" + this.jTextFieldAdresse.getText() + "',"
                        + "ville='" + this.jTextFieldVille.getText() + "', codePostal='" + this.jTextFieldCodePostal.getText() + "', pays='" + this.jTextFieldPays.getText() + "'"
                        + " WHERE identifiant = '" + identifiant + "' ");





            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
            }

            FieldVisible(false);
        }
    }

    public void EventBoutonEnregistrer() {

        boutonEnregistrer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {
  
               
                boutonEnregistrer.removeAll();

                AfficheImage imageBoutonEnregistrer = new AfficheImage("/appli_etudiants/image/btn_enregistrer_down.png", 0, 0, 177, 45);
                boutonEnregistrer.add(imageBoutonEnregistrer);
                boutonEnregistrer.repaint();
            }
        });



        boutonEnregistrer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
       
                boutonEnregistrer.removeAll();

                AfficheImage imageBoutonEnregistrer = new AfficheImage("/appli_etudiants/image/btn_enregistrer.png", 0, 0, 177, 45);
                boutonEnregistrer.add(imageBoutonEnregistrer);
                boutonEnregistrer.repaint();
                
                EnregistrerInformations();
                recupInformationsGenerales();
            }
        });
    }

    public void EventBoutonModifier() {

        boutonModifier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                boutonModifier.removeAll();

                AfficheImage imageBoutonModifier = new AfficheImage("/appli_etudiants/image/btn_modif_down.png", 0, 0, 177, 45);
                boutonModifier.add(imageBoutonModifier);
                boutonModifier.repaint();

            }
        });



        boutonModifier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {

                boutonModifier.removeAll();

                AfficheImage imageBoutonModifier = new AfficheImage("/appli_etudiants/image/btn_modif.png", 0, 0, 177, 45);
                boutonModifier.add(imageBoutonModifier);
                boutonModifier.repaint();


                FieldVisible(true);
                SetTextFieldInformations();


            }
        });
    }

    public void FieldVisible(boolean text) {
        if (text == true) {
            // sans le panel fieldDessin rien ne se dessine.. à voir
            this.fieldDessin.setVisible(true);
            this.jTextFieldAdresse.setVisible(true);
            this.jTextFieldCodePostal.setVisible(true);
            this.jTextFieldDateDeNaissance.setVisible(true);
            this.jTextFieldEmail.setVisible(true);
            this.jTextFieldNom.setVisible(true);
            this.jTextFieldPays.setVisible(true);
            this.jTextFieldPrenom.setVisible(true);
            this.jTextFieldTelephone.setVisible(true);
            this.jTextFieldVille.setVisible(true);
        } else if (text == false) {
            this.fieldDessin.setVisible(false);
            this.jTextFieldAdresse.setVisible(false);
            this.jTextFieldCodePostal.setVisible(false);
            this.jTextFieldDateDeNaissance.setVisible(false);
            this.jTextFieldEmail.setVisible(false);
            this.jTextFieldNom.setVisible(false);
            this.jTextFieldPays.setVisible(false);
            this.jTextFieldPrenom.setVisible(false);
            this.jTextFieldTelephone.setVisible(false);
            this.jTextFieldVille.setVisible(false);
        }



    }

    public void recupInformationsGenerales() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
            Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);


            Statement requete = maConnexion.createStatement();
            ResultSet recupInfos = requete.executeQuery("SELECT * FROM utilisateurs WHERE identifiant = '" + this.identifiant + "' ");

            while (recupInfos.next()) {
                this.jLabelNom1.setText(recupInfos.getString("nom"));
                this.jLabelPrenom1.setText(recupInfos.getString("prenom"));
                this.jLabelEmail1.setText(recupInfos.getString("courriel"));
                this.jLabelTelephone1.setText(recupInfos.getString("num_de_telephone"));
                this.jLabelDateDeNaissance1.setText(recupInfos.getString("date_de_naissance"));
                this.jLabelAdresse1.setText(recupInfos.getString("adresse"));
                this.jLabelVille1.setText(recupInfos.getString("ville"));
                this.jLabelCodePostal1.setText(recupInfos.getString("codePostal"));
                this.jLabelPays1.setText(recupInfos.getString("pays"));




            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
        }
    }
    
    public InformationsGenerales getInformationsGenerales(){
        return this;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelEmail = new javax.swing.JLabel();
        jLabelDateDeNaissance = new javax.swing.JLabel();
        jLabelNom = new javax.swing.JLabel();
        jLabelPrenom = new javax.swing.JLabel();
        jLabelTelephone = new javax.swing.JLabel();
        jLabelAdresse = new javax.swing.JLabel();
        jLabelVille = new javax.swing.JLabel();
        jLabelCodePostal = new javax.swing.JLabel();
        jLabelPays = new javax.swing.JLabel();
        jLabelNom1 = new javax.swing.JLabel();
        jLabelPrenom1 = new javax.swing.JLabel();
        jLabelEmail1 = new javax.swing.JLabel();
        jLabelDateDeNaissance1 = new javax.swing.JLabel();
        jLabelVille1 = new javax.swing.JLabel();
        jLabelCodePostal1 = new javax.swing.JLabel();
        jLabelTelephone1 = new javax.swing.JLabel();
        jLabelAdresse1 = new javax.swing.JLabel();
        jLabelPays1 = new javax.swing.JLabel();
        jTextFieldNom = new javax.swing.JTextField();
        jTextFieldPrenom = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jTextFieldTelephone = new javax.swing.JTextField();
        jTextFieldDateDeNaissance = new javax.swing.JTextField();
        jTextFieldAdresse = new javax.swing.JTextField();
        jTextFieldVille = new javax.swing.JTextField();
        jTextFieldCodePostal = new javax.swing.JTextField();
        jTextFieldPays = new javax.swing.JTextField();

        setForeground(new java.awt.Color(8, 0, 83));
        setMaximumSize(new java.awt.Dimension(960, 350));
        setMinimumSize(new java.awt.Dimension(960, 350));

        jLabelEmail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelEmail.setForeground(new java.awt.Color(8, 0, 83));
        jLabelEmail.setText("Email:");

        jLabelDateDeNaissance.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelDateDeNaissance.setForeground(new java.awt.Color(8, 0, 83));
        jLabelDateDeNaissance.setText("Date de naissance:");

        jLabelNom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelNom.setForeground(new java.awt.Color(8, 0, 83));
        jLabelNom.setText("Nom:");

        jLabelPrenom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelPrenom.setForeground(new java.awt.Color(8, 0, 83));
        jLabelPrenom.setText("Prénom:");

        jLabelTelephone.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelTelephone.setForeground(new java.awt.Color(8, 0, 83));
        jLabelTelephone.setText("Téléphone:");

        jLabelAdresse.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelAdresse.setForeground(new java.awt.Color(8, 0, 83));
        jLabelAdresse.setText("Adresse:");

        jLabelVille.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelVille.setForeground(new java.awt.Color(8, 0, 83));
        jLabelVille.setText("Ville:");

        jLabelCodePostal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelCodePostal.setForeground(new java.awt.Color(8, 0, 83));
        jLabelCodePostal.setText("Code postal:");

        jLabelPays.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelPays.setForeground(new java.awt.Color(8, 0, 83));
        jLabelPays.setText("Pays:");

        jLabelNom1.setForeground(new java.awt.Color(8, 0, 83));
        jLabelNom1.setText("Nom:");

        jLabelPrenom1.setForeground(new java.awt.Color(8, 0, 83));
        jLabelPrenom1.setText("Prénom:");

        jLabelEmail1.setForeground(new java.awt.Color(8, 0, 83));
        jLabelEmail1.setText("Email:");

        jLabelDateDeNaissance1.setForeground(new java.awt.Color(8, 0, 83));
        jLabelDateDeNaissance1.setText("Date de naissance:");

        jLabelVille1.setForeground(new java.awt.Color(8, 0, 83));
        jLabelVille1.setText("Ville:");

        jLabelCodePostal1.setForeground(new java.awt.Color(8, 0, 83));
        jLabelCodePostal1.setText("Code postal:");

        jLabelTelephone1.setForeground(new java.awt.Color(8, 0, 83));
        jLabelTelephone1.setText("Téléphone:");

        jLabelAdresse1.setForeground(new java.awt.Color(8, 0, 83));
        jLabelAdresse1.setText("Adresse:");

        jLabelPays1.setForeground(new java.awt.Color(8, 0, 83));
        jLabelPays1.setText("Pays:");

        jTextFieldPrenom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPrenomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(355, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabelVille)
                        .addGap(4, 4, 4))
                    .addComponent(jLabelNom, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelPrenom, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelTelephone, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelDateDeNaissance, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelAdresse, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelCodePostal, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelPays, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNom1)
                    .addComponent(jLabelPrenom1)
                    .addComponent(jLabelEmail1)
                    .addComponent(jLabelTelephone1)
                    .addComponent(jLabelDateDeNaissance1)
                    .addComponent(jLabelAdresse1)
                    .addComponent(jLabelVille1)
                    .addComponent(jLabelCodePostal1)
                    .addComponent(jLabelPays1))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldNom)
                    .addComponent(jTextFieldPrenom)
                    .addComponent(jTextFieldEmail)
                    .addComponent(jTextFieldTelephone)
                    .addComponent(jTextFieldDateDeNaissance)
                    .addComponent(jTextFieldAdresse)
                    .addComponent(jTextFieldVille)
                    .addComponent(jTextFieldCodePostal)
                    .addComponent(jTextFieldPays, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(156, 156, 156))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNom)
                    .addComponent(jLabelNom1)
                    .addComponent(jTextFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPrenom)
                    .addComponent(jLabelPrenom1)
                    .addComponent(jTextFieldPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEmail)
                    .addComponent(jLabelEmail1)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelephone)
                    .addComponent(jLabelTelephone1)
                    .addComponent(jTextFieldTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDateDeNaissance)
                    .addComponent(jLabelDateDeNaissance1)
                    .addComponent(jTextFieldDateDeNaissance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAdresse)
                    .addComponent(jLabelAdresse1)
                    .addComponent(jTextFieldAdresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelVille)
                    .addComponent(jLabelVille1)
                    .addComponent(jTextFieldVille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCodePostal)
                    .addComponent(jLabelCodePostal1)
                    .addComponent(jTextFieldCodePostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPays)
                    .addComponent(jLabelPays1)
                    .addComponent(jTextFieldPays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldPrenomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPrenomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPrenomActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelAdresse;
    private javax.swing.JLabel jLabelAdresse1;
    private javax.swing.JLabel jLabelCodePostal;
    private javax.swing.JLabel jLabelCodePostal1;
    private javax.swing.JLabel jLabelDateDeNaissance;
    private javax.swing.JLabel jLabelDateDeNaissance1;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelEmail1;
    private javax.swing.JLabel jLabelNom;
    private javax.swing.JLabel jLabelNom1;
    private javax.swing.JLabel jLabelPays;
    private javax.swing.JLabel jLabelPays1;
    private javax.swing.JLabel jLabelPrenom;
    private javax.swing.JLabel jLabelPrenom1;
    private javax.swing.JLabel jLabelTelephone;
    private javax.swing.JLabel jLabelTelephone1;
    private javax.swing.JLabel jLabelVille;
    private javax.swing.JLabel jLabelVille1;
    private javax.swing.JTextField jTextFieldAdresse;
    private javax.swing.JTextField jTextFieldCodePostal;
    private javax.swing.JTextField jTextFieldDateDeNaissance;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldNom;
    private javax.swing.JTextField jTextFieldPays;
    private javax.swing.JTextField jTextFieldPrenom;
    private javax.swing.JTextField jTextFieldTelephone;
    private javax.swing.JTextField jTextFieldVille;
    // End of variables declaration//GEN-END:variables
}
