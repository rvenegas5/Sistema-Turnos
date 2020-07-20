/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.modelo;

import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Anii BC
 */
public class Receta {
    private Medico medico;
    private Paciente paciente;
    private LinkedList<String> medicamentos;
    private Date fecha;
    private String diagnostico; 

    public Receta(Medico medico, Paciente paciente, LinkedList<String> medicamentos, Date fecha, String diagnostico) {
        this.medico = medico;
        this.paciente = paciente;
        this.medicamentos = medicamentos;
        this.fecha = fecha;
        this.diagnostico = diagnostico;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LinkedList<String> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(LinkedList<String> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
    
}
