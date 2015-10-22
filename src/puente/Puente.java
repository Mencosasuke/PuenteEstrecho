/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package puente;

import static java.lang.Thread.sleep;

/**
 *
 * @author David Mencos
 */
public class Puente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Main.gui.setVisible(true);
        Main.gui.setTitle("Puente Estrecho UMG");
        
        Hilo[] carros = new Hilo[10];
        
        for(int i = 0; i < 10; i++){
            carros[i] = new Hilo();
        }
        
        Monitor monitor = new Monitor();
        //monitor.start();
        
        for(int i = 0; i < 10; i++){
            try{
                carros[i].start();
                sleep(1000);
            }catch(InterruptedException e){}
        }
        
//        while(true){
//            try{
//                //Main.gui.cambiarNumeroCarrosNorte();
//                //Main.gui.cambiarNumeroCarrosSur();
//                //Main.gui.cambiarNumeroCarrosPuente();
//                sleep(1000);
//            }catch(InterruptedException e){}
//        }
        
    }
    
}
