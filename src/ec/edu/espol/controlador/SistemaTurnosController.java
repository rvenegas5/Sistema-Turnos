/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controlador;

import ec.edu.espol.controlador.lista.CircularSimplyLinkedList;
import ec.edu.espol.modelo.Video;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

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
    private Label horaLabel;
       @FXML
    private Label minLabel;
          @FXML
    private Label segLabel;
    
    @FXML
     private MediaView videosView;
    @FXML
    private TableView turnosTable;
    @FXML
     private Button regresar;
    
    private volatile boolean enough = false;
    MediaPlayer mediaPlayer;
    CircularSimplyLinkedList<MediaPlayer> videos;
    String archivo="videos.txt";
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       clock();
        videos= inicializarVideos(archivo);
        colaVideos(videos);
       
    }    
    
    
    public CircularSimplyLinkedList<MediaPlayer> inicializarVideos(String archivo){
         CircularSimplyLinkedList<MediaPlayer> urlVideos = new CircularSimplyLinkedList<>();
         
        
        try {
            File file = new File (archivo);
            
            FileReader fr = new FileReader (file);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            MediaPlayer mp;
             Media media;
         
           while((linea = br.readLine()) != null){
              
                media= new Media(new File(linea).toURI().toURL().toExternalForm());
                mp= new MediaPlayer(media);
                urlVideos.addFirst(mp);
               
                
            }
           
            
            
        } catch (IOException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
        return urlVideos ;
    }
    
    
    public void colaVideos(CircularSimplyLinkedList<MediaPlayer> videos){
    
    
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                Iterator it=videos.iterator();
                while(true) {
                    try {
                        
                        mediaPlayer=(MediaPlayer)it.next();
                       videosView.setMediaPlayer(mediaPlayer);
                        mediaPlayer.setAutoPlay(true);
                        
                        Thread.sleep(15000);
                    } catch (InterruptedException ex) {}
                }
               
            }});hilo.start();
      
    }
    
    
    
    
    
    
    
    
    

    
  public void clock(){
    // timer actualiza cada segundo 
    Thread timer = new Thread(() -> {
        SimpleDateFormat dt = new SimpleDateFormat("hh:mm:ss");
        while(!enough) {
            try {
                
                Thread.sleep(1000);
            } catch (InterruptedException ex) {}
            final String time = dt.format(new Date());
            Platform.runLater(()-> {
                // actualizando el Label
                horaLabel.setText(time);
            });
        }
    });timer.start();
    
  }
    
  
  
  
    @FXML
    private void regresarVentana(ActionEvent event) {
        Stage stage = (Stage) this.regresar.getScene().getWindow();
      //if(mediaPlayer.isAutoPlay()==true) {
      mediaPlayer.stop();
       
        stage.close();
    }
    
    
    
    
    
}
