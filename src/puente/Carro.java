/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package puente;

/**
 *
 * @author David Mencos
 */
public class Carro {
    private int direccion;
    
    private int id;

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Carro() { }

    public Carro(int direccion, int id) {
        this.direccion = direccion;
        this.id = id;
    }
}
