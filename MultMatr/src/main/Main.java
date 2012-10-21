/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Main
 * @author dima6120
 */

package main;

import multmatr.MultMatr;

public class Main {
    public static void main(String[] args) {
        MultMatr mm = new MultMatr();
        int [][]a = {{1,2,3},
                     {4,5,6},
                     {7,8,9}
                    };
        int [][]b = {{1,2},
                     {4,5},
                     {7,8}
                    };
        /*for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                if (j < 200) {
                    a[i][j] = 2;
                }
                if (i < 200) {
                    b[i][j] = 3;
                }
            }
        }*/
        int [][]res = mm.mult(a, b);
        /*int [][]res = new int[400][400];
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 400; j++) {
                for (int k = 0; k < 400; k++) {
                    res[i][j] += a[i][k]*b[k][j];
                }
            }
        }*/
        for(int []m : res) {
            for(int i = 0; i < m.length; i++) {
                System.out.print(m[i]+" ");
            }
            System.out.println();
        }
    }
}
