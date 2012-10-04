/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год
 * Main
 * @author Xiao-Mei
 */

package main;

import unisort.*;


class MyCompare implements Compare {
    @Override
    public CResult compare(Object a, Object b) {
        if ((Integer)a > (Integer) b) {
            return CResult.Lg;
        } else {
            if ((Integer)a < (Integer) b) {
                return CResult.Ls;
            }
        }
        return CResult.Eq;
    }
}

public class Main {
    public static void main(String[] args) {
        Integer []m = {2,4,1,9,4,2,5,0};
        UniSort us = new UniSort();
        us.sort(m, new MyCompare());
        for(int i = 0; i < 8; i++) {
            System.out.print(m[i] + " ");
        }
    }
}
