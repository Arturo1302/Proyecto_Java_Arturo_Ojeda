/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_tecno_store;

/**
 *
 * @author artur
 */



import javax.swing.JOptionPane;

public class Prueba {
    public static void main(String[] args) {

       System.out.println("Antes de la ventana...");
        int op = JOptionPane.showConfirmDialog(null, "¿Ves esta ventana?", "Prueba", JOptionPane.YES_NO_OPTION);
        System.out.println("Después de la ventana, elegiste: " + op);
    }
}
