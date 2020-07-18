/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WILLIAM
 */
public class VentanaPrincipalController implements Initializable {

    @FXML
    private Button botonAbrirST;
    @FXML
    private Button botonSalir;
    @FXML
    private Button botonRegistrarPasc;
    @FXML
    private Button botonRegistrarDoc;
    @FXML
    private Button puestoAtencion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void abrirSistemaTurnos(ActionEvent event) {

    }

    @FXML
    private void salirVentanaPrincipal(ActionEvent event) {
        Stage stage = (Stage) this.botonSalir.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void registrarPasciente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/vista/VentanaPaciente.fxml"));

            // Referencia al padre
            Parent root = loader.load();

            // Escogemos el controlador de la vista
            VentanaPacienteController controladorPaciente = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            // Hasta acabar con la tarea de la otra vista no regreso a la vista anterior
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void registrarDoctor(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/vista/VentanaMedico.fxml"));

            // Referencia al padre
            Parent root = loader.load();

            // Escogemos el controlador de la vista
            VentanaMedicoController controladorMedico = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            // Hasta acabar con la tarea de la otra vista no regreso a la vista anterior
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void asignarPuestos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espol/vista/VentanaPuestos.fxml"));

            // Referencia al padre
            Parent root = loader.load();

            // Escogemos el controlador de la vista
            VentanaPuestosController controladorPuesto = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            // Hasta acabar con la tarea de la otra vista no regreso a la vista anterior
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        
        }

    }
}
