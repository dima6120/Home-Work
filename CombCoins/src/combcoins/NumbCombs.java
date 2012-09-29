/**
 * NumbCombs
 * @author dima6120
 */

package combcoins;

public class NumbCombs {
    private int []c;
    private int len;
    
    public NumbCombs(int []c) {
        this.c = c.clone();
        len = c.length;
    }
    public int getNumbs(int n) {
        return numbcombs(n, 0);
    }
    public int numbcombs(int n, int head) {
        if (n == 0) {
            return 1;
        }
        if (head == len || n < 0) {
            return 0;
        }
        return numbcombs(n, head + 1) + numbcombs(n - c[head], head);
    }
}
