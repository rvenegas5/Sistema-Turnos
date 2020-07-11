/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.modelo;

/**
 *
 * @author WILLIAM
 */
public class Paciente extends Persona{
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
                + getGenero() + "|" + getSintoma().getNombre() + "|" + 
                getSintoma().getPrioridad();
    }
    
}
