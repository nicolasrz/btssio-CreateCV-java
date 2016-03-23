/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants.classes;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * @author n.ruiz
 */
public class ImagePanel extends JPanel {

    private static final long serialVersionUID = 1;
    private final Image img;
    private final int w, h,x,y;

    public ImagePanel(String fileName,  int xLocation , int yLocation, int width, int height) {
        img = Toolkit.getDefaultToolkit().getImage(fileName);
        w = width;
        h = height;
        x = xLocation;
        y = yLocation;
        
        this.setBounds(x,y,w,h);
        
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, w, h, this);
    }
}
