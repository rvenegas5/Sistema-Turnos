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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WILLIAM
 */
public class Sintoma {
    private String nombre;
    private int prioridad;
    

    public Sintoma(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }


    
    public static LinkedList<Sintoma> leerSintomas(String archivo){
        LinkedList<Sintoma> listaSintomas = new LinkedList<>();
        
        try {
            File file = new File (archivo);
            FileReader fr = new FileReader (file);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            
            while((linea = br.readLine()) != null){
                String[] l = linea.split("\\|");
                
                String nombre = l[0];
                int prioridad = Integer.parseInt(l[1]);
                
                Sintoma sintoma =  new Sintoma(nombre, prioridad);
                listaSintomas.add(sintoma);
            }
        } catch (IOException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaSintomas;
    }

    @Override
    public String toString() {
        return "Sintoma{" + "nombre=" + nombre + ", prioridad=" + prioridad + '}';
    }
    
}
