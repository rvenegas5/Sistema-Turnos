/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controlador;

import ec.edu.espol.modelo.Medico;
import ec.edu.espol.modelo.Paciente;
import ec.edu.espol.modelo.Puesto;
import ec.edu.espol.modelo.Sintoma;
import ec.edu.espol.modelo.Turno;
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
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * @author WILLIAM
 */
public class VentanaPacienteController implements Initializable {

    @FXML
    private Button botonRegPaciente;
    @FXML
    private TextField nombrePaciente;
    @FXML
    private TextField apellidoPaciente;
    @FXML
    private TextField edadPaciente;
    @FXML
    private TextField sintomaPaciente;
    @FXML
    private Button botonCancelarRegPac;
    @FXML
    private RadioButton generoMasc;
    @FXML
    private RadioButton generoFem;

    public LinkedList<Turno> turnos = new LinkedList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup tg = new ToggleGroup();
        this.generoMasc.setToggleGroup(tg);
        this.generoFem.setToggleGroup(tg);
    }

    @FXML
    private void registrarPaciente(ActionEvent event) {

            try {
                File file = new File("./pacientes.txt");
                BufferedWriter bw;
                bw = new BufferedWriter(new FileWriter(file, true));
                PrintWriter escribir = new PrintWriter(bw);

                String nombreP = this.nombrePaciente.getText();
                String apellidoP = this.apellidoPaciente.getText();
                int edadP = Integer.parseInt(this.edadPaciente.getText());
                char genero;

                // Válido la selección del género y lo establezo
                if (generoMasc.isSelected()) {
                    genero = 'M';
                } else if (generoFem.isSelected()) {
                    genero = 'F';
                } else {
                    throw new IllegalArgumentException();
                }

                String sintoma = sintomaPaciente.getText();
                Sintoma sintomaP = null;
                LinkedList<Sintoma> listaSintoma = Sintoma.leerSintomas("sintomas.txt");
                ListIterator<Sintoma> lIt = listaSintoma.listIterator();

                // Válidacion del sintoma
                while (lIt.hasNext()) {
                    Sintoma next = lIt.next();
                    if (next.getNombre().equals(sintoma)) {
                        sintomaP = next;
                        break;
                    } else if (lIt.hasNext() == false) {
                        sintomaP = new Sintoma(sintoma, 5);
                    }
                }

                Paciente paciente = new Paciente(nombreP, apellidoP, edadP, genero, sintomaP);

                escribir.println(paciente.toString());
                escribir.flush();
                escribir.close();

                bw.close();

                // Muestro mensaje de registro
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("ÉXITO");
                alert.setContentText("REGISTRO EXITOSO");
                alert.showAndWait();

                // Restablece el registro
                nombrePaciente.setText("");
                apellidoPaciente.setText("");
                edadPaciente.setText("");
                sintomaPaciente.setText("");

            } catch (NumberFormatException | IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("ERROR");
                alert.setContentText("ERROR DE REGISTRO");
                alert.showAndWait();

            }

    }

    @FXML
    private void cancelarRegistroPac(ActionEvent event) {
        Stage stage = (Stage) this.botonCancelarRegPac.getScene().getWindow();
        stage.close();
    }

}
