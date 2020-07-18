/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controlador;

import ec.edu.espol.modelo.Medico;
import ec.edu.espol.modelo.Paciente;
import ec.edu.espol.modelo.Sintoma;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anii BC
 */
public class VentanaMedicoController implements Initializable {

    @FXML
    private Button registrar;
    @FXML
    private TextField nombreMedico;
    @FXML
    private TextField apellidoMedico;
    @FXML
    private TextField edadMedico;
    @FXML
    private TextField especialidadMedico;
    @FXML
    private RadioButton generoM;
    @FXML
    private RadioButton generoF;
    @FXML
    private Button botoncancelarDoc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup tg = new ToggleGroup();
        this.generoM.setToggleGroup(tg);
        this.generoF.setToggleGroup(tg);
    }

    @FXML
    private void registrarDoc(ActionEvent event) {
        try {
            File file = new File("medicos.txt");
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(file, true));
            PrintWriter escribir = new PrintWriter(bw);

            String nombreDoc = this.nombreMedico.getText();
            String apellidoDoc = this.apellidoMedico.getText();
            int edadDoc = Integer.parseInt(this.edadMedico.getText());
            char genero;

            // Válido la selección del género y lo establezo
            if (generoM.isSelected()) {
                genero = 'M';
            } else if (generoF.isSelected()) {
                genero = 'F';
            } else {
                throw new IllegalArgumentException();
            }

            String especialidad = especialidadMedico.getText();

            Medico medico = new Medico(nombreDoc, apellidoDoc, edadDoc, genero, especialidad);
            escribir.println(medico.toString());

            escribir.flush();
            escribir.close();
            bw.close();
            
            // Muestro mensaje de registro
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("ÉXITO");
            alert.setContentText("REGISTRO EXITOSO");
            alert.showAndWait();
            
            // Restablece los valores de registro
            nombreMedico.setText("");
            apellidoMedico.setText("");
            edadMedico.setText("");
            especialidadMedico.setText("");

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("ERROR DE REGISTRO");
            alert.showAndWait();
        }
    }

    @FXML
    private void cancelarRegistroDoc(ActionEvent event) {
        Stage stage = (Stage) this.botoncancelarDoc.getScene().getWindow();
        stage.close();
    }

}
