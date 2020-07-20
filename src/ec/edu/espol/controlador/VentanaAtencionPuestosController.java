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
import javafx.scene.control.Label;
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

    @FXML
    private TextField txtFieldPaciente;

    public void initialize(URL url, ResourceBundle rb) {
        LinkedList<Turno> turnos = Turno.getTurnos("./turnos.txt");
        Turno t = turnos.get(0);
        this.txtFieldPaciente.setText("Atendiendo al paciente " + t.getPaciente().getNombre() + " " + t.getPaciente().getApellido());
    }

    @FXML
    private void aceptarReceta(Event event) {

        try {
            LinkedList<Turno> turnos = Turno.getTurnos("./turnos.txt");
            Turno t = turnos.get(0);

            File f = new File("turnos.txt");
            File f1 = new File("turnos1.txt");
            ModificarArchivos.modify(t.getPaciente().toString() + "|" + t.getPuesto().cambiotoString(), "turnos.txt", "turnos1.txt");

            ModificarArchivos.borrarArchivo("turnos.txt");
            ModificarArchivos.renombrarArchivo(f1, f);

            PriorityQueue<Paciente> pacientes = Paciente.getPrioridadAtencion(Paciente.leerPacientes("./pacientes.txt"));
            Paciente p = pacientes.poll();

            File fp = new File("pacientes.txt");
            File fp1 = new File("pacientes1.txt");
            ModificarArchivos.modify(p.toString(), "pacientes.txt", "pacientes1.txt");
            ModificarArchivos.borrarArchivo("pacientes.txt");
            ModificarArchivos.renombrarArchivo(fp1, fp);

            Puesto puesto = t.getPuesto();
            File file = new File("puestos.txt");

            puesto.setEstado("Ocupado");
            File fpu1 = new File("puestos1.txt");
            ModificarArchivos.modify(puesto.cambiotoString(), "puestos.txt", "puestos1.txt");
            ModificarArchivos.borrarArchivo("puestos.txt");
            ModificarArchivos.renombrarArchivo(fpu1, file);

            if (Paciente.leerPacientes("Pacientes.txt").size() > Medico.getMedicos("Medicos.txt").size()) {
                
                PriorityQueue<Paciente> pas = Paciente.getPrioridadAtencion(Paciente.leerPacientes("pacientes.txt"));
                puesto.setEstado("Ocupado");
                Turno turno = new Turno(pas.poll(), puesto);
                VentanaPuestosController.guardarTurno(turno);
                
            } else {
                puesto.setEstado("Libre");
                BufferedWriter bw;
                bw = new BufferedWriter(new FileWriter(file, true));
                PrintWriter escribir = new PrintWriter(bw);
                escribir.println(puesto.cambiotoString());
                escribir.flush();
                escribir.close();
                bw.close();
            }
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

}
