package unisort;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год
 * @author dima6120
 */
public class UniSortTest {
    
    public UniSortTest() {
    }

    @Test
    public void NullListSort() {
        System.out.println("NullListSort");
        Object[] m = null;
        Compare c = null;
        UniSort instance = new UniSort();
        instance.sort(m, c);
        assertNull(m);
    }
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
    @Test
    public void ListSort() {
        System.out.println("ListSort");
        Object[] m = {2,4,1,9,4,2,5,0};
        Object[] sm = {0,1,2,2,4,4,5,9};
        Compare c = new MyCompare();
        UniSort instance = new UniSort();
        instance.sort(m, c);
        assertArrayEquals(sm, m);
    }
    @Test
    public void ListSort1() {
        System.out.println("ListSort1");
        Object[] m = {2};
        Object[] sm = {2};
        Compare c = new MyCompare();
        UniSort instance = new UniSort();
        instance.sort(m, c);
        assertArrayEquals(sm, m);
    }
}
