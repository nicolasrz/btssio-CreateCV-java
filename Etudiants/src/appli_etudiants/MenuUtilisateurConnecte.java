/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants;

import appli_etudiants.classes.AfficheImage;
import appli_etudiants.classes.Constante;
import appli_etudiants.cv.PanelCv;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author Nicolas
 */
public class MenuUtilisateurConnecte extends javax.swing.JPanel {
   
    
    private JPanel ongletInfoGenerales;
    private JPanel ongletVotreCv;
    private JPanel ongletExporterPdf;
    
    private PanelPrincipal fenPanelPrincipal;
    private PanelCv fenPanelCv;
    private InformationsGenerales fenInformationsgenerales;

    public MenuUtilisateurConnecte(PanelPrincipal fenPanelPrincipal) {
        initComponents();
        this.fenPanelPrincipal = fenPanelPrincipal;
        
        Dimension taille = new Dimension(Constante.largeurFenetre, 70);
        this.setSize(taille);
        this.setPreferredSize(taille);
        this.setMaximumSize(taille);
        this.setMinimumSize(taille);

        AfficheImage imageOngletInfosGenerales = new AfficheImage("/appli_etudiants/image/onglets-18.png", 0, 0, 960, 70);
        this.add(imageOngletInfosGenerales);

        //onglets informations generales
        ongletInfoGenerales = new JPanel();
        ongletInfoGenerales.setBounds(0, 0, 320, 70);
        ongletInfoGenerales.setLayout(null);
        this.add(ongletInfoGenerales);
        this.EventOngletsInformationsGenerales();


        //onglets modifier cv
        ongletVotreCv = new JPanel();
        ongletVotreCv.setBounds(320, 0, 320, 70);
        ongletVotreCv.setLayout(null);
        this.add(ongletVotreCv);
        this.EventOngletsVotreCv();

        //onglet Exporter pdf
        ongletExporterPdf = new JPanel();
        ongletExporterPdf.setBounds(640, 0, 320, 70);
        this.add(ongletExporterPdf);
        this.EventOngletsExporterPdf();


    }

    public void EventOngletsInformationsGenerales() {

        ongletInfoGenerales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                getMenuUtilisateurConnecte().removeAll();

                AfficheImage imageInfoGenerales17 = new AfficheImage("/appli_etudiants/image/onglets-17.png", 0, 0, 960, 70);
                getMenuUtilisateurConnecte().add(imageInfoGenerales17);


                getMenuUtilisateurConnecte().add(ongletInfoGenerales);
                getMenuUtilisateurConnecte().repaint();


            }
        });

        ongletInfoGenerales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                getMenuUtilisateurConnecte().removeAll();
                getPanelPrincipal().SuppressionPanelCv();
                getPanelPrincipal().SuppressionInformationsGenerales();
            

                AfficheImage imageInfoGenerales18 = new AfficheImage("/appli_etudiants/image/onglets-18.png", 0, 0, 960, 70);
                getMenuUtilisateurConnecte().add(imageInfoGenerales18);


                if (fenInformationsgenerales == null){
                 
                    getPanelPrincipal().CreationInformationsGenerales();
                }
                
                
                getMenuUtilisateurConnecte().add(ongletInfoGenerales);
                getMenuUtilisateurConnecte().add(ongletVotreCv);

                getMenuUtilisateurConnecte().add(ongletExporterPdf);
               
               
               
           
                
                

                getMenuUtilisateurConnecte().repaint();
                getPanelPrincipal().repaint();



            }
        });

    }

    public void EventOngletsVotreCv() {

        ongletVotreCv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {

                getMenuUtilisateurConnecte().removeAll();

                AfficheImage imageVotreCv15 = new AfficheImage("/appli_etudiants/image/onglets-15.png", 0, 0, 960, 70);
                getMenuUtilisateurConnecte().add(imageVotreCv15);


                getMenuUtilisateurConnecte().add(ongletVotreCv);
                getMenuUtilisateurConnecte().repaint();



            }
        });


        ongletVotreCv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                getMenuUtilisateurConnecte().removeAll();
                getPanelPrincipal().SuppressionPanelCv();
                getPanelPrincipal().SuppressionInformationsGenerales();
             

                AfficheImage imageVotreCv18 = new AfficheImage("/appli_etudiants/image/onglets-18.png", 0, 0, 960, 70);
                getMenuUtilisateurConnecte().add(imageVotreCv18);
                
                
                  if (fenPanelCv == null){
                    
                    getPanelPrincipal().CreationPanelCv();
                }
                

                getMenuUtilisateurConnecte().add(ongletInfoGenerales);
                getMenuUtilisateurConnecte().add(ongletVotreCv);

                getMenuUtilisateurConnecte().add(ongletExporterPdf);
                
           
              


                getMenuUtilisateurConnecte().repaint();
                    getPanelPrincipal().repaint();

            }
        });
    }

    public void EventOngletsExporterPdf() {
        ongletExporterPdf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {

                getMenuUtilisateurConnecte().removeAll();

                AfficheImage imageExporter16 = new AfficheImage("/appli_etudiants/image/onglets-16.png", 0, 0, 960, 70);
                getMenuUtilisateurConnecte().add(imageExporter16);

                getMenuUtilisateurConnecte().add(ongletExporterPdf);
                getMenuUtilisateurConnecte().repaint();


            }
        });
        ongletExporterPdf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                getMenuUtilisateurConnecte().removeAll();

                AfficheImage imageExporterPdf18 = new AfficheImage("/appli_etudiants/image/onglets-18.png", 0, 0, 960, 70);
                getMenuUtilisateurConnecte().add(imageExporterPdf18);

                getMenuUtilisateurConnecte().add(ongletInfoGenerales);
                getMenuUtilisateurConnecte().add(ongletVotreCv);
                getMenuUtilisateurConnecte().add(ongletExporterPdf);
                getMenuUtilisateurConnecte().repaint();
                
                getPanelPrincipal().getFenetre().CreationPdf();
                
 }
        });

    }

    public MenuUtilisateurConnecte getMenuUtilisateurConnecte() {
        return this;
    }

    public PanelPrincipal getPanelPrincipal() {
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
