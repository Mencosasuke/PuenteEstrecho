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
public class Hilo extends Thread {    
    @Override
    public void run() {
        
        Carro carro = new Carro((int)(Math.random() * 2), Main.gui.getCurrentId());
        Main.gui.setCurrentId(Main.gui.getCurrentId() + 1);
        
        try{
            switch(carro.getDireccion()){
                case 0:
                    Main.gui.agregaCarroNorte(carro);
                    Main.gui.actualizaCarrosNorte();
                    break;
                case 1:
                    Main.gui.agregaCarroSur(carro);
                    Main.gui.actualizaCarrosSur();
                    break;
            }
            
            sleep(1333);
            
            // Espera mientras no sea seguro pasar por el puente
            while(!isSafe(carro.getDireccion())){sleep(((int)(Math.random() * 223)) + 333);}
            Main.gui.contadorPuente++;
            
            // Pasa por el puente
            switch(carro.getDireccion()){
                case 0:
                    Main.gui.eliminaCarroNorte(carro);
                    Main.gui.actualizaCarrosNorte();
                    break;
                case 1:
                    Main.gui.eliminaCarroSur(carro);
                    Main.gui.actualizaCarrosSur();
                    break;
            }
            
            Main.gui.agregarCarroPuente(carro);
            Main.gui.actualizaCarrosPuente();
            Main.gui.setDireccionActual(carro.getDireccion());
            System.out.println(String.format(Main.gui.contadorPuente + " Carro %s pasando por el puente...", carro.getId()));
            sleep(5733);
            
            // Sale del puente
            Main.gui.eliminarCarroPuente(carro);
            Main.gui.actualizaCarrosPuente();
            Main.gui.contadorPuente--;
            System.out.println(String.format(Main.gui.contadorPuente + " Carro %s saliendo del puente...", carro.getId()));
            
            if(Main.gui.contadorPuente < 1){
                switch(Main.gui.getDireccionActual()){
                    case 0:
                        if(Main.gui.getCarrosSur().size() != 0){
                            Main.gui.setAccesoNorte(true);
                            Main.gui.setAccesoSur(false);
                            Main.gui.setDireccionActual(1);
                        }
                        break;
                    case 1:
                        if(Main.gui.getCarrosNorte().size() != 0){
                            Main.gui.setAccesoNorte(false);
                            Main.gui.setAccesoSur(true);
                            Main.gui.setDireccionActual(0);
                        }
                        break;
                }
            }
        }catch(InterruptedException e){}
    }
    
    private boolean isSafe(int direccion){
        switch(direccion){
            case 0:
                if((Main.gui.contadorPuente == 0) && Main.gui.getAccesoSur()){
                    return true;
                }else if((Main.gui.contadorPuente < Main.gui.MAX_CARROS) && (Main.gui.getDireccionActual() == direccion) && Main.gui.getAccesoSur()){
                    return true;
                }else{
                    return false;
                }
            case 1:
                if((Main.gui.contadorPuente == 0) && Main.gui.getAccesoNorte()){
                    return true;
                }else if((Main.gui.contadorPuente < Main.gui.MAX_CARROS) && (Main.gui.getDireccionActual() == direccion) && Main.gui.getAccesoNorte()){
                    return true;
                }else{
                    return false;
                }
        }
        return false;
    }
}
