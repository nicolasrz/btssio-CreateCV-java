/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants;

import appli_etudiants.EnTete.Photo;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Image;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 * @author Nicolas
 */
public class CreationPdf {

    private RequetePdf requetePdf;
    private InterfaceGraphique fenetre;
    private Document document;
    private String identifiant;
    private Font fontTitre;
    private PdfWriter writer;
    
    


    public CreationPdf(InterfaceGraphique parent) throws BadElementException, MalformedURLException, IOException {
        this.fenetre = (InterfaceGraphique) parent;
        this.identifiant = this.fenetre.getIdentifiant();
        
        this.fontTitre = new Font();
       
      
        
       
        
     
        



        this.generer();


    }

    public void generer() {
        document = new Document();
        Paragraph sautDeLignes = new Paragraph();


        try {
            String utilisateur = System.getProperty("user.name");
           
            
           
            writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Users/"+utilisateur+"/Desktop/cv_"+this.identifiant+".pdf"));
          //  writer.setViewerPreferences(PdfWriter.PageLayoutTwoColumnRight);
            document.open();
            
            

           this.fontTitre.setSize(15);

            requetePdf = new RequetePdf(this, fenetre);
            
           requetePdf.requetePhoto();
            
            requetePdf.requeteInformations();
            this.ajouterLigneVide(sautDeLignes, 2);
            
            

            requetePdf.requeteTitre();
             this.ajouterLigneVide(sautDeLignes, 1);
            
            
            
            Chunk TitreExpPro = new Chunk("Expériences Professionnelles",this.fontTitre);
            this.document.add(TitreExpPro);
            requetePdf.requeteExperiencePro();
          //   this.ajouterLigneVide(sautDeLignes,  1);
            
            
            
            Chunk TitreFormation = new Chunk("Formation",this.fontTitre);
            this.document.add(TitreFormation);
            requetePdf.requeteFormation();
        //    this.ajouterLigneVide(sautDeLignes, 1);
            
            
            
            Chunk TitreInformatique = new Chunk("Informatique",this.fontTitre);
            this.document.add(TitreInformatique);
            requetePdf.requeteInformatique();
            
            
            Chunk TitreLangues = new Chunk("Langues",this.fontTitre);
            this.document.add(TitreLangues);
            requetePdf.requeteLangues();
            
            
            Chunk TitreCentreDinteret = new Chunk ("Centres d'intérêt",this.fontTitre);
            this.document.add(TitreCentreDinteret);
            requetePdf.requeteCentresDinteret();
                    









        } catch (Exception e) {
            e.printStackTrace();
        }
        document.close();
    }

    public InterfaceGraphique getFenetre() {
        return this.fenetre;
    }

    public Document getDocument() {
        return this.document;
    }

    public void ajouterLigneVide(Paragraph paragraph, int number) throws DocumentException {
        for (int i = 0; i < number; i++) {
            this.document.add(new Paragraph(" "));
        }
    }
    
    public PdfWriter getWriter(){
        return writer;
    }


}
