/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net1_project3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author hp
 */
public class Net1_project3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    
    
        Client client1 = new Client();
        client1.setVisible(true);
        client1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Client client2 = new Client();
        client2.setVisible(true);
        client2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ServerFrame ServerFrame = new ServerFrame();
        ServerFrame.setVisible(true);
        ServerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     
         
    }

}
