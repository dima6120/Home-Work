/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год
 * UniSort
 * @author Xiao-Mei
 */

package unisort;

public class UniSort {
    private Compare c;
    
    private void swap(Object []m, int a, int b)
    {
        Object c = m[a];
        m[a] = m[b];
        m[b] = c;
    }
    private void quicksort(Object []m, int min, int max)
    {
        Object x = m[min + (max - min)/2];
        int l = min;
        int r = max;

        do {
            while (c.compare(m[l], x) == CResult.Ls) {
               l++;
            }
            while (c.compare(m[r], x) == CResult.Lg) {
               r--;
            }
            if (l<r) {
                swap(m, l, r);
            }

            if (l <= r) { 
               l++;
               r--;
            }                     
        }           
        while (l < r);

        if (r > min) {
            quicksort(m, min, r);
        }
        if (l < max) {        
            quicksort(m,l,max);                             
        }
    }
    public void sort(Object []m, Compare c) {
        if (c != null && m != null) {
            this.c = c;
            quicksort(m, 0, m.length - 1);
        }
    }
}
