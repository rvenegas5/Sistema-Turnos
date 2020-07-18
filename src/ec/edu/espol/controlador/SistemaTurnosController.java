/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controlador;

import ec.edu.espol.modelo.Video;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author Anii BC
 */
public class SistemaTurnosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
     private MediaView videosView;
    @FXML
    private TableView turnosTable;

    
    
    MediaPlayer mediaPlayer;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        
        
        
    }    
    
    
    public Queue inicializarVideos(String archivo){
         Queue<MediaPlayer> urlVideos = new LinkedList<>();
        
        try {
            File file = new File (archivo);
            FileReader fr = new FileReader (file);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            
            while((linea = br.readLine()) != null){
                Media media= new Media(linea);
                mediaPlayer= new MediaPlayer(media);
                urlVideos.add(mediaPlayer);
                //videosView.setMediaPlayer(mediaPlayer);
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
        return urlVideos;
    }
    
    
    
   
    
    
    
    
    
}
