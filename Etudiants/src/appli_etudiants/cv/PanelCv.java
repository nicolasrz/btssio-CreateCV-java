/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants.cv;

import appli_etudiants.EnTete.AfficherInformations;
import appli_etudiants.EnTete.PosteRecherche;
import appli_etudiants.InterfaceGraphique;
import appli_etudiants.PanelPrincipal;
import appli_etudiants.classes.AfficheImage;
import appli_etudiants.classes.Constante;
import appli_etudiants.cv.CentresDinteret.PanelIntermediaireCentresDinteret;
import appli_etudiants.cv.ExperiencePro.AfficherExpererienceProfessionnelle;
import appli_etudiants.cv.ExperiencePro.PanelIntermediaireExpPro;
import appli_etudiants.cv.Formation.PanelIntermediaireFormation;
import appli_etudiants.cv.Informatique.PanelIntermediaireInformatique;
import appli_etudiants.cv.Langues.PanelIntermediaireLangues;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Nicolas
 */
public class PanelCv extends javax.swing.JPanel {

    private InterfaceGraphique fenetre;
    private PanelPrincipal fenPanelPrincipal;
    private AfficherInformations fenAfficherInfos;
    private PosteRecherche fenPosteRecherche;
    
    private PanelIntermediaireExpPro panelInterExpPro;
    private PanelIntermediaireFormation panelInterFormation;
    private PanelIntermediaireInformatique panelInterInformatique;
    private PanelIntermediaireLangues panelInterLangues;
    private PanelIntermediaireCentresDinteret panelInterCentresDinteret;
    
    
    private AfficherExpererienceProfessionnelle fenAfficherExpPro;
    
    private JPanel boutonAjouterExpPro;
    private JPanel boutonAjouterFormation;
    private JPanel boutonAjouterInformatique;
    private JPanel boutonAjouterLangues;
    private JPanel boutonAjouterCentresDinteret;

