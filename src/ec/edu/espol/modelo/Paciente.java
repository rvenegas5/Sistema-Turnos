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
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WILLIAM
 */
public class Paciente extends Persona {

    private Sintoma sintoma;

    public Paciente(String nombre, String apellido, int edad, char genero, Sintoma sintoma) {
        super(nombre, apellido, edad, genero);
        this.sintoma = sintoma;
    }

    public Sintoma getSintoma() {
        return sintoma;
    }

    public void setSintoma(Sintoma sintoma) {
        this.sintoma = sintoma;
    }

    @Override
    public String toString() {
        return getNombre() + "|" + getApellido() + "|" + getEdad() + "|"
                + getGenero() + "|" + getSintoma().getNombre() + "|"
                + getSintoma().getPrioridad();
    }

    public static LinkedList<Paciente> leerPacientes(String archivo) {
        LinkedList<Paciente> listaPacientes = new LinkedList<>();

        try {
            File file = new File(archivo);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] l = linea.split("\\|");

                String nombre = l[0];
                String apellido = l[1];
                int edad = Integer.parseInt(l[2]);
                char genero = l[3].charAt(0);
                String nombreSint = l[4];
                int prioridadSint = Integer.parseInt(l[5]);
                Sintoma sint = new Sintoma(nombreSint, prioridadSint);

                Paciente paciente = new Paciente(nombre, apellido, edad, genero, sint);
                listaPacientes.add(paciente);
            }
        } catch (IOException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPacientes;
    }

    public static PriorityQueue<Paciente> getPrioridadAtencion(LinkedList<Paciente> pacientes) {
        PriorityQueue<Paciente> prioridadAtencion = new PriorityQueue<>(
        (Paciente p1, Paciente p2)
        -> p1.getSintoma().getPrioridad() - p2.getSintoma().getPrioridad());
        
        prioridadAtencion.addAll(pacientes);
        return prioridadAtencion;
    }
}
