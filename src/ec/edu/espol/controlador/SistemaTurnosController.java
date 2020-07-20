/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controlador;

import ec.edu.espol.controlador.lista.CircularSimplyLinkedList;
import ec.edu.espol.modelo.Puesto;
import ec.edu.espol.modelo.Turno;
import ec.edu.espol.modelo.Video;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn turnoCol;
    @FXML
    private TableColumn puestoCol;
    @FXML
    private MediaView videosView;
    @FXML
    private TableView turnosTable;
    @FXML
    private Button regresar;
    
    private ObservableList<Turno> turnos;

    private volatile boolean enough = false;
    

    
    CircularSimplyLinkedList<String> videos;
    
    String archivo = "videos.txt";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        turnos = FXCollections.observableArrayList();
        this.turnoCol.setCellValueFactory(new PropertyValueFactory("turno"));
        this.puestoCol.setCellValueFactory(new PropertyValueFactory("puesto"));
        clock();
        videos = inicializarVideos(archivo);
        colaVideos(videos);
        initTurnos();

    }

    public CircularSimplyLinkedList<String> inicializarVideos(String archivo) {
        CircularSimplyLinkedList<String> urlVideos = new CircularSimplyLinkedList<>();

        try {
            File file = new File(archivo);

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while((linea = br.readLine()) != null){
              String f= new File(linea).getAbsolutePath();
               urlVideos.addFirst(f);
            }
        } catch (IOException ex) {
            Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
        }
        return urlVideos;
    }

    public void colaVideos(CircularSimplyLinkedList<String> videos) {

        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                Iterator it=videos.iterator();
                while(!enough) {
                    try {
                        
                        Media media= new Media(new File((String)it.next()).toURI().toURL().toExternalForm());
                         MediaPlayer mp= new MediaPlayer(media);     
                        videosView.setMediaPlayer(mp);
                        mp.play();
                        Thread.sleep(15000);
                                               
                    } catch (InterruptedException ex) {} catch (MalformedURLException ex) {
                        Logger.getLogger(SistemaTurnosController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }   
            }
        });hilo.start();
    }

    public void clock() {

        // timer actualiza cada segundo 
        Thread timer = new Thread(() -> {
            SimpleDateFormat dt = new SimpleDateFormat("hh:mm:ss");
            while (!enough) {
                try {

                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
                final String time = dt.format(new Date());
                Platform.runLater(() -> {
                    // actualizando el Label
                    horaLabel.setText(time);
                });
            }
        });
        timer.start();

    }

  
    @FXML
    private void regresarVentana(ActionEvent event) {
        Stage stage = (Stage) this.regresar.getScene().getWindow();
       
        stage.close();
    }

    
    private void initTurnos(){
        if (!Turno.getTurnos("turnos.txt").isEmpty()) {
            ObservableList<Turno> obsTurno = FXCollections.observableArrayList();
            obsTurno.addAll(Turno.getTurnos("turnos.txt"));
            turnos.addAll(obsTurno);
            this.turnosTable.setItems(turnos);
        }
        this.turnosTable.setItems(turnos);
    
    }

}
