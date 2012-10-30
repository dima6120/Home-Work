/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * IDENTIFIER
 * @author dima6120
 */

package interpreter.treenodes;


public class Identifier extends Expression {
    private String name;
    
    public Identifier(String name) {
        this.name = name;
        type = ExprType.IDENTIFIER;
    }
    
    public String getName() {
        return name;
    } 

    @Override
    public Object deepcopy() {
        return new Identifier(name);
    }
    
    @Override
    public String toString() {
        return name;
    }
}
