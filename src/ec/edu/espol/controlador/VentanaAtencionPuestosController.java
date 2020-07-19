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
        
    }
    
    @FXML
    private void cancelarReceta(Event event) {
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        stage.close();
    } 
}
