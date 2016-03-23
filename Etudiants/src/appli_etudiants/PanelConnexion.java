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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Nicolas
 */
public class PanelConnexion extends javax.swing.JPanel {

    private InterfaceGraphique fenetre;
    private PanelPrincipal fenPanelPrincipal;
    private JPanel boutonConnexion;
  

    /**
     * Creates new form PanelConnexion
     */
    public PanelConnexion(InterfaceGraphique parent) {
        initComponents();

        this.fenetre = (InterfaceGraphique) parent;
        this.fenPanelPrincipal=fenPanelPrincipal;
     
        
        Dimension taille = new Dimension(Constante.largeurFenetre, Constante.hauteurPanelIns);
        this.setSize(taille);
        this.setPreferredSize(taille);
        this.setMaximumSize(taille);
        this.setMinimumSize(taille);


        Color couleurConnexion = new Color(8, 0, 83);
        this.setBackground(couleurConnexion);



        boutonConnexion = new JPanel();
        boutonConnexion.setBounds(435, 100, 87, 25);
        boutonConnexion.setLayout(null);
        this.add(boutonConnexion);



        AfficheImage imageBoutonConnexion = new AfficheImage("/appli_etudiants/image/btn_connexion.png", 0, 0, 87, 25);
        boutonConnexion.add(imageBoutonConnexion);


        boutonConnexion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {

                boutonConnexion.removeAll();

                AfficheImage imageBoutonConnexion = new AfficheImage("/appli_etudiants/image/btn_connexion_down.png", 0, 0, 87, 25);
                boutonConnexion.add(imageBoutonConnexion);
                boutonConnexion.repaint();
                
             

            }
        });


        boutonConnexion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {

                boutonConnexion.removeAll();

                AfficheImage imageBoutonConnexion = new AfficheImage("/appli_etudiants/image/btn_connexion.png", 0, 0, 87, 25);
                boutonConnexion.add(imageBoutonConnexion);
                boutonConnexion.repaint();
                Connexion();
                
               
                


            }
        });

       
    

 
}
    
    
    public void Connexion(){

            
        if (this.jTextFieldIdentifiant.getText().length() != 0 || this.jPasswordFieldMotDePasse.getText().length()!=0){
            try {
                //interrogation de la BD pour savoir si l'identifiant/mot de passe est correct
                //instanciation de la classe Driver du paquetage jdbc de mysql
                Class.forName("com.mysql.jdbc.Driver");
                //Chaine de connexion (prise dans l'onglet services)
                String connexionUrl="jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
               
                //etablissement de la connexion
                Connection maConnexion=(Connection)DriverManager.getConnection(connexionUrl);
                
                //requete
                Statement requete=maConnexion.createStatement();
                String identifiant=jTextFieldIdentifiant.getText();
                String mdp=jPasswordFieldMotDePasse.getText();
                
            
                //application du cryptage md5 au mdp
                // ici on appelle md5 membre static de la classe outils
                mdp=Outils.md5(mdp);
            
                ResultSet lignesRetournees=requete.executeQuery("select * from Utilisateurs where identifiant='"+identifiant+"' and mot_de_passe='"+mdp+"'");
                if (lignesRetournees.next()){
                    String nom=lignesRetournees.getString("nom");
                    String prenom=lignesRetournees.getString("prenom");
                    String courriel=lignesRetournees.getString("courriel");
                    String numtel=lignesRetournees.getString("num_de_telephone");
                    String ddn=lignesRetournees.getString("date_de_naissance");
                    String adresse=lignesRetournees.getString("adresse");
                    String ville=lignesRetournees.getString("ville");
                    String codePostal=lignesRetournees.getString("codePostal");
                    String pays=lignesRetournees.getString("pays");
                   
                  
                    //Modifications de la Mission 2 à placer ici
                 
                    this.fenetre.connecte(nom,prenom,courriel,numtel,ddn,adresse,identifiant,ville);
                   
                    this.fenetre.majConnexion();
                   
                  this.fenetre.setPanelVisible(false);
                  this.fenetre.CreationPanelPrincipal();
                
                  

        
           
                  
                  

             
                    
                    
                }else{
                    JOptionPane.showMessageDialog(this, "Identifiant ou mot de passe incorrect");
                };
                
                
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..."+ex.toString());
            }
             catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "SQL exception ... "+ex.toString());
            }
            catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
    
    
    public void setFieldIdentifiant(String text){
        this.jTextFieldIdentifiant.setText(text);
    }
    
    public PanelPrincipal getPanelPrincipal(){
        return this.fenPanelPrincipal;
    }
    
/**
 * This method is called from within the constructor to initialize the form.
 * WARNING: Do NOT modify this code. The content of this method is always
 * regenerated by the Form Editor.
 */
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldIdentifiant = new javax.swing.JTextField();
        jLabelIdentifiant = new javax.swing.JLabel();
        jLabelMotDePasse = new javax.swing.JLabel();
        jPasswordFieldMotDePasse = new javax.swing.JPasswordField();

        jLabelIdentifiant.setForeground(new java.awt.Color(240, 240, 240));
        jLabelIdentifiant.setText("Identifiant");

        jLabelMotDePasse.setForeground(new java.awt.Color(240, 240, 240));
        jLabelMotDePasse.setText("Mot de passe");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(447, 447, 447)
                        .addComponent(jLabelIdentifiant))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(444, 444, 444)
                        .addComponent(jLabelMotDePasse))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(413, 413, 413)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPasswordFieldMotDePasse)
                            .addComponent(jTextFieldIdentifiant, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelIdentifiant)
                .addGap(6, 6, 6)
                .addComponent(jTextFieldIdentifiant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabelMotDePasse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPasswordFieldMotDePasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelIdentifiant;
    private javax.swing.JLabel jLabelMotDePasse;
    private javax.swing.JPasswordField jPasswordFieldMotDePasse;
    private javax.swing.JTextField jTextFieldIdentifiant;
    // End of variables declaration//GEN-END:variables
}
