/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controlador;

import ec.edu.espol.modelo.Medico;
import ec.edu.espol.modelo.Puesto;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
        obsMedicos.addAll(Medico.getMedicos("medicos.txt"));

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
            obsPuesto.addAll(Puesto.getPuestos("puestos.txt"));
            puestos.addAll(obsPuesto);
            this.tablaPuesto.setItems(puestos);
            initCombox();
        }
    }

    @FXML
    private void añadirPuesto(ActionEvent event) {

        try {
            if (!this.comboBoxMedicos.getItems().isEmpty()) {
                File file = new File("puestos.txt");
                BufferedWriter bw;
                bw = new BufferedWriter(new FileWriter(file, true));
                PrintWriter escribir = new PrintWriter(bw);

                Medico doc = comboBoxMedicos.getValue();
                int numeroPuesto = getNumPuesto(numPuesto);

                Puesto puesto = new Puesto(numeroPuesto, doc);
                this.puestos.add(puesto);
                this.tablaPuesto.setItems(puestos);

                escribir.println(puesto.toString());
                escribir.flush();
                escribir.close();
                bw.close();

                initCombox();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("ERROR");
                alert.setContentText("NO HA SELECCIONADO A UN MEDICO");
                alert.showAndWait();
            }

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("ERROR DE REGISTRO");
            alert.showAndWait();
        }

    }

    @FXML
    private void salirPuestos(ActionEvent event) {
        Stage stage = (Stage) this.botonSalir.getScene().getWindow();
        stage.close();
    }

    private int getNumPuesto(int numPuesto) {
        LinkedList<Puesto> p = Puesto.getPuestos("puestos.txt");
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
            p.setEstado("Eliminado");
            this.puestos.remove(p);
            this.tablaPuesto.refresh();
        }
    }

    @FXML
    private void atenderPuesto(ActionEvent event) {

    }

}
