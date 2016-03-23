/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants.EnTete;

import appli_etudiants.InterfaceGraphique;
import appli_etudiants.classes.AfficheImage;
import appli_etudiants.classes.ImagePanel;
import com.mysql.jdbc.Connection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Nicolas
 */
public class AfficherInformations extends javax.swing.JPanel {

    private InterfaceGraphique fenetre;
    private String identifiant;
    private String nom;
    private String prenom;
    private String courriel;
    private String numTel;
    private String ddn;
    private String adresse;
    private String ville;
    private String codePostal;
    private String pays;
    private JFileChooser choixPhoto;
    private ImageIcon lImage = null;
    private FileInputStream istreamImage;
    private JPanel boutonEnregistrer;
    private Photo tPhoto;
    private JPanel boutonSupprimer;

    /**
     * Creates new form AfficherInformations
     */
    public AfficherInformations(InterfaceGraphique fenetre) throws IOException {
        initComponents();
        Dimension size = new Dimension(this.getWidth(), this.getHeight());
        this.setSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
        this.setMinimumSize(size);

        this.choixPhoto = null;
        this.fenetre = fenetre;


        this.identifiant = this.fenetre.getIdentifiant();
        Color couleurDeFond = new Color(226, 225, 235);
        this.setBackground(couleurDeFond);

        boutonSupprimer = new JPanel();
        this.boutonSupprimer.setLayout(null);
        this.boutonSupprimer.setBounds(700, 115, 16,16);
        this.add(boutonSupprimer);

        AfficheImage imageBoutonSupprimer = new AfficheImage("/appli_etudiants/image/iconeSupprimer.png", 0, 0, 16,16);
        boutonSupprimer.add(imageBoutonSupprimer);
        this.boutonSupprimer.setVisible(false);
        this.EventBoutonSupprimer();







        try {

            Class.forName("com.mysql.jdbc.Driver");
            String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
            Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);


            Statement requete = maConnexion.createStatement();
            ResultSet recupInfos = requete.executeQuery("SELECT * FROM utilisateurs WHERE identifiant = '" + identifiant + "' ");

            while (recupInfos.next()) {
                this.jLabelNom.setText(recupInfos.getString("nom"));
                this.jLabelPrenom.setText(recupInfos.getString("prenom"));
                this.jLabelCourriel.setText(recupInfos.getString("courriel"));
                this.jLabelTelephone.setText(recupInfos.getString("num_de_telephone"));
                this.jLabelDdn.setText(recupInfos.getString("date_de_naissance"));
                this.jLabelAdresse.setText(recupInfos.getString("adresse"));
                this.jLabelVille.setText(recupInfos.getString("ville"));
                this.jLabelCodePostal.setText(recupInfos.getString("codePostal"));
                this.jLabelPays.setText(recupInfos.getString("pays"));


            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Classe de connexion mysql non trouvee..." + ex.toString());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL exception ... " + ex.toString());
        }


        this.chargerImage();

        this.add(tPhoto);
        this.EventPanelPhoto();


        boutonEnregistrer = new JPanel();
        boutonEnregistrer.setLayout(null);
        boutonEnregistrer.setBounds(715, 100, 32, 32);
        this.add(boutonEnregistrer);

        AfficheImage imageBoutonEnregistrer = new AfficheImage("/appli_etudiants/image/iconeEnregistrer.png", 0, 0, 32, 32);
        boutonEnregistrer.add(imageBoutonEnregistrer);
        this.boutonEnregistrer.setVisible(false);

