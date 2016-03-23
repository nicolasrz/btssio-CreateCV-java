/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants;

import appli_etudiants.Administration.ConnexionAdministration;
import appli_etudiants.EnTete.AfficherInformations;
import appli_etudiants.classes.Constante;
import appli_etudiants.classes.ImagePanel;

import com.itextpdf.text.BadElementException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author nc
 */
public class InterfaceGraphique extends javax.swing.JFrame {

    /**
     * attribut qui indique si l'etudiant est connecté ou non
     */
    private boolean connecte;
    private Connexion fenConnexion;
    private Deconnexion fenDeconnexion;
    private String identifiant;
    private String mdp;
    private String nom;
    private String prenom;
    private String courriel;
    private int numtel;
    private String ddn;
    private String adresse;
    private String ville;
 
    private Suppression fenSuppression;
   
    private Outils fenOutils;
    private AfficherInformations fenAfficherInformations;
    private Connexion connexion;
    private ConnexionAdministration fenConnexionAdministration;
    private UtilisateurConnecte fenUtilisateurConnecte;
    private JPanel panelInscription;
    private JLabel labelInscription;
    private JPanel panelConnexion;
    private PanelInscription inscriptionExtension;
    private JPanel panelConnexion2;
    private PanelConnexion connexionExtension;
    private PanelConnexion fenPanelConnexion;
    private PanelPrincipal fenPanelPrincipal;
    private ImagePanel panelLogo;

    /**
     * constructeur : Creates new form InterfaceGraphique
     *
     */
    public InterfaceGraphique() {
        this.setUndecorated(true);
        initComponents();
      //  Border blackline = BorderFactory.createLineBorder(Color.black);
Border blackline = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY);
        this.getRootPane().setBorder(blackline) ;


        this.connexion = connexion;
        //par defaut, la connexion est inactive
        // pas besoin de cette ligne car de base on le deconnecte  this.connecte=false; 
        this.deconnecte();
        //element du menu de deconnexion grisé
        this.majConnexion();
        //centrage
        this.setLocationRelativeTo(null);
        //titre 
        this.setTitle("Gestion des étudiants du bts sio");

        Dimension taille = new Dimension(Constante.largeurFenetre, Constante.hauteurFenetre);
        this.setSize(taille);
        this.setPreferredSize(taille);
        this.setMaximumSize(taille);
        this.setMinimumSize(taille);
      //  this.setResizable(false);

        Color couleurDeFond = new Color(226, 225, 235);


        this.setTitle("Gestion des Etudiants du bts sio");
        this.getContentPane().setBackground(couleurDeFond);


        this.connexion = connexion;
        //par defaut, la connexion est inactive
        // pas besoin de cette ligne car de base on le deconnecte  this.connecte=false; 

        //logo
        panelLogo = new ImagePanel("src/appli_etudiants/image/logo140.png", 0,0,336,140);
        panelLogo.setBounds(312,0,336,140);
        this.getContentPane().add(panelLogo);


