/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cripito;

import Jama.Matrix;
import com.sun.management.jmx.Introspector;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author computador 3
 */
public class C {

    public void imprimir(int matriz[][]) {

        for (int i = 0; i < matriz.length; i++) {
            System.out.println();
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + "  ");

            }
        }

        System.out.println();

    }

    public void imprimirCol(int matriz[][]) {
        String n = "";
        for (int i = 0; i < matriz.length; i++) {
            System.out.println();

            n += "\n | " + matriz[i][0] + " | ";

        }

        JOptionPane.showMessageDialog(null, "sub matriz " + n);

    }
    
    public void imprimirColD(int matriz[][]) {
        String n = "";
        for (int i = 0; i < matriz.length; i++) {
            System.out.println();

            n += "\n | " + matriz[i][0] + " | ";

        }

        JOptionPane.showMessageDialog(null, "sub matriz descripitografada " + n);

    }
    
    public String imprimirColCrip(int matriz[][], ArrayList<letra> l, int reciproco, C c) {
        String n = "", sub = "";

        for (int i = 0; i < matriz.length; i++) {
            System.out.println();
            if(matriz[i][0]>l.size()-1){
            n += "\n | " + matriz[i][0] + " | " + "  | " + matriz[i][0] * reciproco % l.size() + " | ";
            sub += c.concatena(matriz[i][0] * reciproco % l.size(), l); 
            }else{
            n += "\n | " + matriz[i][0] + " | " + "  | " + matriz[i][0] + " | ";
            sub += c.concatena(matriz[i][0] , l); 
            }

        }

        JOptionPane.showMessageDialog(null, "cripada " + n);
        return sub;
    }

    public String imprimirColDesc(int matriz[][], ArrayList<letra> l, int reciproco, C c) {
        String n = "", sub = "";

        for (int i = 0; i < matriz.length; i++) {
           
          
            sub += c.concatena(matriz[i][0], l);

        }

        return sub;
    }

    public void todasletras(ArrayList<letra> alfabeto) {
        String n = "";
        for (int i = 0; i < alfabeto.size(); i++) {
            n += "\n" + alfabeto.get(i).alfa + "-" + alfabeto.get(i).beta;

        }
        JOptionPane.showMessageDialog(null, n);
    }

  

    public Matrix descripitografa(Matrix cripito, Matrix messacri) {

        Matrix inversa = cripito.inverse();
        Matrix descripitografada = inversa.times(messacri);
        return descripitografada;

    }

    public int[][] submatrix(String palavra, int tamanho, ArrayList<letra> l) {
        palavra = virgula(palavra);
        String[] letras = palavra.split(",");
        int[][] matriz = new int[tamanho][1];

        for (int j = 0; j < letras.length; j++) {
            for (int i = 0; i < l.size(); i++) {
                if (l.get(i).alfa.equals(letras[j])) {
                    matriz[j][0] = l.get(i).beta;

                }
            }
        }

        return matriz;
    }

    public Matrix preparacripito(int[][] matriz, int tamanho) {
        double[][] aux = new double[tamanho][1];

        for (int i = 0; i < tamanho; i++) {
            aux[i][0] = matriz[i][0];

        }
        Matrix x = new Matrix(aux);

        return x;
        // ver isso  return Matrix.identity(5, 0);
    }

    public int mdc(int dividendo, int divisor) {

        if ((dividendo % divisor == 0)) {
            return divisor;
        } else {
            return mdc(divisor, (dividendo % divisor));
        }
    }

    public int recipro(int n, int det) {
        int r = 0;

        for (int i = 1; i < n; i++) {
            if (i * det % 26 == 1) {
                r = i;
            }
        }

        return r;
    }

    public int[][] modularizar(int col[][], int n) {

        int[][] novo = new int[n][1];

        for (int j = 0; j < col.length; j++) {
            novo[j][0] = col[j][0] % 26;

        }

        return novo;
    }

    public int deter(int[][] matriz) {
        int det;
        if (matriz.length == 2) {
            det = (matriz[0][0] * matriz[1][1]) - (matriz[1][0] * matriz[0][1]);
            return det;
        }
        int suma = 0;
        for (int i = 0; i < matriz.length; i++) {
            int[][] nxm = new int[matriz.length - 1][matriz.length - 1];
            for (int j = 0; j < matriz.length; j++) {
                if (j != i) {
                    for (int k = 1; k < matriz.length; k++) {
                        int indice = -1;
                        if (j < i) {
                            indice = j;
                        } else if (j > i) {
                            indice = j - 1;
                        }
                        nxm[indice][k - 1] = matriz[j][k];
                    }
                }
            }
            if (i % 2 == 0) {
                suma += matriz[i][0] * deter(nxm);
            } else {
                suma -= matriz[i][0] * deter(nxm);
            }
        }
        return suma;
    }

    public int[][] inversa(int[][] chave, int n) {
        double[][] chaveD = new double[chave.length][chave.length];
        for (int i = 0; i < chave.length; i++) {
            for (int j = 0; j < chave.length; j++) {
                chaveD[i][j] = (double) chave[i][j];

            }

        }

        Matrix A = new Matrix(chaveD);

        Matrix B = A.inverse();

        B.print(5, 16);

        int[][] D = new int[chave.length][chave.length];

        for (int i = 0; i < chave.length; i++) {
            for (int j = 0; j < chave.length; j++) {

                D[i][j] = (int) B.get(i, j) % n;

            }

        }
        return D;

    }

    public String concatena(int posi, ArrayList<letra> l) {
        String x = "";
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).beta == posi) {
                x = l.get(i).alfa;

            }
        }
        return x;
    }

    public String virgula(String texto) {
        texto = texto.replaceAll(" ", "");
        StringBuilder stringBuilder = new StringBuilder(texto);

        String x1 = "";
        for (int i = texto.length(), j = 1; i > 1; i--, j++) {
            x1 += stringBuilder.insert(texto.length() - j, ',');

        }
        String virgulado = stringBuilder.toString();
        return virgulado;
    }

    public int[][] cripitografar(Matrix cripitografar, Matrix chave, int n) {

        int[][] mat = new int[n][1];

        Matrix cripitografada = chave.times(cripitografar);
        double[][] matd = cripitografada.getArray();

        for (int i = 0; i < mat.length; i++) {
            mat[i][0] = (int) matd[i][0];

        }

        return mat;

    }

    public int[][] descripitogafar(Matrix x, Matrix y, int n) {
        int[][] mat = new int[n][1];

        Matrix descripitografada = x.inverse().times(x.times(y));
        double[][] matd = descripitografada.getArray();
        
        for (int i = 0; i < mat.length; i++) {
            if( n > 2){
             mat[i][0] = (int)Math.ceil(matd[i][0]);
            }else{
            mat[i][0] = (int)(matd[i][0]);}

        }

        return mat;

    }
}
