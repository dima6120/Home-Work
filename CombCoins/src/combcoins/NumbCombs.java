/**
 * NumbCombs
 * @author dima6120
 */

package combcoins;

public class NumbCombs {
    private int []c;
    private int len;
    
    public NumbCombs(int []c) throws ArgumentException {
        if (c == null) {
            throw new ArgumentException("wrong set");
        }
        this.c = c.clone();
        len = c.length;
    }
    public int getNumbs(int n) throws ArgumentException {
        if (n < 0) {
            throw new ArgumentException("negate value");
        }
        return n == 0 ? 0 : numbcombs(n, 0);
    }
    int numbcombs(int n, int head) {
        if (n == 0) {
            return 1;
        }
        if (head == len || n < 0) {
            return 0;
        }
        return numbcombs(n, head + 1) + numbcombs(n - c[head], head);
    }
}
