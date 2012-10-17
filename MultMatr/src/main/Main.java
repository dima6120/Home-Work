/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Main
 * @author dima6120
 */

package main;

import java.util.Scanner;
import static multmatr.MultMatr.*;

public class Main {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        int [][]a = new int [400][200];
        int [][]b = new int [200][400];
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (j < 200) {
                    a[i][j] = 2;
                }
                if (i < 200) {
                    b[i][j] = 3;
                }
            }
        }
        int [][]res = mult(a, b);
        /*int [][]res = new int[400][400];
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                for (int k = 0; k < 400; k++) {
                    res[i][j] += a[i][k]*b[k][j];
                }
            }
        }*/
        /*for(int []m : res) {
            for(int i = 0; i < m.length; i++) {
                System.out.print(m[i]+" ");
            }
            System.out.println();
        }*/
    }
}
