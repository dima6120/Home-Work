/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * NUMBER
 * @author dima6120
 */

package interpreter.syntax;


public class Number extends Expression {
    private int val;
    
    public Number(int val) {
        this.val = val;
        type = ExprType.NUMBER;
    }
    
    public int getVal() {
        return val;
    }
}
