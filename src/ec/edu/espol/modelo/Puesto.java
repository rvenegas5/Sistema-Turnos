/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.modelo;

import java.util.LinkedList;

/**
 *
 * @author Anii BC
 */
public class Puesto {
    private int numero;
    private Medico medico;
    
    
    
    
    public Puesto(int numero, Medico medico) {
        this.numero = numero;
        this.medico = medico;
              
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
    
 
     
    public String numtoString() {
        if(this.numero>=1 && this.numero<=9)
           return "0"+getNumero();
        else
            return ""+getNumero();
    }
    
    @Override
    public String toString() {
        return numtoString() + "|" + getMedico();
    }
    
}
