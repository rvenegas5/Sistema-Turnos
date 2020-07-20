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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anii BC
 */
public class Puesto {

    private int numero;
    private Medico medico;
    private String estado;

    public Puesto(int numero, Medico medico) {
        this.numero = numero;
        this.medico = medico;
        this.estado = "Libre";

    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estadoOcupado) {
        this.estado = estadoOcupado;
    }


    @Override
    public String toString() {
            return  getNumero() + "";
    }
    
    public static LinkedList<Puesto> getPuestos(String archivo) {
    
        LinkedList<Puesto> listaPuestos = new LinkedList<>();
        
        try {
            File file = new File (archivo);
            FileReader fr = new FileReader (file);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            
            while((linea = br.readLine()) != null){
                String[] l = linea.split("\\|");
                
                String estado = l[0];
                int numPues = Integer.parseInt(l[1]);
                String nombre = l[2];
                String apellido = l[3];
                int edad = Integer.parseInt(l[4]);
                char genero = l[5].charAt(0) ;
                String especialidad = l[6];
                
                
                Medico doctor = new Medico(nombre, apellido, edad, genero, especialidad);
                Puesto puesto = new Puesto(numPues, doctor);
                puesto.setEstado(estado);
                if (!listaPuestos.contains(puesto))
                    listaPuestos.add(puesto);
            }
        } catch (IOException ex) {
            Logger.getLogger(Medico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPuestos;
    }
    
    
     public String cambiotoString() {
         return  getEstado() + "|" + getNumero() + "|" + getMedico().cambiotoString();
    }
    
    
    
    
    
}
