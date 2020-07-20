/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author WILLIAM
 */
public class ModificarArchivos {
    
    
    public static void modify(String lineToRemove, String archivo, String archivoAux) {
        // doc1|jua|31|M|cardiologo1
        File inputFile = new File(archivo);
        File outputFile = new File(archivoAux);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.trim().equals(lineToRemove)) {
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            
            writer.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public static void borrarArchivo(String archivo){
        File file = new File(archivo);
        if (file.exists()){
            if(file.delete()){
                System.out.println(archivo + " eliminado");
            }
        }
    }
    
    public static void renombrarArchivo(File file, File f){
        file.renameTo(f);
    }
}