        this.EventEnregistrerPhoto();



    }

    public String getJLabelNom() {
        return this.jLabelNom.getText();
    }
    
    public void EventBoutonSupprimer(){
        
        boutonSupprimer.addMouseListener(new java.awt.event.MouseAdapter() {
           
            public void mousePressed(MouseEvent e) {

                      try {
                          String identifiant = fenetre.getIdentifiant();
                        
            Class.forName("com.mysql.jdbc.Driver");
            String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
            java.sql.Connection maConnexion = (java.sql.Connection) DriverManager.getConnection(connexionUrl);

            Statement requete = maConnexion.createStatement();
            requete.executeUpdate("DELETE FROM cv_photo WHERE identifiant = '" +identifiant+"' ");
          


          
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Classe de connexion mysql non trouvee..." + ex.toString());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL exception ... " + ex.toString());


        }
                      
                      tPhoto.removeAll();
                      AfficheImage imageAjouterPhoto = new AfficheImage("/appli_etudiants/image/pictoAjouterPhoto.jpg", 0, 0, 120, 120);
                      tPhoto.add(imageAjouterPhoto);
                      tPhoto.repaint();
                      tPhoto.validate();
                      boutonSupprimer.setVisible(false);

                }

            
        });
    }

    public void EventPanelPhoto() {


        tPhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                choixPhoto = null;
                choixPhoto = new JFileChooser(".");

                choixPhoto.setFileFilter(new FileNameExtensionFilter("Images", "png", "jpg"));




                choixPhoto.setCurrentDirectory(choixPhoto.getCurrentDirectory());
                choixPhoto.showOpenDialog(null);

                if (choixPhoto.getSelectedFile() == null) {
                    choixPhoto = null;
                   
                } else {

                    tPhoto.removeAll();
                    ImagePanel laPhoto = new ImagePanel(choixPhoto.getSelectedFile().toString(), 0, 0, 120, 120);
                    tPhoto.add(laPhoto);
                    tPhoto.repaint();






                    getAfficherInformations().repaint();
                    getAfficherInformations().validate();
                    boutonEnregistrer.setVisible(true);
                    boutonSupprimer.setVisible(false);


                }

            }
        });



    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelDdn = new javax.swing.JLabel();
        jLabelNom = new javax.swing.JLabel();
        jLabelAdresse = new javax.swing.JLabel();
        jLabelPrenom = new javax.swing.JLabel();
        jLabelCourriel = new javax.swing.JLabel();
        jLabelTelephone = new javax.swing.JLabel();
        jLabelCodePostal = new javax.swing.JLabel();
        jLabelPays = new javax.swing.JLabel();
        jLabelVille = new javax.swing.JLabel();

        jLabelDdn.setText("Date de Naissance");

        jLabelNom.setText("Nom");

        jLabelAdresse.setText("Adresse");

        jLabelPrenom.setText("Prenom");

        jLabelCourriel.setText("Courriel");

        jLabelTelephone.setText("Telephone");

        jLabelCodePostal.setText("Code Postal");

        jLabelPays.setText("Pays");

        jLabelVille.setText("Ville");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTelephone)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelNom)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelPrenom))
                            .addComponent(jLabelDdn)
                            .addComponent(jLabelAdresse)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelCodePostal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelVille)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelPays)))
                        .addContainerGap(800, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelCourriel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNom)
                    .addComponent(jLabelPrenom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelDdn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAdresse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCodePostal)
                    .addComponent(jLabelVille)
                    .addComponent(jLabelPays))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTelephone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCourriel)
                .addContainerGap(57, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public AfficherInformations getAfficherInformations() {
        return this;
    }

    public void EventEnregistrerPhoto() {
        boutonEnregistrer.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                boutonEnregistrer.removeAll();

                AfficheImage imageBoutonEnregistrer = new AfficheImage("/appli_etudiants/image/iconeEnregistrer.png", 0, 0, 32, 32);
                boutonEnregistrer.add(imageBoutonEnregistrer);
                EnregistrerPhoto();
                
                boutonEnregistrer.setVisible(false);
                boutonSupprimer.setVisible(true);
                
            }
        });
        getAfficherInformations().repaint();
        getAfficherInformations().validate();

    }

    public void EnregistrerPhoto() {
        File monImage = new File(choixPhoto.getSelectedFile().toString());

        try {
            istreamImage = new FileInputStream(monImage);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }


        try {

            Class.forName("com.mysql.jdbc.Driver");
            String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
            java.sql.Connection maConnexion = (java.sql.Connection) DriverManager.getConnection(connexionUrl);

            Statement requete = maConnexion.createStatement();
            requete.executeUpdate("DELETE FROM cv_photo WHERE identifiant = '" + this.identifiant + "' ");
          
            if(monImage.length() > 1048576){
                JOptionPane.showMessageDialog(null, "La taille de la photo dépasse 1 mo, la photo ne s'est pas enregistrée");
            }else{
                
           

            PreparedStatement ps = maConnexion.prepareStatement("insert into cv_photo values (null,?,'" + this.identifiant + "' )");
            
            

            JOptionPane.showMessageDialog(null, "Votre photo s'est correctement enregistrée");
          
            ps.setBinaryStream(1, istreamImage, (int) monImage.length());
            ps.executeUpdate();
            ps.close();
            
           }
            try {
                istreamImage.close();
            } catch (IOException ex) {
                Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());


        }
       
    }

    private void chargerImage() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
            com.mysql.jdbc.Connection maConnexion = (com.mysql.jdbc.Connection) DriverManager.getConnection(connexionUrl);

            Statement requete = maConnexion.createStatement();

            ResultSet recupPhoto = requete.executeQuery("SELECT photoBinaire FROM cv_photo WHERE identifiant='" + this.identifiant + "'");

            if (recupPhoto.next()) {
                try {
                    InputStream istreamImage = recupPhoto.getBinaryStream("photoBinaire");
                    BufferedImage imBuff = ImageIO.read(istreamImage);


                    tPhoto = new Photo(imBuff);
                    tPhoto.setBounds(760, 10, 120, 120);
                    this.add(tPhoto);
                 
             boutonSupprimer.setVisible(true);

                } catch (IOException ex) {
                    System.out.println(ex);
                }



            } else {
          
                
                tPhoto = new Photo(null);
                tPhoto.setBounds(760, 10, 120, 120);
                tPhoto.setLayout(null);
           
                
                AfficheImage imageAjouterPhoto = new AfficheImage("/appli_etudiants/image/pictoAjouterPhoto.jpg", 0, 0, 120, 120);
                tPhoto.add(imageAjouterPhoto);
                
                this.add(tPhoto);
              
          
               






            }


        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Classe de connexion mysql non trouvee..." + ex.toString());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "SQL exception ... " + ex.toString());
        }

    }
    
    public Photo getPhoto(){
        return this.tPhoto;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelAdresse;
    private javax.swing.JLabel jLabelCodePostal;
    private javax.swing.JLabel jLabelCourriel;
    private javax.swing.JLabel jLabelDdn;
    private javax.swing.JLabel jLabelNom;
    private javax.swing.JLabel jLabelPays;
    private javax.swing.JLabel jLabelPrenom;
    private javax.swing.JLabel jLabelTelephone;
    private javax.swing.JLabel jLabelVille;
    // End of variables declaration//GEN-END:variables
}
