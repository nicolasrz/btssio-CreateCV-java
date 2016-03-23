/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants.classes;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Nicolas
 */
public class AfficheImage extends JLabel {

    public Image img;
    private int x;
    private int y;
    private int xWidth;
    private int yHeight;

    public AfficheImage(String s, int xLocation, int yLocation, int xLargeur, int yHauteur) {



        this.x = x;
        this.y = y;
        this.xWidth = xLargeur;
        this.yHeight = yHauteur;

        this.img = img;

//img = getToolkit().getImage(s); 
        this.img = new ImageIcon(getClass().getResource(s)).getImage();
        this.setBounds(x, y, xWidth, yHeight);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.img, 0, 0, xWidth, yHeight, this);
    }
}
