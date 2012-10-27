/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * FUNDEF
 * @author dima6120
 */

package interpreter.syntax;


public class FunDef extends Expression {
    private String id;
    private Expression body;
    
    public FunDef(String id, Expression body) {
        this.id = id;
        this.body = body;
        type = ExprType.FUNDEF;
    }
    
    public String getId() {
        return id;
    }
    
    public Expression getBody() {
        return body;
    }
    
    public void setBody(Expression b) {
        body = b;
    }
}
