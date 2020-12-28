package poo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author : Sara Tuma
 * 
 */
public class Desenho extends JPanel implements Runnable{

    Input entrada = new Input(); //Para entrada de dados
    Thread t = new Thread(this);
    Bola bola = new Bola();
    Defesa defesa = new Defesa();

    
    public Desenho(){
        
        t.start();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500,500);
        f.add(this);
        f.addKeyListener(entrada);
        f.setVisible(true);
    }
    
    public void dormir(){
        try {
            t.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(Desenho.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.fillOval(bola.getPosx(), bola.getPosy(), bola.getLargura(), bola.getAltura());
        g2d.setColor(Color.black);
        g2d.fillRoundRect(defesa.getPosx(), defesa.getPosy(), defesa.getLargura(), defesa.getAltura(),10,10);
        
    }
    
    private void actualizar(){
                
        //Condições que fazem a bola girar por toda tela
        if(bola.getPosx()>=this.getWidth()-bola.getLargura() || bola.getPosx()<=0)
                bola.setDx(bola.getDx()*-1);
        if(bola.getPosy()>=this.getHeight()-bola.getAltura()|| bola.getPosy()<=0)
                bola.setDy(bola.getDy()*-1);
        
        //Condições de colisão entre a bola e a defesa
        if(bola.getPosx()<=defesa.getPosx()+defesa.getLargura()){
            if(defesa.getPosy()<= bola.getPosy() && defesa.getPosy()+defesa.getAltura() >= bola.getPosy() )
                bola.setDx(bola.getDx()*-1);
        }
        if(bola.getPosy()+bola.getAltura()>=defesa.getPosy()){
            if(defesa.getPosx()<= bola.getPosx() && defesa.getPosx()+defesa.getLargura()>= bola.getPosx() )
                bola.setDy(bola.getDy()*-1);
        }
        if(bola.getPosy()>=defesa.getPosy()+defesa.getAltura()){
            if(defesa.getPosx()<= bola.getPosx() && defesa.getPosx()+defesa.getLargura()>= bola.getPosx() )
                bola.setDy(bola.getDy()*-1);
        }
                 
        //Verificando as entradas via teclado (cima e baixo)
        if(entrada.isCima() && defesa.getPosy()>=0) defesa.setPosy(defesa.getPosy()-2);
        if(entrada.isBaixo() && defesa.getPosy()<this.getHeight()-defesa.getAltura()) defesa.setPosy(defesa.getPosy()+2);
        //Alterando as posições em x e  y da bola
        bola.setPosx(bola.getPosx()+bola.getDx());
        bola.setPosy(bola.getPosy()+bola.getDy());
    }

    public static int getWIDTH() {
        return WIDTH;
    }
    
    @Override
    public void run() {
        while(true){       
            actualizar();
            repaint();
            dormir();
        }
    }   
}