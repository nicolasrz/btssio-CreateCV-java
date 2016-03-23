/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants;

import appli_etudiants.EnTete.Photo;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.mysql.jdbc.Connection;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Nicolas
 */
public class RequetePdf {

    private CreationPdf fenCreationPdf;
    private InterfaceGraphique fenetre;
    private String nom;
    private String prenom;
    private String courriel;
    private String numTel;
    private String dateDeNaissance;
    private String adresse;
    private String ville;
    private String codePostal;
    private String pays;
    private String espace = " ";
    private String espace8Carac = "        ";
    private Font font;
    private String titre;
    private String periode;
    private String emploi;
    private String description;
    private String entreprise;
    private String lieu;
    private String diplome;
    private String competences;
    private String langues;
    private String centresDinteret;
    private Photo tPhoto;

    public RequetePdf(CreationPdf parent, InterfaceGraphique fenetre) {
        this.fenCreationPdf = (CreationPdf) parent;
        this.fenetre = fenetre;

        this.font = new Font();

    }

    public void requetePhoto() throws DocumentException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
            com.mysql.jdbc.Connection maConnexion = (com.mysql.jdbc.Connection) DriverManager.getConnection(connexionUrl);

            Statement requete = maConnexion.createStatement();

            String identifiant = this.fenCreationPdf.getFenetre().getIdentifiant();
            ResultSet recupPhoto = requete.executeQuery("SELECT photoBinaire FROM cv_photo WHERE identifiant='" + identifiant + "'");

