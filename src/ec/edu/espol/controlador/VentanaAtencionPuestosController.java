/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controlador;


import ec.edu.espol.modelo.Medico;
import ec.edu.espol.modelo.Paciente;
import ec.edu.espol.modelo.Puesto;
import ec.edu.espol.modelo.Turno;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author WILLIAM
 */
public class VentanaAtencionPuestosController {

    @FXML
    private static TextArea textAreaDiagnostico;
    @FXML
    private TextField txtFieldMedicamento;
    @FXML
    private TextField txtFieldDosis;
    @FXML
    private TextField txtFieldDias;
    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonAceptar;
    
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    @FXML
    private void aceptarReceta(Event event) {
        
        LinkedList<Turno> turnos = Turno.getTurnos("turnos.txt");
        Turno t = turnos.get(0);
        removeLineFromFile(t.toString(), "turnos.txt");
        PriorityQueue<Paciente> pacientes = Paciente.getPrioridadAtencion(Paciente.leerPacientes("pacientes.txt"));
        pacientes.poll();
        removeLineFromFile(t.getPaciente().toString(), "pacientes.txt");
        
    }
    
    @FXML
    private void cancelarReceta(Event event) {
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        stage.close();
    } 
    
    public void removeLineFromFile(String lineToRemove, String archivo) {

        try {

            File inFile = new File(archivo);

            if (!inFile.isFile()) {
                System.out.println("no hay file");
                return;
            }

            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(archivo));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {

                if (!line.trim().equals(lineToRemove)) {

                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            //Delete the original file
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile)) {
                System.out.println("Could not rename file");

            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
