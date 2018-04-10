/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolgrafico;

import java.io.Serializable;


public class Nodo implements Serializable{
    final static long serialVersionUID = 1;
    
    Object dato;
    Nodo izq, der;
    String getDato;

    public Nodo(Object dato, Nodo izq, Nodo der) {
        this.dato = dato;
        this.izq = izq;
        this.der = der;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public Nodo getIzq() {
        return izq;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public Nodo getDer() {
        return der;
    }

    public void setDer(Nodo der) {
        this.der = der;
    } 

}
