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
    private Paciente paciente;
    private Puesto puesto;
    

    public Turno(int numero, Paciente paciente, Puesto puesto) {
        this.numero = numero;
        this.paciente = paciente;
        this.puesto = puesto;
        setLetra(letra);
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

    public void setNumero(int numero) {
        this.numero = numero;
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
    
    private void setLetra(char letra){
        Random aleatorio = new Random(System.currentTimeMillis());
        int intAletorio = aleatorio.nextInt(6);
        this.letra = getLetras()[intAletorio];
    }
    @Override
    public String toString() {
        return getLetra() + "|" + getNumero() + "|" + getPaciente().toString() + "|" + getPuesto().toString();
    }
    
}
