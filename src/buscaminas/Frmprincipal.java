/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javafx.event.ActionEvent;


/**
 *
 * @author artur
 */
public class Frmprincipal extends javax.swing.JFrame {
   private JToggleButton tablero[][];
   private boolean campodeminas[][];
   private boolean finpartida=false;
   int x=0;
   int y=0;
  

   public int probabilidad;
   public int grandex;
   public int grandey;
    /**
     * Creates new form Frmprincipal
     */
   
   public void carga()
   {
       this.setSize(600,600);
        this.setLayout(new GridLayout(grandey,grandex));
        tablero = new JToggleButton[grandey][grandex];
        campodeminas = new boolean[grandey][grandex];
        construyetablero();
        ponminas();
   }
    public Frmprincipal() {
        initComponents();
        
    }
  
    private void muestraminas()
    {
         for(int posx=0;posx<grandex;posx++){
            for(int posy=0;posy<grandey;posy++){
                if (campodeminas[posy][posx])
                {
                    tablero[posy][posx].setText("X");
                }
            }
         }
       
    }
    
    private void ponminas()
    {
        int aleatorio;
        boolean mina;
        
         for(int posx=0;posx<grandex;posx++){
            for(int posy=0;posy<grandey;posy++){
                aleatorio =(int)(Math.random()*(100)); 
                aleatorio++;
                 
                if (aleatorio<= probabilidad)
                {
                    
                    mina = true;
                    campodeminas[posy][posx]=true;
             
                    
                }
            }
        }
        
    }
    
    private void compruebaganado()
    {
        int x,y;
        boolean salx = false;
        boolean saly = false;
        boolean noterminado = false;
        x=0;
        y=0;
        while(!salx)
        {
            if(x<grandex)
            {
                saly=false;
                y=0;
                while(!saly)
                {
                    if(y<grandey)
                    {
                        if (!tablero[y][x].isSelected())
                        {
                             if(tablero[y][x].getText()=="M")
                             {
                                 if(!campodeminas[y][x])
                                 {
                                     salx=true;
                                     saly=true;
                                     noterminado=true;
                                            
                                 }
                             }
                             else
                             {
                                 salx=true;
                                 saly=true;
                                 noterminado=true;
                             }
                        }
                        y++;
                    }
                    else
                    {
                        saly=true;
                    }
                }
                x++;
            }
            else
            {
                salx=true;
            }
        }
        
        if (!noterminado)
        {
            JOptionPane.showMessageDialog(null, "Felicidades has ganado la partida.");
            finpartida=true;
        }
        
      
    }
    
    private void pulsadoboton(int posx, int posy)
    {
       
        if (campodeminas[posy][posx])
        {
            muestraminas();
            JOptionPane.showMessageDialog(null, "Has perdido, has tocado una mina");
            finpartida=true;    
        }
        else
        {
            compruebaminas(posx,posy);
        }
        compruebaganado();
    }
    
