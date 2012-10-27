/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Interpreter
 * @author dima6120
 */

package interpreter;

import interpreter.syntax.*;
import interpreter.syntax.Number;

public abstract class Interpreter {
    public Expression substitute(Expression expr, String id, Expression x) {
        switch (expr.getType()) {
            case BINOP: 
                BinOp op = (BinOp)expr;
                op.setLeft(substitute(op.getLeft(), id, x));        
                op.setRight(substitute(op.getRight(), id, x));
                return op;
            case IDENTIFIER: 
                Identifier i = (Identifier)expr;
                if (id.equals(i.getName())) {
                    return x;
                }    
                return i;
            case FUNDEF:
                FunDef f = (FunDef)expr;
                //а если id = f.id? чё делать?
                f.setBody(substitute(f.getBody(), id, x));
                return f;
            case FUNCALL: 
                FunCall c = (FunCall)expr;
                c.setFun(substitute(c.getFun(), id, x));
                c.setArg(substitute(c.getArg(), id, x));
                return c;
            case NUMBER: 
                return expr;
            case LET: 
                Let l = (Let)expr;
                l.setBound(substitute(l.getBound(), id, x));
                l.setExpr(substitute(l.getExpr(), id, x));
                return l;
            default: 
                //кидаем исключение
                return null; 
        }
    }
    
    abstract Expression eval(FunCall funcll);
    abstract Expression eval(Let let);
    
    private Expression eval(BinOp op) {
        Expression l = eval(op.getLeft());
        Expression r = eval(op.getRight());
       
        if (l.getType() != ExprType.NUMBER || r.getType() != ExprType.NUMBER) {
            //кидаем исключение
            return null;
        }
        
        Number ln = (Number)l;
        Number rn = (Number)r;
        
        switch (op.getOp()) {
            case ADD: return new Number(ln.getVal() + rn.getVal());
            case SUB: return new Number(ln.getVal() - rn.getVal());
            case MULT: return new Number(ln.getVal() * rn.getVal());
            case DIV: 
                if (rn.getVal() == 0) {
                    //кидаем исключение
                    return null;
                }
                return new Number(ln.getVal()/rn.getVal());
            default: 
                //кидаем исключение
                return null;
        }
    }
    
    private Expression eval(Identifier id) {
        //ошибка!!!
        return null;
    }
    private Expression eval(FunDef fundf) {
        return fundf;
    }
    private Expression eval(Number numb) {
        return numb;
    }
    public Expression eval(Expression expr) {
        switch (expr.getType()) {
            case BINOP: return eval((BinOp)expr);
            case IDENTIFIER: return eval((Identifier)expr);
            case FUNDEF: return eval((FunDef)expr);
            case FUNCALL: return eval((FunCall)expr);
            case NUMBER: return eval((Number)expr);
            case LET: return eval((Let)expr);
            default: 
                //кидаем исключение
                return null; 
        }
    }
}
