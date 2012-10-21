/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * MultMatr
 * @author dima6120
 */

package multmatr;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MultThread implements Callable<int[]> {
    private int []a;
    private int [][]b;
    
    public MultThread(int []a, int [][]b) {
        this.a = a; 
        this.b = b;
    }
    @Override
    public int[] call() {
        int []res = new int[b[0].length];
        for(int j = 0; j < b[0].length; j++) {
            for (int i = 0; i < a.length; i++) {
                res[j] += a[i]*b[i][j];
            }
        }
        return res;
    }
    
}
public class MultMatr {
    private boolean checkmatr(int [][]a, int [][]b) {
        if (a == null || b == null || a.length == 0 || b.length == 0) {
            return false;
        }
        int len = a[0].length;
        for(int i = 1; i < a.length; i++) {
            if (len != a[i].length) {
                return false;
            }
        }
        len = b[0].length;
        for(int i = 1; i < b.length; i++) {
            if (len != b[i].length) {
                return false;
            }
        }
        if (a[0].length != b.length) {
            return false;
        }
        return true;
    }
    public int [][] mult(int [][]a, int [][]b) {
        //проверка корректности матриц
        if (!checkmatr(a,b)) {
            throw new IllegalArgumentException();
        }
        //
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<int[]>> res = new ArrayList<>();
        int k = -1; int [][] r = new int[a.length][b[0].length];
        for(int i = 0; i < a.length; i++) {
            res.add(exec.submit(new MultThread(a[i], b)));
        }
        
        for(Future<int[]> f : res) {
            try {
                k++;
                r[k] = f.get();
            } catch (InterruptedException | ExecutionException e) {
                
            } finally {
                exec.shutdown();
            }
        }
        
        return r;
    }
}
