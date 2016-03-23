/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants;

import appli_etudiants.classes.AfficheImage;
import appli_etudiants.classes.Constante;
import appli_etudiants.classes.ImagePanel;

import com.itextpdf.text.BadElementException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Nicolas
 */
public class UtilisateurConnecte extends javax.swing.JPanel {

    private PanelPrincipal fenPanelPrincipal;
    private JPanel boutonVotreCv;
    private JPanel boutonInformationGenerale;
    private JPanel boutonExporterPdf;
    private InformationsGenerales panelInformationsGenerales;
    private InterfaceGraphique fenetre;
    private JPanel separateur;
    private JPanel separateur2;
 

    public UtilisateurConnecte(PanelPrincipal parent, InterfaceGraphique fenetre) {
        initComponents();
        this.fenPanelPrincipal = (PanelPrincipal) parent;
        this.fenetre = fenetre;


        Color couleurDeFond = new Color(226, 225, 235);
        this.setBackground(couleurDeFond);

        Dimension taille = new Dimension(Constante.largeurFenetre, 420);
        this.setSize(taille);
        this.setPreferredSize(taille);
        this.setMaximumSize(taille);
        this.setMinimumSize(taille);





        //informations générales
        boutonInformationGenerale = new JPanel();
        boutonInformationGenerale.setLayout(null);
        boutonInformationGenerale.setBounds(75, 0, 220, 240);
        this.add(boutonInformationGenerale);

        AfficheImage imageBoutonInformationsGenerales = new AfficheImage("/appli_etudiants/image/picto_profil_up.png", 0, 0, 220, 240);
        boutonInformationGenerale.add(imageBoutonInformationsGenerales);
        boutonInformationGenerale.repaint();
       
        
        //separateur 
        separateur = new JPanel();
        separateur.setBounds(331, 0, 4, 250);
        separateur.setLayout(null);
        this.add(separateur);
               

        AfficheImage imageSeparateur = new AfficheImage("/appli_etudiants/image/bande-15.png", 0, 0, 4, 250);
        separateur.add(imageSeparateur);


        // modificer cv

        boutonVotreCv = new JPanel();
        boutonVotreCv.setLayout(null);
        boutonVotreCv.setBounds(370, 0, 220, 240); //+62
        this.add(boutonVotreCv);

        AfficheImage imageBoutonVotreCv = new AfficheImage("/appli_etudiants/image/picto_cv_up.png", 0, 0, 220, 240);
        boutonVotreCv.add(imageBoutonVotreCv);
        boutonVotreCv.repaint();

        // second separateur
        separateur2 = new JPanel();
        separateur2.setBounds(625, 0, 4, 250);
        separateur2.setLayout(null);
        this.add(separateur2);

        AfficheImage imageSeparateur2 = new AfficheImage("/appli_etudiants/image/bande-15.png", 0, 0, 4, 250);
        separateur2.add(imageSeparateur2);


        // Exporter en pdf
        boutonExporterPdf = new JPanel();
        boutonExporterPdf.setLayout(null);
        boutonExporterPdf.setBounds(665, 0, 220, 240); //+62
        this.add(boutonExporterPdf);

        AfficheImage imageBoutonExporterPdf = new AfficheImage("/appli_etudiants/image/picto_export_up.png", 0, 0, 220, 240);
        boutonExporterPdf.add(imageBoutonExporterPdf);
        boutonExporterPdf.repaint();



        this.EventInformationsGenerales();
        this.EventVotreCv();
        this.EventExportPdf();








    }

    public void EventInformationsGenerales() {
        // bouton Informations generale Mouse Lister
        boutonInformationGenerale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                boutonInformationGenerale.removeAll();

                AfficheImage imageBoutonVotreCv = new AfficheImage("/appli_etudiants/image/picto_profil_down.png", 0, 0, 220, 240);
                boutonInformationGenerale.add(imageBoutonVotreCv);
                boutonInformationGenerale.repaint();
                
                




            }
        });


        boutonInformationGenerale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {

                boutonInformationGenerale.removeAll();

                AfficheImage imageBoutonVotreCv = new AfficheImage("/appli_etudiants/image/picto_profil_up.png", 0, 0, 220, 240);
                boutonInformationGenerale.add(imageBoutonVotreCv);
                boutonInformationGenerale.repaint();

                UtilisateurConnecteVisible(false);


           
                fenPanelPrincipal.ViderPanelPrincipal();
                fenPanelPrincipal.CreationMenuUtilisateurs();
               fenPanelPrincipal.CreationInformationsGenerales();
           



            }
        });
    }

    public void EventVotreCv() {
        // bouton Informations generale Mouse Lister
        boutonVotreCv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                boutonVotreCv.removeAll();

                AfficheImage imageBoutonInformationsGenerales = new AfficheImage("/appli_etudiants/image/picto_cv_down.png", 0, 0, 220, 240);
                boutonVotreCv.add(imageBoutonInformationsGenerales);
                boutonVotreCv.repaint();
                


            }
        });


        boutonVotreCv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {

                boutonVotreCv.removeAll();

                AfficheImage imageBoutonInformationsGenerales = new AfficheImage("/appli_etudiants/image/picto_cv_up.png", 0, 0, 220, 240);
                boutonVotreCv.add(imageBoutonInformationsGenerales);
                boutonVotreCv.repaint();

                fenPanelPrincipal.ViderPanelPrincipal();
                fenPanelPrincipal.CreationMenuUtilisateurs();
                fenPanelPrincipal.CreationPanelCv();
              
                





            }
        });
    }

    public void EventExportPdf() {
        // bouton Informations generale Mouse Lister
        boutonExporterPdf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                boutonExporterPdf.removeAll();

                AfficheImage imageBoutonExporterPdf = new AfficheImage("/appli_etudiants/image/picto_export_down.png", 0, 0, 220, 240);
                boutonExporterPdf.add(imageBoutonExporterPdf);
                boutonExporterPdf.repaint();


            }
        });


        boutonExporterPdf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {

                boutonExporterPdf.removeAll();

                AfficheImage imageBoutonExporterPdf = new AfficheImage("/appli_etudiants/image/picto_export_up.png", 0, 0, 220, 240);
                boutonExporterPdf.add(imageBoutonExporterPdf);
                boutonExporterPdf.repaint();

             
                getFenetre().CreationPdf();
                



            }
        });
    }
    


    public void UtilisateurConnecteVisible(boolean text) {
        this.setVisible(text);
    }
    
    public InterfaceGraphique getFenetre(){
        return this.fenetre;
    }
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMaximumSize(new java.awt.Dimension(960, 420));
        setMinimumSize(new java.awt.Dimension(960, 420));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
