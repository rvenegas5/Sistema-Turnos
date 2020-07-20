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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anii BC
 */
public class Turno {
    
    private final char[] letras = {'A', 'B', 'C', 'D', 'C'};
    private char letra;
    private int numero;
    private String turno;
    private Paciente paciente;
    private Puesto puesto;
    

    public Turno(Paciente paciente, Puesto puesto) {
        this.paciente = paciente;
        this.puesto = puesto;
        setNumero(numero);
        setLetra(letra);
        this.turno = getLetra() + getNumero() + "";
        
    }

    public char[] getLetras() {
        return letras;
    }

    public char getLetra() {
        return letra;
    }

    public int getNumero() {
        return numero;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public String getTurno() {
        return turno;
    }
    
    private void setLetra(char letra){
        Random aleatorio = new Random(System.currentTimeMillis());
        int intAletorio = aleatorio.nextInt(6);
        this.letra = getLetras()[intAletorio];
    }
    @Override
    public String toString() {
        return getLetra() + "|" + getNumero() + "|" + getPaciente().toString() + "|" + getPuesto().toString();
    }
    
    private void setNumero (int numero) {
        PriorityQueue<Paciente> cola = Paciente.getPrioridadAtencion(Paciente.leerPacientes("pacientes.txt"));
        int count = 0;
        while (!cola.isEmpty()) {            
            Paciente p = cola.poll();
            count++;
            if (this.paciente.equals(p)) {
                numero = count;
                break;
            }
        }
        
        
    }
    
    public static LinkedList<Turno> getTurnos(String archivo) {
        LinkedList<Turno> turnos = new LinkedList<>();
        try {
            File file = new File(archivo);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] l = linea.split("\\|");

                String nombreP = l[0];
                String apellidoP = l[1];
                int edadP = Integer.parseInt(l[2]);
                char generoP = l[3].charAt(0);
                String nombreSint = l[4];
                int prioridadSint = Integer.parseInt(l[5]);
                Sintoma sint = new Sintoma(nombreSint, prioridadSint);
                
                String stado = l[6];
                int numPues = Integer.parseInt(l[7]);
                String nombreM = l[8];
                String apellidoM = l[9];
                int edadM = Integer.parseInt(l[10]);
                char generoM = l[11].charAt(0);
                String especialidad = l[12];
                
                Medico doctor = new Medico(nombreM, apellidoM, edadM, generoM, especialidad);
                Paciente paciente = new Paciente(nombreP, apellidoP, edadP, generoP, sint);
                Puesto puesto = new Puesto(numPues, doctor);
                Turno t = new Turno(paciente, puesto);
                turnos.add(t);
            }
        } catch (IOException ex) {
            Logger.getLogger(Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return turnos;
    }
    
    
    public String cambiotoString() {
         if(numero>1 && numero<10)
         return this.letra+"0"+this.numero;
         else
         return this.letra+""+this.numero;
    }
    
    
    
    
    
    
}
