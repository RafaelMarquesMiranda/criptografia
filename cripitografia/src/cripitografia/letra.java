/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cripito;

import java.util.ArrayList;

/**
 *
 * @author computador
 */
public class letra {
    
    public String alfa;
    public int beta;
    
    
     public ArrayList metodo1(String a){
    
    String[] resultado=a.split(",");
        
 
         ArrayList alfabeto = new ArrayList();
 
        for (int i = 0; i < resultado.length; i++) {
            letra l = new letra();
            l.alfa= resultado[i];
            l.beta= i;
            alfabeto.add(l);
            
            
        }
    return alfabeto;
    }
}
