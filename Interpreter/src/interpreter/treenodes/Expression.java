/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Expression
 * @author dima6120
 */

package interpreter.treenodes;

public abstract class Expression extends Node {
    protected ExprType type;
    
    public ExprType getType() {
        return type;
    }
}
