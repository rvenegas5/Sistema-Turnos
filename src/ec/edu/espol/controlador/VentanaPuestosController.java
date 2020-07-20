/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controlador;

import ec.edu.espol.modelo.Medico;
import ec.edu.espol.modelo.Paciente;
import ec.edu.espol.modelo.Puesto;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WILLIAM
 */
public class VentanaPuestosController implements Initializable {

    @FXML
    private TableView<Puesto> tablaPuesto;
    @FXML
    private ComboBox<Medico> comboBoxMedicos;
    @FXML
    private TableColumn colEstadoPuesto;
    @FXML
    private TableColumn colMedico;
    @FXML
    private TableColumn colNumPuesto;
    @FXML
    private Button botonAñadirPuesto;
    @FXML
    private Button botonSalir;
    @FXML
    private Button botonEliminarPuesto;
    @FXML
    private Button botonAtenderPuesto;

    private ObservableList<Puesto> puestos;

    private int numPuesto = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        puestos = FXCollections.observableArrayList();
        this.colNumPuesto.setCellValueFactory(new PropertyValueFactory("numero"));
        this.colMedico.setCellValueFactory(new PropertyValueFactory("medico"));
        this.colEstadoPuesto.setCellValueFactory(new PropertyValueFactory("estado"));
        initCombox();
        initPuestos();
    }

    public void initCombox() {
        ObservableList<Medico> obsMedicos = FXCollections.observableArrayList();
        ObservableList<Puesto> obsTablaMedicos = tablaPuesto.getItems();
        obsMedicos.addAll(Medico.getMedicos("./medicos.txt"));

        if (obsTablaMedicos.isEmpty()) {
            this.comboBoxMedicos.getItems().clear();
            this.comboBoxMedicos.setItems(obsMedicos);
        } else {
            Iterator<Puesto> itColMed = obsTablaMedicos.iterator();
            while (itColMed.hasNext()) {
                Puesto p = itColMed.next();
                Medico m = p.getMedico();
                obsMedicos.remove(m);
            }
            this.comboBoxMedicos.getItems().clear();
            this.comboBoxMedicos.setItems(obsMedicos);
        }

    }

    public void initPuestos() {
        if (!Puesto.getPuestos("puestos.txt").isEmpty()) {
            ObservableList<Puesto> obsPuesto = FXCollections.observableArrayList();
            obsPuesto.addAll(Puesto.getPuestos("./puestos.txt"));
            puestos.addAll(obsPuesto);
            this.tablaPuesto.setItems(puestos);
            initCombox();
        }
    }

    @FXML
    private void añadirPuesto(ActionEvent event) {

        if (!this.comboBoxMedicos.getItems().isEmpty()) {

            try {
                Medico doc = comboBoxMedicos.getValue();
                int numeroPuesto = getNumPuesto(numPuesto);

                Puesto puesto = new Puesto(numeroPuesto, doc);
                File file = new File("./puestos.txt");
                BufferedWriter bw;
                bw = new BufferedWriter(new FileWriter(file, true));
                PrintWriter escribir = new PrintWriter(bw);
                escribir.println(puesto.cambiotoString());
                escribir.flush();
                escribir.close();
                bw.close();
                
                this.puestos.add(puesto);
                this.tablaPuesto.setItems(puestos);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("EXITO");
                alert.setContentText("SE HA AGREGADO UN NUEVO PUESTO");
                alert.showAndWait();

                initCombox();
            } catch (IOException ex) {
                Logger.getLogger(VentanaPuestosController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("NO HA SELECCIONADO A UN MEDICO");
            alert.showAndWait();
        }

    }

    @FXML
    private void salirPuestos(ActionEvent event) {
        Stage stage = (Stage) this.botonSalir.getScene().getWindow();
        stage.close();
    }

    private int getNumPuesto(int numPuesto) {
        LinkedList<Puesto> p = Puesto.getPuestos("./puestos.txt");
        if (p.isEmpty()) {
            numPuesto += 1;
        } else {
            numPuesto = p.size() + 1;
        }
        return numPuesto;
    }

    @FXML
    private void eliminarPuesto(ActionEvent event) {
        Puesto p = this.tablaPuesto.getSelectionModel().getSelectedItem();

        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("DEBE SELECCIONAR UN PUESTO");
            alert.showAndWait();
        } else {
            this.puestos.remove(p);
            this.tablaPuesto.refresh();
            removeLine("puestos.txt", p.cambiotoString());
            initCombox();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("EXITO");
            alert.setContentText("SE HA ELIMINADO UN PUESTO");
            alert.showAndWait();
        }
    }

    @FXML
    private void atenderPuesto(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/vista/VentanaaAtencionPuestos.fxml"));

            // Referencia al padre
            Parent root = loader.load();

            // Escogemos el controlador de la vista
            VentanaAtencionPuestosController controladorPuesto = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            // Hasta acabar con la tarea de la otra vista no regreso a la vista anterior
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(VentanaPuestosController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void removeLine(String file, String lineToRemove) {

        try {

            File inFile = new File(file);

            if (!inFile.isFile()) {
                inFile.isFile();
                System.out.println("Parameter is not an existing file");
                return;
            }

            //Construct the new file that will later be renamed to the original filename. 
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(file));
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
                inFile.delete();
                System.out.println("Could not delete file");
                return;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile)) {
                tempFile.renameTo(inFile);
                System.out.println("Could not rename file");
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
