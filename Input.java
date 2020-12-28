package poo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Sara Tuma
 */
//Ouvir do teclado (keylistener)

public class Input implements KeyListener{ //Apenas vai se encarregar da entrada de dados (teclado,mouse,outros...)

    private boolean cima=false;
    private boolean baixo=false;    
    @Override
    public void keyTyped(KeyEvent ke) //Como um get - retorna o codigo da tecla pressionada
    {}
    @Override
    public void keyPressed(KeyEvent ke) // Diz quando foi pressionado uma tecla (metodo de escuta)
    {
        if(ke.getKeyCode()==KeyEvent.VK_UP){
            this.cima=true;  
        }
        if(ke.getKeyCode()==KeyEvent.VK_DOWN){
            this.baixo=true;
            
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) // O que acontecece enquanto uma tecla é pressionada
    {
        if(ke.getKeyCode()==KeyEvent.VK_UP){
            this.cima=false;
            
        }
        if(ke.getKeyCode()==KeyEvent.VK_DOWN){
            this.baixo=false;
            
        }
    }

    public boolean isCima() {
        return cima;
    }

    public boolean isBaixo() {
        return baixo;
    } 
}
