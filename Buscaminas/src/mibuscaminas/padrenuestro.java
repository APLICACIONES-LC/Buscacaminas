

package mibuscaminas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class padrenuestro extends JPanel implements ActionListener {
  private int casillasFaltantes = 0, minas3 = 150;
	int valorespadre[][] = new int[minas3][minas3];
	public JLabel labels;	
	private JButton botonespadrenuestro[][] = new JButton[minas3][minas3];
	public JButton botons;
	
	public String[] archi = {"/imagenes/gano.png", "/imagenes/perdio.png", "/imagenes/nueva.png"};
	private String archivos[] = {"/imagenes/0.PNG", "/imagenes/1.PNG", 
            "/imagenes/2.PNG", "/imagenes/3.PNG", "/imagenes/4.PNG", "/imagenes/5.PNG", 
            "/imagenes/6.PNG", "/imagenes/7.PNG", "/imagenes/8.PNG", "/imagenes/9.PNG"};
	
	private ImageIcon[] imagenes = new ImageIcon[10];    
	public ImageIcon[] ima = new ImageIcon[3];   
    
    private boolean visiblepadre[][] = new boolean[minas3][minas3];
	

	public padrenuestro() {
		this.setLayout(null);
		for(int i=0;i<3;i++){
            ima[i] = new ImageIcon(getClass().getResource(archi[i]));
        }
		nuevaPartidapadre();        
		this.setSize(600, 640);
		botons = new JButton();
		botons.setBounds(286, 5, 30, 30);
        botons.setIcon(ima[2]);                
        this.add(botons);
        this.botons.addActionListener(this);
		labels = new JLabel();
		labels.setBounds(15, 15, 60, 15);
		this.add(labels);
	}
	
	public void nuevaPartidapadre(){
        casillasFaltantes = 0;        
        ponerBotonespadre();
        verpadre(false);
        ponerMinaspadre();
        contornopadre();
        visualizarMinaspadre();
        eventospadre();
    }
	
	public void ponerBotonespadre(){               
        
        for(int i=0;i<10;i++){
            imagenes[i] = new ImageIcon(getClass().getResource(archivos[i]));
        }
        
        for(int f = 0; f<minas3; f++){
        for(int c = 0; c<minas3; c++){
            botonespadrenuestro[f][c] = new JButton();
              botonespadrenuestro[f][c].setBounds(20*f, 20*c+40,20, 20);
             botonespadrenuestro[f][c].setBackground(Color.gray);            
            this.add(    botonespadrenuestro[f][c]);
        }
        }        
    }
	
	public void ponerMinaspadre(){
        for(int f=0;f<minas3;f++){
        for(int c=0;c<minas3;c++){
            valorespadre[f][c]=0;
        }
        }
           int f1, c1;
        for ( int i=0;i<3*minas3;i++){
            do{
                f1=(int)(Math.random()*minas3);
                c1=(int)(Math.random()*minas3);
            }while(valorespadre[f1][c1]!=0);
            valorespadre[f1][c1]=9;
        }
    }
	
	public void contornopadre(){
        for(int f=0;f<minas3;f++){
            for(int c=0;c<minas3;c++){
                if(valorespadre[f][c]==9){
                    for(int f2=f-1;f2<=f+1;f2++){
                        for(int c2=c-1;c2<=c+1;c2++){
                            if(f2>=0 && f2<minas3 && c2>=0 && c2<minas3 && valorespadre[f2][c2]!=9)
                                valorespadre[f2][c2]++;
                        }
                      }
                   }
                }
            }
        }
	
	public void verpadre(boolean valor){
        for(int f=0;f<minas3;f++){
        for(int c=0;c<minas3;c++){
            visiblepadre[f][c]=valor;
        }
        }
    }
	
	public void pulsarBotonVaspadre(int f, int c){
        if(f>=0 && f<minas3 && c>=0 && c<minas3 && visiblepadre[f][c]==false){
            if(visiblepadre[f][c]==false){
                visiblepadre[f][c]=true;
                if(valorespadre[f][c]==9){
                    verpadre(true);
                    JOptionPane.showMessageDialog(null, "              PERDISTE");
                    botons.setIcon(ima[1]);}
            else if(visiblepadre[f][c]==true){
                casillasFaltantes++;
                if (casillasFaltantes == 1500){
                    verpadre(true);
                    JOptionPane.showMessageDialog(null, "              GANASTE");
                    botons.setIcon(ima[0]);
                    labels.setText("");
                }
                labels.setText(casillasFaltantes+"/1500");
            }
            }
            if(valorespadre[f][c]==0){
                pulsarBotonVaspadre(f, c-1);
                pulsarBotonVaspadre(f, c+1);
                pulsarBotonVaspadre(f-1, c);
                pulsarBotonVaspadre(f+1, c);
                
            }
        }
    }
	
	public void pulsarBotonpadre(int f, int c) {
        pulsarBotonVaspadre(f,c);
        visualizarMinaspadre();
    }
	
	public void eventospadre(){
        for(int f=0;f<minas3;f++){
        for(int c=0;c<minas3;c++){
            botonespadrenuestro[f][c].addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    for(int f=0;f<minas3;f++){
                    for(int c=0;c<minas3;c++){
                        if(e.getSource()==botonespadrenuestro[f][c])
                            pulsarBotonpadre(f,c);
                    }
                    }
                }
                }
            );
        }
        }
    }
	
	public void visualizarMinaspadre(){
        for(int f=0;f<minas3;f++){
        for(int c=0;c<minas3;c++){
         if(visiblepadre[f][c]==false){
           botonespadrenuestro[f][c].setText("");
         }else if(visiblepadre[f][c]==true){
           if(valorespadre[f][c]==0){
           botonespadrenuestro[f][c].setIcon(imagenes[0]);
           }else if(valorespadre[f][c]==1){
         botonespadrenuestro[f][c].setIcon(imagenes[1]);
           }else if(valorespadre[f][c]==2){
       botonespadrenuestro[f][c].setIcon(imagenes[2]);
           }else if(valorespadre[f][c]==3){
         botonespadrenuestro[f][c].setIcon(imagenes[3]);
           }else if(valorespadre[f][c]==4){
       botonespadrenuestro[f][c].setIcon(imagenes[4]);
           }else if(valorespadre[f][c]==5){
           botonespadrenuestro[f][c].setIcon(imagenes[5]);
           }else if(valorespadre[f][c]==6){
         botonespadrenuestro[f][c].setIcon(imagenes[6]);
           }else if(valorespadre[f][c]==7){
    botonespadrenuestro[f][c].setIcon(imagenes[7]);
           }else if(valorespadre[f][c]==8){
         botonespadrenuestro[f][c].setIcon(imagenes[8]);
           }else if(valorespadre[f][c]==9)
          botonespadrenuestro[f][c].setIcon(imagenes[9]);
          }
        }
       }
   }
	
	public void quitarBotonespadre(){
        for(int f1 = 0; f1<minas3; f1++){
               for(int c1 = 0; c1<minas3; c1++){
                   this.remove(botonespadrenuestro[f1][c1]);
               }
        }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==botons){
			botons.setIcon(ima[2]);
            quitarBotonespadre();
            this.setVisible(false);            
            labels.setText("");
			nuevaPartidapadre();
			this.setVisible(true);
		}		
	}  
}
