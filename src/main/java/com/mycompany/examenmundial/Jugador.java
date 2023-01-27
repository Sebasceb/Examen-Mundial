/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examenmundial;

/**
 *
 * @author Gladys
 */
public class Jugador {
    private String nombre;
    private String posicion;
    private double salario;
    private String nombreImagen;

    public Jugador(String nombre, String posicion, double salario, String nombreImagen) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.salario = salario;
        this.nombreImagen =nombreImagen;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public double getSalario() {
        return salario;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }
    
    
    
}
