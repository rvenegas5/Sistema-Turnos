/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.modelo;

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
   
    
}
