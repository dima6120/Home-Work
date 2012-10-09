/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * NumbCombs
 * @author dima6120
 */

package combcoins;

public class NumbCombs {
    private int []c; //достоинства монет
    private int len; //их кол-во (для удобства)
    
    public NumbCombs(int []c) throws ArgumentException {
        if (c == null) {
            System.out.println("пустой набор!");
            throw new ArgumentException(); // (2)
        }
        this.c = c.clone();
        len = c.length;
    }
    public int getNumbs(int n) throws ArgumentException {
        if (n < 0) {
            System.out.println("отрицательная сумма!");
            throw new ArgumentException(); // (1)
        }
        return n == 0 ? 0 : numbcombs(n, 0);
    }
    /* подсчёт с помощью рекурентного уравнения
     * q(n,k1..km) - количество вариантов размена суммы в n монетами достоинств
     * 
     * q(n,k1..km) = q(n,k2..km) + q(n-k1,k1..km);
     * q(0,k1..km) = 1
     * на Хаскеле
     * q 0 _ = 1; 
     * q n _ | n < 0 = 0; <- в этом случаей здесь (1) ошибка
     * q n [] = 0; <- и здесь (2) тоже ошибка 
     * q n ks = q n (tail ks) + q (n - (head ks)) ks
     * собственно ниже, токо на Java
     */
    private int numbcombs(int n, int head) {
        if (n == 0) {
            return 1;
        }
        if (head == len || n < 0) {
            return 0;
        }
        return numbcombs(n, head + 1) + numbcombs(n - c[head], head);
    }
}
