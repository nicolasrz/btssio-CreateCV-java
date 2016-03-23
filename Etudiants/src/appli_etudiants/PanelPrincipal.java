/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants;

import appli_etudiants.classes.Constante;
import appli_etudiants.cv.PanelCv;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Nicolas
 */
public class PanelPrincipal extends JPanel{
    private InterfaceGraphique fenetre;
    private UtilisateurConnecte fenUtilisateurConnecte;
    private InformationsGenerales panelInformationsGenerales;
    private MenuUtilisateurConnecte fenMenuUtilisateurs;
    private PanelConnexion fenPanelConnexion;
    private PanelCv fenPanelCv;
 
    
    
    public PanelPrincipal(InterfaceGraphique parent) {
        this.fenetre=(InterfaceGraphique) parent;
        
        Color couleurDeFond = new Color(226, 225, 235);
        this.setBackground(couleurDeFond);
        
        Dimension taille = new Dimension (Constante.largeurFenetre,420);
        this.setSize(taille);
        this.setPreferredSize(taille);
        this.setMaximumSize(taille);
        this.setMinimumSize(taille);
        
                 
              
                fenUtilisateurConnecte = new UtilisateurConnecte(this,fenetre);
                fenUtilisateurConnecte.setBounds(0,70,Constante.largeurFenetre,350);
                fenUtilisateurConnecte.setVisible(true);
                this.add(fenUtilisateurConnecte);
                
              
        
    }
    
    public void ViderPanelPrincipal(){

        this.remove(fenUtilisateurConnecte);
        this.SuppressionInformationsGenerales();
        this.SuppressionPanelCv();
        this.repaint();
    }
    
    
    // creation des onglets qui servent de menu à l'utilisateur
    public void CreationMenuUtilisateurs(){
                fenMenuUtilisateurs = new MenuUtilisateurConnecte(this);
                fenMenuUtilisateurs.setBounds(0,0,Constante.largeurFenetre,70);
                this.add(fenMenuUtilisateurs);
                fenMenuUtilisateurs.setVisible(true);
                this.repaint();
    }
    
    // creation du panel Informations générales
    
            public void CreationInformationsGenerales(){
                panelInformationsGenerales = new InformationsGenerales(this.fenetre,this);
                panelInformationsGenerales.setBounds(0,70,Constante.largeurFenetre,350);
                this.add(panelInformationsGenerales);
                panelInformationsGenerales.setVisible(true);
                this.repaint();
                this.validate();
                
            }
            
          
            
            public void CreationPanelCv(){
                 fenPanelCv = new PanelCv(fenetre,this);
                fenPanelCv.setBounds(0,70,Constante.largeurFenetre,350);
                fenPanelCv.setLayout(null);
                this.add(fenPanelCv);
                fenPanelCv.setVisible(true);
                this.repaint();
                this.validate();
            }
    

    
    public InformationsGenerales getPanelInformationsGenerales(){
        return this.panelInformationsGenerales;
    }
    public MenuUtilisateurConnecte getFenMenuUtilisateurs(){
        return this.fenMenuUtilisateurs;
    }
    
    public void PanelPrincipalVisible(boolean text){
        this.setVisible(text);
    }

     
    public InterfaceGraphique getFenetre(){
        return this.fenetre;
    }
    public PanelCv getPanelCv(){
        return this.fenPanelCv;
    }
    
    public void SuppressionPanelCv(){
       if(fenPanelCv != null){
        this.remove(fenPanelCv);
    }
    }
    public void SuppressionInformationsGenerales(){
        
        if (panelInformationsGenerales!=null){
            this.remove(panelInformationsGenerales);
        }
    }
    public void RepaintMenuUtilisateurs(){
        this.remove(fenMenuUtilisateurs);
        this.CreationMenuUtilisateurs();
        this.fenMenuUtilisateurs.repaint();
        this.repaint();
    }
    
    

}
