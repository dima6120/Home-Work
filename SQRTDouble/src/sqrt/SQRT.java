/**
 * SQRT
 * @author dima6120
 */

package sqrt;

public class SQRT {
    //Newton's Iteration
    public static double sqrt(double d) {
        double x = d/2, px;
        do
        {
          px=x;
          x=(x+d/x)/2;
        } while (x!=px);
        return x;
    } 
}
