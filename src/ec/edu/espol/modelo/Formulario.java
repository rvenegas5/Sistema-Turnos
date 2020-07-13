/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *
 * @author Anii BC
 */
public class Formulario {
 LinkedList<Paciente> pac; //cola
    
    
    
    
    public void crearPaciente(){
        String nombre; 
        String apellido; 
        int edad; 
        String genero; 
        String sintoma ;
         Sintoma sint;
         int prioridad;
        System.out.println("Nombre");
        
        Scanner input = new Scanner(System.in);
        
        nombre = input.next();
        apellido = input.next();
        edad = input.nextInt();
        genero = input.next();
        
        sintoma= input.next();
        //validacion sino pues se sobreescribe archivo y se ingresa prioridad mas baja
       // Paciente p= new Paciente(nombre, apellido,edad,genero, new Sintoma(sintoma,value));
       
       // this.pac.add(p);
        
        
        
        
    }
    
    
    
    
    public void derivarPuesto(LinkedList<Paciente> pacientes){ //paciente y turno
        
    
    
    }
    
    
    public static LinkedList<Paciente> generarTurno (LinkedList<Paciente> pacientes){
        PriorityQueue<Paciente> cola = new PriorityQueue<>(
          (Paciente p1, Paciente p2)-> p2.getEdad() - p1.getEdad());
        cola.addAll(pacientes);
        LinkedList<Paciente> atendidos = new LinkedList<>();
      /*  while (!cola.isEmpty()){
            Paciente p = cola.poll();
            if (p.getTiempo() > 15){
                p.setTiempo(p.getTiempo() - 15);
                cola.offer(p);
            }
            else{
                p.setTiempo(0);
            }
            atendidos.add(p.nuevo());}
       */ 
        return atendidos;
    }
    
    public void almacenarDatosPacientes(Paciente p,boolean añadir) 
    {
        // se instancia la ruta especifica y el nombre del fichero
        File archivo=new File("Paciente.cvs");
       
        
        //comprueba si es que ya existe y no se lo necesita 
        //por tanto se lo borrara para dar lugar a un nuevo archivo
        if(archivo.exists()==true && añadir==false)
            archivo.delete();
        
        FileWriter flujosalida=null;
        BufferedWriter salida=null;
        try
        {
            //gracias al bool añade este nos dira si quiere sobre-escribir el archivo o si lo quiero crear
            //por primera vez
            flujosalida=new FileWriter(archivo,añadir);
            salida=new BufferedWriter(flujosalida);
            
            //aqui es donde se asiganaran los nombres de las cabeceras dentro de nuestro archivo
            if (añadir==false)
            {
                salida.write("Nombre;Apellido;Edad;Genero;Sintoma");
                salida.newLine();
            }
            //aqui es donde se escribe directamente el archivo
            salida.write(p.getNombre()+";"+ p.getApellido()+";"+p.getEdad()+";"+ p.getGenero()+";"+p.getSintoma());
            //realiza un salto de linea
            salida.newLine();
            System.out.println("Registro añadido correctamente");
            //si existe alguna excepcion esta seccion nos la indicara
        }catch(IOException ex)
        {
            System.out.print("Error al abrir el archivo");
        }
        finally
        {
            //finalmente se cierra 
            try
            {
                salida.close();
                flujosalida.close();
            }catch(IOException ex){
                System.out.print("Error al cerrar el archivo");
                ex.printStackTrace();
            }
        }
    }

    
    
    
    
}
