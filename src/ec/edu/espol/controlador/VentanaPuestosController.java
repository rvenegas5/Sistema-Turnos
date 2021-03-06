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
                puesto.setEstado("Ocupado");

                PriorityQueue<Paciente> pacientes = Paciente.getPrioridadAtencion(Paciente.leerPacientes("pacientes.txt"));
                if (pacientes.poll() != null) {
                    puesto.setEstado("Ocupado");
                    Turno turno = new Turno(pacientes.poll(), puesto);
                    guardarTurno(turno);
                }

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
        File f = new File("puestos.txt");
        File f1 = new File("puestos1.txt");

        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("DEBE SELECCIONAR UN PUESTO");
            alert.showAndWait();
        } else {
            this.puestos.remove(p);
            this.tablaPuesto.refresh();

            ModificarArchivos.modify(p.cambiotoString(), "puestos.txt", "puestos1.txt");
            ModificarArchivos.borrarArchivo("puestos.txt");
            ModificarArchivos.renombrarArchivo(f1, f);
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
        if (!Puesto.getPuestos("puestos.txt").isEmpty()) {
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
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("ERROR DE SELECCION");
            alert.showAndWait();

        }
    }

    public static void guardarTurno(Turno turno) {
        // return getLetra() + "|" + getNumero() + "|" + getPaciente().toString() + "|" + getPuesto().toString();
        try {
            File file = new File("turnos.txt");
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(file, true));
            PrintWriter escribir = new PrintWriter(bw);

            Paciente p = turno.getPaciente();
            Puesto puesto = turno.getPuesto();
            escribir.println(p.toString() + "|" + puesto.cambiotoString());

            escribir.flush();
            escribir.close();

            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(VentanaPacienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