        this.CreationInscriptionConnexion();

 

    }

    public void setPanelVisible(boolean text) {
        panelInscription.setVisible(text);
        panelConnexion.setVisible(text);
        panelConnexion2.setVisible(text);
        connexionExtension.setVisible(text);
        inscriptionExtension.setVisible(text);
        this.repaint();

    }

    public PanelPrincipal getPanelPrincipal() {
        return this.fenPanelPrincipal;
    }

    public void CreationInscriptionConnexion() {

        Color couleurInscription = new Color(61, 55, 123);
        Color couleurConnexion = new Color(8, 0, 83);

// niveau 1
        panelInscription = new JPanel();
        panelInscription.setBounds(0, 140, Constante.largeurFenetre, Constante.hauteurPanelIns);
        panelInscription.setBackground(couleurInscription);
        panelInscription.setLayout(null);
        this.getContentPane().add(panelInscription);

        labelInscription = new JLabel("Inscription");
        labelInscription.setFont(new Font("TAHOMA PLAIN", Font.PLAIN, 50));
        labelInscription.setForeground(Color.white);
        labelInscription.setBounds(355, 45, 250, 50);
        panelInscription.add(labelInscription);

// niveau 2
        panelConnexion = new JPanel();
        panelConnexion.setBounds(0, 280, Constante.largeurFenetre, Constante.hauteurPanelIns);
        panelConnexion.setBackground(couleurConnexion);
        panelConnexion.setLayout(null);
        this.getContentPane().add(panelConnexion);

        JLabel labelConnexion = new JLabel("Connexion");
        labelConnexion.setFont(new Font("TAHOMA PLAIN", Font.PLAIN, 50));
        labelConnexion.setForeground(Color.white);
        labelConnexion.setBounds(355, 45, 250, 50);
        panelConnexion.add(labelConnexion);


// niveau 2
        inscriptionExtension = new PanelInscription(this);
        inscriptionExtension.setBounds(0, 280, Constante.largeurFenetre, Constante.hauteurPanelIns);
        inscriptionExtension.setVisible(false);
        this.getContentPane().add(inscriptionExtension);


// niveau 3
        connexionExtension = new PanelConnexion(this);
        connexionExtension.setBounds(0, 420, Constante.largeurFenetre, Constante.hauteurPanelIns);
        connexionExtension.setVisible(false);
        this.getContentPane().add(connexionExtension);


//niveau 3       
        panelConnexion2 = new JPanel();
        panelConnexion2.setBounds(0, 420, Constante.largeurFenetre, Constante.hauteurPanelIns);
        panelConnexion2.setBackground(couleurConnexion);
        panelConnexion2.setVisible(false);
        this.getContentPane().add(panelConnexion2);

        JLabel labelConnexion2 = new JLabel("Connexion");
        labelConnexion2.setFont(new Font("TAHOMA PLAIN", Font.PLAIN, 50));
        labelConnexion2.setForeground(Color.white);
        labelConnexion2.setBounds(50, 50, 50, 50);
        panelConnexion2.add(labelConnexion2);








        panelInscription.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                panelInscription.setVisible(true);
                inscriptionExtension.setVisible(true);

                panelConnexion.setVisible(false);
                connexionExtension.setVisible(false);

                panelConnexion2.setVisible(true);






            }
        });

        panelConnexion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelInscription.setVisible(true);
                inscriptionExtension.setVisible(false);

                panelConnexion.setVisible(true);
                connexionExtension.setVisible(true);

                panelConnexion2.setVisible(false);





            }
        });

        panelConnexion2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelInscription.setVisible(true);
                inscriptionExtension.setVisible(false);

                panelConnexion.setVisible(true);
                connexionExtension.setVisible(true);

                panelConnexion2.setVisible(false);

            }
        });
    }

    public void CreationPanelPrincipal() {
        //Panel Principal qui va servir d'affichage des autres panels
        fenPanelPrincipal = new PanelPrincipal(this);
        fenPanelPrincipal.setBounds(0, 140, Constante.largeurFenetre, 420);
        fenPanelPrincipal.setLayout(null);
        fenPanelPrincipal.setVisible(true);
        this.getContentPane().add(fenPanelPrincipal);

    }

    public JPanel getPanelInscription() {
        return this.panelInscription;
    }

    public JPanel getInscriptionExtension() {
        return this.inscriptionExtension;
    }

    public JPanel getPanelConnexion() {
        return this.panelConnexion;
    }

    public JPanel getConnexionExtension() {
        return this.connexionExtension;
    }

    public JPanel getPanelConnexion2() {
        return this.panelConnexion2;
    }

    // accesseur
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getIdentifiant() {
        return this.identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public String getDdn() {
        return ddn;
    }

    public void setDdn(String ddn) {
        this.ddn = ddn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
    // fin des accesseurs

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        nomMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        connexionMenuItem = new javax.swing.JMenuItem();
        jMenuItemInscription = new javax.swing.JMenuItem();
        jMenuItemModification = new javax.swing.JMenuItem();
        jMenuItemSuppression = new javax.swing.JMenuItem();
        deconnexionMenuItem = new javax.swing.JMenuItem();
        SortieMenuItem = new javax.swing.JMenuItem();
        jMenuCv = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemVotreCv = new javax.swing.JMenuItem();
        jMenuItemExporterEnPdf = new javax.swing.JMenuItem();
        nomjMenu = new javax.swing.JMenu();
        aideMenu = new javax.swing.JMenu();
        aproposMenuItem = new javax.swing.JMenuItem();
        jMenuItemAdministration = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenu2.setText("File");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Edit");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("File");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("Edit");
        jMenuBar2.add(jMenu5);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        fileMenu.setMnemonic('f');
        fileMenu.setText("Etudiants");

        connexionMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        connexionMenuItem.setMnemonic('o');
        connexionMenuItem.setText("Connexion");
        connexionMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connexionMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(connexionMenuItem);

        jMenuItemInscription.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemInscription.setText("Inscription");
        jMenuItemInscription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInscriptionActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItemInscription);

        jMenuItemModification.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemModification.setText("Modification");
        jMenuItemModification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemModificationActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItemModification);

        jMenuItemSuppression.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSuppression.setText("Suppression");
        jMenuItemSuppression.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSuppressionActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItemSuppression);

        deconnexionMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        deconnexionMenuItem.setMnemonic('s');
        deconnexionMenuItem.setText("Déconnexion");
        deconnexionMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deconnexionMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(deconnexionMenuItem);

        SortieMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        SortieMenuItem.setMnemonic('x');
        SortieMenuItem.setText("Sortie");
        SortieMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SortieMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(SortieMenuItem);

        nomMenuBar.add(fileMenu);

        jMenuCv.setText("CV");
        jMenuCv.add(jSeparator1);

        jMenuItemVotreCv.setText("Votre Cv");
        jMenuItemVotreCv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVotreCvActionPerformed(evt);
            }
        });
        jMenuCv.add(jMenuItemVotreCv);

        jMenuItemExporterEnPdf.setText("Exporter en PDF");
        jMenuItemExporterEnPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExporterEnPdfActionPerformed(evt);
            }
        });
        jMenuCv.add(jMenuItemExporterEnPdf);

        nomMenuBar.add(jMenuCv);
        nomMenuBar.add(nomjMenu);

        aideMenu.setMnemonic('h');
        aideMenu.setText("Aide");

        aproposMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        aproposMenuItem.setMnemonic('c');
        aproposMenuItem.setText("A propos");
        aideMenu.add(aproposMenuItem);

        jMenuItemAdministration.setText("Administration");
        jMenuItemAdministration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAdministrationActionPerformed(evt);
            }
        });
        aideMenu.add(jMenuItemAdministration);

        nomMenuBar.add(aideMenu);

        setJMenuBar(nomMenuBar);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 1103, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 215, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SortieMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SortieMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_SortieMenuItemActionPerformed

    private void connexionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connexionMenuItemActionPerformed
        this.getPanelInscription().setVisible(true);
        this.getInscriptionExtension().setVisible(false);
        this.getPanelConnexion().setVisible(true);
        this.getConnexionExtension().setVisible(true);
        this.getPanelConnexion2().setVisible(false);



    }//GEN-LAST:event_connexionMenuItemActionPerformed

    private void deconnexionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deconnexionMenuItemActionPerformed
        fenDeconnexion = new Deconnexion(this, true);
        this.fenDeconnexion.setVisible(true);
    }//GEN-LAST:event_deconnexionMenuItemActionPerformed

    private void jMenuItemInscriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInscriptionActionPerformed
        this.getPanelInscription().setVisible(true);
        this.getInscriptionExtension().setVisible(true);
        this.getPanelConnexion().setVisible(false);
        this.getConnexionExtension().setVisible(false);
        this.getPanelConnexion2().setVisible(true);
    }//GEN-LAST:event_jMenuItemInscriptionActionPerformed

    private void jMenuItemModificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemModificationActionPerformed

