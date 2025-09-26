/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej9;

/**
 *
 * @author migue
 */
public class main {
    public static void main(String[] args) {
        Paciente paciente = new Paciente("Jose Martinez", "OSDE");

        // Crear profesional
        Profesional profesional = new Profesional("Dra. Ana Ruiz", "Cardiologia");

        // Crear cita medica con paciente y profesional
        CitaMedica cita = new CitaMedica("2025-10-05", "14:00", paciente, profesional);

        // Mostrar informacion de la cita
        System.out.println("Fecha de la cita: " + cita.getFecha());
        System.out.println("Hora de la cita: " + cita.getHora());
        System.out.println("Paciente: " + cita.getPaciente().getNombre());
        System.out.println("Obra social: " + cita.getPaciente().getObraSocial());
        System.out.println("Profesional: " + cita.getProfesional().getNombre());
        System.out.println("Especialidad: " + cita.getProfesional().getEspecialidad());
    }
}
