/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год
 * SQRT
 * @author dima6120
 */

package sqrt;

public class SQRT {
    //Итерационный метод Ньютона
    public static double sqrt(double d) {
        double x = d/2, px;
        if (x == 0) {
            return 0;
        }
        if (x < 0) {
            return Double.NaN;
        }
        do
        {
          px=x;
          x=(x+d/x)/2;
        } while (Math.abs(x - px) > 0.0001);
        return x;
    } 
}
