/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * CountWays
 * @author dima6120
 */

package ways;

public class CountWays {
    private int[][] A;
    
    public int getNumbWays(int x, int y) throws ArgException {
        if (x < 0 || y < 0) {
            throw new ArgException();
        }
        if (x == 0 || y == 0) {
            return 1;
        }
        A = new int[x+1][y+1];
        for(int i = 0; i < x+1; i++) {
            A[i][0] = 1;
        }
        for(int i = 0; i < y+1; i++) {
            A[0][i] = 1;
        }
        for(int i = 1; i < x+1; i++) {
            for(int j = 1; j < y+1; j++) {
                A[i][j] = A[i-1][j] + A[i][j-1];
            }
        }
        return A[x][y];
    }
}
