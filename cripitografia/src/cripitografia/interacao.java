/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cripito;

import Jama.Matrix;
import java.sql.Array;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sun.org.mozilla.javascript.internal.Token;

/**
 *
 * @author rafael marques miranda
 */
public class interacao {
//metodos estaticos detalhes vide documentação

    public static void escrever(String texto) {
        JOptionPane.showMessageDialog(null, texto);
    }

    public static String ler(String texto) {
        return JOptionPane.showInputDialog(null, texto);
    }

    public static ArrayList bijecao(String texto) {
        letra l = new letra();
        return l.metodo1(texto);
    }

    public static Matrix criarMatriz(int tamanho) {
        double[][] aux = new double[tamanho][tamanho];

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                aux[i][j] = Double.parseDouble(ler("digite numero posicao" + i + "x" + j));

            }

        }
        Matrix mat = new Matrix(aux);
        return mat;
    }

  
    
    
    
    public static int valida(Matrix matriz) {
      
        int det = (int) Math.ceil(matriz.det());
        if (det != 0) {
            escrever("matriz inversivel \n determinante é " + det);

            return det;
        } else {
            escrever("matriz não inversivel \n determinante é " + det);
            return det ;
        }
    }

    public static int valida2(C cifra, int determinante, int bij_tamanho) {

        int mdc = cifra.mdc(determinante, bij_tamanho);
        if (mdc != 1) {
            escrever("o mdc " + mdc + " é  diferente de 1 não inversivel \n modulo n ");
            return mdc;
        }
        escrever("o mdc é " + mdc + " é inversivel \n modulo " + bij_tamanho);

        return mdc;
    }

  public static ArrayList<String> separarString(String texto, int n){
		texto = texto.replaceAll(" ", "");
		
		// se o tamanho da string nao for multiplo de n, entao faltara letras
		// neste caso complemeta o texto com "h", ate o tamanho ser um multiplo de n
		while(texto.length() % n != 0){
			texto += "h";
		}
		
		ArrayList<String> listaSubstrings = new ArrayList<>();
		
		// divide o texto em substrings de tamanho n e adiciona tudo na listaSubstrings
		int i;
		for (i = 0; i < texto.length()-1; i+=n) {
			listaSubstrings.add(texto.substring(i, i+n));
		}
		
		return listaSubstrings;
	}

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //1 2 3  4 5  6 11 9 8
        int op = 1000;
        int tamanho = 0;
        String alfabeto = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
        ArrayList<letra> bij = null;//bijeção letras e numeros 
        int determinante = 0, mdc , reciproco = 0;
        String palavra="";
        Matrix chave = null ;
        C cifra = new C();//
        Matrix cripitografar ;

        // iteração detalhes vide documentação 
        while (op == 1000) {

            op = Integer.parseInt(ler("digite uma opção ! \n"
                    + "1 para usar alfabeto 26 letras \n"
                    + "2 para editar alfabeto \n"
            ));

            if (op == 1) {
                bij = bijecao(alfabeto);//M1
                 escrever(" alfabeto de " + bij.size() + "  letras \n veja a bijeção");
                 cifra.todasletras(bij);
               //bloco de interação 
                tamanho = Integer.parseInt(ler("Digite o tamanho da matriz \n"
                        + "Dica!  ao inserir 3\n"
                        + "o tamanho escolhido sera 3X3 \n"));

                 chave = criarMatriz(tamanho);
               
                
                determinante = valida(chave);
                mdc = valida2(cifra, determinante, bij.size());
                if (determinante == 0 || mdc != 1) {
                    System.exit(0);
                    break;
                }
                   reciproco = cifra.recipro(bij.size(),determinante);
                   escrever("o determinante "+determinante +" tem reciproco "+ reciproco);
            }
            
            if (op == 2) {
                
                String alfabetoEdit= cifra.virgula( ler("digite seu alfabeto dica!:"
                        + "\n"
                        + "\n "
                        + "\nexemplo: #$%&*8abc"));  
                        
                bij = bijecao(alfabetoEdit);
                 escrever(" alfabeto de " + bij.size() + "  letras \n veja a bijeção");
                cifra.todasletras(bij);
                tamanho = Integer.parseInt(ler("Digite o tamanho da matriz \n"
                        + "Dica!  ao inserir 3\n"
                        + "o tamanho escolhido sera 3X3 \n"));

                 chave = criarMatriz(tamanho);
                
                escrever(" alfabeto de " + bij.size() + "  letras \n veja a bijeção");
                cifra.todasletras(bij);
                determinante = valida(chave);
                mdc = valida2(cifra, determinante, bij.size());
                if (determinante == 0 || mdc != 1) {
                    escrever("sua matriz não serve tente outra");
                    System.exit(0);
                    break;
                }
                   reciproco = cifra.recipro(bij.size(),determinante);
                   escrever("o determinante "+determinante +" tem reciproco "+ reciproco);
            }
            if (op > 2 || op < 1) {
                escrever("digite uma operação valida");
                op = 1000;
            }
        }
          palavra = ler("digite texto que vai ser cripitografado ");
          escrever("seu texto é "+ palavra);
          
      ArrayList<String> lista=separarString(palavra, tamanho);
          
      String cripitografada ="";
      String descripitografada="";
      for (String subString : lista) {
                 
          
          
          
          
          
          
          
       int[][] MatOri = cifra.submatrix(subString, tamanho, bij);
       cifra.imprimirCol(MatOri);
          
       cripitografar =  cifra.preparacripito(MatOri, tamanho);
         
         
       
       int MatCri[][] =cifra.cripitografar(cripitografar,chave,tamanho);
        
    cripitografada+=   cifra.imprimirColCrip(MatCri ,bij,reciproco,cifra);
          
          escrever("seu texto cripitografada : "+cripitografada);
  
       int[][] MatDes =   cifra.descripitogafar(chave, cripitografar,tamanho);
          
          cifra.imprimirColD(MatOri);
          
          descripitografada+=   cifra.imprimirColDesc(MatDes ,bij,reciproco,cifra);
          
           escrever("seu texto descripitografada : "+descripitografada);
           
        // chave.inverse().times(chave.times(cripitografar)).print(5, 0);
          
          
    
		}
           
     
       
     
         
         
         
         
    }

}