fenPanelPrincipal.ViderPanelPrincipal();
fenPanelPrincipal.CreationMenuUtilisateurs();
fenPanelPrincipal.CreationInformationsGenerales();
fenPanelPrincipal.getPanelInformationsGenerales().FieldVisible(true);


    }//GEN-LAST:event_jMenuItemModificationActionPerformed

    private void jMenuItemSuppressionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSuppressionActionPerformed

        fenSuppression = new Suppression(this, true);
        this.fenSuppression.setVisible(true);

    }//GEN-LAST:event_jMenuItemSuppressionActionPerformed

    private void jMenuItemVotreCvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVotreCvActionPerformed
        
        this.fenPanelPrincipal.ViderPanelPrincipal();
        this.fenPanelPrincipal.CreationMenuUtilisateurs();
        this.fenPanelPrincipal.CreationPanelCv();
    }//GEN-LAST:event_jMenuItemVotreCvActionPerformed

    private void jMenuItemAdministrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAdministrationActionPerformed
        fenConnexionAdministration = new ConnexionAdministration(this, true, fenOutils);
        this.fenConnexionAdministration.setVisible(true);
    }//GEN-LAST:event_jMenuItemAdministrationActionPerformed

    private void jMenuItemExporterEnPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExporterEnPdfActionPerformed
        this.CreationPdf();

    }//GEN-LAST:event_jMenuItemExporterEnPdfActionPerformed
    public void connecte(String leNom, String lePrenom, String leCourriel, String leNumTel, String laDateDeNaissance, String lAdresse, String identifiant, String laVille) {
        //maj de l'etat de la connexion
        this.connecte = true;
        //ajout du nom dans la fenetre
        this.nomjMenu.setText("Connecté en tant que : " + leNom);
        this.nomjMenu.setEnabled(false);

        // bouton modification > possibilité de cliquer dessus
        this.jMenuItemModification.setEnabled(true);

        // Bouton supprimé activé
        this.jMenuItemSuppression.setEnabled(true);


        this.setIdentifiant(identifiant);
        this.jMenuItemVotreCv.setEnabled(true);
        this.jMenuItemExporterEnPdf.setEnabled(true);





    }

    public void deconnecte() {
        this.connecte = false;
        this.nomjMenu.setText(null);
        // bouton modification > possibilité de cliquer dessus
        this.jMenuItemModification.setEnabled(false);

        // Bouton supprimé désactivé
        this.jMenuItemSuppression.setEnabled(false);


        this.jMenuItemVotreCv.setEnabled(false);
        this.jMenuItemExporterEnPdf.setEnabled(false);

        this.majConnexion();



    }
    
    public void CreationPdf(){
        try {
            CreationPdf creationPdf = new CreationPdf(this);
        } catch (BadElementException ex) {
            Logger.getLogger(InterfaceGraphique.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(InterfaceGraphique.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceGraphique.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        JOptionPane.showMessageDialog(this,"Votre cv s'est bien exporté sur votre bureau");
    }

    public void majConnexion() {
        deconnexionMenuItem.setEnabled(this.connecte);
        connexionMenuItem.setEnabled(!this.connecte);
        jMenuItemInscription.setEnabled(!this.connecte);
        if (connecte == true) {
        }
    }

    public void supprimer(String identifiant, String mdp) {
        this.setIdentifiant(identifiant);
        this.setMdp(mdp);
    }
    
    public AfficherInformations getAfficherInformations(){
        return this.fenAfficherInformations;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfaceGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceGraphique.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceGraphique().setVisible(true);


            }
        });


    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem SortieMenuItem;
    private javax.swing.JMenu aideMenu;
    private javax.swing.JMenuItem aproposMenuItem;
    private javax.swing.JMenuItem connexionMenuItem;
    private javax.swing.JMenuItem deconnexionMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenu jMenuCv;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemAdministration;
    private javax.swing.JMenuItem jMenuItemExporterEnPdf;
    private javax.swing.JMenuItem jMenuItemInscription;
    private javax.swing.JMenuItem jMenuItemModification;
    private javax.swing.JMenuItem jMenuItemSuppression;
    private javax.swing.JMenuItem jMenuItemVotreCv;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuBar nomMenuBar;
    private javax.swing.JMenu nomjMenu;
    // End of variables declaration//GEN-END:variables
}
