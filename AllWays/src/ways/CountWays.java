/**
 * CountWays
 * @author dima6120
 */

package ways;

public class CountWays {
    private int fx;
    private int fy;
    private int numb;
    
    public int getNumbWays(int x, int y) throws ArgumentException {
        if (x < 0 || y < 0) {
            throw new ArgumentException("отрицательные числа!");
        }
        fx = x; fy = y; numb = 0;
        count(0, 0);
        return numb;
    }
    //да да да...я знаю про биномиальные коэфиценты и треугольник Паскаля...
    //но так же не интересно :)
    void count(int x, int y) {
        if (x == fx || y == fy) {
            numb++;
            return;
        }
        
        if (x != fx) {
            count(x+1, y);
        } 
        
        if (y != fy) {
            count(x, y+1);
        } 
    }
}
