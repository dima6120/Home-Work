/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Print
 * @author dima6120
 */

package strcount;

//для удобства вывода текста
public class Print {
    public static void append(char x) {
        System.out.append(x);
    }
    public static void append(CharSequence x) {
        System.out.append(x);
    }
    public static void print(Object x, boolean nline) {
        if (nline) {
            System.out.println(x);
        } else {
           System.out.print(x); 
        }
    }
    public static void print(String x, boolean nline) {
        if (nline) {
            System.out.println(x);
        } else {
           System.out.print(x); 
        }
    }
    public static void print(Integer x, boolean nline) {
        if (nline) {
            System.out.println(x);
        } else {
           System.out.print(x); 
        }
    }
    public static void print(int x, boolean nline) {
        if (nline) {
            System.out.println(x);
        } else {
           System.out.print(x); 
        }
    }
    public static void print(float x, boolean nline) {
        if (nline) {
            System.out.println(x);
        } else {
           System.out.print(x); 
        }
    }
    public static void print() {
        System.out.println();
    }
    public static void print(double x, boolean nline) {
        if (nline) {
            System.out.println(x);
        } else {
           System.out.print(x); 
        }
    }
    public static void print(char x, boolean nline) {
        if (nline) {
            System.out.println(x);
        } else {
           System.out.print(x); 
        }
    }
    public static void print(char[] x, boolean nline) {
        if (nline) {
            System.out.println(x);
        } else {
           System.out.print(x); 
        }
    }
}
