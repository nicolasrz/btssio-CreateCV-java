 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants.EnTete;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;


public class Photo extends JLabel {

    private BufferedImage image;

    public Photo(BufferedImage image) {

        this.image = image;
    }

    public void paintComponent(Graphics g) {


        g.drawImage(image, 0, 0, 120,120, null);


    }
    
 
}