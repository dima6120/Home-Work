/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * NUMBER
 * @author dima6120
 */

package interpreter.treenodes;


public class Number extends Expression {
    private int val;
    
    public Number(int val) {
        this.val = val;
        type = ExprType.NUMBER;
    }
    
    public int getVal() {
        return val;
    }

    @Override
    public Object deepcopy() {
        return new Number(val);
    }
    
    @Override
    public String toString() {
        return Integer.toString(val);
    }
}
