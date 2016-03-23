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
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Nicolas
 */
public class PanelInscription extends javax.swing.JPanel {
    private final JPanel boutonInscription;
    private InterfaceGraphique fenetre;
    private String identifiant;
    private PanelConnexion fenPanelConnexion;
    
    
    public PanelInscription(InterfaceGraphique fenetre) {
        initComponents();

        this.fenetre=fenetre;
        this.fenPanelConnexion=fenPanelConnexion;
        
        Dimension taille = new Dimension(Constante.largeurFenetre, Constante.hauteurPanelIns);
        this.setSize(taille);
        this.setPreferredSize(taille);
        this.setMaximumSize(taille);
        this.setMinimumSize(taille);
        
        
        Color couleurInscription = new Color(61, 55, 123);
        this.setBackground(couleurInscription);
        
        boutonInscription = new JPanel();
        boutonInscription.setLayout(null);
        boutonInscription.setBounds(440, 113, 87, 25);
        this.add(boutonInscription);
        
        AfficheImage imageBoutonInscripton = new AfficheImage("/appli_etudiants/image/btn_inscription.png", 0, 0, 87, 25);
        boutonInscription.add(imageBoutonInscripton);
        
        
    
        
        
      
        
  
        
        
            
       
        
        
       this.EventInscription();
    }
    
