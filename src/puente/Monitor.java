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
public class Monitor extends Thread {
    @Override
    public void run() {
        while(true){
            // Si se alcanza el maximo de carros en el puente, espera a que el
            // numero de carros llegue a 0 e intercambia el turno de los carros
            // siempre y cuando, existan carros esperando en la vía contraria.
            if(Main.gui.getCarrosPuente().size() == Main.gui.MAX_CARROS){
                
                // Bloquea los accesos de ambos lados
                Main.gui.setAccesoNorte(false);
                Main.gui.setAccesoSur(false);
                
                // Espera hasta que ya no hayan carros en el puente
                while(Main.gui.getCarrosPuente().size() != 0){}
                
                // Evalua los permisos según la dirección que se tenga actualmente
                switch(Main.gui.getDireccionActual()){
                    case 0:
                        if(Main.gui.getCarrosSur().size() > 0){
                            Main.gui.setAccesoNorte(true);
                            Main.gui.setDireccionActual(1);
                        }else{
                            Main.gui.setAccesoSur(true);
                        }
                        break;
                    case 1:
                        if(Main.gui.getCarrosNorte().size() > 0){
                            Main.gui.setAccesoSur(true);
                            Main.gui.setDireccionActual(0);
                        }else{
                            Main.gui.setAccesoNorte(true);
                        }
                        break;
                }
            }else{
                // Si no hay carros en el sur pero sí hay en el norte, espera a que el puente
                // se desocupe y deja pasar a los restantes
                // Si no, si no hay carros en el norte pero sí en el sur, deja pasar a los
                // restantes cuando el puente se desocupe
                if(Main.gui.getCarrosSur().size() == 0 && Main.gui.getCarrosNorte().size() > 0){
                    // Espera hasta que ya no hayan carros en el puente
                    while(Main.gui.getCarrosPuente().size() != 0){}
                    Main.gui.setAccesoNorte(false);
                    Main.gui.setAccesoSur(true);
                    Main.gui.setDireccionActual(0);
                }else if(Main.gui.getCarrosNorte().size() == 0 && Main.gui.getCarrosSur().size() > 0){
                    // Espera hasta que ya no hayan carros en el puente
                    while(Main.gui.getCarrosPuente().size() != 0){}
                    Main.gui.setAccesoNorte(true);
                    Main.gui.setAccesoSur(false);
                    Main.gui.setDireccionActual(1);
                }
            }
            try{
                sleep(1000);
            }catch(InterruptedException e){}
        }
    }
}
