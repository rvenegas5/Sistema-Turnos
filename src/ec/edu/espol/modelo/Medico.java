/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Anii BC
 */
public class Medico extends Persona {
    private String especialidad;
    
    

    public Medico(String nombre, String apellido, int edad, char genero, String especialidad) {
        super(nombre, apellido, edad, genero);
        this.especialidad=especialidad;
    }
  

   

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
     @Override
    public String toString() {
        return getNombre() + "|" + getApellido() + "|" + getEdad() + "|"
                + getGenero() + "|" + getEspecialidad();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    
    
    
    public static LinkedList<Medico> getMedicos(String archivo){
        LinkedList<Medico> listaDoctores = new LinkedList<>();
        
        try {
            File file = new File (archivo);
            FileReader fr = new FileReader (file);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            
            while((linea = br.readLine()) != null){
                String[] l = linea.split("\\|");
                
                String nombre = l[0];
                String apellido = l[1];
                int edad = Integer.parseInt(l[2]);
                char genero = l[3].charAt(0) ;
                String especialidad = l[4];
                
                Medico doctor = new Medico(nombre, apellido, edad, genero, especialidad);
                listaDoctores.add(doctor);
            }
        } catch (IOException ex) {
            Logger.getLogger(Medico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaDoctores;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Medico other = (Medico) obj;
        if (!Objects.equals(this.especialidad, other.especialidad)) {
            return false;
        }
        return true;
    }
    
    public String cambiotoString() {
        return "Nombre:" +getNombre() + ", " + "Apellido: "+ getApellido() + ", " +"Edad: "+ getEdad() + ", "
                +"Genero: "+ getGenero() + ", " + "Especialidad: "+ getEspecialidad();
    }
    
    
    
    
}
