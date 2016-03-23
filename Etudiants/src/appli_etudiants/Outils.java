/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appli_etudiants;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

/**
 *
 * @author nc
 */
public class Outils {
    /**
     * 
     * @param mot
     * @return le md5(mot) comme en php
     */
    public static String md5(String mot) throws NoSuchAlgorithmException{
                        MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(mot.getBytes());
			byte[] md5 = md.digest();
                        
			StringBuffer sb = new StringBuffer(2 * md5.length);
			
                        
                        for (int i = 0; i < md5.length; i++) //Conversion du array byte
                        {
                                int value = md5[i] & 0xFF;
                                if(Integer.toHexString(value).length()!=2) {sb.append("\u0030"); sb.append(Integer.toHexString(value));}
                                else sb.append(Integer.toHexString(value));
                                
                        }
                    
			mot= sb.toString();
                        return mot;
    }
}
