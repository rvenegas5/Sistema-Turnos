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
import java.util.logging.Level;
import java.util.logging.Logger;
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

        try {
            LinkedList<Turno> turnos = Turno.getTurnos("./turnos.txt");
            Turno t = turnos.get(0);

            removeLine(t.getPaciente().toString() + "|" + t.getPuesto().cambiotoString(), "./turnos.txt");
            PriorityQueue<Paciente> pacientes = Paciente.getPrioridadAtencion(Paciente.leerPacientes("./pacientes.txt"));
            Paciente p = pacientes.poll();
            removeLine(p.toString(), "./pacientes.txt");
            removeLine("./puestos.txt", t.getPuesto().cambiotoString());

            Puesto puesto = t.getPuesto();
            puesto.setEstado("Libre");
            File file = new File("./puestos.txt");
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(file, true));
            PrintWriter escribir = new PrintWriter(bw);
            escribir.println(puesto.cambiotoString());
            escribir.flush();
            escribir.close();
            bw.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ÉXITO");
            alert.setContentText("REGISTRO EXITOSO");
            alert.showAndWait();

            Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            Logger.getLogger(VentanaAtencionPuestosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancelarReceta(Event event) {
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        stage.close();
    }

    public void removeLine(String file, String lineToRemove) {

        try {

            File inFile = new File(file);

            if (!inFile.isFile()) {
                inFile.isFile();
            }

            //Construct the new file that will later be renamed to the original filename. 
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            try (BufferedReader br = new BufferedReader(new FileReader(file));
                    PrintWriter pw = new PrintWriter(new FileWriter(tempFile))) {

                String line = null;

                //Read from the original file and write to the new
                //unless content matches data to be removed.
                while ((line = br.readLine()) != null) {

                    if (!line.trim().equals(lineToRemove)) {

                        pw.println(line);
                        pw.flush();
                    }
                }
            }

            //Delete the original file
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                inFile.delete();
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile)) {
                tempFile.renameTo(inFile);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
