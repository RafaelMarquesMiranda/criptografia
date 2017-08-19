/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cripito;
import java.util.ArrayList;
/**
 *
 * @author computador 3
 */
public class separador {
    




	public static void main(String[] args) {
		// testando
		
		ArrayList<String> lista = separarString("ola mundoss", 3);
		for (String subString : lista) {
                            System.out.println(subString.length());
			System.out.println(subString);
		}
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
}