    public PanelCv(InterfaceGraphique fenetre, PanelPrincipal parent)  {
        initComponents();

        this.fenetre = fenetre;
        this.fenPanelPrincipal = (PanelPrincipal) parent;
        this.setLayout(null);


        Color couleurDeFond = new Color(226, 225, 235);
        this.setBackground(couleurDeFond);

        Dimension taille = new Dimension(Constante.largeurFenetre, 420);
        this.setSize(taille);
        this.setPreferredSize(taille);
        this.setMaximumSize(taille);
        this.setMinimumSize(taille);
        
        this.jTabbedPaneCv.setBackground(couleurDeFond);


// informations de l'utilisateur    
        try{
            fenAfficherInfos = new AfficherInformations(fenetre);
        this.jPanelTitre.add(fenAfficherInfos);
        this.fenAfficherInfos.setBounds(0, 0, Constante.largeurCadre, Constante.hauteurCadre); 
        }catch(IOException ex){
            System.out.println(ex);}
       

//Poste recherché
        fenPosteRecherche = new PosteRecherche(fenetre);
        this.jPanelTitre.add(fenPosteRecherche);
        this.jPanelTitre.setBackground(couleurDeFond);
        this.fenPosteRecherche.setBounds(0, Constante.hauteurCadre, Constante.largeurCadre, Constante.hauteurCadre);

        // ExperienceProfessionnelles
        JScrollPane scrollExpPro = new JScrollPane();
        this.jPanelExperiencePro.add(scrollExpPro);
        this.jPanelExperiencePro.setBackground(couleurDeFond);
        scrollExpPro.setBounds(0, Constante.margeHautScroll, Constante.largeurScroll, 420);

        this.panelInterExpPro = new PanelIntermediaireExpPro(fenetre,fenAfficherExpPro);
        scrollExpPro.getViewport().add(panelInterExpPro);
        scrollExpPro.setBackground(couleurDeFond);
        this.panelInterExpPro.setBounds(0, 0, Constante.largeurFenetre, Constante.hauteurPanelInter);

        // Formation
        JScrollPane scrollFormation = new JScrollPane();
        this.jPanelFormation.add(scrollFormation);
        this.jPanelFormation.setBackground(couleurDeFond);
        scrollFormation.setBounds(0, Constante.margeHautScroll, Constante.largeurScroll, 420);

        this.panelInterFormation = new PanelIntermediaireFormation(fenetre);
        scrollFormation.getViewport().add(panelInterFormation);
        scrollFormation.setBackground(couleurDeFond);
        this.panelInterFormation.setBounds(0, 0, Constante.largeurFenetre, Constante.hauteurPanelInter);
        

        //Informatique 
        JScrollPane scrollInformatique = new JScrollPane();
        this.jPanelInformatique.add(scrollInformatique);
        this.jPanelInformatique.setBackground(couleurDeFond);
        scrollInformatique.setBounds(0, Constante.margeHautScroll, Constante.largeurScroll, 420);

        this.panelInterInformatique = new PanelIntermediaireInformatique(fenetre);
        scrollInformatique.getViewport().add(panelInterInformatique);
        scrollInformatique.setBackground(couleurDeFond);
        this.panelInterInformatique.setBounds(0, 0, Constante.largeurFenetre, Constante.hauteurPanelInter);

// Langues
        JScrollPane scrollLangues = new JScrollPane();
        this.jPanelLangues.add(scrollLangues);
        this.jPanelLangues.setBackground(couleurDeFond);
        scrollLangues.setBounds(0, Constante.margeHautScroll, Constante.largeurScroll, 420);

        this.panelInterLangues = new PanelIntermediaireLangues(fenetre);
        scrollLangues.getViewport().add(panelInterLangues);
        scrollLangues.setBackground(couleurDeFond);
        this.panelInterLangues.setBounds(0, 0, Constante.largeurFenetre, Constante.hauteurPanelInter);

// Centres dinteret
        JScrollPane scrollCentresDinteret = new JScrollPane();
        this.jPanelCentresDinteret.add(scrollCentresDinteret);
        this.jPanelCentresDinteret.setBackground(couleurDeFond);
        scrollCentresDinteret.setBounds(0, Constante.margeHautScroll, Constante.largeurScroll, 420);

        this.panelInterCentresDinteret = new PanelIntermediaireCentresDinteret(fenetre);
        scrollCentresDinteret.getViewport().add(panelInterCentresDinteret);
        scrollCentresDinteret.setBackground(couleurDeFond);
        this.panelInterCentresDinteret.setBounds(0, 0, Constante.largeurFenetre, Constante.hauteurPanelInter);
        
// bouton ajouter exp Pro
        boutonAjouterExpPro = new JPanel();
        this.boutonAjouterExpPro.setLayout(null);
        this.boutonAjouterExpPro.setBounds(0,0,25,25);
        this.jPanelExperiencePro.add(boutonAjouterExpPro);
        
        AfficheImage imageBoutonAjouterExpPro = new AfficheImage("/appli_etudiants/image/iconeAjouter.png", 0, 0, 25,25);
        this.boutonAjouterExpPro.add(imageBoutonAjouterExpPro);
        this.EventBoutonAjouterExpPro();
        
        
        
// bouton ajouter Formation
        boutonAjouterFormation = new JPanel();
        this.boutonAjouterFormation.setLayout(null);
        this.boutonAjouterFormation.setBounds(0,0,25,25);
        this.jPanelFormation.add(boutonAjouterFormation);
        
        AfficheImage imageBoutonAjouterFormation = new AfficheImage("/appli_etudiants/image/iconeAjouter.png", 0, 0, 25,25);
        this.boutonAjouterFormation.add(imageBoutonAjouterFormation);
        this.EventBoutonAjouterFormation();
        
//bouton ajouter Informatique
        boutonAjouterInformatique = new JPanel();
        this.boutonAjouterInformatique.setLayout(null);
        this.boutonAjouterInformatique.setBounds(0,0,25,25);
        this.jPanelInformatique.add(boutonAjouterInformatique);
        
        AfficheImage imageBoutonAjouterInformatique = new AfficheImage("/appli_etudiants/image/iconeAjouter.png", 0, 0, 25,25);
        this.boutonAjouterInformatique.add(imageBoutonAjouterInformatique);
        this.EventBoutonAjouterInformatique();
        
//bouton ajouter Langues
        boutonAjouterLangues = new JPanel();
        this.boutonAjouterLangues.setLayout(null);
        this.boutonAjouterLangues.setBounds(0,0,25,25);
        this.jPanelLangues.add(boutonAjouterLangues);
        
        AfficheImage imageBoutonAjouterLangues = new AfficheImage("/appli_etudiants/image/iconeAjouter.png", 0, 0, 25,25);
        this.boutonAjouterLangues.add(imageBoutonAjouterLangues);
        this.EventBoutonAjouterLangues();
        
//bouton ajouter Centre Dinteret
        boutonAjouterCentresDinteret = new JPanel();
        this.boutonAjouterCentresDinteret.setLayout(null);
        this.boutonAjouterCentresDinteret.setBounds(0,0,25,25);
        this.jPanelCentresDinteret.add(boutonAjouterCentresDinteret);
        
        AfficheImage imageBoutonAjouterCentresDinteret = new AfficheImage("/appli_etudiants/image/iconeAjouter.png", 0, 0, 25,25);
        this.boutonAjouterCentresDinteret.add(imageBoutonAjouterCentresDinteret);
        this.EventBoutonAjouterCentresDinteret();
        
        
        
        
    }
    
    
    public void EventBoutonAjouterExpPro() {
        
        boutonAjouterExpPro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {

        panelInterExpPro.AjouterExpPro();
        panelInterExpPro.getFenAfficherExpPro().BoutonModifierVisible(false);
        panelInterExpPro.getFenAfficherExpPro().BoutonEnregistrerVisible(true);
        panelInterExpPro.getFenAfficherExpPro().FieldVisible(true);

        panelInterExpPro.validate();
        panelInterExpPro.repaint();
             

            }
        });
        
    }
    
    public void EventBoutonAjouterFormation() {
        
        boutonAjouterFormation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {

        panelInterFormation.AjouterFormation();
        panelInterFormation.getFenAfficherFormation().BoutonModifierVisible(false);
        panelInterFormation.getFenAfficherFormation().BoutonEnregistrerVisible(true);
        panelInterFormation.getFenAfficherFormation().FieldVisible(true);

        panelInterFormation.validate();
        panelInterFormation.repaint();
         

            }
        });


    }
    
    
    
        public void EventBoutonAjouterInformatique() {
        
        boutonAjouterInformatique.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {

        panelInterInformatique.AjouterInformatique();
        panelInterInformatique.getFenAfficherInformatique().BoutonModifierVisible(false);
        panelInterInformatique.getFenAfficherInformatique().BoutonEnregistrerVisible(true);
        panelInterInformatique.getFenAfficherInformatique().FieldVisible(true);

        panelInterInformatique.validate();
        panelInterInformatique.repaint();
         

            }
        });


    }
        
        public void EventBoutonAjouterLangues() {
        
        boutonAjouterLangues.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {

        panelInterLangues.AjouterLangues();
        panelInterLangues.getFenAfficherLangues().BoutonModifierVisible(false);
        panelInterLangues.getFenAfficherLangues().BoutonEnregistrerVisible(true);
        panelInterLangues.getFenAfficherLangues().FieldVisible(true);

        panelInterLangues.validate();
        panelInterLangues.repaint();
         

            }
        });


    }
        
        public void EventBoutonAjouterCentresDinteret() {
        
        boutonAjouterCentresDinteret.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {

        panelInterCentresDinteret.AjouterCentresDinteret();
        panelInterCentresDinteret.getFenAfficherCentresDinteret().BoutonModifierVisible(false);
        panelInterCentresDinteret.getFenAfficherCentresDinteret().BoutonEnregistrerVisible(true);
        panelInterCentresDinteret.getFenAfficherCentresDinteret().FieldVisible(true);

        panelInterCentresDinteret.validate();
        panelInterCentresDinteret.repaint();
         

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

        jTabbedPaneCv = new javax.swing.JTabbedPane();
        jPanelTitre = new javax.swing.JPanel();
        jPanelExperiencePro = new javax.swing.JPanel();
        jPanelFormation = new javax.swing.JPanel();
        jPanelInformatique = new javax.swing.JPanel();
        jPanelLangues = new javax.swing.JPanel();
        jPanelCentresDinteret = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(960, 420));
        setMinimumSize(new java.awt.Dimension(960, 420));
        setLayout(null);

        javax.swing.GroupLayout jPanelTitreLayout = new javax.swing.GroupLayout(jPanelTitre);
        jPanelTitre.setLayout(jPanelTitreLayout);
        jPanelTitreLayout.setHorizontalGroup(
            jPanelTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 955, Short.MAX_VALUE)
        );
        jPanelTitreLayout.setVerticalGroup(
            jPanelTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
        );

        jTabbedPaneCv.addTab("En-tête", jPanelTitre);

        javax.swing.GroupLayout jPanelExperienceProLayout = new javax.swing.GroupLayout(jPanelExperiencePro);
        jPanelExperiencePro.setLayout(jPanelExperienceProLayout);
        jPanelExperienceProLayout.setHorizontalGroup(
            jPanelExperienceProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 955, Short.MAX_VALUE)
        );
        jPanelExperienceProLayout.setVerticalGroup(
            jPanelExperienceProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
        );

        jTabbedPaneCv.addTab("Experiences Professionnelles", jPanelExperiencePro);

        javax.swing.GroupLayout jPanelFormationLayout = new javax.swing.GroupLayout(jPanelFormation);
        jPanelFormation.setLayout(jPanelFormationLayout);
        jPanelFormationLayout.setHorizontalGroup(
            jPanelFormationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 955, Short.MAX_VALUE)
        );
        jPanelFormationLayout.setVerticalGroup(
            jPanelFormationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
        );

        jTabbedPaneCv.addTab("Formation", jPanelFormation);

        javax.swing.GroupLayout jPanelInformatiqueLayout = new javax.swing.GroupLayout(jPanelInformatique);
        jPanelInformatique.setLayout(jPanelInformatiqueLayout);
        jPanelInformatiqueLayout.setHorizontalGroup(
            jPanelInformatiqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 955, Short.MAX_VALUE)
        );
        jPanelInformatiqueLayout.setVerticalGroup(
            jPanelInformatiqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
        );

        jTabbedPaneCv.addTab("Informatique", jPanelInformatique);

        javax.swing.GroupLayout jPanelLanguesLayout = new javax.swing.GroupLayout(jPanelLangues);
        jPanelLangues.setLayout(jPanelLanguesLayout);
        jPanelLanguesLayout.setHorizontalGroup(
            jPanelLanguesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 955, Short.MAX_VALUE)
        );
        jPanelLanguesLayout.setVerticalGroup(
            jPanelLanguesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
        );

        jTabbedPaneCv.addTab("Langues", jPanelLangues);

        javax.swing.GroupLayout jPanelCentresDinteretLayout = new javax.swing.GroupLayout(jPanelCentresDinteret);
        jPanelCentresDinteret.setLayout(jPanelCentresDinteretLayout);
        jPanelCentresDinteretLayout.setHorizontalGroup(
            jPanelCentresDinteretLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 955, Short.MAX_VALUE)
        );
        jPanelCentresDinteretLayout.setVerticalGroup(
            jPanelCentresDinteretLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
        );

        jTabbedPaneCv.addTab("Centres d'interêt", jPanelCentresDinteret);

        add(jTabbedPaneCv);
        jTabbedPaneCv.setBounds(0, 0, 960, 420);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelCentresDinteret;
    private javax.swing.JPanel jPanelExperiencePro;
    private javax.swing.JPanel jPanelFormation;
    private javax.swing.JPanel jPanelInformatique;
    private javax.swing.JPanel jPanelLangues;
    private javax.swing.JPanel jPanelTitre;
    private javax.swing.JTabbedPane jTabbedPaneCv;
    // End of variables declaration//GEN-END:variables
}