    public void EventInscription(){
       
          // bouton Informations generale Mouse Lister
        boutonInscription.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                boutonInscription.removeAll();

                AfficheImage imageBoutonInscripton = new AfficheImage("/appli_etudiants/image/btn_inscription_down.png", 0, 0, 87, 25);
                boutonInscription.add(imageBoutonInscripton);
                boutonInscription.repaint();
       
       
          }
        });
        
       
        boutonInscription.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {

                boutonInscription.removeAll();

                AfficheImage imageBoutonInscripton = new AfficheImage("/appli_etudiants/image/btn_inscription.png", 0, 0, 87, 25);
                boutonInscription.add(imageBoutonInscripton);
                boutonInscription.repaint();
                
                Inscription();
                
                
               
                


            }
        });
    }
    
    
        public void Inscription(){
              if(this.jTextFieldAdresse.getText().length()==0 || this.jTextFieldCodePostal.getText().length()==0
            || this.jTextFieldDateDeNaissance.getText().length()==0 || this.jTextFieldEmail.getText().length()==0
            || this.jTextFieldNom.getText().length() == 0 || this.jTextFieldPrenom.getText().length()==0
                    || this.jTextFieldVille.getText().length()==0 || this.jTextFieldIdentifiant.getText().length()==0
                      || this.jTextFieldTelephone.getText().length()==0 ||this.jPasswordFieldMotDePasse.getText().length()==0
                      || this.jPasswordFieldConfirmation.getText().length()==0 || this.jTextFieldPays.getText().length()==0){
        JOptionPane.showMessageDialog(this, "Erreur de saisie, tous les  champs doivent être renseignés.");
          
            
    }else{
            
            try {
                //interrogation de la BD pour savoir si l'identifiant/mot de passe est correct
                //instanciation de la classe Driver du paquetage jdbc de mysql
                Class.forName("com.mysql.jdbc.Driver");
                //Chaine de connexion (prise dans l'onglet services)
                String connexionUrl="jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
               
                //etablissement de la connexion
                Connection maConnexion=(Connection)DriverManager.getConnection(connexionUrl);
                
                // requete
                Statement requete=maConnexion.createStatement();
                
                
        
        String adresse = this.jTextFieldAdresse.getText();
        String codePostal = this.jTextFieldCodePostal.getText();
        String confirmation = this.jPasswordFieldConfirmation.getText();
        String dateDN = this.jTextFieldDateDeNaissance.getText();
        String email = this.jTextFieldEmail.getText();
        String motDePasse = this.jPasswordFieldMotDePasse.getText();
        String nom = this.jTextFieldNom.getText();
        String prenom = this.jTextFieldPrenom.getText();
        String ville = this.jTextFieldVille.getText();
        this.identifiant = this.jTextFieldIdentifiant.getText();
        String telephone = this.jTextFieldTelephone.getText();
        String pays = this.jTextFieldPays.getText();
       
        
        if(motDePasse.equals(confirmation)){
            motDePasse=Outils.md5(motDePasse);
            
            requete.executeUpdate("INSERT INTO utilisateurs VALUES('"+this.identifiant+"', '"+motDePasse+"','"+nom+"', '"+prenom+"','"+email+"','"+telephone+"' , "
                    + " '"+dateDN+"', '"+adresse+"', '"+ville+"', '"+codePostal+"','"+pays+"')");
        }else{
            JOptionPane.showMessageDialog(this, "Les mots de passes ne sont pas identiques.");
        }
        
        
        }  
         catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..."+ex.toString());
            }
             catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "SQL exception ... "+"l'identifiant " +"'"+this.jTextFieldIdentifiant.getText()+"'" +" est déja pris");
            }
            catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
            }
         
              }
              
              
              
             this.fenetre.getPanelInscription().setVisible(true);
             this.fenetre.getInscriptionExtension().setVisible(false);
             this.fenetre.getPanelConnexion().setVisible(true);
             this.fenetre.getConnexionExtension().setVisible(true);
             this.fenetre.getPanelConnexion2().setVisible(false);
             
             
             
             
             
           
    }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelIdentifiant = new javax.swing.JLabel();
        jLabelMotDePasse = new javax.swing.JLabel();
        jLabelConfirmation = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldIdentifiant = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jPasswordFieldMotDePasse = new javax.swing.JPasswordField();
        jPasswordFieldConfirmation = new javax.swing.JPasswordField();
        jLabelDateDeNaissance = new javax.swing.JLabel();
        jLabelNom = new javax.swing.JLabel();
        jLabelPrenom = new javax.swing.JLabel();
        jLabelTelephone = new javax.swing.JLabel();
        jTextFieldNom = new javax.swing.JTextField();
        jTextFieldPrenom = new javax.swing.JTextField();
        jTextFieldTelephone = new javax.swing.JTextField();
        jLabelAdresse = new javax.swing.JLabel();
        jLabeVille = new javax.swing.JLabel();
        jLabelCodePostal = new javax.swing.JLabel();
        jLabelPays = new javax.swing.JLabel();
        jTextFieldAdresse = new javax.swing.JTextField();
        jTextFieldVille = new javax.swing.JTextField();
        jTextFieldCodePostal = new javax.swing.JTextField();
        jTextFieldPays = new javax.swing.JTextField();
        jTextFieldDateDeNaissance = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(960, 140));
        setMinimumSize(new java.awt.Dimension(960, 140));
        setPreferredSize(new java.awt.Dimension(960, 140));

        jLabelIdentifiant.setForeground(new java.awt.Color(240, 240, 240));
        jLabelIdentifiant.setText("Identifiant");

        jLabelMotDePasse.setForeground(new java.awt.Color(240, 240, 240));
        jLabelMotDePasse.setText("Mot de passe");

        jLabelConfirmation.setForeground(new java.awt.Color(240, 240, 240));
        jLabelConfirmation.setText("Confirmation");

        jLabelEmail.setForeground(new java.awt.Color(240, 240, 240));
        jLabelEmail.setText("Email");

        jTextFieldEmail.setMinimumSize(new java.awt.Dimension(130, 20));

        jPasswordFieldConfirmation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldConfirmationActionPerformed(evt);
            }
        });

        jLabelDateDeNaissance.setForeground(new java.awt.Color(240, 240, 240));
        jLabelDateDeNaissance.setText("Date de naissance");

        jLabelNom.setForeground(new java.awt.Color(240, 240, 240));
        jLabelNom.setText("Nom");

        jLabelPrenom.setForeground(new java.awt.Color(240, 240, 240));
        jLabelPrenom.setText("Prénom");

        jLabelTelephone.setForeground(new java.awt.Color(240, 240, 240));
        jLabelTelephone.setText("Téléphone");

        jTextFieldNom.setMaximumSize(new java.awt.Dimension(130, 20));
        jTextFieldNom.setMinimumSize(new java.awt.Dimension(130, 20));

        jTextFieldPrenom.setMaximumSize(new java.awt.Dimension(130, 20));
        jTextFieldPrenom.setMinimumSize(new java.awt.Dimension(130, 20));

        jTextFieldTelephone.setMaximumSize(new java.awt.Dimension(130, 20));
        jTextFieldTelephone.setMinimumSize(new java.awt.Dimension(130, 20));

        jLabelAdresse.setForeground(new java.awt.Color(240, 240, 240));
        jLabelAdresse.setText("Adresse (n° + rue)");

        jLabeVille.setForeground(new java.awt.Color(240, 240, 240));
        jLabeVille.setText("Ville");

        jLabelCodePostal.setForeground(new java.awt.Color(240, 240, 240));
        jLabelCodePostal.setText("Code postal");

        jLabelPays.setForeground(new java.awt.Color(240, 240, 240));
        jLabelPays.setText("Pays");

        jTextFieldCodePostal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodePostalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelEmail)
                    .addComponent(jLabelConfirmation)
                    .addComponent(jLabelMotDePasse)
                    .addComponent(jLabelIdentifiant))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextFieldIdentifiant, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addComponent(jPasswordFieldMotDePasse)
                        .addComponent(jPasswordFieldConfirmation))
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelNom)
                    .addComponent(jLabelDateDeNaissance)
                    .addComponent(jLabelPrenom)
                    .addComponent(jLabelTelephone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldNom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(jTextFieldPrenom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldTelephone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldDateDeNaissance, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(jLabelAdresse))
                        .addComponent(jLabelCodePostal, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabelPays, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jLabeVille)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldVille, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addComponent(jTextFieldCodePostal, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldAdresse))
                    .addComponent(jTextFieldPays, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIdentifiant)
                    .addComponent(jTextFieldIdentifiant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDateDeNaissance)
                    .addComponent(jLabelAdresse)
                    .addComponent(jTextFieldAdresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDateDeNaissance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMotDePasse)
                    .addComponent(jPasswordFieldMotDePasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNom)
                    .addComponent(jTextFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabeVille)
                    .addComponent(jTextFieldVille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelConfirmation)
                    .addComponent(jPasswordFieldConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPrenom)
                    .addComponent(jTextFieldPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCodePostal)
                    .addComponent(jTextFieldCodePostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEmail)
                    .addComponent(jLabelTelephone)
                    .addComponent(jTextFieldTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPays)
                    .addComponent(jTextFieldPays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordFieldConfirmationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldConfirmationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldConfirmationActionPerformed

    private void jTextFieldCodePostalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodePostalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCodePostalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabeVille;
    private javax.swing.JLabel jLabelAdresse;
    private javax.swing.JLabel jLabelCodePostal;
    private javax.swing.JLabel jLabelConfirmation;
    private javax.swing.JLabel jLabelDateDeNaissance;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelIdentifiant;
    private javax.swing.JLabel jLabelMotDePasse;
    private javax.swing.JLabel jLabelNom;
    private javax.swing.JLabel jLabelPays;
    private javax.swing.JLabel jLabelPrenom;
    private javax.swing.JLabel jLabelTelephone;
    private javax.swing.JPasswordField jPasswordFieldConfirmation;
    private javax.swing.JPasswordField jPasswordFieldMotDePasse;
    private javax.swing.JTextField jTextFieldAdresse;
    private javax.swing.JTextField jTextFieldCodePostal;
    private javax.swing.JTextField jTextFieldDateDeNaissance;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldIdentifiant;
    private javax.swing.JTextField jTextFieldNom;
    private javax.swing.JTextField jTextFieldPays;
    private javax.swing.JTextField jTextFieldPrenom;
    private javax.swing.JTextField jTextFieldTelephone;
    private javax.swing.JTextField jTextFieldVille;
    // End of variables declaration//GEN-END:variables
}
