/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * FUNCALL
 * @author dima6120
 */

package interpreter.treenodes;


public class FunCall extends Expression {
    private Expression fun;
    private Expression arg;
    
    public FunCall(Expression fun, Expression arg) {
        this.arg = arg;
        this.fun = fun;
        type = ExprType.FUNCALL;
    }
    
    public Expression getFun() {
        return fun;
    }
    
    public Expression getArg() {
        return arg;
    }
    
    public void setFun(Expression f) {
        fun = f;
    }
    
    public void setArg(Expression a) {
        arg = a;
    }

    @Override
    public Object deepcopy() {
        return new FunCall((Expression)fun.deepcopy(),(Expression)arg.deepcopy());
    }
    
    @Override
    public String toString() {
        return "call " + fun.toString() + " (" + arg.toString() + ")";
    }
}