            if (recupPhoto.next()) {
                try {
                    InputStream istreamImage = recupPhoto.getBinaryStream("photoBinaire");
                    BufferedImage imBuff = ImageIO.read(istreamImage);

                    // recupération et creation de la photo dans le jlabel tPhoto
                    tPhoto = new Photo(imBuff);
                    tPhoto.setBounds(0,0,100,100);
                    
                    PdfContentByte contentByte = this.fenCreationPdf.getWriter().getDirectContent();
                    PdfTemplate template = contentByte.createTemplate(100,100);
                    Graphics2D g2 = template.createGraphics(100,100);
                    
                    //creation d'un panel qui va accueillir le jlabel
                    JPanel panelPhoto = new JPanel();
                    panelPhoto.setLayout(null);
                    panelPhoto.add(tPhoto);
                    //on termine l'excution -> comme un repaint;
                    panelPhoto.addNotify();
                    panelPhoto.setSize(120, 120);
                    panelPhoto.validate();
                    
                    
                    panelPhoto.print(g2);
                    g2.dispose();
                    contentByte.addTemplate(template, 460, 720);
                 

                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Classe de connexion mysql non trouvee..." + ex.toString());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL exception ... " + ex.toString());
        }

    }

    public void requeteInformations() throws DocumentException {


        try {

            Class.forName("com.mysql.jdbc.Driver");
            String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
            Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);
            Statement requete = maConnexion.createStatement();

            String identifiant = this.fenCreationPdf.getFenetre().getIdentifiant();


            ResultSet recupInformations = requete.executeQuery("SELECT * FROM utilisateurs WHERE identifiant = '" + identifiant + "' ");
            while (recupInformations.next()) {
                this.nom = recupInformations.getString("nom");
                this.prenom = recupInformations.getString("prenom");
                this.courriel = recupInformations.getString("courriel");
                this.numTel = recupInformations.getString("num_de_telephone");
                this.dateDeNaissance = recupInformations.getString("date_de_naissance");
                this.adresse = recupInformations.getString("adresse");
                this.ville = recupInformations.getString("ville");
                this.codePostal = recupInformations.getString("codePostal");
                this.pays = recupInformations.getString("pays");
            }


            this.font.setSize(10);

            Chunk c1 = new Chunk(this.nom + this.espace + this.prenom, this.font);
            Paragraph p2 = new Paragraph(this.dateDeNaissance, this.font);
            Paragraph p3 = new Paragraph(this.adresse, this.font);
            Chunk c4 = new Chunk(this.codePostal + this.espace + this.ville + this.espace + this.pays, this.font);
            Paragraph p5 = new Paragraph(this.numTel, this.font);
            Paragraph p6 = new Paragraph(this.courriel, this.font);


            this.fenCreationPdf.getDocument().add(c1);
            this.fenCreationPdf.getDocument().add(p2);
            this.fenCreationPdf.getDocument().add(p3);
            this.fenCreationPdf.getDocument().add(c4);
            this.fenCreationPdf.getDocument().add(p5);
            this.fenCreationPdf.getDocument().add(p6);





        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }



    }

    public void requeteTitre() throws DocumentException {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
            Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);
            Statement requete = maConnexion.createStatement();

            String identifiant = this.fenCreationPdf.getFenetre().getIdentifiant();


            ResultSet recupTitre = requete.executeQuery("SELECT * FROM cv_titre WHERE identifiant = '" + identifiant + "' ");
            while (recupTitre.next()) {
                this.titre = recupTitre.getString("titre");
            }

            this.font.setSize(18);
            Paragraph pTitre = new Paragraph(this.titre, this.font);

            this.fenCreationPdf.getDocument().add(pTitre);




        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void requeteExperiencePro() throws DocumentException {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
            Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);
            Statement requete = maConnexion.createStatement();

            String identifiant = this.fenCreationPdf.getFenetre().getIdentifiant();


            ResultSet recupExpPro = requete.executeQuery("SELECT * FROM cv_experience_professionnelle WHERE identifiant = '" + identifiant + "' ");
            while (recupExpPro.next()) {
                this.periode = recupExpPro.getString("periode");
                this.emploi = recupExpPro.getString("emploi");
                this.description = recupExpPro.getString("description");
                this.entreprise = recupExpPro.getString("entreprise");
                this.lieu = recupExpPro.getString("lieu");

                this.font.setSize(10);

                Paragraph p1 = new Paragraph(new Chunk(this.periode + this.espace8Carac + this.entreprise, this.font));
                Paragraph p2 = new Paragraph(this.emploi, this.font);
                Paragraph p3 = new Paragraph(this.description, this.font);
                Paragraph p4 = new Paragraph(this.lieu, this.font);

                this.fenCreationPdf.getDocument().add(p1);
                this.fenCreationPdf.getDocument().add(p2);
                this.fenCreationPdf.getDocument().add(p3);
                this.fenCreationPdf.getDocument().add(p4);

                this.fenCreationPdf.ajouterLigneVide(p4, 1);

            }







        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void requeteFormation() throws DocumentException {


        try {

            Class.forName("com.mysql.jdbc.Driver");
            String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
            Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);
            Statement requete = maConnexion.createStatement();

            String identifiant = this.fenCreationPdf.getFenetre().getIdentifiant();


            ResultSet recupFormation = requete.executeQuery("SELECT * FROM cv_formation WHERE identifiant = '" + identifiant + "' ");
            while (recupFormation.next()) {
                this.periode = recupFormation.getString("periode");

                this.diplome = recupFormation.getString("diplome");

                this.lieu = recupFormation.getString("lieu");

                this.font.setSize(10);

                Paragraph p1 = new Paragraph(new Chunk(this.periode + this.espace8Carac + this.lieu, this.font));

                Paragraph p7 = new Paragraph(this.diplome, this.font);


                this.fenCreationPdf.getDocument().add(p1);
                this.fenCreationPdf.getDocument().add(p7);


                this.fenCreationPdf.ajouterLigneVide(p1, 1);

            }







        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void requeteInformatique() throws DocumentException {


        try {

            Class.forName("com.mysql.jdbc.Driver");
            String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
            Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);
            Statement requete = maConnexion.createStatement();

            String identifiant = this.fenCreationPdf.getFenetre().getIdentifiant();


            ResultSet recupInformatique = requete.executeQuery("SELECT * FROM cv_informatique WHERE identifiant = '" + identifiant + "' ");
            while (recupInformatique.next()) {
                this.competences = recupInformatique.getString("competences");
                this.description = recupInformatique.getString("description");

                this.font.setSize(10);

                Paragraph p1 = new Paragraph(new Chunk(this.competences, this.font));

                Paragraph p2 = new Paragraph(this.description, this.font);


                this.fenCreationPdf.getDocument().add(p1);
                this.fenCreationPdf.getDocument().add(p2);


                this.fenCreationPdf.ajouterLigneVide(p1, 1);

            }







        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void requeteLangues() throws DocumentException {


        try {

            Class.forName("com.mysql.jdbc.Driver");
            String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
            Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);
            Statement requete = maConnexion.createStatement();

            String identifiant = this.fenCreationPdf.getFenetre().getIdentifiant();


            ResultSet recupLangues = requete.executeQuery("SELECT * FROM cv_langues WHERE identifiant = '" + identifiant + "' ");
            while (recupLangues.next()) {
                this.langues = recupLangues.getString("langues");
                this.description = recupLangues.getString("description");

                this.font.setSize(10);

                Paragraph p1 = new Paragraph(new Chunk(this.langues, this.font));

                Paragraph p2 = new Paragraph(this.description, this.font);


                this.fenCreationPdf.getDocument().add(p1);
                this.fenCreationPdf.getDocument().add(p2);


                this.fenCreationPdf.ajouterLigneVide(p1, 1);

            }







        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void requeteCentresDinteret() throws DocumentException {


        try {

            Class.forName("com.mysql.jdbc.Driver");
            String connexionUrl = "jdbc:mysql://localhost:3306/applietudiants?user=applietudiants&password=sio";
            Connection maConnexion = (Connection) DriverManager.getConnection(connexionUrl);
            Statement requete = maConnexion.createStatement();

            String identifiant = this.fenCreationPdf.getFenetre().getIdentifiant();


            ResultSet recupCentresDinteret = requete.executeQuery("SELECT * FROM cv_centres_dinteret WHERE identifiant = '" + identifiant + "' ");
            while (recupCentresDinteret.next()) {
                this.centresDinteret = recupCentresDinteret.getString("centres_dinteret");
                this.description = recupCentresDinteret.getString("cd_description");

                this.font.setSize(10);

                Paragraph p1 = new Paragraph(new Chunk(this.centresDinteret, this.font));

                Paragraph p2 = new Paragraph(this.description, this.font);


                this.fenCreationPdf.getDocument().add(p1);
                this.fenCreationPdf.getDocument().add(p2);


                this.fenCreationPdf.ajouterLigneVide(p1, 1);

            }







        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
}