    private void compruebaminas(int posx, int posy)
    {
        int cuentaminas=0;
        boolean estaizquierda;
        boolean estaderecha;
        boolean estaarriba;
        boolean estaabajo;
        
        if(posx>0 )
        {
            estaizquierda=false;     
        }
        else
        {
            estaizquierda=true;
        }
       
      
        if(posx<grandex-1)
        {
            estaderecha=false;   
            
        }
        else
        {
            estaderecha=true;
        }
        if(posy>0 )
        {
            estaarriba=false;     
        }
        else
        {
            estaarriba=true;
        }
        if(posy<grandey-1)
        {
            estaabajo=false;     
        }
        else
        {
            estaabajo=true;
        }
        if(!estaizquierda && !estaarriba)
        {
            if(campodeminas[posy-1][posx-1])
            {
                cuentaminas++;
            }
        }
        
        if(!estaarriba)
        {
             if(campodeminas[posy-1][posx])
            {
                cuentaminas++;
            }
        }
        if(!estaderecha && !estaarriba)
        {
             if(campodeminas[posy-1][posx+1])
            {
                cuentaminas++;
            }
        }
        if(!estaderecha)
        {
             if(campodeminas[posy][posx+1])
            {
                cuentaminas++;
            }
        }
        if(!estaderecha & !estaabajo)
        {
             if(campodeminas[posy+1][posx+1])
            {
                cuentaminas++;
            }
        }
        
        if(!estaabajo)
        {
             if(campodeminas[posy+1][posx])
            {
                cuentaminas++;
            }
        }
        if(!estaabajo && !estaizquierda)
        {
             if(campodeminas[posy+1][posx-1])
            {
                cuentaminas++;
            }
        }
        if(!estaizquierda)
        {
             if(campodeminas[posy][posx-1])
            {
                cuentaminas++;
            }
        }
        if (cuentaminas==0)
        {
            
             if(!estaizquierda && !estaarriba)
            {
                if (!campodeminas[posy-1][posx-1])
                {
                    if (!tablero[posy-1][posx-1].isSelected())
                    {
                        tablero[posy-1][posx-1].setSelected(true);
                        compruebaminas(posx-1,posy-1);
                    }
                }         
            }
        
            if(!estaarriba)
            {
                if (!campodeminas[posy-1][posx])
                {
                    if (!tablero[posy-1][posx].isSelected())
                    {
                        tablero[posy-1][posx].setSelected(true);
                        compruebaminas(posx,posy-1);
                    }
                }
            }
            if(!estaderecha && !estaarriba)
            {
                if (!campodeminas[posy-1][posx+1])
                {
                    if (!tablero[posy-1][posx+1].isSelected())
                    {
                        tablero[posy-1][posx+1].setSelected(true);
                        compruebaminas(posx+1,posy-1);
                    }
                }
           }
            if(!estaderecha)
            {
                if (!campodeminas[posy][posx+1])
                {
                   if (!tablero[posy][posx+1].isSelected())
                   {
                        tablero[posy][posx+1].setSelected(true);
                        compruebaminas(posx+1,posy);
                   }
                }
            }
            if(!estaderecha & !estaabajo)
            {
                if (!campodeminas[posy+1][posx+1])
                {
                    if (!tablero[posy+1][posx+1].isSelected())
                    {
                        tablero[posy+1][posx+1].setSelected(true);
                        compruebaminas(posx+1,posy+1); 
                    }
                }
            }
            if(!estaabajo)
            {
                if (!campodeminas[posy+1][posx])
                {
                   if (!tablero[posy+1][posx].isSelected())
                   {
                        tablero[posy+1][posx].setSelected(true);
                        compruebaminas(posx,posy+1);
                   }
                }
            }
            if(!estaabajo && !estaizquierda)
            {
                if (!campodeminas[posy+1][posx-1])
                {
                   if (!tablero[posy+1][posx-1].isSelected())
                   {
                       tablero[posy+1][posx-1].setSelected(true);
                       compruebaminas(posx-1,posy+1);
                   }
                   
                }
            }
            if(!estaizquierda)
            {
                if (!campodeminas[posy][posx-1])
                {
                   if (!tablero[posy][posx-1].isSelected())
                   {
                        tablero[posy][posx-1].setSelected(true);
                        compruebaminas(posx-1,posy);
                   }
                }
            }
        }
        else
        {   
            tablero[posy][posx].setText(String.valueOf(cuentaminas));
        }
    }
 private Coordenada obtieneposicion(String cadena)
 {
     Coordenada posicionleida = new Coordenada();
     String[] coordenadas;
     coordenadas = cadena.split(",");
     posicionleida.setx(Integer.parseInt(coordenadas[0]));
     posicionleida.sety(Integer.parseInt(coordenadas[1]));
     
     return posicionleida;
 }
   
 private void construyetablero(){

     for(y=0;y<grandey;y++){
          for(x=0;x<grandex;x++){
               
               
               
               JToggleButton boton = new JToggleButton();
               boton.setActionCommand(x + "," + y);
               boton.addActionListener(new ActionListener(){
              
               
                
                 
                  
                   @Override
                   public void actionPerformed(java.awt.event.ActionEvent e) {
                       Coordenada posicion;
                       
                       JToggleButton pulsado = (JToggleButton)e.getSource();
                       if (pulsado.isSelected())
                       {
                           if (!finpartida)
                           {
                            posicion= obtieneposicion(e.getActionCommand());
                            pulsadoboton(posicion.getx(),posicion.gety());
                           }
                           else
                           {
                               pulsado.setSelected(false);
                           }
                           
                           
                       }
                       else
                       {
                           pulsado.setSelected(true);
                       }
                       
                    
//                     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                   }
                   
               });
               boton.addMouseListener(new MouseAdapter() {
                    @Override
                   public void mouseClicked(MouseEvent e){
                       if (e.getButton() == 3) {
                          if(!finpartida)
                          {
                                
                            Coordenada posicion;
                       
                            JToggleButton pulsado = (JToggleButton)e.getSource();
                            
                       
                           posicion= obtieneposicion(pulsado.getActionCommand());
                           if (!tablero[posicion.gety()][posicion.getx()].isSelected())
                           {
                               if (tablero[posicion.gety()][posicion.getx()].getText()=="M")
                               {
                                   pulsado.setText("");
                               }
                               else
                               {
                                   pulsado.setText("M");
                               }
                           }
                           
                           compruebaganado();
                          }
                                                       
                       }
                   }
                   
                   
               });
               
               tablero[y][x]=boton;
             // tablero[x][y].setSize(,400);
              this.add(tablero[y][x]);
              campodeminas[y][x]=false;
          }
     }
}  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Buscaminas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frmprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frmprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frmprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frmprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frmprincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
