/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cripito;

import Jama.Matrix;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.sql.Array;
import java.util.ArrayList;

/**
 *
 * @author computador
 */
public class Cripito {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n = 2;
double[][] valores ={{5,6},{2,3}};
Matrix matriz = new Matrix(valores);
Matrix inversa = matriz.inverse();

letra l = new letra();

          System.out.println("palavra :  ");  
 ArrayList<letra> aux =    l.metodo1("a,b,c,d,e,f,g,h,y,j,l,m,n,o,p,q,r,s,t,u,v,x,z");
       
Matrix letras = new Matrix(new double[][] {  {  aux.get(17).beta },
                                             {  aux.get(0).beta },
                                              });
int a = (int)matriz.det();
    System.out.println("determinante != 0 é inversivel");
        System.out.println(a);
        letras.print(5, 0);

matriz.print(5, 0);
System.out.println("inversa da matriz chave");
inversa.print(5,1);

  
    Matrix cripitografada= matriz.times(letras);
    
    System.out.println("matriz cripitografada ");
    cripitografada.print(5, 0);
        
   

    
    
    
    
 Matrix  descripitografada= inversa.times(cripitografada);
    
    descripitografada.print(5, 0);
    
      
        double[][] aux2= descripitografada.getArray();
        
       
          
       int [][] x = new int[n][1];
        for (int i = 0; i < aux2.length; i++) {
                   
                        x[i][0]=(int) aux2[i][0];
                    
        }
                
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < aux.size(); j++) {
                        if (x[i][0]==aux.get(j).beta) {
                            System.out.print(aux.get(j).alfa);
                }
                
            }
            
        }

}
    
   
}

