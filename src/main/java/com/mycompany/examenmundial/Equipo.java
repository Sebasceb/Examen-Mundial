/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examenmundial;

import java.util.ArrayList;

/**
 *
 * @author Gladys
 */
public class Equipo {

    private String pais;
    private ArrayList<Jugador> listaJugadores;

    public Equipo(String pais, ArrayList<Jugador> listaJugadores) {
        this.pais = pais;
        this.listaJugadores = listaJugadores;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(ArrayList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    @Override
    public String toString() {
        return pais;
    }

    public static ArrayList<Equipo> obtenerEquipos() {
        ArrayList<Equipo> equipos = new ArrayList<>();
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Jugador("Moises Ramirez", "Arquero", 56000, "moises.png"));
        jugadores.add(new Jugador("Pero Hincapié", "Delantero", 70000, "piero.png"));
        equipos.add(new Equipo("Ecuador", jugadores));
        jugadores = new ArrayList<>();
        jugadores.add(new Jugador("Lionel Messi", "Delantero", 800000, "messi.png"));
        equipos.add(new Equipo("Argentina", jugadores));
        return equipos;
    }

    public double calcularTotalSueldos () {
        double total = 0;
        for (Jugador j : listaJugadores) {
            total += j.getSalario();
        }
        return total;
    }
    
    
}
