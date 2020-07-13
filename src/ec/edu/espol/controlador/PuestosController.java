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
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Anii BC
 */
public class PuestosController implements Initializable {

    @FXML
    private ComboBox<Medico> medicoBox;
    private ObservableList<Medico> options = FXCollections.observableArrayList();
    private static ArrayList<Medico> medList= new ArrayList<>();
    
    private static String MedFile="medicos.txt";
    
    @FXML
    private Button crearPuesto;
    @FXML
    private Button eliminarPuesto;
     @FXML
    private Button salir;
   
    private boolean flag=false;
    
    LinkedList<Puesto> puestos;
    
    
 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        //loadComboBox();
        medicoBox.getItems().addAll(options);
        puestos= new LinkedList<>();
        
        
    }   
    
   
    

    
     
   public static void readMedicosFile(String ruta) throws IOException{//lee el archivo de medicos y los almacena en un array
            
        String nombre, apellido, especialidad,edad, genero;
        
        
        try (BufferedReader inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(ruta)))) {
           
            String line;
            
            while ((line = inputStream.readLine()) != null) {
                String[] array = line.strip().split("|"); 
                nombre=array[0];
                apellido=array[1];
                edad=array[2];
                genero=array[3];
                especialidad=array[4];
                int edadM = Integer.parseInt(edad);
            char generoM= genero.charAt(0);
                Medico m = new Medico(nombre,apellido,edadM,generoM,especialidad);
                medList.add(m);
                
            }
            System.out.println("Lista de medicos cargada con exito");
         
            
            
        }catch (FileNotFoundException e) {
            System.out.println("File " + ruta + " not found.");
           
    }
   }
     
      private void loadComboBox(){
        medicoBox.getItems().clear(); //borrar items
        options = FXCollections.observableArrayList();
        for(Medico m: medList){
            options.add(m);
        }
        medicoBox.getItems().addAll(options);//añadir items
    }
    
    
    
    
    private void asignarPuesto(ActionEvent event) {
        
        if (flag == false){
            
            Puesto pInicio= new Puesto(1,medicoBox.getValue() );
            puestos.add(pInicio);
            flag= true;    
            medicoBox.getItems().remove(medicoBox.getValue());
            //quitar medico del combobox
        }else{
            
            int n= medList.size()-1; 
            int num = (int) (Math.random() * n) + 1; //numero aleatorio entre el rango del size de medList 
             for(Puesto p : puestos){
             if(num == p.getNumero()){
                 //validar para que repita 
             }else{
                 Puesto pAleatorio= new Puesto(num,medicoBox.getValue() );
                 
                 //quitar medico de combobox
                 medicoBox.getItems().remove(medicoBox.getValue());
             }
            
             }//fin for puestos
            
        }
        
       
    }
    
    private void eliminarPuesto(ActionEvent event){
        
    
    
    
    
    }
    
  /*  
    private boolean validarPuesto(Puesto pto){ //si esta en un puesto ya o no 
        boolean flagM= false;
    for(Puesto p :puestos){
        if(p.getNumero()== pto.getNumero())){
            flagM=true;
         
        }       
    }
    
    return flagM;
    
    }
    */
    
    
    //guardar archivo puestos
    
    
    
     @FXML
    private void salirPag(ActionEvent event) {
        Stage stage = (Stage) this.salir.getScene().getWindow();
        stage.close();
    }

    
    
    
}
    
    
    

