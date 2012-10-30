/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * FUNDEF
 * @author dima6120
 */

package interpreter.treenodes;


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

    @Override
    public Object deepcopy() {
        return new FunDef(id,(Expression)body.deepcopy());
    }
    
    @Override
    public String toString() {
        return "fun " + id + " -> " + body.toString(); 
    }
}
